package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Response {
    private static final Gson gson = new GsonBuilder()
//            .setPrettyPrinting()
            .create();
    private final String response;
    private final String value;
    private final String reason;

    public Response(String response, String value, String reason) {
        this.response = response;
        this.value = value;
        this.reason = reason;
    }

    public static String isStatusOK() {
        Response response = new Response("OK", null, null);
        return gson.toJson(response);
    }

    public static String isPutTheValue(String value) {
        Response response = new Response("OK", value, null);
        return gson.toJson(response);
    }

    public static String isPutTheObject(JsonObject jsonObject) {
        JsonObject resJsonObject = new JsonObject();
        resJsonObject.addProperty("response", "OK");
        resJsonObject.add("value", jsonObject);
        return gson.toJson(resJsonObject);
    }

    public static String hasNoSuchKey() {
        Response response = new Response("ERROR", null, "No such key");
        return gson.toJson(response);
    }

    public static String isRuntimeError() {
        Response response = new Response("ERROR", null, "Runtime error!");
        return gson.toJson(response);
    }
}
