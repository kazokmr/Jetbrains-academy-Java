package platform.helper;

import platform.entity.Code;

public class CodeAvailableChecker {

    private final Code code;

    public CodeAvailableChecker(Code code) {
        this.code = code;
    }

    public boolean isNotViewable() {
        if (code == null) {
            return true;
        }
        if (code.getViewsRestriction() > 0 && code.getAvailableNumOfView() < 0) {
            return true;
        }
        return code.getTimeRestriction() > 0 && code.getAvailableSeconds() < 0;
    }
}
