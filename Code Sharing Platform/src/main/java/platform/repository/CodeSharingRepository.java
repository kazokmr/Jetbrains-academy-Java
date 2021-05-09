package platform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import platform.entity.Code;

import java.util.Optional;

public interface CodeSharingRepository extends PagingAndSortingRepository<Code, Long> {

    Optional<Code> findByUuid(String uuid);

    Page<Code> findAllByTimeRestrictionAndViewsRestriction(Long l1, Long l2, Pageable pageable);
}
