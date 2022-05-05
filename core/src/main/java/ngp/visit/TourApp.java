package ngp.visit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.IntMap;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class TourApp extends ApplicationAdapter {
	private NgpScreen screen, oldScreen;
	private AssetManager assets;
	public int language;
	public IntMap<TextureRegionDrawable> langBns = new IntMap<>();

	@Override
	public void create() {
		assets = new AssetManager();
		Style.init(assets);
		Text.init();
		langBns.put(Style.ENGLISH,new TextureRegionDrawable(new Texture("images/ui/en.png")));
		langBns.put(Style.KAZAKH,new TextureRegionDrawable(new Texture("images/ui/KZ.png")));
		langBns.put(Style.RUSSIAN,new TextureRegionDrawable(new Texture("images/ui/RU.png")));
		setScreen(new MainScreen(this));
	}

	@Override
	public void render() {
		screen.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		screen.dispose();
		assets.dispose();
	}

	public void setScreen(NgpScreen newScreen){
		if (newScreen!=this.oldScreen&&null!=this.oldScreen) oldScreen.dispose();
		if (null!=this.screen) oldScreen = this.screen;
		screen = newScreen;
		screen.refreshText();
	}

    public void setLanguage(int s) {
		language = s;
		screen.refreshText();
    }
}