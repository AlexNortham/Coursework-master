package mazegamecoursework;

import java.util.ArrayList;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import mazegamecoursework.EmailValidator;
import mazegamecoursework.Objects.PathedJLabel;
import mazegamecoursework.Objects.Player;

public class MazeBoard {
    String email;
    String password;
    JFrame jframe = new JFrame();
    Player player = new Player();
    String dir = System.getProperty("user.dir");
    ImageIcon icon = new ImageIcon("blockTile.png");
    ArrayList<PathedJLabel> grid  = new ArrayList<PathedJLabel>();
    



    public void setUpBoard() {
       
        jframe.setUndecorated(true);
        jframe.setSize(1920, 1080);
        jframe.setVisible(true);
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 60; j++) {
                int x = j * 32;
                int y = i * 32;
                PathedJLabel temp = new PathedJLabel(false, icon);
                temp.setSize(32, 32);
                
                temp.setIcon(icon);

                temp.setBounds(x, y, 32, 32);
                grid.add(temp);
                
            }
        }
        jframe.repaint();

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    public void setUpPlayer(){
        
         player.setDownFacingStill(new ImageIcon(dir + "playerSpriteStillDown.png"));
         player.setIcon(player.getDownFacingStill());
         player.setLocation(0, 0);
         player.setVisible(true);
         jframe.add(player);
    }

    public MazeBoard(String emailx, String passwordx) {
        this.setEmail(emailx);
        this.setPassword(passwordx);
        setUpBoard();
        
        for (int i = 0; i < grid.size(); i++){
            jframe.add(grid.get(i));
        }
        setUpPlayer();
        jframe.repaint();
        jframe.setVisible(true);
        
        
    }
}
