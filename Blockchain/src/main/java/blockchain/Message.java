package blockchain;

public class Message {
    private final String account;
    private final String text;

    public Message(String account, String text) {
        this.account = account;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getAccount() {
        return account;
    }
}
