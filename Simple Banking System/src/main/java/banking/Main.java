package banking;

import banking.data.DatabaseConfig;
import banking.presentation.view.TopMenuView;

public class Main {
    public static void main(String[] args) {
        DatabaseConfig.getInstance().setDbFile(args[1]);
        TopMenuView.prompt();
    }
}
