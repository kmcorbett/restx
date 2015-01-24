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

    public Integer getThingCount() {
        return getThings().size();
    }


    public JSONArray toJsonArray() throws JSONException {
        JSONArray thingsJson = new JSONArray();
        for (Thing thing : getThings()) {
            JSONObject thingJson = thing.toJsonObject();
            thingsJson.put(thingJson);
        }
        return thingsJson;
    }

    public String toJsonString() throws JSONException {
        String jsonString = toJsonArray().toString();
        return jsonString;
    }

}
