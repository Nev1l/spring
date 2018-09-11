package by.gomel.vkapachou.communication;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Администратор on 15.04.2017.
 */
public class ResponseJSON {
    @SerializedName("action")
    private String action;
    @SerializedName("result")
    private JsonObject result;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public JsonObject getResult() {
        return result;
    }

    public void setResult(JsonObject result) {
        this.result = result;
    }
}
