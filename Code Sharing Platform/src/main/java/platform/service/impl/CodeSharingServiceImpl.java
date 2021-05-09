package platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import platform.entity.Code;
import platform.repository.CodeSharingRepository;
import platform.service.CodeSharingService;

import java.util.UUID;

@Service
public class CodeSharingServiceImpl implements CodeSharingService {

    private final CodeSharingRepository repository;

    @Autowired
    public CodeSharingServiceImpl(CodeSharingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Code findByUuid(String uuid) {
        Code code = repository.findByUuid(uuid).orElse(new Code());
        code.countUpNumOfViewed();
        repository.save(code);
        return code;
    }

    @Override
    public Page<Code> findAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("updateAt").descending());
        return repository.findAllByTimeRestrictionAndViewsRestriction(0L, 0L, pageable);
    }

    @Override
    public Code save(Code code) {
        code.setUuid(UUID.randomUUID().toString());
        return repository.save(code);
    }
}
