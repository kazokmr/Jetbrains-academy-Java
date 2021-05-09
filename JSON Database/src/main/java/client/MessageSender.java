package client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.Socket;

public class MessageSender {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 32254;

    public void send(Arguments args) {
        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream inputStream = new DataInputStream(socket.getInputStream())
        ) {
            System.out.println("Client started!");
            String reqParams;
            if (args.getFilename() != null) {
                reqParams = String.format("\n%s", new GsonBuilder()
//                        .setPrettyPrinting()
                        .create()
                        .toJson(JsonParser.parseString(getParamsByFile(args.getFilename()))));

            } else {
                reqParams = new Gson().toJson(new RequestData(args.getType(), args.getKey(), args.getValue()));
            }
            System.out.println();
            outputStream.writeUTF(reqParams);
            System.out.printf("Sent: %s\n", reqParams);
            System.out.printf("Received: %s\n\n", inputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String getParamsByFile(String fileName) throws IOException {
        File file = new File("src/client/data/" + fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
