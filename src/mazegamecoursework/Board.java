
package mazegamecoursework;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;


public class Board extends JFrame {
    private ArrayList<JLabel> grid = new ArrayList<JLabel>();
    JLabel tile = new JLabel();

    JPanel panel = new JPanel();
    
    public void setupBoard(){
        tile.setLocation(0, 0);
        for (int y = 1;y <= 305; y = y + 16){
            for(int x = 1; x <= 465; x = x + 16){
                JLabel temp = tile;
                temp.setBackground(Color.yellow);
                temp.setLocation(x,y);
                temp.setVisible(true);
                grid.add(temp);
            }
        }
        
        
    }
    public void displayBoard(){
            }
    
    Board(){
        this.setSize(480, 320);
        setupBoard();
        for(int i = 0; i < 600; i++){
            this.add(grid.get(i));
        }
        this.repaint();
        this.setVisible(true);
    }

}
