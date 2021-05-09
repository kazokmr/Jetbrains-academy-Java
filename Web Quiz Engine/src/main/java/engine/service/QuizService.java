package engine.service;

import engine.entity.Account;
import engine.entity.Quiz;
import engine.model.json.Result;
import engine.model.json.SubmitAnswer;
import org.springframework.data.domain.Page;

public interface QuizService {

    Quiz getQuizById(Long id);

    Page<Quiz> getQuizzesByPage(Integer page);

    Quiz saveQuiz(Quiz quiz);

    Result getResult(Long id, SubmitAnswer answer);

    void remove(Long id, Account account);
}
