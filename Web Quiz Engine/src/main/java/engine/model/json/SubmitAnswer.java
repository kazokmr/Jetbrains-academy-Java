package engine.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubmitAnswer {

    @JsonProperty
    private Integer[] answer;

    public Integer[] getAnswer() {
        return answer;
    }

    public void setAnswer(Integer[] answer) {
        this.answer = answer == null ? new Integer[0] : answer;
    }
}
