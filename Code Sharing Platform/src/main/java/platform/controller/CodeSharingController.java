package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import platform.entity.Code;
import platform.helper.CodeAvailableChecker;
import platform.service.CodeSharingService;

@Controller
public class CodeSharingController {

    private final CodeSharingService service;

    @Autowired
    public CodeSharingController(CodeSharingService service) {
        this.service = service;
    }

    @GetMapping("/code/new")
    public String getNewCode() {
        return "newCode";
    }

    @GetMapping("/code/{uuid}")
    public String getCode(@PathVariable String uuid, Model model) {
        Code code = service.findByUuid(uuid);
        if (new CodeAvailableChecker(code).isNotViewable()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("code", code);
        return "viewCode";
    }

    @GetMapping("/code/latest")
    public String getLatestCode(Model model) {
        model.addAttribute("codeList", service.findAll(0).getContent());
        return "viewLatestCode";
    }
}
