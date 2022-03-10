package ngp.visit;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Style {
    static BitmapFont font, fontLarge;
    static TextButton.TextButtonStyle styleText, styleTextLarge;
    static Button.ButtonStyle stylePin, stylePinHL;

    static void init(AssetManager assets){
        assets.load("font/font_1.fnt",BitmapFont.class);
        assets.load("font/font_1_64.fnt",BitmapFont.class);
        assets.load("T_UI_tenpatch.png",Texture.class);
        assets.load("pin.png",Texture.class);
        assets.load("pinHL.png",Texture.class);
        boolean complete = false;
        while (!complete) complete = assets.update();
        font = assets.get("font/font_1.fnt", BitmapFont.class);
        fontLarge = assets.get("font/font_1_64.fnt", BitmapFont.class);
        Texture tTenpatch = assets.get("T_UI_tenpatch.png",Texture.class);
        int w1 = 128;
        int h1 = 128;
        int w2 = 256;
        int h2 = 256;
        int w3 = 128;
        int h3 = 128;
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
        TextureRegionDrawable tPin = new TextureRegionDrawable(assets.get("pin.png",Texture.class));
        TextureRegionDrawable tPinHL = new TextureRegionDrawable(assets.get("pinHL.png",Texture.class));
        stylePin = new Button.ButtonStyle(tPin,tPin,tPin);
        stylePinHL = new Button.ButtonStyle(tPin,tPinHL,tPin);
        stylePinHL.over = tPinHL;
    }
}
