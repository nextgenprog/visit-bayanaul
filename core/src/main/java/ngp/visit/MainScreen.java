package ngp.visit;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static ngp.visit.Style.BOX_H;
import static ngp.visit.Style.DOS;
import static ngp.visit.Style.FLAG_H;
import static ngp.visit.Style.GHUB;
import static ngp.visit.Style.GMAP;
import static ngp.visit.Style.ICON;
import static ngp.visit.Style.MAP;
import static ngp.visit.Style.MAP_L;
import static ngp.visit.Style.MAP_S;
import static ngp.visit.Style.NGP;
import static ngp.visit.Style.SPACE;
import static ngp.visit.Style.TBOX_W;
import static ngp.visit.Style.TITLE_W;
import static ngp.visit.Style.TRES;
import static ngp.visit.Style.UNO;
import static ngp.visit.Style.map2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainScreen extends NgpScreen {
    private TextButton aboutBn, selectBn;
    private Group mapGrp, locBns;
    private Button title;
    private float cooldown = 1.5f;
    private float travelTime = 0f;
    private float transitionTime = 0f;
    private final Vector2 travelPath = new Vector2();
    private final Vector2 travelSpeed = new Vector2();
    private float distanceTraveled = 0f;
    private LocationData nextLoc;
    private float scale;
    private int mapW, mapH;

    protected Group mapStyles;
    protected Button s, m, l;
    protected NgpActor mapSelector;

    public MainScreen(TourApp app) {
        super(app);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (cooldown>0) {cooldown-=delta;
            aboutBn.getLabel().setColor(aboutBn.getStyle().font.getColor().r,aboutBn.getStyle().font.getColor().g, aboutBn.getStyle().font.getColor().b,2*(0.5f-cooldown));
            aboutBn.setColor(aboutBn.getColor().r,aboutBn.getColor().g, aboutBn.getColor().b,2*(0.5f-cooldown));
        }
        if (transitionTime>0){
            transitionTime-=delta;
            if (!(transitionTime>0)) app.setScreen(new PlaceScreen(app, nextLoc));
        }
        if (!aboutBn.isVisible()) aboutBn.setVisible(!(cooldown>0.5f));
        if (travelTime>0) {
            if (travelPath.len()>travelSpeed.len()) {
                if (travelSpeed.len() < 18) travelSpeed.scl(1.01f);
            }
            else {
                travelSpeed.x = travelPath.x; travelSpeed.y = travelPath.y;
                travelTime = 0;
                transitionTime = 1.0f;
                Image target = new Image(Style.lasso);
                target.setSize(Style.dims.get(ICON, 0) * 2F, Style.dims.get(ICON, 0) * 2F);
                mapGrp.addActor(target);
                target.setPosition(nextLoc.coordinates.x*scale-0.5f*Style.dims.get(ICON, 0),nextLoc.coordinates.y*scale-0.5f*Style.dims.get(ICON, 0));
            }
            float xAct = travelSpeed.x;
            float yAct = travelSpeed.y;
            mapGrp.moveBy(xAct,yAct);
            distanceTraveled+=travelSpeed.len();
            app.x+=xAct;
            app.y+=yAct;
            travelPath.x = 0.5f*Gdx.graphics.getWidth()-nextLoc.coordinates.x*scale - mapGrp.getX();
            travelPath.y = 0.5f*Gdx.graphics.getHeight()-nextLoc.coordinates.y*scale - mapGrp.getY();
            travelTime-=delta;
            if (distanceTraveled>50){
                distanceTraveled = 0;
                Image trek = new Image(new TextureRegionDrawable(Style.breadcrumb));
                trek.setSize(Style.dims.get(ICON, 0) * 0.5F, Style.dims.get(ICON, 0) * 0.5F);
                trek.setRotation(new RandomXS128().nextInt(89));
                mapGrp.addActor(trek);
                trek.setPosition(-mapGrp.getX() + 0.5f * Gdx.graphics.getWidth(), -mapGrp.getY() + 0.5f * Gdx.graphics.getHeight());
            }
        }
    }

    public void initUI() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        scale = (app.mapStyle==MAP_S)?(3839f)/(7072f):1;
        mapW = (app.mapStyle==MAP_S)?3839:7072;
        mapH = (app.mapStyle==MAP_S)?2048:3772;
        setUpMapSelector();
        title = new Button(new TextureRegionDrawable(new Texture("images/title.png")));
        aboutBn = new TextButton(Text.about_button.get(app.language), Style.styleTextLargeHL);
        aboutBn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                app.setScreen(new AboutScreen(app));
            }
        });
        aboutBn.setVisible(false);
        selectBn = new TextButton(Text.select_button.get(app.language), Style.styleTextLargeHL);
        selectBn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                showDropdown();
            }
        });
        stage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });
        mapGrp = new Group();
        if (app.mapStyle==MAP_L){
            mapGrp.addActor(Style.map2);
            map2.setPosition(2976,0);
        }
        mapGrp.addActor(Style.map);
        locBns = new Group();
        for (int i = 0; i < Text.locations.size; i++){
            final LocationData loc = Text.locations.get(i);
            Button b = new Button(Style.buttons.get(Style.icons.get(loc.icon)));
            b.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    app.setScreen(new PlaceScreen(app, loc));
                }
            });
            mapGrp.addActor(b);
            b.setBounds(loc.coordinates.x*scale,loc.coordinates.y*scale,Style.dims.get(ICON,0),Style.dims.get(ICON,0));
            TextButton dBn = new TextButton(loc.name.get(app.language), Style.styleTextHL);
            dBn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    locBns.setVisible(false);
                    selectBn.setVisible(false);
                    nextLoc = loc;
                    travelTime = 8f;
                    travelPath.x = 0.5f*Gdx.graphics.getWidth()-nextLoc.coordinates.x*scale - mapGrp.getX();
                    travelPath.y = 0.5f*Gdx.graphics.getHeight()-nextLoc.coordinates.y*scale - mapGrp.getY();
                    travelSpeed.x = 0.0025f*travelPath.x; travelSpeed.y = 0.0025f*travelPath.y;
                }
            });
            locBns.addActor(dBn);
            dBn.setBounds(Style.dims.get(SPACE,0),-(1+i)*Style.dims.get(BOX_H,0),Style.dims.get(TBOX_W,0)-2*Style.dims.get(SPACE,0),Style.dims.get(BOX_H,0));
        }
        DragListener mapDrag = new DragListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (travelTime+transitionTime>0) return;
                float xAct = Gdx.input.getDeltaX(pointer);
                float yAct = -Gdx.input.getDeltaY(pointer);
                float xPos = mapGrp.getX()+xAct;
                float yPos = mapGrp.getY()+yAct;
                xAct = (xPos > 0 || xPos < (Gdx.graphics.getWidth()-mapW))? 0 : xAct;
                yAct = (yPos > 0 || yPos < (Gdx.graphics.getHeight()-mapH))? 0 : yAct;
                mapGrp.moveBy(xAct,yAct);
                app.x+=xAct/scale;
                app.y+=yAct/scale;
            }
        };
        mapGrp.addListener(mapDrag);
        locBns.setVisible(false);
        stage.addActor(mapGrp);
        mapGrp.setPosition(min(0,max((app.x)*scale+0.5f*w,Gdx.graphics.getWidth()-mapW)), min(0,max((app.y)*scale+0.5f*h,Gdx.graphics.getHeight()-mapH)));
        stage.addActor(title);
        stage.addActor(aboutBn);
        stage.addActor(selectBn);
        stage.addActor(languages);
        stage.addActor(mapStyles);
        stage.addActor(locBns);
        refreshText();
    }

    private void setUpMapSelector() {
        int flagH = Style.dims.get(ICON,0);
        int space = Style.dims.get(SPACE,0);
        int selec = space/4;

        m = new Button(Style.buttons.get(UNO));
        s = new Button(Style.buttons.get(DOS));
        l = new Button(Style.buttons.get(TRES));
        mapStyles = new Group();
        mapSelector = new NgpActor(Color.DARK_GRAY,2*selec+flagH,2*selec+flagH);
        mapStyles.addActor(mapSelector);
        mapStyles.addActor(m);
        mapStyles.addActor(s);
        mapStyles.addActor(l);
        m.setBounds(0,0,flagH,flagH);
        s.setBounds(flagH+space,0,flagH,flagH);
        l.setBounds(2*(flagH+space),0,flagH,flagH);

        m.addListener(new ClickListener(){
            @Override public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y); setMapStyle(MAP);
            }
        });
        s.addListener(new ClickListener(){
            @Override public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y); setMapStyle(MAP_S);
            }
        });
        l.addListener(new ClickListener(){
            @Override public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y); setMapStyle(MAP_L);
            }
        });
        mapSelector.setPosition(app.mapStyle*(flagH+space)-selec,-selec);
        mapStyles.setPosition(Gdx.graphics.getWidth()-3*flagH-3*space-selec, flagH+2*space + 3*selec);
    }

    protected void setMapStyle(int mapStyle){
        if (app.mapStyle != mapStyle) {
            int flagH = Style.dims.get(ICON,0);
            int space = Style.dims.get(SPACE,0);
            int selec = space/4;
            app.setMapStyle(mapStyle);
            mapSelector.setPosition(mapStyle*(flagH+space)-selec,-selec);
        }
    }

    private void showDropdown() {
        locBns.setVisible(!locBns.isVisible());
    }

    @Override
    public void resize(int width, int height) {
        int space = Style.dims.get(SPACE,0);
        int boxh = Style.dims.get(BOX_H,0);
        int tboxw = Style.dims.get(TBOX_W,0);
        int titleW = Style.dims.get(TITLE_W,0);
        int titleH = (int)(10*titleW/43f);
        title.setBounds(0.5f*(width-titleW),height - titleH, titleW, titleH);
        locBns.setPosition(0.5f*(width - tboxw),height - titleH - boxh);
        aboutBn.setBounds(space, space, width-6*space-6*Style.dims.get(FLAG_H,0), boxh);
        selectBn.setBounds(0.5f*(width - tboxw),height - titleH - boxh, tboxw, boxh);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void refreshText() {
        aboutBn.setText(Text.about_button.get(app.language));
        selectBn.setText(Text.select_button.get(app.language));
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
