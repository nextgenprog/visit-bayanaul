package ngp.visit;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public class LocationData implements Disposable {
    protected String name;
    protected Texture banner;
    protected Texture icon;
    protected String content;
    protected Vector2 coordinates;
    protected String url;

    public LocationData(String name, Texture banner, Texture icon, String content, Vector2 coordinates, String url){
        this.name = name;
        this.banner = banner;
        this.icon = icon;
        this.content = content;
        this.coordinates = coordinates;
        this.url = url;
    }

    @Override
    public void dispose() {
        banner.dispose();
        icon.dispose();
    }
}
