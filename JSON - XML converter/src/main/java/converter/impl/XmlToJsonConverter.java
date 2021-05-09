package converter.impl;

import converter.Converter;
import converter.Element;
import converter.XmlParser;

import java.util.*;
import java.util.stream.Collectors;

public class XmlToJsonConverter extends Converter {

    private final Map<String, Integer> keyMapOfArray = new HashMap<>();
    private Element prevElement;

    public XmlToJsonConverter(String document) {
        super(document);
    }

    @Override
    protected Deque<Element> parse(String document) {
        return new XmlParser(document).start();
    }

    @Override
    public void preConvert(Element element) {

        checkNameOfSubChildren(element);

        if (elementsStack.size() == 0) {
            return;
        }

        String parentPath = element.getAncestor();
        String closeElement = null;

        while (!Objects.equals(parentPath, elementsStack.peek())) {
            closeElement = elementsStack.poll();
            if (keyMapOfArray.containsKey(closeElement)) {
                output.append("]");
                keyMapOfArray.remove(closeElement);
            } else {
                output.append("}");
            }
        }

        // The second and the subsequent elements of array
        if (keyMapOfArray.containsKey(parentPath)) {
            int count = keyMapOfArray.get(parentPath);
            if (count > 0) {
                output.append(",");
            }
            count++;
            keyMapOfArray.put(parentPath, count);
        } else if (prevElement != null && Objects.equals(parentPath, prevElement.getAncestor())) {
            // The second and the subsequent children of the same parent
            output.append(",");
        } else if (closeElement != null) {
            output.append(",");
        }
        prevElement = element;
    }

    @Override
    public void setName(Element element) {

        // If the element is array, don't out the key.
        if (!keyMapOfArray.containsKey(element.getAncestor())) {
            output.append(String.format("\"%s\": ", element.getName()));
        }

        // If the element has array, value is a bracket.
        if (element.getAttribute().size() > 0) {
            output.append("{");
            elementsStack.offer(element.getPath());
        } else if (keyMapOfArray.containsKey(element.getPath())) {
            output.append("[");
            elementsStack.offer(element.getPath());
        } else if (!element.isSingleObject()) {
            output.append("{");
            elementsStack.offer(element.getPath());
        }
    }

    @Override
    public void setAttributes(Element element) {
        element.getAttribute().stream()
                .map(a -> String.format("\"@%s\": \"%s\",", a.getKey(), a.getValue()))
                .forEach(output::append);
    }

    @Override
    public void setValue(Element element) {
        String value = element.getValue() == null ? "null" : String.format("\"%s\"", element.getValue());

        if (element.getAttribute().size() > 0) {
            output.append(String.format("\"#%s\": ", element.getName()));
            if (keyMapOfArray.containsKey(element.getPath())) {
                output.append("[");
                elementsStack.offer(element.getPath());
            } else if (!element.isSingleObject()) {
                output.append("{");
                elementsStack.offer(element.getPath());
            } else {
                output.append(value);
            }

        } else if (element.isSingleObject()) {
            output.append(value);
        }
    }

    @Override
    public void postConvert() {
        for (String path : elementsStack) {
            if (keyMapOfArray.containsKey(path)) {
                output.append("]");
            } else {
                output.append("}");
            }
        }
        output.insert(0, "{");
        output.append("}");
    }

    private void checkNameOfSubChildren(Element parent) {
        if (parent.isSingleObject()) {
            return;
        }
        // Get SubChildren
        List<Element> subChildren = deque.stream()
                .filter(e -> !e.equals(parent))
                .filter(e -> Objects.equals(e.getAncestor(), parent.getPath()))
                .collect(Collectors.toList());

        if (subChildren.size() < 2) {
            return;
        }

        if (subChildren.stream().allMatch(c -> c.getPath().equals(subChildren.get(0).getPath()))) {
            keyMapOfArray.put(parent.getPath(), 0);
        }
    }
}
