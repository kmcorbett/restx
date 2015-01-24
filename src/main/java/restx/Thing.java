package restx;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by kmc on 1/24/15.
 */
public class Thing {

    public JSONObject toJsonObject() throws JSONException {
        JSONObject thingJson = new JSONObject();
        thingJson.put("id", getId().toString());
        thingJson.put("name", getName());
        thingJson.put("date", "<unknown>");
        return thingJson;
    }
    public String toJsonString() throws JSONException {
        return toJsonObject().toString();
    }

    private static Integer nextId = 0;
    Integer id;
    String name;
    Date createDate;

    public Thing() {
        this.id = nextId++;
        this.name = "<unknown>";
        this.createDate = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
