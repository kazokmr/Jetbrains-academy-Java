package animals.game.knowledge.knowledgebase;

import animals.game.knowledge.KnowledgeFormatter;
import animals.game.knowledge.KnowledgeNode;
import animals.game.knowledge.KnowledgeTree;

import java.text.MessageFormat;
import java.util.Deque;
import java.util.LinkedList;

import static animals.game.i18n.AppResource.getMessage;

public class AnimalSearcher {
    private final String name;
    private final Deque<String> factDeque;

    public AnimalSearcher(String name) {
        this.name = KnowledgeFormatter.formatWithoutArticle(name);
        this.factDeque = new LinkedList<>();
    }

    public void search() {
        collectStatements(KnowledgeTree.getInstance().getRoot());
        Object[] params = {name};
        if (factDeque.size() == 0) {
            System.out.println(new MessageFormat(getMessage("tree.search.noFacts")).format(params));
            return;
        }
        System.out.println(new MessageFormat(getMessage("tree.search.facts")).format(params));
        factDeque.forEach(statement -> System.out.printf(getMessage("tree.search.printf"), statement));
    }

    private boolean collectStatements(KnowledgeNode node) {
        if (node.isAnimal()) {
            return node.isMatchAnimal(name);
        }
        if (next(node, true)) {
            return true;
        }
        return next(node, false);
    }

    private boolean next(KnowledgeNode node, boolean answer) {
        factDeque.offerLast(node.getStatement(answer));
        if (collectStatements(answer ? node.getYes() : node.getNo())) {
            return true;
        }
        factDeque.pollLast();
        return false;
    }
}
