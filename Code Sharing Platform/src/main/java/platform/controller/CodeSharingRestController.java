package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.entity.Code;
import platform.helper.CodeAvailableChecker;
import platform.service.CodeSharingService;

import java.util.List;
import java.util.Map;

@RestController
public class CodeSharingRestController {

    private final CodeSharingService service;

    @Autowired
    public CodeSharingRestController(CodeSharingService service) {
        this.service = service;
    }

    @PostMapping("/api/code/new")
    public Map<String, String> addCode(@RequestBody Code code) {
        Code responseCode = service.save(code);
        return Map.of("id", responseCode.getUuid());
    }

    @GetMapping("/api/code/{uuid}")
    public Code getCode(@PathVariable String uuid) {
        Code code = service.findByUuid(uuid);
        if (new CodeAvailableChecker(code).isNotViewable()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return code;
    }

    @GetMapping("/api/code/latest")
    public List<Code> getLatestCode() {
        return service.findAll(0).getContent();
    }
}
