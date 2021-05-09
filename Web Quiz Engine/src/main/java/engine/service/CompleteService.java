package engine.service;

import engine.entity.Account;
import engine.entity.Complete;
import engine.entity.Quiz;
import org.springframework.data.domain.Page;

public interface CompleteService {

    void save(Quiz quiz, Account account);

    Page<Complete> findAllByAccountWithPagination(Account account, Integer page);
}
