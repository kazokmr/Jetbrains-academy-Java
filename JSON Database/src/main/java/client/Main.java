package client;

import com.beust.jcommander.JCommander;

public class Main {

    public static void main(String[] args) {
        Arguments arguments = new Arguments();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);
        new MessageSender().send(arguments);
    }
}
