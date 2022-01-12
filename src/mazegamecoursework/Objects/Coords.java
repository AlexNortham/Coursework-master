/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegamecoursework.Objects;




public class Coords {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public boolean equals(Coords coords) {
        int x = coords.getX();
        int y = coords.getY();
        if(this.x == x && this.y == y){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public String toString() {
        return "Coords{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
