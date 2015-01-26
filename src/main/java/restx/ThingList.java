package restx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmc on 1/24/15.
 */
public class ThingList implements ThingApiInterface {
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

    public JSONArray toJsonArray() {
        JSONArray thingsJson = new JSONArray();
        for (Thing thing : getThings()) {
            JSONObject thingJson = thing.toJsonObject();
            thingsJson.put(thingJson);
        }
        return thingsJson;
    }

    public JSONObject toJsonObject() {
        JSONObject thingsJson = new JSONObject();
        try {
            thingsJson.put("count", getThingCount());
            thingsJson.put("things", toJsonArray());
            return thingsJson;
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

}
