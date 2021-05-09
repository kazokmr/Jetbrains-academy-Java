package animals.game.learning;

import animals.game.knowledge.KnowledgeFormatter;
import animals.game.knowledge.KnowledgeNode;
import animals.game.knowledge.KnowledgeTree;
import animals.game.message.Confirmation;

import java.text.MessageFormat;
import java.util.Random;
import java.util.Scanner;

import static animals.game.i18n.AppResource.getMessage;
import static animals.game.i18n.AppResource.getPattern;

public class LearningAnimals {

    public void learn(KnowledgeNode node) {
        String actuallyAnimal = KnowledgeFormatter.formatWithArticle(getActuallyAnimal());
        String thoughtAnimal = node.toString();
        String fact = distinguish(thoughtAnimal, actuallyAnimal);

        String confirmation = new MessageFormat(getMessage("game.isCorrect")).format(new Object[]{actuallyAnimal});
        boolean isFactActuallyAnimal = new Confirmation().is(confirmation);

        KnowledgeTree knowledgeTree = KnowledgeTree.getInstance();
        if (isFactActuallyAnimal) {
            knowledgeTree.replace(node, fact, actuallyAnimal, thoughtAnimal);
        } else {
            knowledgeTree.replace(node, fact, thoughtAnimal, actuallyAnimal);
        }

        System.out.println(getMessage("game.learned"));
        System.out.printf(" - %s\n", KnowledgeFormatter.formatFact(fact, thoughtAnimal, !isFactActuallyAnimal));
        System.out.printf(" - %s\n", KnowledgeFormatter.formatFact(fact, actuallyAnimal, isFactActuallyAnimal));
        System.out.println(getMessage("game.distinguish"));
        System.out.printf(" - %s\n", node.askQuestion());
        String[] thanks = getMessage("game.thanks").split("\\f");
        System.out.println(thanks[new Random().nextInt(thanks.length)]);
    }

    private String getActuallyAnimal() {
        System.out.println(getMessage("game.giveUp"));
        String input = new Scanner(System.in).nextLine();
        if (input.matches(getPattern("animal.isCorrect"))) {
            return input;
        }
        System.out.printf("\n%s\n\n", getMessage("animal.error"));
        return getActuallyAnimal();
    }

    private String distinguish(String thoughtAnimal, String actuallyAnimal) {
        String[] animals = {thoughtAnimal, actuallyAnimal};
        String statementPrompt = new MessageFormat(getMessage("statement.prompt")).format(animals);
        System.out.println(statementPrompt);

        String fact = new Scanner(System.in).nextLine();
        if (fact.toLowerCase().matches(getPattern("statement.isCorrect"))) {
            return fact;
        }
        System.out.printf("\n%s\n\n", getMessage("statement.error"));
        return distinguish(thoughtAnimal, actuallyAnimal);
    }
}
