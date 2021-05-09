package converter;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Queue;

public abstract class Converter {

    protected final Deque<Element> deque;
    protected final StringBuilder output;
    protected final Queue<String> elementsStack;
    protected int numberOfRoot;

    public Converter(String document) {
        this.deque = parse(document);
        this.output = new StringBuilder();
        this.elementsStack = Collections.asLifoQueue(new ArrayDeque<>());
        this.numberOfRoot = 0;
    }

    public static boolean isInputDataXml(String inputData) {
        return inputData.matches("^\\s?<.+");
    }

    public String output() {
        for (Element element : deque) {
            preConvert(element);
            setName(element);
            setAttributes(element);
            setValue(element);
        }
        postConvert();
        return output.toString();
    }

    protected String getParentName(String ancestor) {
        String[] names = ancestor.split(",");
        return names[names.length - 1].trim();
    }

    protected abstract Deque<Element> parse(String document);

    protected abstract void preConvert(Element element);

    protected abstract void setName(Element element);

    protected abstract void setAttributes(Element element);

    protected abstract void setValue(Element element);

    protected abstract void postConvert();
}
