package ngp.visit;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class LocationData {
    protected String name;
    protected Texture banner;
    protected String content;
    protected Vector2 coordinates;

    public LocationData(String name, Texture banner, String content, Vector2 coordinates){
        this.name = name;
        this.banner = banner;
        this.content = content;
        this.coordinates = coordinates;
    }
}
