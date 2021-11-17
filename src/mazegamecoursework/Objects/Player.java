
package mazegamecoursework.Objects;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    private ImageIcon icon;
    private int x = 0;
    private int y = 0;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void changeX(int change){
        this.x = this.x + change;
    }
    public void changeY(int change){
        this.y = this.y + change;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
