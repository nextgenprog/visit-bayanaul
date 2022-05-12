package ngp.visit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;

public class PlaceScreen extends NgpScreen {
    private final LocationData locData;
    private TextButton title, aboutBn;
    private final Group imageStrip = new Group();
    private final NgpActor backdrop;
    private NgpActor topBlock;
    private Button backBn, navBn;
    private final Array<Image> images;
    private Label text;
    private final boolean ready;
    private boolean scrollText;

    public PlaceScreen(TourApp app, LocationData locData){
        super(app);
        this.locData = locData;
        navBn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.net.openURI(locData.url);
                event.handle();
            }
        });
        backdrop = new NgpActor(Color.DARK_GRAY,8,8);
        imageStrip.addActor(backdrop);
        images = new Array<>();
        float width = 50;
        for (int i = 0; i < 19; i++){
            Image img = null;
            String test = locData.imageLoc.concat(Integer.toString(i+1).concat(".png"));
            try{img = new Image(new Texture(test));}catch(Exception e){
                test = locData.imageLoc.concat(Integer.toString(i+1).concat(".jpg"));
                try{img = new Image(new Texture(test));}catch(Exception f){
                    test = locData.imageLoc.concat(Integer.toString(i+1).concat(".jpeg"));
                    try{img = new Image(new Texture(test));
                    }catch(Exception ignored){}}}
            if (null!=img) {
                img.setSize(800,400);
                img.setScaling(Scaling.fill);
                images.add(img);
                imageStrip.addActor(images.get(i));
                images.get(i).setPosition(width,0);}
        }
        stage.addActor(imageStrip);
        DragListener mapDrag = new DragListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                float xAct = Gdx.input.getDeltaX(pointer);
                float xPos = imageStrip.getX()+xAct;
                xAct = (xPos > 0 || xPos < (Gdx.graphics.getWidth()-7072))? 0 : xAct;
                imageStrip.moveBy(xAct,0);
            }
        };
        imageStrip.addListener(mapDrag);
        ready = true;
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void refreshText(){
        title.setText(locData.name.get(app.language));
        text.setText(locData.contents.get(app.language));
        aboutBn.setText(Text.about_button.get(app.language));
        text.setBounds(0.1f*Gdx.graphics.getWidth(),0,0.8f*Gdx.graphics.getWidth(),Gdx.graphics.getHeight()-860);
    }

    public void initUI() {
        title = new TextButton("", Style.styleTextLarge);
        aboutBn = new TextButton(Text.about_button.get(app.language), Style.styleTextLarge);
        aboutBn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                app.setScreen(new AboutScreen(app));
            }
        });
        text = new Label("", Style.styleLabelLarge);
        text.setWrap(true);
        text.setAlignment(Align.topLeft);
        backBn = new Button(new TextureRegionDrawable(new Texture("images/ui/return.png")));
        backBn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(new MainScreen(app));
            }
        });
        navBn = new Button(new TextureRegionDrawable(new Texture("images/ui/gmap.png")));
        DragListener textDrag = new DragListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                scrollText = y < Gdx.graphics.getHeight()-800;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (scrollText) {
                    float yAct = Gdx.input.getDeltaY(pointer);
                    float yPos = text.getY() - yAct;
                    yAct = (yPos < 0 || yPos > (Gdx.graphics.getHeight() + 2400)) ? 0 : yAct;
                    text.moveBy(0, -yAct);
                }
            }
        };
        stage.addListener(textDrag);
        topBlock = new NgpActor(Style.back,Gdx.graphics.getWidth(), 800);
        topBlock.setPosition(0, Gdx.graphics.getHeight()-800);
        stage.addActor(text);
        stage.addActor(topBlock);
        stage.addActor(aboutBn);
        stage.addActor(title);
        stage.addActor(backBn);
        stage.addActor(navBn);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void resize(int width, int height) {
        title.setBounds(0.2f*width,height-160,0.6f*width,128);
        text.setBounds(0.1f*width,0,0.8f*width,height-860);
        backBn.setBounds(36,height-132,96,96);
        navBn.setBounds(width-132,height-132,96,96);
        if (ready) {
            imageStrip.setBounds(0, height - 800, width, 600);
            backdrop.setBounds(0, height - 892, width, 700);
            for (int i = 0; i < images.size; i++) {
                images.get(i).setPosition(i * 850 + 50, 0);
            }
        }
        languages.setPosition(448, 32);
        aboutBn.setBounds(64, 32, 320,148);
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
