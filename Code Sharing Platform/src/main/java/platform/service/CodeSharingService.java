package platform.service;

import org.springframework.data.domain.Page;
import platform.entity.Code;

public interface CodeSharingService {

    Code findByUuid(String uuid);

    Page<Code> findAll(Integer page);

    Code save(Code code);
}
