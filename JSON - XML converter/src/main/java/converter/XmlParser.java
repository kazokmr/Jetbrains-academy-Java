package converter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlParser {

    private final Matcher matcher;
    private final Deque<Element> stack;

    public XmlParser(String document) {
        this.matcher = Pattern.compile("(<[^<>]+\\s*[^<>\"=\\s]*\\s?=?\\s?[\"']?[^<>\"'=]*[\"']?\\s*/?>|[^<>/]+)").matcher(document);
        this.stack = new ArrayDeque<>();
    }

    public Deque<Element> start() {
        while (matcher.find()) {
            String object = matcher.group().trim();
            if (Pattern.compile("^<\\?.+\\?>").matcher(object).matches()) {
                continue;
            }
            Element rootElement = generateElement(object, "");
            parse(rootElement);
        }
        return stack;
    }

    private void parse(Element element) {

        if (!matcher.find()) {
            return;
        }

        String object = matcher.group();
        // if node is close tag, the element is over.
        if (object.startsWith("</")) {
            // if the element is last in the stack, it is single-object.
            if (element.equals(stack.peekLast())) {
                element.setSingleObject(true);
                element.setValue("");
            } else {
                element.setSingleObject(false);
            }
            return;

        } else if (object.endsWith("/>")) {
            // The node is child and single-object with value of null.
            element.setSingleObject(false);
            Element child = generateElement(object, element.getPath());
            child.setValue(null);

        } else if (object.startsWith("<")) {
            // start parsing the child node.
            element.setSingleObject(false);
            Element child = generateElement(object, element.getPath());
            parse(child);

        } else {
            element.setValue(object);
        }
        parse(element);
    }

    private Element generateElement(String object, String prevPath) {
        String name = getName(object);
        Element element = new Element(name, prevPath);
        setAttributes(element, object);
        stack.offerLast(element);
        return element;
    }

    private String getName(String object) {
        return object
                .replaceAll("\\s*</*\\s*", "")
                .replaceAll("(\\s+[\\w=\"',.\\s]+/?>.*|\\s*/?>.*)", "");
    }

    private void setAttributes(Element element, String object) {
        Matcher matcher = Pattern.compile("[^\"'=\\s]+\\s?=\\s?[\"'][^\"'=]*[\"']").matcher(object);
        while (matcher.find()) {
            String kv = matcher.group().replaceAll("[\"']", "");
            int split = kv.indexOf("=");
            element.addAttribute(kv.substring(0, split).trim(), kv.substring(split + 1).trim());
        }
    }
}