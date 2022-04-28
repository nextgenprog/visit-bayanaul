package ngp.visit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntMap;

public class LocationData implements Disposable {
    protected final String imageLoc;
    protected final String icon;
    protected final String url;
    protected final Vector2 coordinates;
    protected final IntMap<String> names;
    protected final IntMap<String> contents;
    protected final Array<Texture> pictures;

    public LocationData(String imageLoc, String icon, Vector2 coordinates, String url, IntMap<String> names, IntMap<String> contents){
        this.imageLoc = imageLoc;
        this.icon = icon;
        this.coordinates = coordinates;
        this.url = url;
        this.names = names;
        this.contents = contents;
        this.pictures = new Array<>();
        FileHandle[] files = Gdx.files.internal(imageLoc).list();
        for (FileHandle file : files) {
            pictures.add(new Texture(file));
        }
    }

    @Override
    public void dispose() {
        for (int i = 0; i < pictures.size; i++) pictures.get(i).dispose();
    }
}
