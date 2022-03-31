package ngp.visit;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class LocationData {
    protected String name;
    protected Texture banner;
    protected String content;
    protected Vector2 coordinates;
    protected String url;

    public LocationData(String name, Texture banner, String content, Vector2 coordinates, String url){
        this.name = name;
        this.banner = banner;
        this.content = content;
        this.coordinates = coordinates;
        this.url = url;
    }
}
