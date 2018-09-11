package by.gomel.vkapachou.communication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Администратор on 15.04.2017.
 */
public class RequestJSON {
    @SerializedName("action")
    private String action;
    @SerializedName("value")
    private JsonObject value;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

   
    public JsonObject getValue() {
		return value;
	}

	public void setValue(JsonObject value) {
		this.value = value;
	}

	public static RequestJSON create(String message) {
        RequestJSON result = new Gson().fromJson(message, RequestJSON.class);
        return result;
    }
}
