package animals.game;

import animals.game.knowledge.knowledgebase.AnimalSearcher;
import animals.game.knowledge.knowledgebase.AnimalStatics;
import animals.game.message.Confirmation;

import java.text.MessageFormat;
import java.util.Random;
import java.util.Scanner;

import static animals.game.i18n.AppResource.getMessage;

public class Menu {

    Menu() {
        System.out.println(getMessage("welcome"));
        String[] greetings = getMessage("greeting").split("\\f");
        System.out.println(greetings[new Random().nextInt(greetings.length)]);
    }

    void selectGame() {
        System.out.printf("\n%s\n", getMessage("menu.property.title"));
        System.out.printf("1. %s\n", getMessage("menu.entry.play"));
        System.out.printf("2. %s\n", getMessage("menu.entry.list"));
        System.out.printf("3. %s\n", getMessage("menu.entry.search"));
        System.out.printf("4. %s\n", getMessage("menu.entry.statistics"));
        System.out.printf("5. %s\n", getMessage("menu.entry.print"));
        System.out.printf("0. %s\n", getMessage("menu.property.exit"));
        String input = new Scanner(System.in).nextLine();
        switch (input) {
            case "1":
                playTheGuessingGame();
                break;
            case "2":
                listOfAllAnimals();
                break;
            case "3":
                searchAnimal();
                break;
            case "4":
                calculateStatistics();
                break;
            case "5":
                printTheKnowledgeTree();
                break;
            case "0":
                return;
            default:
                System.out.println(new MessageFormat(getMessage("menu.property.error")).format(new Object[]{5}));
                break;
        }
        selectGame();
    }

    private void playTheGuessingGame() {
        GuessingGame guessingGame = new GuessingGame();
        Confirmation confirmation = new Confirmation();
        String[] again = getMessage("game.again").split("\\f");
        do {
            guessingGame.play();
            System.out.println();
        } while (confirmation.is(again[new Random().nextInt(again.length)]));
    }

    private void listOfAllAnimals() {
        new AnimalStatics().listAnimals();
    }

    private void searchAnimal() {
        System.out.println(getMessage("animal.prompt"));
        String animal = new Scanner(System.in).nextLine();
        new AnimalSearcher(animal).search();
    }

    private void calculateStatistics() {
        new AnimalStatics().calculate();
    }

    private void printTheKnowledgeTree() {
        new AnimalStatics().printTree();
    }
}
