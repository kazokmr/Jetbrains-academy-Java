package animals.game.knowledge;

import static animals.game.i18n.AppResource.getPattern;

public class KnowledgeFormatter {

    public static String formatWithArticle(String animal) {
        if (animal.matches(getPattern("animal.1.pattern"))) {
            return animal.replaceFirst(getPattern("animal.1.pattern"), getPattern("animal.1.replace"));
        }
        if (animal.matches(getPattern("animal.2.pattern"))) {
            return animal.replaceFirst(getPattern("animal.2.pattern"), getPattern("animal.2.replace"));
        }
        return animal.replaceFirst(getPattern("animal.3.pattern"), getPattern("animal.3.replace"));
    }

    public static String formatWithoutArticle(String animal) {
        return animal.replaceFirst(getPattern("animalName.1.pattern"), getPattern("animalName.1.replace"));
    }

    public static String formatFact(String statement, String animal, boolean isPositive) {
        if (!isPositive) {
            statement = statement
                    .replaceFirst(getPattern("negative.1.pattern"), getPattern("negative.1.replace"))
                    .replaceFirst(getPattern("negative.2.pattern"), getPattern("negative.2.replace"))
                    .replaceFirst(getPattern("negative.3.pattern"), getPattern("negative.3.replace"));
        }
        if (animal == null) {
            return statement;
        }
        return String.format(
                statement.replaceFirst(getPattern("animalFact.1.pattern"), getPattern("animalFact.1.replace")),
                animal.replaceFirst(getPattern("definite.1.pattern"), getPattern("definite.1.replace"))
        );
    }

    public static String formatQuestion(String statement, boolean isAnimal) {
        if (isAnimal) {
            return statement.replaceFirst(getPattern("guessAnimal.1.pattern"), getPattern("guessAnimal.1.replace"));
        }
        return statement
                .replaceFirst(getPattern("question.1.pattern"), getPattern("question.1.replace"))
                .replaceFirst(getPattern("question.2.pattern"), getPattern("question.2.replace"));
    }
}
