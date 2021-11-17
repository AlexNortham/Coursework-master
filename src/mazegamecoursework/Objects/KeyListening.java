package mazegamecoursework.Objects;
import java.awt.event.*;
public class KeyListening implements KeyListener {

    private boolean help;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if(key == KeyEvent.VK_R){
            help = true;
        }
        if (key == keyEvent.VK_UP){
            up = true;
        }
        if (key == keyEvent.VK_RIGHT){
            right =true;
        }
        if(key == keyEvent.VK_DOWN){
            down = true;
        }
        if(key == keyEvent.VK_LEFT){
            left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }


    public boolean getHelp(){
        return help;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }
}
