
package mazegamecoursework.Objects;

import java.awt.Component;
import javax.accessibility.AccessibleContext;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;

public class Player extends JLabel {
    private ImageIcon downFacingStill;
    private ImageIcon downFacingMoving;
    private ImageIcon upFacingStill;
    private ImageIcon upFacingMoving;
    private ImageIcon leftFacingStill;
    private ImageIcon leftFacingMoving;
    private ImageIcon rightFacingStill;
    private ImageIcon rightFacingMoving;

    public ImageIcon getDownFacingStill() {
        return downFacingStill;
    }

    public void setDownFacingStill(ImageIcon downFacingStill) {
        this.downFacingStill = downFacingStill;
    }

    public ImageIcon getDownFacingMoving() {
        return downFacingMoving;
    }

    public void setDownFacingMoving(ImageIcon downFacingMoving) {
        this.downFacingMoving = downFacingMoving;
    }

    public ImageIcon getUpFacingStill() {
        return upFacingStill;
    }

    public void setUpFacingStill(ImageIcon upFacingStill) {
        this.upFacingStill = upFacingStill;
    }

    public ImageIcon getUpFacingMoving() {
        return upFacingMoving;
    }

    public void setUpFacingMoving(ImageIcon upFacingMoving) {
        this.upFacingMoving = upFacingMoving;
    }

    public ImageIcon getLeftFacingStill() {
        return leftFacingStill;
    }

    public void setLeftFacingStill(ImageIcon leftFacingStill) {
        this.leftFacingStill = leftFacingStill;
    }

    public ImageIcon getLeftFacingMoving() {
        return leftFacingMoving;
    }

    public void setLeftFacingMoving(ImageIcon leftFacingMoving) {
        this.leftFacingMoving = leftFacingMoving;
    }

    public ImageIcon getRightFacingStill() {
        return rightFacingStill;
    }

    public void setRightFacingStill(ImageIcon rightFacingStill) {
        this.rightFacingStill = rightFacingStill;
    }

    public ImageIcon getRightFacingMoving() {
        return rightFacingMoving;
    }

    public void setRightFacingMoving(ImageIcon rightFacingMoving) {
        this.rightFacingMoving = rightFacingMoving;
    }

    public Component getLabelFor() {
        return labelFor;
    }

    public void setLabelFor(Component labelFor) {
        this.labelFor = labelFor;
    }

    public ComponentUI getUi() {
        return ui;
    }

    public void setUi(ComponentUI ui) {
        this.ui = ui;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public AccessibleContext getAccessibleContext() {
        return accessibleContext;
    }

    public void setAccessibleContext(AccessibleContext accessibleContext) {
        this.accessibleContext = accessibleContext;
    }

    public Player(String string, Icon icon, int i) {
        super(string, icon, i);
    }

    public Player(String string, int i) {
        super(string, i);
    }

    public Player(String string) {
        super(string);
    }

    public Player(Icon icon, int i) {
        super(icon, i);
    }

    public Player(Icon icon) {
        super(icon);
    }

    public Player() {
    }

    
}
