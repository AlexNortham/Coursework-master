package mazegamecoursework.Objects;

import java.util.*;

public class PathFinder {
    private Cell[][] board;
    private Coords start;
    private Coords end;

    public PathFinder(Cell[][] board, Coords start, Coords end){
        this.board = board;
        this.start = start;
        this.end = end;
    }

    public ArrayList<Coords> findPath(){
        ArrayList<Coords> stack = new ArrayList<Coords>();
        Coords next;
        String chosenDirection = null;
        int x = start.getX();
        int y = start.getY();
        int EndX = end.getX();
        int EndY = end.getY();
        boolean repeat = true;
        boolean progress = true;
        while(repeat) {
            progress = true;

            Cell temp = board[x][y];
            Coords current = new Coords(x, y);
            if(temp.isSearched()){
                int stackSize = stack.size();
                stack.remove(stackSize - 1);
                stackSize--;
                next = stack.get(stackSize - 1);
                stack.remove(stackSize - 1);
                board[next.getX()][next.getY()].setSearched(false);
                System.out.print(Integer.toString(next.getX()) + " ");
                System.out.println(Integer.toString(next.getY()));
                progress = false;
                x = next.getX();
                y = next.getY();
            }
            if(progress){
                if(current.equals(end)){
                    return stack;
                }
                stack.add(current);
                board[x][y].setSearched(true);
                boolean[] directionsBooleans = getPaths(temp, x, y);
                ArrayList<String> directions = new ArrayList<String>();
                if (directionsBooleans[0]) {
                    directions.add("up");
                }
                if (directionsBooleans[1]) {
                    directions.add("right");
                }
                if (directionsBooleans[2]) {
                    directions.add("down");
                }
                if (directionsBooleans[3]) {
                    directions.add("left");
                }
                int size = directions.size();

                try {
                    Random random = new Random();
                    int randomInt = random.nextInt(size);
                    chosenDirection = directions.get(randomInt);
                }catch(Exception e){
                    chosenDirection = "";
                }
                try {
                    if (chosenDirection.equals("up")) {
                        if (board[x][y - 1].isSearched() == false) {

                            y--;
                        }
                    }
                    if (chosenDirection.equals("right")) {
                        if (board[x + 1][y].isSearched() == false) {

                            x++;
                        }
                    }
                    if (chosenDirection.equals("down")) {
                        if (board[x][y + 1].isSearched() == false) {

                            y++;
                        }
                    }
                    if (chosenDirection.equals("left")) {
                        if (board[x - 1][y].isSearched() == false) {

                            x--;
                        }
                    }

                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }

        }
        return stack;
    }

    public boolean[] getPaths(Cell cell, int x, int y){
        boolean[] directions = {false,false,false,false};
        if(cell.isPathup()){
            try {
                Cell temp = board[x][y - 1];
                if (temp.isSearched() == false) {
                    directions[0] = true;
                }
            } catch (Exception e) {

            }

        }
        if(cell.isPathright()){
            try {
                Cell temp = board[x + 1][y];
                if (temp.isSearched() == false) {
                    directions[1] = true;
                }
            } catch (Exception e) {

            }
        }
        if(cell.isPathdown()){
            try {
                Cell temp = board[x][y + 1];
                if (temp.isSearched() == false) {
                    directions[2] = true;
                }
            } catch (Exception e) {

            }
        }
        if(cell.isPathleft()){
            try {
                Cell temp = board[x - 1][y];
                if (temp.isSearched() == false) {
                    directions[3] = true;
                }
            } catch (Exception e) {

            }
        }
        return directions;
    }
}
