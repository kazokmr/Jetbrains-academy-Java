package blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChatData {
    private final List<Message> chatData;

    public ChatData() {
        chatData = new ArrayList<>();
    }

    public void addMessage(String account, String text) {
        chatData.add(new Message(account, text));
    }

    public synchronized String getMessage() {
        if (chatData.isEmpty()) {
            return "no messages";
        }
        String message = chatData.stream()
                .map(m -> String.format("\n%s : %s", m.getAccount(), m.getText()))
                .collect(Collectors.joining());
        chatData.clear();
        return message;
    }
}
