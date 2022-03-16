package ngp.visit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PlaceScreen implements Screen {
    private final TourApp app;
    private final Stage stage;
    private final LocationData locData;
    private TextButton title, backBn;
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
		backBn = new TextButton(locData.name, Style.styleTextLarge);
		backBn.setText("back");
        stage.addActor(title);
        stage.addActor(banner);
        stage.addActor(backBn);
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
        title.setBounds(0.1f*width,height-0.5f*width,0.8f*width,0.4f*width);
        backBn.setBounds(0.1f*width,height-1.5f*width,0.8f*width,0.4f*width);
        banner.setPosition(0.05f*width,0.05f*width);
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
