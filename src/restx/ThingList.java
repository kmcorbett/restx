package restx;

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
}
