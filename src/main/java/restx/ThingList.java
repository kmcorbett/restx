package restx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmc on 1/24/15.
 */
public class ThingList {
    private List<Thing> getThings() {
        return things;
    }

    List<Thing> things;

    public ThingList() {
        this.things = new ArrayList<Thing>();
    }

    public void addThing(Thing thing) {
        things.add(thing);
    }

    public Boolean deleteThing(Integer id) {
        Thing thing = findThingById(id);
        if (thing != null) {
            getThings().remove(thing);
            return true;
        } else return false;
    }

    public Integer getThingCount() {
        return getThings().size();
    }

    public Thing findThingById(Integer id) {
        for (Thing thing : getThings()) {
            if (thing.getId().equals(id))
                return thing;
        }
        return null;
    }

    public JSONArray toJsonArray() throws JSONException {
        JSONArray thingsJson = new JSONArray();
        for (Thing thing : getThings()) {
            JSONObject thingJson = thing.toJsonObject();
            thingsJson.put(thingJson);
        }
        return thingsJson;
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject thingsJson = new JSONObject();
        thingsJson.put("count", getThingCount());
        thingsJson.put("things", toJsonArray());
        return thingsJson;
    }

    public String toJsonString() throws JSONException {
        String jsonString = toJsonObject().toString();
        return jsonString;
    }

}
