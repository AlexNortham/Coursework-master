package mazegamecoursework.Objects;

import javafx.application.Platform;
import javafx.stage.Stage;
import mazegamecoursework.GUIs.GameOverGUI;
import mazegamecoursework.SQLClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MazeGenerator {
    private final Cell[][] board = new Cell[30][30];
    private final ArrayList<Coords> stack = new ArrayList<Coords>();
    private Coords next = new Coords(0, 0);
    private final Random random = new Random();
    private final JFrame jframe = new JFrame();
    private final JLayeredPane layeredPane = new JLayeredPane();
    private final ArrayList<JLabel> grid = new ArrayList<JLabel>();
    private String direction = "";
    private Coords end;
    private Coords start;
    private Player player;
    private final JLabel playerLabel = new JLabel();
    private final JLabel timeLabel = new JLabel();
    private Timer timer;
    private int distance;
    private final MusicPlayer mp = new MusicPlayer();
    boolean createMaze;


    private final JButton solve = new JButton();


    private void fillCells() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                Cell temp = new Cell();
                if ((i == end.getX() && j == end.getY())) {
                    temp.setEnd(true);
                }
                if (i == start.getX() && j == start.getY()) {
                    temp.setStart(true);
                }
                board[i][j] = temp;
            }
        }
    }

    public void setUpBoard() {
        end = pickEnd();
        start = pickStart();
        setUpActionListener();
        setUpKeyListener();
        setUpTimer();
        fillCells();
        carveTo(0, 0);

        displayJLabels();
        setUpPlayer();
        startTimer();


    }

    public Coords pickEnd() {
        Random random = new Random();
        int x = random.nextInt(27) + 2;
        int y = random.nextInt(27) + 2;
        return new Coords(x, y);
    }

    public Coords pickStart() {
        Random random = new Random();
        int x = random.nextInt(27) + 2;
        int y = random.nextInt(27) + 2;
        return new Coords(x, y);
    }

    public void displayJLabels() {
        layeredPane.setSize(1920, 1080);
        layeredPane.setVisible(true);

        setUpPlayer();

        String path = System.getProperty("user.dir");
        try {
            jframe.setUndecorated(true);
        } catch (Exception e) {

        }
        jframe.setSize(1920, 1080);
        jframe.setVisible(true);
        jframe.getContentPane().setBackground(Color.BLACK);
        jframe.add(layeredPane);


        for (int j = 0; j < 30; j++) {
            for (int i = 0; i < 30; i++) {
                int x = (i + 1) * 32;
                int y = (j + 2) * 32;
                Cell temp = board[i][j];
                String directions = "";
                if (temp.isPathup()) {
                    directions = directions + "up";
                }
                if (temp.isPathright()) {
                    directions = directions + "right";
                }
                if (temp.isPathdown()) {
                    directions = directions + "down";
                }
                if (temp.isPathleft()) {
                    directions = directions + "left";
                }
                if (directions.equals("")) {
                    directions = "none";
                }
                ImageIcon icon;
                if (!temp.isOrange()) {
                    icon = new ImageIcon(path + "\\mazeImages\\" + directions + ".png");
                } else {
                    icon = new ImageIcon(path + "\\mazeImages\\" + "orange" + directions + ".png");
                }
                if (temp.isEnd() || temp.isStart()) {
                    icon = new ImageIcon(path + "\\mazeImages\\" + "red" + directions + ".png");
                }
                JLabel label = new JLabel(icon);
                label.setIcon(icon);
                label.setBounds(x, y, 32, 32);
                layeredPane.add(label, new Integer(0));
                System.out.print(directions + " ");

            }
            System.out.println("");
        }
        solve.setBounds(1856, 0, 32, 32);
        solve.setSize(64, 32);
        solve.setText("Solve");
        timeLabel.setBounds(1834, 32, 64, 32);
        timeLabel.setText(Double.toString(timer.getTime()));
        layeredPane.add(timeLabel);
        layeredPane.add(solve, new Integer(0));
        layeredPane.add(playerLabel, new Integer(2));
        layeredPane.repaint();
        jframe.repaint();
        mp.playSong();

    }

    public void setUpPlayer(){
        int x = ((start.getX()+1)*32)+8;
        int y = ((start.getY()+2)*32)+8;
        String path = System.getProperty("user.dir");
        ImageIcon icon = new ImageIcon(path + "\\mazeImages\\player.png");
        player = new Player();
        player.setIcon(icon);
        player.setX(x);
        player.setY(y);

        playerLabel.setIcon(player.getIcon());
        playerLabel.setBounds(player.getX(), player.getY(), 16, 16);



    }

    private void pathReplacement(ArrayList<Coords> stack) {
        String path = System.getProperty("user.dir");
        for (int j = 0; j < stack.size(); j++) {

                Coords c = stack.get(j);

                Cell temp = board[c.getX()][c.getY()];
                String directions = "";
                if (temp.isPathup()) {
                    directions = directions + "up";
                }
                if (temp.isPathright()) {
                    directions = directions + "right";
                }
                if (temp.isPathdown()) {
                    directions = directions + "down";
                }
                if (temp.isPathleft()) {
                    directions = directions + "left";
                }
                if (directions.equals("")) {
                    directions = "none";
                }
                ImageIcon icon = new ImageIcon(path + "\\mazeImages\\" + "orange" + directions + ".png");
                JLabel label = new JLabel(icon);
                label.setIcon(icon);
                int x = (c.getX() + 1) * 32;
                int y = (c.getY() + 2) * 32;
                label.setBounds(x, y, 32, 32);
                layeredPane.add(label, new Integer(1));

        }


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

        }
        try {
            Cell temp = board[x + 1][y];
            if (!temp.isVisited()) {
                neighbours[1] = true;
            }
        } catch (Exception ignored) {

        }
        try {
            Cell temp = board[x][y + 1];
            if (!temp.isVisited()) {
                neighbours[2] = true;
            }
        } catch (Exception ignored) {

        }
        try {
            Cell temp = board[x - 1][y];
            if (!temp.isVisited()) {
                neighbours[3] = true;
            }
        } catch (Exception ignored) {

        }

        return neighbours;
    }

    private void carveTo(int x, int y) {
        boolean repeat = true; //This boolean determines whether the loop will activate
        boolean progress; //This boolean determines whether the maze should continue carving a path on the current iteration
        while (repeat) {
            progress = true;
            if((new Coords(x,y).equals(end))){
                distance = stack.size();
            }
            if (board[x][y].isVisited()) { //This checks if the current cell has been visited
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

            if (progress) {
                board[x][y].setVisited(true);
                stack.add(new Coords(x, y));
                //These lines set the current cell as visited and add it to the stack

                boolean[] neighbours = getNeighbours(x, y); //This gets the unvisited neighbours of the current cell

                ArrayList<String> directions = new ArrayList<String>();
                if (neighbours[0]) {
                    directions.add("up");
                }
                if (neighbours[1]) {
                    directions.add("right");
                }
                if (neighbours[2]) {
                    directions.add("down");
                }
                if (neighbours[3]) {
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
                        if (!board[x][y - 1].isVisited()) {
                            clearWall("up", x, y);
                            clearWall("down", x, y - 1);
                            y--;
                        }
                    }
                    if (direction.equals("right")) {
                        if (!board[x + 1][y].isVisited()) {
                            clearWall("right", x, y);
                            clearWall("left", x + 1, y);
                            x++;
                        }
                    }
                    if (direction.equals("down")) {
                        if (!board[x][y + 1].isVisited()) {
                            clearWall("down", x, y);
                            clearWall("up", x, y + 1);
                            y++;
                        }
                    }
                    if (direction.equals("left")) {
                        if (!board[x - 1][y].isVisited()) {
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

    private void setUpActionListener() {
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PathFinder pathFinder = new PathFinder(board, start, end);
                ArrayList<Coords> stack = pathFinder.findPath();
                distance = stack.size();
                pathReplacement(stack);
            }
        });
    }

    private void setUpKeyListener(){
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                int key = keyEvent.getKeyCode();
                if(key == KeyEvent.VK_R){

                }
                if (key == keyEvent.VK_UP){
                    if(isLegal("up")) {
                        player.setY(player.getY() - 32);
                        playerLabel.setBounds(player.getX(), player.getY(), 16, 16);
                        jframe.repaint();
                        int x = ((player.getX()-8)/32)-1;
                        int y = ((player.getY()-8)/32)-2;
                        System.out.print(x);
                        System.out.print(y);
                        System.out.println();
                        System.out.println(end.toString());
                        Coords current = new Coords(x,y);
                        if(current.equals(end)){
                            try {
                                finishGame();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (key == keyEvent.VK_RIGHT){
                    if(isLegal("right")) {
                        player.setX(player.getX() + 32);
                        playerLabel.setBounds(player.getX(), player.getY(), 16, 16);
                        jframe.repaint();
                        int x = ((player.getX()-8)/32)-1;
                        int y = ((player.getY()-8)/32)-2;
                        System.out.print(x);
                        System.out.print(y);
                        System.out.println();
                        System.out.println(end.toString());
                        Coords current = new Coords(x,y);
                        if(current.equals(end)){
                            try {
                                finishGame();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if(key == keyEvent.VK_DOWN){
                    if(isLegal("down")) {
                        player.setY(player.getY() + 32);
                        playerLabel.setBounds(player.getX(), player.getY(), 16, 16);
                        jframe.repaint();
                        int x = ((player.getX()-8)/32)-1;
                        int y = ((player.getY()-8)/32)-2;
                        System.out.print(x);
                        System.out.print(y);
                        System.out.println();
                        System.out.println(end.toString());
                        Coords current = new Coords(x,y);
                        if(current.equals(end)){
                            try {
                                finishGame();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if(key == keyEvent.VK_LEFT){
                    if(isLegal("left")) {
                        player.setX(player.getX() - 32);
                        playerLabel.setBounds(player.getX(), player.getY(), 16, 16);
                        jframe.repaint();
                        int x = ((player.getX()-8)/32)-1;
                        int y = ((player.getY()-8)/32)-2;
                        System.out.print(x);
                        System.out.print(y);
                        System.out.println();
                        System.out.println(end.toString());
                        Coords current = new Coords(x,y);
                        if(current.equals(end)){
                            try {
                                finishGame();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        jframe.addKeyListener(keyListener);
        layeredPane.addKeyListener(keyListener);
    }

    private boolean isLegal(String direction){
        int x = (((player.getX()-8)/32)-1);
        int y = (((player.getY()-8)/32)-2);
        Cell temp = board[x][y];
        switch (direction){
            case ("up"):
                if(temp.isPathup()){
                    return true;
                }
                break;
            case ("right"):
                if(temp.isPathright()){
                    return true;
                }
                break;
            case ("down"):
                if(temp.isPathdown()){
                    return true;
                }
                break;
            case ("left"):
                if (temp.isPathleft()){
                    return true;
                }
                break;
        }
        return false;

    }

    private void finishGame() throws Exception {

        for(KeyListener k : jframe.getKeyListeners()){
            jframe.removeKeyListener(k);
        }
        for(KeyListener k : layeredPane.getKeyListeners()){
            layeredPane.removeKeyListener(k);
        }
        timer.setRunnable(false);
        double finalTime  = timer.getTime();
        ScoreCalculator sc = new ScoreCalculator(finalTime, distance);
        double score = sc.CalculateScore();
        score = Math.round(score*10000)/100;
        Settings.setScore(score);
        String email = Settings.getEmail();
        String name = Settings.getName();
        String command = "INSERT INTO Scores (Score, EmailAddress, UserName) VALUES (" + score + ", '" + email+ "', '"+name+"')";
        SQLClass.insert(SQLClass.getConnection(), command);

        Thread.sleep(1000);
        Platform.runLater(() -> {
            GameOverGUI gameOverGUI = new GameOverGUI();
            Stage stage = new Stage();
            try {
                gameOverGUI.start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jframe.dispose();








    }

    private void setUpTimer(){
        timer = new Timer(timeLabel);
    }

    private void startTimer(){
        timer.setRunnable(true);
        timer.start();
    }



}
