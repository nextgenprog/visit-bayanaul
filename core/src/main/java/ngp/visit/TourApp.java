package ngp.visit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

public class TourApp extends ApplicationAdapter {
	private NgpScreen screen, oldScreen;
	private AssetManager assets;
	public int language;
	public int x = -3300, y = -650;

	@Override
	public void create() {
		assets = new AssetManager();
		Style.init(assets, Gdx.graphics.getWidth());
		Text.init();
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
		if (newScreen==this.oldScreen) {
			NgpScreen tempScreen = oldScreen;
			oldScreen = screen;
			screen = tempScreen;
			return;
		}
		else if (null!=this.oldScreen) oldScreen.dispose();
		if (null!=this.screen) oldScreen = this.screen;
		screen = newScreen;
		screen.refreshText();
	}

    public void setLanguage(int s) {
		language = s;
		screen.refreshText();
    }
}