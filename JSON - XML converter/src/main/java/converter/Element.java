package converter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Element {
    private final String name;
    private final String ancestor;
    private final Map<String, String> attributes;
    private String value;
    private boolean singleObject;

    public Element(String name, String path) {
        this.name = name;
        this.ancestor = path;
        this.attributes = new LinkedHashMap<>();
        this.singleObject = true;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return ancestor.isEmpty() ? name : String.format("%s, %s", getAncestor(), name);
    }

    public Set<Map.Entry<String, String>> getAttribute() {
        return attributes.entrySet();
    }

    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public void clearAttributes() {
        attributes.clear();
    }

    public boolean isSingleObject() {
        return singleObject;
    }

    public void setSingleObject(boolean singleObject) {
        this.singleObject = singleObject;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        // value can set at once;
        this.value = this.value == null ? value : this.value;
    }

    public String getAncestor() {
        return ancestor;
    }

    public String toString() {
        StringBuilder text = new StringBuilder("Element:\n");
        text.append("path = ").append(getPath()).append("\n");

        if (singleObject) {
            text.append(String.format("value = %s\n", Objects.equals(value, "null") ? null : String.format("\"%s\"", value)));
        }
        if (attributes.size() > 0) {
            text.append("attributes:\n");
            attributes.entrySet().stream()
                    .map(e -> String.format("%s = \"%s\"\n", e.getKey(), Objects.equals(e.getValue(), "null") ? "" : e.getValue()))
                    .forEach(text::append);
        }
        return text.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return name.equals(element.name) && ancestor.equals(element.ancestor) && Objects.equals(attributes, element.attributes) && Objects.equals(value, element.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ancestor, attributes, value);
    }
}