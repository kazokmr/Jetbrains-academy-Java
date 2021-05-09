package converter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

    private final Matcher matcher;
    private final Deque<Element> stack;

    public JsonParser(String document) {
        // Bracket only or Key-Value or Value Only
        this.matcher = Pattern.compile("([{}\\[\\]],?|\"[^\"]*\"\\s*:\\s*([{\\[]|\"?[^@#,:\"}\\]]*\"?,?)|[^,\\]]+,?)").matcher(document);
        this.stack = new ArrayDeque<>();
    }

    public Deque<Element> start() {
        while (matcher.find()) {
            String object = matcher.group().trim();
            if (object.matches("^[{}],?$")) {
                continue;
            }
            Element root = createElementByObject(object, "");
            String value = getValueBy(object);
            if (!value.equals("{")) {
                root.setValue(value);
            }
            parse(root);
        }
        return stack;
    }

    private void parse(Element element) {

        if (!matcher.find()) {
            return;
        }
        String object = matcher.group().trim();
        if (object.startsWith("}")) {
            checkLastElementForSingleObject(element);
            return;
        }
        if (object.startsWith("]")) {
            return;
        }
        parseObject(object, element);
        parse(element);
    }

    private void parseObject(String object, Element element) {

        if (isObjectInvalidated(object, element)) {
            createElementByAttributes(element);
        }
        if (!element.isSingleObject()) {
            object = object.replaceAll("#", "");
        }

        if (isValueObject(object, element)) {
            setValue(element, object);

        } else if (isAttributeObject(object)) {
            setAttribute(element, object);

        } else if (isElementWithAttributes(object)) {
            Element child = createElementByObject(object, element.getPath());
            parse(child);

        } else if (isElementWithoutAttributes(object)) {
            element.setSingleObject(false);
            Element child = createElementByObject(object, element.getPath());
            child.setValue(getValueBy(object));

        } else if (isElementBeginningArray(object)) {
            Element child = createElementByObject(object, element.getPath());
            child.setSingleObject(false);
            parse(child);

        } else if (isObjectWithoutCollon(object)) {
            String key = object.replaceAll(",", "").trim();
            if (!key.isEmpty()) {
                Element child = createElementByObject(key, element.getPath());
                child.setValue(key.replaceAll("[,\"]", ""));
            }

        } else if (isObjectWithoutKey(object)) {
            while (!object.matches("^[}\\]],?") && matcher.find()) {
                object = matcher.group().trim();
            }
        }
    }

    private Element createElement(String name, String path) {
        Element element = new Element(name, path);
        stack.stream()
                .filter(Objects::nonNull)
                .filter(e -> !Objects.equals(e.getName(), "element"))
                .filter(e -> Objects.equals(name, e.getName()))
                .filter(e -> Objects.equals(path, e.getAncestor()))
                .forEach(stack::remove);
        stack.offerLast(element);
        return element;
    }

    private Element createElementByObject(String object, String prevPath) {
        String key = getKeyBy(object);
        return key.length() == 0 ? createElement("element", prevPath) : createElement(key, prevPath);
    }

    private void createElementByAttributes(Element element) {
        if (!element.isSingleObject()) {
            return;
        }
        element.getAttribute()
                .forEach(attr -> {
                    Element elementByAttribute = createElement(attr.getKey(), element.getPath());
                    elementByAttribute.setValue(attr.getValue());
                });
        if (element.getValue() != null) {
            Element elementByValue = createElement(element.getName(), element.getPath());
            elementByValue.setValue(element.getValue());
        }
        element.clearAttributes();
        element.setSingleObject(false);
    }

    private String getKeyBy(String object) {
        if (isObjectWithoutCollon(object)) {
            return "";
        }
        return Pattern.compile("([\"@#{\\[]|:.*)").matcher(object).replaceAll("").trim();
    }

    private String getValueBy(String object) {
        return Pattern.compile("([^:]+:|[\",])").matcher(object).replaceAll("").trim();
    }

    private void setAttribute(Element element, String object) {
        String key = getKeyBy(object);
        String value = getValueBy(object);
        if (Pattern.compile("[\\[{]").matcher(value).find()) {
            boolean duplicateElement = stack.stream()
                    .anyMatch(e -> Objects.equals(e.getAncestor(), element.getPath()) && Objects.equals(e.getName(), key));
            if (duplicateElement) {
                while (!object.matches("^[}\\]],?") && matcher.find()) {
                    object = matcher.group().trim();
                }
                return;
            }
            element.addAttribute(key, "");
            if (matcher.find()) {
                String subObject = matcher.group().trim();
                if (Pattern.compile("[]}]").matcher(subObject).find()) {
                    return;
                }
                element.setSingleObject(false);
                element.clearAttributes();
                Element subElement = createElement(key, element.getPath());
                parseObject(subObject, subElement);
                parse(subElement);
            }
        } else {
            element.addAttribute(key, value);
        }
    }

    private void setValue(Element element, String object) {
        String value = getValueBy(object);
        if (Pattern.compile("[{\\[]").matcher(value).matches()) {
            element.setSingleObject(false);
            parse(element);

        } else {
            element.setValue(getValueBy(object));
        }
    }

    private void checkLastElementForSingleObject(Element element) {
        if (!element.equals(stack.peekLast())) {
            return;
        }
        if (element.getValue() == null && element.getAttribute().size() > 0) {
            createElementByAttributes(element);
        } else {
            element.setSingleObject(true);
            element.setValue("");
        }
    }

    private boolean isObjectInvalidated(String object, Element element) {
        if (!element.isSingleObject()) {
            return true;
        }
        if (object.contains("#") && !isValueObject(object, element)) {
            return true;
        }
        if (object.contains("@") && !isAttributeObject(object)) {
            return true;
        }
        if (getKeyBy(object).length() == 0) {
            return true;
        }
        if (isElementWithAttributes(object) || isElementWithoutAttributes(object)) {
            return true;
        }
        if (object.contains("@") || object.contains("#")) {
            return false;
        }
        return element.getAttribute().size() > 0 || element.getValue() != null;
    }

    private boolean isValueObject(String object, Element element) {
        String regex = String.format("\"#%s\":", element.getName());
        return Pattern.compile(regex).matcher(object).find();
    }

    private boolean isAttributeObject(String object) {
        return Pattern.compile("\"@[^\":]+\":\\s*[^\\s]+").matcher(object).find();
    }

    private boolean isElementWithAttributes(String object) {
        return Pattern.compile("(\"[^\"@#]+\":\\s*\\{|^\\{,?$)").matcher(object).find();
    }

    private boolean isElementWithoutAttributes(String object) {
        return Pattern.compile("\"[^\"#@]+\":\\s*(\"?[^@#,:\"}\\[\\s]+\"?|\"\"),?").matcher(object).find();
    }

    private boolean isObjectWithoutCollon(String object) {
        return Pattern.compile("[^:]+").matcher(object).matches();
    }

    private boolean isElementBeginningArray(String object) {
        return Pattern.compile("(\"[^\"#@]+\":\\s*\\[|^\\[)").matcher(object).find();
    }

    private boolean isObjectWithoutKey(String object) {
        return Pattern.compile("\"\":\\s*[{\\[]").matcher(object).find();
    }
}