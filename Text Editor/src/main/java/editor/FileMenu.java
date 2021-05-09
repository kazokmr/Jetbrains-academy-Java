package editor;

import editor.business.controller.ActionListenerController;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu {

    private final ActionListenerController controller;

    public FileMenu(ActionListenerController controller) {
        this.controller = controller;
        setText("File");
        setName("MenuFile");

        addOpenMenu();
        addSaveFile();
        addSeparator();
        addClose(controller);
    }

    private void addClose(ActionListenerController controller) {
        JMenuItem exitFrame = new JMenuItem("Exit");
        exitFrame.setName("MenuExit");
        exitFrame.addActionListener(event -> controller.closeFrame());
        add(exitFrame);
    }

    private void addSaveFile() {
        JMenuItem saveFile = new JMenuItem("Save");
        saveFile.setName("MenuSave");
        saveFile.addActionListener(event -> controller.saveFile());
        add(saveFile);
    }

    private void addOpenMenu() {
        JMenuItem openFile = new JMenuItem("Open");
        openFile.setName("MenuOpen");
        openFile.addActionListener(event -> controller.openFile());
        add(openFile);
    }
}
