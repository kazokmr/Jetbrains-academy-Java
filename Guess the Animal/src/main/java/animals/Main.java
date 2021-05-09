package animals;

import animals.game.Game;

public class Main {

    private static String fileType = "";

    public static void main(String[] args) {
        if (args.length >= 2 && args[0].equals("-type")) {
            fileType = args[1];
        }
        new Game().start();
    }

    public static String getFileType() {
        return fileType;
    }
}

