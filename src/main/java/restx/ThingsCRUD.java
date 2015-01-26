package restx;

import org.json.JSONObject;
import restx.ThingResponseStatus.ThingResponseStatusCode;

import java.util.Date;

/**
 * Created by kmc on 1/24/15.
 */
public class ThingsCRUD implements ThingApiInterface {
    @Override
    public String toJsonString() {
        return null;
    }

    @Override
    public JSONObject toJsonObject() {
        return null;
    }

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

    // CRUD methods

    public ThingResponseStatus doList() {
        return new ThingResponseStatus(ThingResponseStatusCode.OK);
    }

    public ThingResponseStatus doCreate() {
        return doCreate(new Thing());
    }

    public ThingResponseStatus doCreate(Thing thing) {
        getThings().addThing(thing);
        return new ThingResponseStatus(ThingResponseStatusCode.CREATED);
    }

    public ThingResponseStatus doRead(Integer id) {
        Thing thing = getThings().findThingById(id);
        if (thing != null) {
            return new ThingResponseStatus(ThingResponseStatusCode.OK);
        } else return new ThingResponseStatus(ThingResponseStatusCode.NOT_FOUND);
    }

    public ThingResponseStatus doUpdate(Integer id, String name, Date createDate) {
        Thing thing = getThings().findThingById(id);
        if (thing != null) {
            if (name != null) thing.setName(name);
            if (createDate != null) thing.setCreateDate(createDate);
            return new ThingResponseStatus(ThingResponseStatusCode.OK);
        } else return new ThingResponseStatus(ThingResponseStatusCode.NOT_FOUND);
    }

    public ThingResponseStatus doDelete(Integer id) {
        return new ThingResponseStatus(getThings().deleteThing(id));
    }

}
