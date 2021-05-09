package editor;

import editor.business.controller.ActionListenerController;
import editor.business.matcher.TextMatcher;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class TextEditor extends JFrame {

    private final TextPane textPane;
    private final MenuPanel menuPanel;
    private final FileChooserDialog fileChooser;

    public TextEditor() {

        setTitle("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ActionListenerController controller = new ActionListenerController(this);
        textPane = new TextPane();
        menuPanel = new MenuPanel(controller);
        fileChooser = new FileChooserDialog();

        setJMenuBar(new MenuBar(controller));
        add(menuPanel, BorderLayout.NORTH);
        add(textPane, BorderLayout.CENTER);
        add(fileChooser, BorderLayout.SOUTH);

        setVisible(true);
    }

    public TextPane getTextPane() {
        return textPane;
    }

    public void openFile() {
        fileChooser.loadFile(textPane);
    }

    public void saveFile() {
        fileChooser.saveFile(textPane);
    }

    public boolean isRegexp() {
        return menuPanel.isRegexp();
    }

    public void setUseRegExp() {
        menuPanel.setRegexp();
    }

    public String getPattern() {
        return menuPanel.getPattern();
    }

    public void setTextMather(TextMatcher matcher) {
        menuPanel.setTextMatcher(matcher);
    }

    public TextMatcher getTextMatcher() {
        return menuPanel.getTextMatcher();
    }
}
