package animals.game;

import animals.game.knowledge.KnowledgeFormatter;
import animals.game.knowledge.KnowledgeTree;
import animals.game.message.Goodbyes;
import animals.game.message.Greeting;

import java.util.Random;
import java.util.Scanner;

import static animals.game.i18n.AppResource.getMessage;
import static animals.game.i18n.AppResource.getPattern;

public class Game {

    public void start() {
        System.out.println(new Greeting().say());
        System.out.println();
        preSetAnimal();
        new Menu().selectGame();
        KnowledgeTree.getInstance().save();
        System.out.println();
        System.out.println(new Goodbyes().say());
    }

    private void preSetAnimal() {
        if (KnowledgeTree.getInstance().existRoot()) {
            return;
        }
        System.out.println(getMessage("animal.wantLearn"));
        String animal = KnowledgeFormatter.formatWithArticle(askAnimal());
        KnowledgeTree.getInstance().initializeRoot(animal);
        System.out.printf("%s %s\n", nice(), getMessage("animal.learnedMuch"));
        System.out.println();
    }

    private String askAnimal() {
        System.out.println(getMessage("animal.askFavorite"));
        String input = new Scanner(System.in).nextLine();
        if (input.matches(getPattern("animal.isCorrect"))) {
            return input;
        }
        System.out.printf("\n%s\n\n", getMessage("animal.error"));
        return askAnimal();
    }

    private String nice() {
        String[] words = getMessage("animal.nice").split("\\f");
        return words[new Random().nextInt(words.length)];
    }

}
