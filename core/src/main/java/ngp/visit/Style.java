package ngp.visit;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Scaling;

import java.util.HashMap;

public class Style {
    static Texture mapTex, mapTex2;
    static Image map, map2;
    static TextureRegion breadcrumb, lasso;
    static BitmapFont font, fontL;
    static TextButton.TextButtonStyle styleText, styleTextLarge, styleTextHL, styleTextLargeHL;
    static Label.LabelStyle styleLabel, styleLabelLarge;
    static final int ENGLISH = 0;
    static final int KAZAKH = 1;
    static final int RUSSIAN = 2;
    static final HashMap<String, Integer> languages = new HashMap<String, Integer>(){{
        put("en", ENGLISH);
        put("kz", KAZAKH);
        put("ru", RUSSIAN);
    }};
    static final HashMap<String, Integer> icons = new HashMap<String, Integer>(){{
        put("swim", SWIMR);
        put("mount", MOUNT);
        put("beach", BEACH);
        put("forest", FORST);
        put("home", HOUSE);
    }};

    static final int MAP = 0;
    static final int MAP_S = 1;
    static final int MAP_L = 2;

    static final int LOW = 1;
    static final int MID = 2;
    static final int HI = 3;
    static final int XHI = 4;

    static final IntIntMap dims = new IntIntMap();
    static final int SPACE = 10;
    static final int BOX_H = 11;
    static final int WIDE_S = 12;
    static final int PHOTO_H = 13;
    static final int ICON = 14;
    static final int FLAG_H = 15;
    static final int TBOX_W = 16;
    static final int TITLE_W = 17;

    static final IntMap<Button.ButtonStyle> buttons = new IntMap<>();
    static final int SWIMR = 20;
    static final int MOUNT = 21;
    static final int BEACH = 22;
    static final int FORST = 23;
    static final int HOUSE = 24;
    static final int GMAP = 25;
    static final int NGP = 26;
    static final int WEB = 27;
    static final int GHUB = 28;
    static final int BACK = 29;
    static final int UNO = 30;
    static final int DOS = 31;
    static final int TRES = 32;
    static final int CUATRO = 33;

    // background colour
    static final Color back = new Color(0.85f,0.85f,0.85f,1f);

