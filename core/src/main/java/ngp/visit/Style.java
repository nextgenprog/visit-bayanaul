package ngp.visit;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.IntMap;

import java.util.HashMap;

public class Style {
    static BitmapFont font, fontLarge;
    static TextButton.TextButtonStyle styleText, styleTextLarge;
    static Label.LabelStyle styleLabel, styleLabelLarge;
    static Button.ButtonStyle stylePin, stylePinHL;
    static final int ENGLISH = 0;
    static final int KAZAKH = 1;
    static final int RUSSIAN = 2;
    static final HashMap<String, Integer> languages = new HashMap<String, Integer>(){{
        put("en",ENGLISH);
        put("kz", KAZAKH);
        put("ru", RUSSIAN);
    }};
    // background colour
    static Color back = new Color(0.75f,0.75f,0.75f,1f);
    static Color strip = new Color(0f,0f,0f,1f);

    static void init(AssetManager assets){
        assets.load("font/font_1.fnt",BitmapFont.class);
        assets.load("font/font_1_64.fnt",BitmapFont.class);
        assets.load("images/T_UI_tenpatch.png",Texture.class);
        assets.load("images/pin.png",Texture.class);
        assets.load("images/pinHL.png",Texture.class);
        assets.load("images/pinOver.png",Texture.class);
        boolean complete = false;
        while (!complete) complete = assets.update();
        font = assets.get("font/font_1.fnt", BitmapFont.class);
        fontLarge = assets.get("font/font_1_64.fnt", BitmapFont.class);
        Texture tTenpatch = assets.get("images/T_UI_tenpatch.png",Texture.class);
        int w1 = 32;
        int h1 = 32;
        int w2 = 64;
        int h2 = 64;
        int w3 = 32;
        int h3 = 32;
        TextureRegion t1 = new TextureRegion(tTenpatch,0,0,w1,h1);
        TextureRegion t2 = new TextureRegion(tTenpatch,w1,0,w2,h1);
        TextureRegion t3 = new TextureRegion(tTenpatch,w1+w2,0,w3,h1);
        TextureRegion t4 = new TextureRegion(tTenpatch,0,h1,w1,h2);
        TextureRegion t5 = new TextureRegion(tTenpatch,w1,h1,w2,h2);
        TextureRegion t6 = new TextureRegion(tTenpatch,w1+w2,h1,w3,h2);
        TextureRegion t7 = new TextureRegion(tTenpatch,0,h1+h2,w1,h3);
        TextureRegion t8 = new TextureRegion(tTenpatch,w1,h1+h2,w2,h3);
        TextureRegion t9 = new TextureRegion(tTenpatch,w1+w2,h1+h2,w3,h3);
        Drawable buttonDrawable = new NinePatchDrawable(new NinePatch(t1, t2, t3, t4, t5, t6, t7, t8, t9));
        styleText = new TextButton.TextButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable, font);
        styleTextLarge = new TextButton.TextButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable, fontLarge);
        styleLabel = new Label.LabelStyle(font, new Color(0.15f, 0.18f, 0.25f,0.5f));
        styleLabelLarge = new Label.LabelStyle(fontLarge, new Color(0.15f, 0.18f, 0.25f,0.5f));
        TextureRegionDrawable tPin = new TextureRegionDrawable(assets.get("images/pin.png",Texture.class));
        TextureRegionDrawable tPinHL = new TextureRegionDrawable(assets.get("images/pinHL.png",Texture.class));
        TextureRegionDrawable tPinOver = new TextureRegionDrawable(assets.get("images/pinOver.png",Texture.class));
        stylePinHL = new Button.ButtonStyle(tPin,tPinHL,tPin);
        stylePinHL.over = tPinOver;
        stylePin = new Button.ButtonStyle(tPin,tPin,tPin);
    }
}
