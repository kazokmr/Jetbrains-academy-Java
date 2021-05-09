package converter.impl;

import converter.Converter;
import converter.Element;
import converter.JsonParser;

import java.util.Deque;
import java.util.Objects;

public class JsonToXmlConverter extends Converter {

    public JsonToXmlConverter(String document) {
        super(document);
    }

    @Override
    protected Deque<Element> parse(String document) {
        return new JsonParser(document).start();
    }

    @Override
    protected void preConvert(Element element) {
        String ancestors = element.getAncestor();
        if (ancestors.isEmpty()) {
            numberOfRoot++;
        }
        if (elementsStack.size() == 0) {
            return;
        }
        while (!elementsStack.isEmpty() && !Objects.equals(ancestors, elementsStack.peek())) {
            output.append(String.format("</%s>", getParentName(elementsStack.poll())));
        }
    }

    @Override
    protected void setName(Element element) {
        output.append(String.format("<%s", element.getName()));
    }

    @Override
    protected void setAttributes(Element element) {
        element.getAttribute().stream()
                .map(e -> String.format(" %s=\"%s\"", e.getKey(), Objects.equals(e.getValue(), "null") ? "" : e.getValue()))
                .forEach(output::append);
    }

    @Override
    protected void setValue(Element element) {
        if (element.isSingleObject()) {
            output.append(
                    Objects.equals(element.getValue(), "null") ? "/>" : String.format(">%s</%s>", element.getValue(), element.getName()));
        } else {
            output.append(">");
            elementsStack.offer(element.getPath());
        }
    }

    @Override
    protected void postConvert() {
        elementsStack.stream().map(n -> String.format("</%s>", getParentName(n))).forEach(output::append);
        if (numberOfRoot > 1) {
            output.insert(0, "<root>");
            output.append("</root>");
        }
    }
}
