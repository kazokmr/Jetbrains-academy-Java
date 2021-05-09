package editor;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ActionButton extends JButton {

    public ActionButton(String name, String iconPath) {
        setName(name);
        setIcon(new ImageIcon(iconPath));
    }
}
