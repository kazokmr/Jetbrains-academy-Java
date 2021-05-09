package engine.repository;

import engine.entity.Account;
import engine.entity.Complete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompleteRepository extends PagingAndSortingRepository<Complete, Long> {
    Page<Complete> findAllByAccount(Account account, Pageable pageable);
}
