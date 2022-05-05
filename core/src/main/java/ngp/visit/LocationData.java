package ngp.visit;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntMap;

public class LocationData {
    protected final String imageLoc;
    protected final String icon;
    protected final String url;
    protected final Vector2 coordinates;
    protected final IntMap<String> name;
    protected final IntMap<String> contents;

    public LocationData(String imageLoc, String icon, Vector2 coordinates, String url, IntMap<String> names, IntMap<String> contents){
        this.imageLoc = imageLoc;
        this.icon = icon;
        this.coordinates = coordinates;
        this.url = url;
        this.name = names;
        this.contents = contents;
    }
}
