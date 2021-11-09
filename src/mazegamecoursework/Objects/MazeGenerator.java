package mazegamecoursework.Objects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MazeGenerator {
    Cell[][] board = new Cell[30][30];
    ArrayList<Coords> stack = new ArrayList<Coords>();
    Coords next = new Coords(0, 0);
    Random random = new Random();
    JFrame jframe = new JFrame();
    ImageIcon blackicon = new ImageIcon("black.png");
    ImageIcon whiteicon = new ImageIcon("white.png");
    ArrayList<JLabel> grid = new ArrayList<JLabel>();
    String direction = "";



    private void fillCells(){
        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 30; j++){
                Cell temp = new Cell();
                board[i][j] = temp;
            }
        }
    }

    public void setUpBoard() {
        fillCells();
        carveTo(0,0);

        String path = System.getProperty("user.dir");
        jframe.setUndecorated(true);
        jframe.setSize(1920, 1080);
        jframe.setVisible(true);
        jframe.getContentPane().setBackground(Color.BLACK);


        for(int j = 0; j < 30; j++){
            for (int i = 0; i < 30; i++){
                int x = (i+1)*32;
                int y = (j+2)*32;
                Cell temp = board[i][j];
                String directions = "";
                if(temp.isPathup()){
                    directions = directions + "up";
                }
                if(temp.isPathright()){
                    directions = directions + "right";
                }
                if(temp.isPathdown()){
                    directions = directions + "down";
                }
                if (temp.isPathleft()){
                    directions = directions + "left";
                }
                if(directions.equals("")){
                    directions = "none";
                }
                ImageIcon icon = new ImageIcon(path+"\\mazeImages\\"+directions+".png");
                JLabel label = new JLabel(icon);
                label.setIcon(icon);
                label.setBounds(x,y,32,32);
                jframe.add(label);
                System.out.print(directions+" ");

            }
            System.out.println("");
        }

        jframe.repaint();

    }

    private void backtracker() {

    }

    public Cell getCell(int x, int y) {
        return board[x][y];
    }

    private void clearWall(String direction, int x, int y) {
        if (direction.equals("up")) {
            board[x][y].setPathup(true);
        }
        if (direction.equals("down")) {
            board[x][y].setPathdown(true);
        }
        if (direction.equals("left")) {
            board[x][y].setPathleft(true);
        }
        if (direction.equals("right")) {
            board[x][y].setPathright(true);
        }
    }

    private void setWall(String direction, int x, int y) {
        if (direction.equals("up")) {
            board[x][y].setPathup(false);
        }
        if (direction.equals("down")) {
            board[x][y].setPathdown(false);
        }
        if (direction.equals("left")) {
            board[x][y].setPathleft(false);
        }
        if (direction.equals("right")) {
            board[x][y].setPathright(false);
        }
    }

    private boolean[] getNeighbours(int x, int y) {
        boolean[] neighbours = new boolean[]{false, false, false, false};
        try {
            Cell temp = board[x][y - 1];
            if (temp.isVisited() == false) {
                neighbours[0] = true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        try {
            Cell temp = board[x + 1][y];
            if (temp.isVisited() == false) {
                neighbours[1] = true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
        try {
            Cell temp = board[x][y + 1];
            if (temp.isVisited() == false) {
                neighbours[2] = true;
            }
        } catch (Exception e) {

        }
        try {
            Cell temp = board[x - 1][y];
            if (temp.isVisited() == false) {
                neighbours[3] = true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return neighbours;
    }

    private void carveTo(int x, int y) {
        boolean repeat = true; //This boolean determines whether the loop will activate
        boolean progress; //This boolean determines whether the maze should continue carving a path on the current iteration
        while(repeat == true) {
            progress = true;
            if (board[x][y].isVisited() == true) { //This checks if the current cell has been visited
                int temp = stack.size();
                stack.remove(temp - 1);
                temp--;
                next = stack.get(temp - 1);
                //This removes the current cell from the stack and backtracks once

                if (stack.size() > 0) {
                    if (!(next.getX() == 0 && next.getY() == 0)) { //This checks whether the current cell is [0,0]
                        stack.remove(temp - 1);
                        board[next.getX()][next.getY()].setVisited(false);
                        System.out.print(Integer.toString(next.getX()) + " ");
                        System.out.println(Integer.toString(next.getY()));
                        progress = false;
                        x = next.getX();
                        y = next.getY();
                        //If the current cell is not [0,0], it is removed from the stack and set to unvisited, and progress is set to false, meaning no other code will be executed on this iteration
                    } else {
                        repeat = false;
                        progress = false;
                        //If the current cell is [0,0], then the maze has completely generated, so the booleans progress and repeat are set to false, meaning no more code will be executed on this iteration, and there will be no more subsequent iterations
                    }
                }


            }

            if(progress) {
                board[x][y].setVisited(true);
                stack.add(new Coords(x, y));
                //These lines set the current cell as visited and add it to the stack

                boolean[] neighbours = getNeighbours(x, y); //This gets the unvisited neighbours of the current cell

                ArrayList<String> directions = new ArrayList<String>();
                if (neighbours[0] == true) {
                    directions.add("up");
                }
                if (neighbours[1] == true) {
                    directions.add("right");
                }
                if (neighbours[2] == true) {
                    directions.add("down");
                }
                if (neighbours[3] == true) {
                    directions.add("left");
                }
                //These if statements create an ArrayList containing the String names of all the potential directions the maze coukd choose to take from the current cell

                int directionNumber = directions.size();
                try {
                    directionNumber = random.nextInt(directionNumber);
                    direction = directions.get(directionNumber);
                } catch (Exception e) {
                    String direction = "";
                }
                //This generates a random number corresponding to the size of the ArrayList, and uses that number as an index to pick a random direction. If the size of the ArrayList is 0, the direction is set to ""

                try {
                    if (direction.equals("up")) {
                        if (board[x][y - 1].isVisited() == false) {
                            clearWall("up", x, y);
                            clearWall("down", x, y - 1);
                            y--;
                        }
                    }
                    if (direction.equals("right")) {
                        if (board[x + 1][y].isVisited() == false) {
                            clearWall("right", x, y);
                            clearWall("left", x + 1, y);
                            x++;
                        }
                    }
                    if (direction.equals("down")) {
                        if (board[x][y + 1].isVisited() == false) {
                            clearWall("down", x, y);
                            clearWall("up", x, y + 1);
                            y++;
                        }
                    }
                    if (direction.equals("left")) {
                        if (board[x - 1][y].isVisited() == false) {
                            clearWall("left", x, y);
                            clearWall("right", x - 1, y);
                            x--;
                        }
                    }
                    //These if statements carve a path through the wall in the direction chosen. The function clearWall removes the wall in a specified direction, so it is used to clear a wall in the current cell in the chosen direction, and in the chosen neighbour in the inverse direction

                } catch (Exception e) {
                    //e.printStackTrace();
                }

            }
            System.out.println();
        }
    }
}
