package calculator;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

class Variables {

    private final Map<String, String> map;

    Variables() {
        map = new HashMap<>();
    }

    BigInteger valueOf(String key) {
        String value = map.get(key);
        if (value.matches("[+\\-]?[1-9]?\\d+")) {
            return new BigInteger(value);
        }
        return valueOf(value);
    }

    void assignment(String exp) {
        if (!isValidIdentifier(exp)) {
            System.out.println("Invalid identifier");
            return;
        }
        if (!isValidAssignment(exp)) {
            System.out.println("Invalid assignment");
            return;
        }
        String[] idValue = exp.replaceAll("\\s+", "").split("=");
        if (idValue[1].matches("[a-zA-Z]+") && !map.containsKey(idValue[1])) {
            System.out.println("Unknown variable");
            return;
        }
        map.put(idValue[0], idValue[1]);
    }

    boolean isVariable(String exp) {
        return exp.matches(".+?=.+");
    }

    private boolean isValidIdentifier(String exp) {
        return exp.matches("^\\s*[a-zA-Z]+\\s*=.+");
    }

    private boolean isValidAssignment(String exp) {
        return exp.matches("^\\s*[\\w]+\\s*=\\s*([+\\-]?[1-9]?\\d+|[a-zA-Z]+)\\s*$");
    }

    public boolean isKnown(String id) {
        return map.containsKey(id);
    }

}
