package editor;

import editor.business.controller.ActionListenerController;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class SearchMenu extends JMenu {

    private final ActionListenerController controller;

    public SearchMenu(ActionListenerController controller) {
        this.controller = controller;
        setText("Search");
        setName("MenuSearch");

        addStartSearchMenu();
        addPreviousMatchMenu();
        addNextMatchMenu();
        addUseRegExpCheck();
    }

    private void addStartSearchMenu() {
        JMenuItem startSearch = new JMenuItem("Start search");
        startSearch.setName("MenuStartSearch");
        startSearch.addActionListener(event -> controller.startSearch());
        add(startSearch);
    }

    private void addPreviousMatchMenu() {
        JMenuItem prevMatch = new JMenuItem("Previous search");
        prevMatch.setName("MenuPreviousMatch");
        prevMatch.addActionListener(event -> controller.prevMath());
        add(prevMatch);
    }

    private void addNextMatchMenu() {
        JMenuItem nextMatch = new JMenuItem("Next match");
        nextMatch.setName("MenuNextMatch");
        nextMatch.addActionListener(event -> controller.nextMatch());
        add(nextMatch);
    }

    private void addUseRegExpCheck() {
        JMenuItem useRegExp = new JMenuItem("Use regular expressions");
        useRegExp.setName("MenuUseRegExp");
        useRegExp.addActionListener(event -> controller.setUseRegExp());
        add(useRegExp);
    }
}
