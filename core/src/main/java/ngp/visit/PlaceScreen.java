package ngp.visit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class PlaceScreen extends NgpScreen {
    private final TourApp app;
    private final Stage stage = new Stage();
    private final LocationData locData;
    private TextButton title, backBn, navBn;
    private final Group imageStrip = new Group();
    private Image backdrop;
    private final Array<Image> images;
    private Label text;

    public PlaceScreen(TourApp app, LocationData locData){
        this.app = app;
        this.locData = locData;
        images = new Array<>();
        FileHandle folder = new FileHandle(locData.imageLoc);
        FileHandle[] imageList = folder.list();
        for (int i = 0; i < imageList.length; i++){
            images.add(new Image(new Texture(imageList[i])));
            imageStrip.addActor(images.get(i));
            images.get(i).setPosition(i*800,0);
        }
        initUI();
        Gdx.input.setInputProcessor(stage);
    }

    public void refreshText(){
        title.setText(locData.names.get(app.language));
        text.setText(locData.contents.get(app.language));
    }

    public void initUI() {
        title = new TextButton(locData.names.get(app.language), Style.styleTextLarge);
        text = new Label(locData.contents.get(app.language), Style.styleLabelLarge);
        text.setWrap(true);
        backBn = new TextButton("Back", Style.styleTextLarge);
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
        Pixmap pix = new Pixmap(148,84, Pixmap.Format.RGB888);
        pix.setColor(Color.WHITE);
        backdrop = new Image(new Texture(pix));
        stage.addActor(backdrop);
        stage.addActor(title);
        stage.addActor(text);
        stage.addActor(imageStrip);
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
        title.setBounds(0.1f*width,height-164,0.8f*width,128);
        text.setBounds(0.1f*width,200,0.8f*width,128);
        backBn.setBounds(36,36,0.5f*width-54,128);
        navBn.setBounds(0.5f*width+18,36,0.5f*width-54,128);

        imageStrip.setBounds(0,height-800,width,600);
        backdrop.setBounds(0,height-800,width,600);
        for (int i = 0; i < images.size; i++){
            images.get(i).setPosition(i*800,0);
        }
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
