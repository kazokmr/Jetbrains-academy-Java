package animals.game.knowledge;

import animals.game.knowledge.dao.KnowledgeNodeDao;

public class KnowledgeTree {

    private static final KnowledgeTree instance = new KnowledgeTree();
    private KnowledgeNode root;

    private KnowledgeTree() {
        root = KnowledgeNodeDao.getInstance().readFile();
    }

    public static KnowledgeTree getInstance() {
        return instance;
    }

    public boolean existRoot() {
        return root != null;
    }

    public KnowledgeNode getRoot() {
        return root;
    }

    public void initializeRoot(String data) {
        root = new KnowledgeNode(data, 0);
    }

    public void replace(KnowledgeNode node, String data, String yes, String no) {
        node.replace(data, yes, no);
    }

    public void save() {
        KnowledgeNodeDao.getInstance().writeFile(root);
    }
}
