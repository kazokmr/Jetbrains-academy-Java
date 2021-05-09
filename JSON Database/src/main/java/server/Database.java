package server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {

    private static final Database instance = new Database();
    private final JsonElement json;
    private final File file = new File("src/server/data/db.json");

    private Database() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String text = reader.readLine();
            if (text != null) {
                this.json = JsonParser.parseString(text);
            } else {
                this.json = new JsonObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Database getInstance() {
        return instance;
    }

    public String set(String[] keys, String value) {
        if (keys.length == 1) {
            if (value.contains(":")) {
                json.getAsJsonObject().add(keys[0], JsonParser.parseString(value));
            } else {
                json.getAsJsonObject().addProperty(keys[0], value);
            }
            writeDb();
            return Response.isStatusOK();
        }
        JsonObject object = json.getAsJsonObject();
        for (int i = 0; i < keys.length - 1; i++) {
            object = (JsonObject) object.get(keys[i]);
        }
        if (value.contains(":")) {
            object.add(keys[keys.length - 1], JsonParser.parseString(value));
        } else {
            object.addProperty(keys[keys.length - 1], value);
        }
        writeDb();
        return Response.isStatusOK();
    }

    public String get(String[] keys) {
        JsonObject object = json.getAsJsonObject();
        for (String key : keys) {
            if (!object.has(key)) {
                return Response.hasNoSuchKey();
            }
            JsonElement element = object.get(key);
            if (element.isJsonPrimitive()) {
                return Response.isPutTheValue(element.getAsString());
            }
            object = element.getAsJsonObject();
        }
        if (object.isJsonObject()) {
            return Response.isPutTheObject(object);
        }
        return Response.isPutTheValue(object.getAsString());
    }

    public String delete(String[] keys) {
        JsonObject object = json.getAsJsonObject();
        for (int i = 0; i < keys.length - 1; i++) {
            if (!object.has(keys[i])) {
                return Response.hasNoSuchKey();
            }
            object = (JsonObject) object.get(keys[i]);
        }
        if (object.has(keys[keys.length - 1])) {
            object.remove(keys[keys.length - 1]);
            return Response.isStatusOK();
        }
        return Response.hasNoSuchKey();
    }

    private void writeDb() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            ReadWriteLock lock = new ReentrantReadWriteLock();
            Lock writeLock = lock.writeLock();
            writeLock.lock();
            new Gson().toJson(json, writer);
            writeLock.unlock();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
