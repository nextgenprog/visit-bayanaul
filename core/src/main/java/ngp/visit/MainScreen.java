package ngp.visit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainScreen implements Screen {
    private final TourApp app;
    private final Stage stage;
    private Texture mapTex;
    private TextureRegion mapTexR;
    private TextureRegionDrawable mapTexD;
    private TextButton title;
    private Image map;
    private Button locBn1, locBn2;
    private LocationData locData1, locData2;

    public MainScreen(TourApp app){
        this.app = app;
        stage = new Stage();
        locData1 = new LocationData("Zhassybay",new Texture("zhesybay.png"),"",new Vector2(300,600));
        initUI();
        Gdx.input.setInputProcessor(stage);
    }

    private void initUI() {
        title = new TextButton("Visit Bayanaul!", Style.styleTextLarge);
        locBn1 = new Button(Style.stylePinHL);
        locBn2 = new Button(Style.stylePin);
        stage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });
        locBn1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(new PlaceScreen(app, locData1));
            }
        });
        mapTex = new Texture("map.PNG");
        int w = mapTex.getWidth();
        int h = mapTex.getHeight();
        int sW = Gdx.graphics.getWidth();
        int sH = Gdx.graphics.getHeight();
        int aW = (int) (0.9f*sW);
        int aH = (int) (sH-0.65f*sW);
        int x0 = (int) ((w-aW)/2f);
        int y0 = (int) ((h-aH)/2f);
        mapTexR = new TextureRegion(mapTex,x0,y0,aW,aH);
        mapTexD = new TextureRegionDrawable(mapTexR);
        map = new Image(mapTexD, Scaling.fill, Align.center);
        stage.addActor(title);
        stage.addActor(map);
        stage.addActor(locBn1);
        stage.addActor(locBn2);
        resize(sW,sH);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.35f, 0.35f, 0.35f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        title.setBounds(0.1f*width,height-0.5f*width,0.8f*width,0.4f*width);
        map.setPosition(0.05f*width,0.05f*width);
        locBn1.setBounds(0.2f*width,0.4f*height,64,64);
        locBn2.setBounds(0.7f*width,0.2f*height,64,64);
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
