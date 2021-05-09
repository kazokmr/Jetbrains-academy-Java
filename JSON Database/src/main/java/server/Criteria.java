package server;

import com.google.gson.JsonObject;

public class Criteria {

    public final String type;
    public final String key;
    public final String value;

    public Criteria(JsonObject jsonObject) {
        this.type = jsonObject.get("type").getAsString();
        if (jsonObject.has("key")) {
            this.key = jsonObject.get("key").toString().replaceAll("[\\[\\]\"]", "");
        } else {
            this.key = null;
        }
        if (jsonObject.has("value")) {
            this.value = jsonObject.get("value").toString()
                    .replaceAll("^\"", "")
                    .replaceAll("\"$", "");
        } else {
            this.value = null;
        }
    }
}
