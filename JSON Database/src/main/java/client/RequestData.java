package client;

public class RequestData {
    private final String type;
    private final String key;
    private final String value;

    public RequestData(String type, String key, String value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }
}
