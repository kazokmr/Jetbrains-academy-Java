package animals.game.knowledge;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KnowledgeNode {
    private String data;
    private KnowledgeNode yes;
    private KnowledgeNode no;
    private int depth;

    public KnowledgeNode() {
    }

    KnowledgeNode(String data, int depth) {
        this.data = data.toLowerCase().trim();
        this.depth = depth;
    }

    void replace(String data, String yes, String no) {
        this.data = data.trim();
        this.yes = new KnowledgeNode(yes, depth + 1);
        this.no = new KnowledgeNode(no, depth + 1);
    }

    @Override
    public String toString() {
        return KnowledgeFormatter.formatWithArticle(data);
    }

    public String askQuestion() {
        return KnowledgeFormatter.formatQuestion(data, isAnimal());
    }

    @JsonIgnore
    public boolean isAnimal() {
        return yes == null && no == null;
    }

    @JsonIgnore
    public boolean isMatchAnimal(String name) {
        String targetAnimal = KnowledgeFormatter.formatWithoutArticle(name.toLowerCase().trim());
        return Objects.equals(getNameOfAnimal(), targetAnimal);
    }

    @JsonIgnore
    public String getNameOfAnimal() {
        return isAnimal() ? KnowledgeFormatter.formatWithoutArticle(data) : "";
    }

    @JsonIgnore
    public String getStatement(boolean isFact) {
        return isAnimal() ? "" : KnowledgeFormatter.formatFact(data, null, isFact);
    }

    public KnowledgeNode next(boolean answer) {
        return answer ? yes : no;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public KnowledgeNode getYes() {
        return yes;
    }

    public void setYes(KnowledgeNode yes) {
        this.yes = yes;
    }

    public KnowledgeNode getNo() {
        return no;
    }

    public void setNo(KnowledgeNode no) {
        this.no = no;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