    static void init(AssetManager assets, int mapStyle, int resolution){
        /*switch (mapStyle){
            case MAP:
                mapTex = new Texture("images/map.jpg");
                mapTex.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
                map = new Image(new TextureRegionDrawable(mapTex), Scaling.fill, Align.center);
                break;
            case MAP_S:
                mapTex = new Texture("images/map_small.jpg");
                mapTex.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
                map = new Image(new TextureRegionDrawable(mapTex), Scaling.fill, Align.center);
                break;
            case MAP_L:
                mapTex = new Texture("images/map_big_1.jpg");
                mapTex.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
                map = new Image(new TextureRegionDrawable(mapTex), Scaling.fill, Align.center);
                mapTex2 = new Texture("images/map_big_2.jpg");
                mapTex2.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
                map2 = new Image(new TextureRegionDrawable(mapTex2), Scaling.fill, Align.center);
                break;
        }*/

        mapTex = new Texture("images/map_small.jpg");
        mapTex.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
        map = new Image(new TextureRegionDrawable(mapTex), Scaling.fill, Align.center);

        dims.put(SPACE, 12*resolution);
        dims.put(BOX_H, 48+12*resolution);
        dims.put(WIDE_S, 12+20*resolution);
        dims.put(PHOTO_H, 120+120*resolution);
        dims.put(ICON, 40+6*resolution);
        dims.put(FLAG_H, 24+10*resolution);
        dims.put(TBOX_W, 500+125*resolution);
        dims.put(TITLE_W, 400+200*resolution);

        String fontS = "font/fnt_1_"+(30+6*resolution)+".fnt";
        String fontL = "font/fnt_1_"+(42+6*resolution)+".fnt";
        String uiFile = "images/atlas.png";
        assets.load(uiFile,Texture.class);
        assets.load(fontS,BitmapFont.class);
        assets.load(fontL,BitmapFont.class);
        boolean complete = false;
        while (!complete) complete = assets.update();
        font = assets.get(fontS, BitmapFont.class);
        Style.fontL = assets.get(fontL, BitmapFont.class);

        TextureRegion ninePatch = new TextureRegion(assets.get(uiFile,Texture.class),256,0,128,128);
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

        TextureRegion ninePatchDark = new TextureRegion(assets.get(uiFile,Texture.class),256,128,128,128);
        int wD1 = 20, hD1 = 20, wD2 = 88, hD2 = 88, wD3 = 20, hD3 = 20;
        TextureRegion tD1 = new TextureRegion(ninePatchDark,0,0,wD1,hD1);
        TextureRegion tD2 = new TextureRegion(ninePatchDark,wD1,0,wD2,hD1);
        TextureRegion tD3 = new TextureRegion(ninePatchDark,wD1+wD2,0,wD3,hD1);
        TextureRegion tD4 = new TextureRegion(ninePatchDark,0,hD1,wD1,hD2);
        TextureRegion tD5 = new TextureRegion(ninePatchDark,wD1,hD1,wD2,hD2);
        TextureRegion tD6 = new TextureRegion(ninePatchDark,wD1+wD2,hD1,wD3,hD2);
        TextureRegion tD7 = new TextureRegion(ninePatchDark,0,hD1+hD2,wD1,hD3);
        TextureRegion tD8 = new TextureRegion(ninePatchDark,wD1,hD1+hD2,wD2,hD3);
        TextureRegion tD9 = new TextureRegion(ninePatchDark,wD1+wD2,hD1+hD2,wD3,hD3);
        Drawable buttonDrawableDark = new NinePatchDrawable(new NinePatch(tD1, tD2, tD3, tD4, tD5, tD6, tD7, tD8, tD9));

        styleText = new TextButton.TextButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable, Style.font);
        styleTextLarge = new TextButton.TextButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable, Style.fontL);
        styleTextHL = new TextButton.TextButtonStyle(buttonDrawable, buttonDrawableDark, buttonDrawableDark, Style.font);
        styleTextLargeHL = new TextButton.TextButtonStyle(buttonDrawable, buttonDrawableDark, buttonDrawableDark, Style.fontL);
        styleLabel = new Label.LabelStyle(font, new Color(0.15f, 0.18f, 0.25f,0.5f));
        styleLabelLarge = new Label.LabelStyle(Style.fontL, new Color(0.15f, 0.18f, 0.25f,0.5f));

        TextureRegionDrawable swimr = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),0,0,64,64));
        TextureRegionDrawable mount = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),64,0,64,64));
        TextureRegionDrawable beach = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),128,0,64,64));
        TextureRegionDrawable forst = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),192,0,64,64));
        TextureRegionDrawable house = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),0,64,64,64));
        TextureRegionDrawable ngp = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),0,128,128,128));
        TextureRegionDrawable gmap = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),0,256,128,128));
        TextureRegionDrawable web = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),0,384,128,128));
        TextureRegionDrawable ghub = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),128,256,128,128));
        TextureRegionDrawable back = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),256,256,128,128));
        TextureRegionDrawable en = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),384,0,128,64));
        TextureRegionDrawable kz = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),384,64,128,64));
        TextureRegionDrawable ru = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),384,128,128,64));
        TextureRegionDrawable uno = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),256,384,64,64));
        TextureRegionDrawable dos = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),320,384,64,64));
        TextureRegionDrawable tres = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),256,448,64,64));
        TextureRegionDrawable cuatro = new TextureRegionDrawable( new TextureRegion(assets.get(uiFile,Texture.class),320,448,64,64));

        buttons.put(SWIMR,new Button.ButtonStyle(swimr, swimr, swimr));
        buttons.put(MOUNT,new Button.ButtonStyle(mount, mount, mount));
        buttons.put(BEACH,new Button.ButtonStyle(beach, beach, beach));
        buttons.put(FORST,new Button.ButtonStyle(forst, forst, forst));
        buttons.put(HOUSE,new Button.ButtonStyle(house, house, house));
        buttons.put(NGP,new Button.ButtonStyle(ngp, ngp, ngp));
        buttons.put(GMAP,new Button.ButtonStyle(gmap, gmap, gmap));
        buttons.put(WEB,new Button.ButtonStyle(web, web, web));
        buttons.put(GHUB,new Button.ButtonStyle(ghub, ghub, ghub));
        buttons.put(BACK,new Button.ButtonStyle(back, back, back));
        buttons.put(ENGLISH,new Button.ButtonStyle(en, en, en));
        buttons.put(KAZAKH,new Button.ButtonStyle(kz, kz, kz));
        buttons.put(RUSSIAN,new Button.ButtonStyle(ru, ru, ru));
        buttons.put(UNO,new Button.ButtonStyle(uno, uno, uno));
        buttons.put(DOS,new Button.ButtonStyle(dos, dos, dos));
        buttons.put(TRES,new Button.ButtonStyle(tres, tres, tres));
        buttons.put(CUATRO,new Button.ButtonStyle(cuatro, cuatro, cuatro));

        breadcrumb = new TextureRegion(assets.get(uiFile,Texture.class),384,192,32,32);
        lasso = new TextureRegion(assets.get(uiFile,Texture.class),128,384,128,128);
    }
}
