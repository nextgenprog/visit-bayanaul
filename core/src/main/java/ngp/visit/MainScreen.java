package ngp.visit;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.XmlReader;

import java.util.HashMap;

public class MainScreen implements Screen {
    private final TourApp app;
    private final Stage stage;
    private TextButton title;
    private Image map;
    private final Vector2 mapDragInit = new Vector2();
    private final Vector2 del3 = new Vector2();
    private final Vector2 del2 = new Vector2();
    private final Vector2 del1 = new Vector2();
    private final Array<LocationData> locations = new Array<>();
    private final HashMap<LocationData, Button> locButtons = new HashMap<>();

    public MainScreen(TourApp app){
        this.app = app;
        stage = new Stage();
        initLocs();
        initUI();
        Gdx.input.setInputProcessor(stage);
    }

    private void initLocs() {
        XmlReader reader = new XmlReader();
        XmlReader.Element root = reader.parse(Gdx.files.getFileHandle("locations.xml", Files.FileType.Internal));
        for (int i = 0; i < root.getChildCount(); i++){
            XmlReader.Element child = root.getChild(i);
            if (child.getName().equalsIgnoreCase("location")) {
                String name = child.getAttribute("name");
                String url = child.getAttribute("mapUrl");
                String image = child.getAttribute("image");
                String content = child.getAttribute("content");
                float x = child.getFloatAttribute("x");
                float y = child.getFloatAttribute("y");
                LocationData locData = new LocationData(name, new Texture(image), content, new Vector2(x, y), url);
                locations.add(locData);
            }
        }
        LocationData locData2 = new LocationData("Sabyndikol",new Texture("images/zhesybay.png"),"",new Vector2(950,690), "");
    }

    private void initUI() {
        title = new TextButton("Visit Bayanaul!", Style.styleTextLarge);
        stage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });
        Texture mapTex = new Texture("images/map.jpg");
        int sW = Gdx.graphics.getWidth();
        int sH = Gdx.graphics.getHeight();
        TextureRegionDrawable mapTexD = new TextureRegionDrawable(mapTex);
        map = new Image(mapTexD, Scaling.fill, Align.center);
        DragListener mapDrag = new DragListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mapDragInit.x = x;
                mapDragInit.y = y;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                panMap(this.getDeltaX(), this.getDeltaY());
            }
        };
        map.addListener(mapDrag);
        stage.addActor(map);
        for (int i = 0; i < locations.size; i++){
            Button b = new Button(Style.stylePinHL);
            int finalI = i;
            b.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    app.setScreen(new PlaceScreen(app, locations.get(finalI)));
                }
            });
            locButtons.put(locations.get(finalI),b);
            stage.addActor(b);
        }
        stage.addActor(title);
        resize(sW,sH);
    }

    private void panMap(float x, float y){
        float deltax = 0.6f*x+0.55f*del3.x+0.4f*del2.x+0.25f*del1.x;
        float deltay = 0.6f*y+0.55f*del3.y+0.4f*del2.y+0.25f*del1.y;
        map.setPosition(map.getX()+deltax,map.getY()+deltay);
        for(int i = 0; i < locations.size; i++){
            LocationData l = locations.get(i);
            if (locButtons.containsKey(l)) locButtons.get(l).setPosition(locButtons.get(l).getX()+deltax, locButtons.get(l).getY()+deltay);
        }
        del1.x = del2.x; del1.y = del2.y;
        del2.x = del3.x; del2.y = del3.y;
        del3.x = x; del3.y = y;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Style.back.r, Style.back.g, Style.back.b, Style.back.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        Vector2 scrC = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(0),Gdx.input.getY(0)));
    }

    @Override
    public void resize(int width, int height) {
        title.setBounds(0.1f*width,height-0.3f*width,0.8f*width,0.2f*width);
        map.setPosition(-2300,-550);
        for(int i = 0; i < locations.size; i++){
            LocationData l = locations.get(i);
            if (locButtons.containsKey(l)) locButtons.get(l).setBounds(l.coordinates.x, l.coordinates.y, 64, 64);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
