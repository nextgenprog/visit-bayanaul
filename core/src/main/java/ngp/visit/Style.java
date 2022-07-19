package ngp.visit;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.IntIntMap;

import java.util.HashMap;

public class Style {
    static BitmapFont font, fontLarge;
    static TextButton.TextButtonStyle styleText, styleTextLarge;
    static Label.LabelStyle styleLabel, styleLabelLarge;
    static int resolution = 0;
    static final int ENGLISH = 0;
    static final int KAZAKH = 1;
    static final int RUSSIAN = 2;
    static final HashMap<String, Integer> languages = new HashMap<String, Integer>(){{
        put("en", ENGLISH);
        put("kz", KAZAKH);
        put("ru", RUSSIAN);
    }};
    static final int LOWR = 1;
    static final int MIDR = 2;
    static final int HIGR = 3;
    static final int XHIR = 4;
    static final IntIntMap dims = new IntIntMap();
    static final int SPACING = 10;
    static final int BOXHEIGHT = 11;
    static final int WIDESPACE = 12;
    static final int PHOTOHEIGHT = 13;

    // background colour
    static Color back = new Color(0.75f,0.75f,0.75f,1f);

    static void init(AssetManager assets, int pixelWidth){
        resolution = (pixelWidth>1200)? XHIR: (pixelWidth>1000)? HIGR: (pixelWidth>800)? MIDR : LOWR;

        dims.put(SPACING, 12*resolution);
        dims.put(BOXHEIGHT, 48+12*resolution);
        dims.put(WIDESPACE, 12+20*resolution);
        dims.put(PHOTOHEIGHT, 100+100*resolution);

        String fontS = "font/font_1_"+(30+6*resolution)+".fnt";
        String fontL = "font/font_1_"+(50+10*resolution)+".fnt";
        assets.load(fontS,BitmapFont.class);
        assets.load(fontL,BitmapFont.class);
        assets.load("images/ui/tenpatch.png",Texture.class);
        boolean complete = false;
        while (!complete) complete = assets.update();
        font = assets.get(fontS, BitmapFont.class);
        fontLarge = assets.get(fontS, BitmapFont.class);

        Texture ninePatch = assets.get("images/ui/tenpatch.png",Texture.class);
        int w1 = 20, h1 = 20, w2 = 88, h2 = 88, w3 = 20, h3 = 20;
        TextureRegion t1 = new TextureRegion(ninePatch,0,0,w1,h1);
        TextureRegion t2 = new TextureRegion(ninePatch,w1,0,w2,h1);
        TextureRegion t3 = new TextureRegion(ninePatch,w1+w2,0,w3,h1);
        TextureRegion t4 = new TextureRegion(ninePatch,0,h1,w1,h2);
        TextureRegion t5 = new TextureRegion(ninePatch,w1,h1,w2,h2);
        TextureRegion t6 = new TextureRegion(ninePatch,w1+w2,h1,w3,h2);
        TextureRegion t7 = new TextureRegion(ninePatch,0,h1+h2,w1,h3);
        TextureRegion t8 = new TextureRegion(ninePatch,w1,h1+h2,w2,h3);
        TextureRegion t9 = new TextureRegion(ninePatch,w1+w2,h1+h2,w3,h3);
        Drawable buttonDrawable = new NinePatchDrawable(new NinePatch(t1, t2, t3, t4, t5, t6, t7, t8, t9));

        styleText = new TextButton.TextButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable, font);
        styleTextLarge = new TextButton.TextButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable, fontLarge);
        styleLabel = new Label.LabelStyle(font, new Color(0.15f, 0.18f, 0.25f,0.5f));
        styleLabelLarge = new Label.LabelStyle(fontLarge, new Color(0.15f, 0.18f, 0.25f,0.5f));
    }
}
