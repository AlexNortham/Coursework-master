package mazegamecoursework.Objects;

import javax.swing.Icon;
import javax.swing.JLabel;

public class PathedJLabel extends JLabel {

    private boolean path;

    public boolean getPath() {
        return path;
    }

    public void setPath(boolean path) {
        this.path = path;
    }

    public PathedJLabel(boolean path, String string, Icon icon, int i) {
        super(string, icon, i);
        this.path = path;
    }

    public PathedJLabel(boolean path, String string, int i) {
        super(string, i);
        this.path = path;
    }

    public PathedJLabel(boolean path, String string) {
        super(string);
        this.path = path;
    }

    public PathedJLabel(boolean path, Icon icon, int i) {
        super(icon, i);
        this.path = path;
    }

    public PathedJLabel(boolean path, Icon icon) {
        super(icon);
        this.path = path;
    }

    public PathedJLabel(boolean path) {
        this.path = path;
    }

    
}
