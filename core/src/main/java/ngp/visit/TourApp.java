package ngp.visit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class TourApp extends ApplicationAdapter {
	private NgpScreen screen, oldScreen;
	private AssetManager assets;
	public int language;

	@Override
	public void create() {
		assets = new AssetManager();
		Style.init(assets);
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
	}

    public void setLanguage(int s) {
		language = s;
		screen.refreshText();
    }
}