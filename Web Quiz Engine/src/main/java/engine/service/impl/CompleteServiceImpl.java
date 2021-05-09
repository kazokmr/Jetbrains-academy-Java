package engine.service.impl;

import engine.entity.Account;
import engine.entity.Complete;
import engine.entity.Quiz;
import engine.repository.CompleteRepository;
import engine.service.CompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CompleteServiceImpl implements CompleteService {

    private final CompleteRepository repository;

    @Autowired
    public CompleteServiceImpl(CompleteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Quiz quiz, Account account) {
        Complete complete = new Complete(quiz, account);
        repository.save(complete);
    }

    @Override
    public Page<Complete> findAllByAccountWithPagination(Account account, Integer page) {
        Pageable paging = PageRequest.of(page, 10, Sort.by("completedAt").descending());
        return repository.findAllByAccount(account, paging);
    }
}
