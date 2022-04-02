package ngp.visit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PlaceScreen implements Screen {
    private final TourApp app;
    private final Stage stage;
    private final LocationData locData;
    private TextButton title, backBn, navBn;
    private Image banner;

    public PlaceScreen(TourApp app, LocationData locData){
        this.app = app;
        this.locData = locData;
        stage = new Stage();
        initUI();
        Gdx.input.setInputProcessor(stage);
    }


    private void initUI() {
        title = new TextButton(locData.name, Style.styleTextLarge);
        banner = new Image(locData.banner);
        backBn = new TextButton("Go back", Style.styleTextLarge);
        backBn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(new MainScreen(app));
            }
        });
        navBn = new TextButton("See on map", Style.styleTextLarge);
        navBn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.net.openURI(locData.url);
                event.handle();
            }
        });
        stage.addActor(title);
        stage.addActor(banner);
        stage.addActor(backBn);
        stage.addActor(navBn);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Style.back.r, Style.back.g, Style.back.b, Style.back.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        title.setBounds(0.1f*width,height-0.1f*width-128,0.8f*width,128);
        backBn.setBounds(0.1f*width,0.1f*width,0.8f*width,128);
        navBn.setBounds(0.1f*width,0.2f*width+128,0.8f*width,128);
        banner.setPosition(0.05f*width,height-640);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }
}
