package restx;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by kmc on 1/24/15.
 */
public class ThingsCRUD {
    public enum ThingsCRUDop {
        LIST, CREATE, READ, UPDATE, DELETE;
    }

    public ThingList getThings() {
        return this.things;
    }

    protected ThingList things;

    public ThingsCRUD() {
        this.things = new ThingList();
    }

    public ThingsCRUD(ThingList things) {
        this.things = things;
    }

    public JSONObject doList() throws JSONException {
        return getThings().toJsonObject();
    }

    public JSONObject doCreate(Thing thing) throws JSONException {
        getThings().addThing(thing);
        return makeStatusJSON(true);
    }

    public JSONObject doRead(Integer id) throws JSONException {
        Thing thing = getThings().findThingById(id);
        if (thing != null) {
            return mergeStatusJSON(thing, true);
        } else return makeStatusJSON(false);
    }

    public JSONObject doUpdate(Integer id, String name, Date createDate) throws JSONException {
        Thing thing = getThings().findThingById(id);
        if (thing != null) {
            if (name != null) thing.setName(name);
            if (createDate != null) thing.setCreateDate(createDate);
            return mergeStatusJSON(thing, true);
        } else return makeStatusJSON(false);
    }

    public JSONObject doDelete(Integer id) throws JSONException {
        return makeStatusJSON(getThings().deleteThing(id));
    }

    // JSON representation success or failure status of CRUD operation

    protected static String successFlagValue = "0";
    protected static String failureFlagValue = "-1";

    protected JSONObject mergeStatusJSON(JSONObject jsonObj, Boolean successFlag) throws JSONException {
        jsonObj.put("status", successFlag ? successFlagValue : failureFlagValue);
        return jsonObj;
    }

    protected JSONObject mergeStatusJSON(Thing thing, Boolean successFlag) throws JSONException {
        JSONObject status = thing.toJsonObject();
        return mergeStatusJSON(status, successFlag);
    }

    protected JSONObject makeStatusJSON(Boolean successFlag) throws JSONException {
        JSONObject status = new JSONObject();
        status.put("status", successFlag ? successFlagValue : failureFlagValue);
        return status;
    }

    protected JSONObject makeStatusJSON(Boolean successFlag, Thing thing) throws JSONException {
        JSONObject statusJSON = makeStatusJSON(successFlag);
        statusJSON.put("thing", thing.toJsonString());
        return statusJSON;
    }
}
