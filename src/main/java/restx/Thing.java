package restx;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by kmc on 1/24/15.
 */
public class Thing implements ThingApiInterface {

    public JSONObject toJsonObject() {
        JSONObject thingJson = new JSONObject();
        try {
            thingJson.put("id", getId().toString());
            thingJson.put("name", getName());
            thingJson.put("date", "<unknown>");
            return thingJson;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toJsonString() {
        JSONObject thingJson = toJsonObject();
        if (thingJson != null) {
            return thingJson.toString();
        } else return null;
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

    public Thing(String name, Date date) {
        this.id = nextId++;
        this.name = name;
        this.createDate = date;
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
