package server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageServer {

    private static final int PORT = 32254;

    public void accept() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started!\n");
            ExecutorService executorService = Executors.newFixedThreadPool(4);
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                executorService.submit(() -> {
                    try (DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                         DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
                    ) {
                        Gson gson = new Gson();
                        String read = inputStream.readUTF();
                        JsonObject jsonObject = gson.fromJson(read, JsonObject.class);
                        Criteria criteria = new Criteria(jsonObject);
                        String response = new Query().query(criteria);
                        outputStream.writeUTF(response);
                        if ("exit".equals(criteria.type)) {
                            serverSocket.close();
                        }
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        executorService.shutdownNow();
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
