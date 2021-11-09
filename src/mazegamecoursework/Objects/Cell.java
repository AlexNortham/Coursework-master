/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegamecoursework.Objects;


public class Cell {
    private boolean visited = false;
    private boolean pathup;
    private boolean pathleft;
    private boolean pathdown;
    private boolean pathright;
    private boolean searched;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isPathup() {
        return pathup;
    }

    public void setPathup(boolean pathup) {
        this.pathup = pathup;
    }

    public boolean isPathleft() {
        return pathleft;
    }

    public void setPathleft(boolean pathleft) {
        this.pathleft = pathleft;
    }

    public boolean isPathdown() {
        return pathdown;
    }

    public void setPathdown(boolean pathdown) {
        this.pathdown = pathdown;
    }

    public boolean isPathright() {
        return pathright;
    }

    public void setPathright(boolean pathright) {
        this.pathright = pathright;
    }

    public boolean isSearched() {
        return searched;
    }

    public void setSearched(boolean searched) {
        this.searched = searched;
    }
}
