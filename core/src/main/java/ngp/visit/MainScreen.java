package ngp.visit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

public class MainScreen implements Screen {
    private final TourApp app;
    private final Stage stage;
    private Texture mapTex;
    private TextureRegion mapTexR;
    private TextureRegionDrawable mapTexD;
    private TextButton title;
    private Image map;
    private Label locLbl1, locLbl2;
    private Button locBn1, locBn2;
    private LocationData locData1, locData2;

    public MainScreen(TourApp app){
        this.app = app;
        stage = new Stage();
        locData1 = new LocationData("Zhassybay",new Texture("images/zhesybay.png"),"",new Vector2(300,600));
        locData2 = new LocationData("Sabyndikol",new Texture("images/zhesybay.png"),"",new Vector2(300,600));
        initUI();
        Gdx.input.setInputProcessor(stage);
    }

    private void initUI() {
        title = new TextButton("Visit Bayanaul!", Style.styleTextLarge);
        locBn1 = new Button(Style.stylePinHL);
        locBn2 = new Button(Style.stylePinHL);
        locLbl1 = new Label(locData1.name,Style.styleLabel);
        locLbl2 = new Label(locData2.name,Style.styleLabel);
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
        locBn2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(new PlaceScreen(app, locData2));
            }
        });
        locBn1.addListener(event -> false);
        mapTex = new Texture("images/map.PNG");
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
        stage.addActor(locLbl1);
        stage.addActor(locLbl2);
        resize(sW,sH);
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
        locLbl1.setVisible(stage.hit(scrC.x, scrC.y,false)==locBn1);
        locLbl2.setVisible(stage.hit(scrC.x, scrC.y,false)==locBn2);
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
