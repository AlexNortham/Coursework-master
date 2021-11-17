package mazegamecoursework.Objects;
import javax.swing.*;
import java.util.ArrayList;
public class GameControls extends Thread implements Runnable{
    protected volatile boolean runnable = false;
    private JLabel player;

    public GameControls(JLabel player) {
        this.player = player;
    }

    public void setPlayer(JLabel player) {
        this.player = player;
    }

    public JLabel getPlayer() {
        return player;
    }

    @Override
    public void run() {
        synchronized (this){
            while(runnable){
                KeyListening kl = new KeyListening();
                if(kl.isUp()){

                }
            }
        }
    }
}
