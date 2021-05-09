package editor;

import editor.business.controller.ActionListenerController;
import editor.business.matcher.TextMatcher;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class MenuPanel extends JPanel {

    private final ActionListenerController controller;
    private final JTextField searchField = new JTextField();
    private final JCheckBox useRegexCheck = new JCheckBox();

    private TextMatcher textMatcher;

    public MenuPanel(ActionListenerController controller) {
        setLayout(new FlowLayout(FlowLayout.LEADING));
        this.controller = controller;
        addSaveButton();
        addOpenButton();
        addSearchField();
        addStartSearchButton();
        addPreviousSearchButton();
        addNextButton();
        addUseRegexCheck();
    }

    public String getPattern() {
        return searchField.getText();
    }

    public boolean isRegexp() {
        return useRegexCheck.isSelected();
    }

    public void setRegexp() {
        useRegexCheck.setSelected(true);
    }

    public TextMatcher getTextMatcher() {
        return textMatcher;
    }

    public void setTextMatcher(TextMatcher textMatcher) {
        this.textMatcher = textMatcher;
    }

    private void addSearchField() {
        searchField.setName("SearchField");
        searchField.setColumns(10);
        add(searchField);
    }

    private void addUseRegexCheck() {
        useRegexCheck.setText("Use regex");
        useRegexCheck.setName("UseRegExCheckbox");
        add(useRegexCheck);
    }

    private void addOpenButton() {
        JButton openButton = new ActionButton("OpenButton", "/resources/images/open.png");
        openButton.addActionListener(event -> controller.openFile());
        add(openButton);
    }

    private void addSaveButton() {
        JButton saveButton = new ActionButton("SaveButton", "images/save.png");
        saveButton.addActionListener(event -> controller.saveFile());
        add(saveButton);
    }

    private void addStartSearchButton() {
        JButton startSearchButton = new ActionButton("StartSearchButton", "images/search.png");
        startSearchButton.addActionListener(event -> controller.startSearch());
        add(startSearchButton);
    }

    private void addNextButton() {
        JButton nextButton = new ActionButton("NextMatchButton", "images/next.png");
        nextButton.addActionListener(event -> controller.nextMatch());
        add(nextButton);
    }

    private void addPreviousSearchButton() {
        JButton previousButton = new ActionButton("PreviousMatchButton", "images/prev.png");
        previousButton.addActionListener(event -> controller.prevMath());
        add(previousButton);
    }
}
