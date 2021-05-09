package animals.game.knowledge.knowledgebase;

import animals.game.knowledge.KnowledgeNode;
import animals.game.knowledge.KnowledgeTree;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static animals.game.i18n.AppResource.getMessage;

public class AnimalStatics {
    private final List<KnowledgeNode> list;

    public AnimalStatics() {
        this.list = new ArrayList<>();
        setList(KnowledgeTree.getInstance().getRoot());
    }

    private void setList(KnowledgeNode node) {
        list.add(node);
        if (node.isAnimal()) {
            return;
        }
        setList(node.getYes());
        setList(node.getNo());
    }

    private long numOfNodes() {
        return list.size();
    }

    private long numOfAnimals() {
        return list.stream().filter(KnowledgeNode::isAnimal).count();
    }

    private long numOfStatements() {
        return numOfNodes() - numOfAnimals();
    }

    private int heightOfTree() {
        return list.stream().mapToInt(KnowledgeNode::getDepth).max().orElse(0);
    }

    private int minDepthOfAnimals() {
        return list.stream().filter(KnowledgeNode::isAnimal).mapToInt(KnowledgeNode::getDepth).min().orElse(0);
    }

    private double avgDepthOfAnimals() {
        return list.stream().filter(KnowledgeNode::isAnimal).mapToInt(KnowledgeNode::getDepth).average().orElse(0d);
    }

    private boolean existDepth(int targetDepth, int startIndex) {
        return list.stream().skip(startIndex - 1).anyMatch(node -> node.getDepth() == targetDepth);
    }

    private String[] formatDataBranch(int curIndex) {
        KnowledgeNode node = list.get(curIndex);

        String verticalOrBlank = IntStream.range(0, node.getDepth())
                .mapToObj(depth -> existDepth(depth, curIndex + 1) ? getMessage("tree.print.vertical") : " ")
                .collect(Collectors.joining());

        String branchOrCorner = existDepth(node.getDepth(), curIndex + 1) ?
                getMessage("tree.print.branch") :
                getMessage("tree.print.corner");

        String data = node.isAnimal() ? node.toString() : node.askQuestion();

        return new String[]{verticalOrBlank, branchOrCorner, data};
    }

    public void listAnimals() {
        System.out.println(getMessage("tree.list.animals"));
        list.stream()
                .filter(KnowledgeNode::isAnimal)
                .sorted(Comparator.comparing(KnowledgeNode::getNameOfAnimal))
                .forEach(node -> System.out.printf(getMessage("tree.list.printf"), node.getNameOfAnimal(), node.getDepth() - 1));
    }

    public void calculate() {
        System.out.println(getMessage("tree.stats.title"));
        String rootStatement = KnowledgeTree.getInstance().getRoot().toString();
        System.out.println(new MessageFormat(getMessage("tree.stats.root")).format(new Object[]{rootStatement}));
        System.out.println(new MessageFormat(getMessage("tree.stats.nodes")).format(new Object[]{numOfNodes()}));
        System.out.println(new MessageFormat(getMessage("tree.stats.animals")).format(new Object[]{numOfAnimals()}));
        System.out.println(new MessageFormat(getMessage("tree.stats.statements")).format(new Object[]{numOfStatements()}));
        System.out.println(new MessageFormat(getMessage("tree.stats.height")).format(new Object[]{heightOfTree()}));
        System.out.println(new MessageFormat(getMessage("tree.stats.minimum")).format(new Object[]{minDepthOfAnimals()}));
        System.out.println(new MessageFormat(getMessage("tree.stats.average")).format(new Object[]{avgDepthOfAnimals()}));
    }

    public void printTree() {
        IntStream.range(0, list.size())
                .mapToObj(this::formatDataBranch)
                .forEach(words -> System.out.printf(getMessage("tree.print.printf"), words[0], words[1], words[2]));
    }
}
