package editor;

import editor.business.controller.ActionListenerController;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {

    public MenuBar(ActionListenerController controller) {
        add(new FileMenu(controller));
        add(new SearchMenu(controller));
    }
}
