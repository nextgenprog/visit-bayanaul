package ngp.visit;

import static ngp.visit.Style.HI;
import static ngp.visit.Style.LOW;
import static ngp.visit.Style.MAP_S;
import static ngp.visit.Style.MID;
import static ngp.visit.Style.XHI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;

public class TourApp extends ApplicationAdapter {
	private NgpScreen screen, oldScreen;
	private AssetManager assets;
	public Preferences prefs;
	public int language;
	public int resolution;
	public int mapStyle;
	public int x = -4100, y = -1800;

	@Override
	public void create() {
		assets = new AssetManager();
		int pixelWidth = Gdx.graphics.getWidth();
		resolution = (pixelWidth>1200)? XHI : (pixelWidth>1000)? HI : (pixelWidth>800)? MID : LOW;
		try{
			prefs = Gdx.app.getPreferences("prefs");
			language = prefs.getInteger("language",Style.ENGLISH);
			mapStyle = MAP_S;//prefs.getInteger("mapStyle",Style.MAP);
		}
		catch (Exception ignored) {
			language = Style.ENGLISH;
			mapStyle = Style.MAP;
		}
		setLanguage(language);
		Style.init(assets, mapStyle, resolution);
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
		prefs.putInteger("language",s);
		prefs.flush();
		if (null!=screen) screen.refreshText();
	}

	public void setMapStyle(int mapStyle) {
		this.mapStyle = mapStyle;
		prefs.putInteger("mapStyle",mapStyle);
		prefs.flush();
		Style.init(assets, mapStyle, resolution);
		setScreen(new MainScreen(this));
	}
}