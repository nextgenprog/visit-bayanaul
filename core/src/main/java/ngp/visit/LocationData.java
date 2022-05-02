package ngp.visit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntMap;

public class LocationData {
    protected final String imageLoc;
    protected final String icon;
    protected final String url;
    protected final Vector2 coordinates;
    protected final IntMap<String> names;
    protected final IntMap<String> contents;

    public LocationData(String imageLoc, String icon, Vector2 coordinates, String url, IntMap<String> names, IntMap<String> contents){
        this.imageLoc = imageLoc;
        this.icon = icon;
        this.coordinates = coordinates;
        this.url = url;
        this.names = names;
        this.contents = contents;
    }
}
