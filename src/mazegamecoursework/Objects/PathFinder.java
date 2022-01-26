package mazegamecoursework.Objects;

import java.util.*;

public class PathFinder {
    private final Cell[][] board;
    private final Coords start;
    private final Coords end;

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
            if(temp.isSearched()){ //This line checks whether the current coords have already been searched
                int stackSize = stack.size();
                stack.remove(stackSize - 1); //removes the current cell from the stack
                stackSize--;
                next = stack.get(stackSize - 1); //sets the next cell to the previous one visited
                stack.remove(stackSize - 1); //removes the next cell from the stack
                board[next.getX()][next.getY()].setSearched(false); //sets the next cell to not searched
                progress = false;
                x = next.getX();
                y = next.getY(); //sets the current coords as the next coords
            }
            if(progress){
                if(current.equals(end)){
                    return stack; //This checks whether the current coords are the end of the maze. If they are, they are returned.
                }
                stack.add(current);
                board[x][y].setSearched(true); //Sets the current coords to searched
                boolean[] directionsBooleans = getPaths(temp, x, y); //Gets a list of all the possible paths not yet searched
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
                    chosenDirection = directions.get(randomInt); //This chooses a random direction to search out of all the currently selected directions
                }catch(Exception e){
                    chosenDirection = "";
                }
                try { //This try catch statement moves the currently selected coords by 1 in the direction previously selected
                    if (chosenDirection.equals("up")) {
                        if (!board[x][y - 1].isSearched()) {

                            y--;
                        }
                    }
                    if (chosenDirection.equals("right")) {
                        if (!board[x + 1][y].isSearched()) {

                            x++;
                        }
                    }
                    if (chosenDirection.equals("down")) {
                        if (!board[x][y + 1].isSearched()) {

                            y++;
                        }
                    }
                    if (chosenDirection.equals("left")) {
                        if (!board[x - 1][y].isSearched()) {

                            x--;
                        }
                    }

                } catch (Exception e) {

                }
            }

        }
        return stack;
    }

    public boolean[] getPaths(Cell cell, int x, int y){
        boolean[] directions = {false,false,false,false};
        if(cell.isPathup()){
            try { //This try catch statement checks all the cells immediately surrounding the currently selected one and changes the corresponding position in the directions array to true if said cell is traversable to and if it has not already been searched
                Cell temp = board[x][y - 1];
                if (!temp.isSearched()) {
                    directions[0] = true;
                }
            } catch (Exception ignored) {

            }

        }
        if(cell.isPathright()){
            try {
                Cell temp = board[x + 1][y];
                if (!temp.isSearched()) {
                    directions[1] = true;
                }
            } catch (Exception ignored) {

            }
        }
        if(cell.isPathdown()){
            try {
                Cell temp = board[x][y + 1];
                if (!temp.isSearched()) {
                    directions[2] = true;
                }
            } catch (Exception ignored) {

            }
        }
        if(cell.isPathleft()){
            try {
                Cell temp = board[x - 1][y];
                if (!temp.isSearched()) {
                    directions[3] = true;
                }
            } catch (Exception ignored) {

            }
        }
        return directions;
    }
}
