package animals.game;

import animals.game.knowledge.KnowledgeNode;
import animals.game.knowledge.KnowledgeTree;
import animals.game.learning.LearningAnimals;
import animals.game.message.Confirmation;

import java.util.Random;
import java.util.Scanner;

import static animals.game.i18n.AppResource.getMessage;

public class GuessingGame {

    public void play() {
        KnowledgeNode node = KnowledgeTree.getInstance().getRoot();
        System.out.println(getMessage("game.letsPlay"));
        System.out.println(getMessage("game.think"));
        System.out.println(getMessage("game.enter"));
        new Scanner(System.in).nextLine();

        Confirmation confirmation = new Confirmation();
        do {
            boolean answer = confirmation.is(node.askQuestion());
            if (node.isAnimal()) {
                result(answer, node);
                return;
            }
            node = node.next(answer);
        } while (true);
    }

    private void result(boolean answer, KnowledgeNode node) {
        String[] win = getMessage("game.win").split("\\f");
        if (answer) {
            System.out.println(win[new Random().nextInt(win.length)]);
        } else {
            new LearningAnimals().learn(node);
        }
    }
}
