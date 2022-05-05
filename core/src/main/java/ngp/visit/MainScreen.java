package ngp.visit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;

import java.util.HashMap;

public class MainScreen extends NgpScreen {
    private TextButton title, aboutBn;
    private Group mapGrp;

    public MainScreen(TourApp app) {
        super(app);
    }

    public void initUI() {
        title = new TextButton(Text.main_title.get(app.language), Style.styleTextLarge);
        aboutBn = new TextButton(Text.about_button.get(app.language), Style.styleTextLarge);
        aboutBn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                app.setScreen(new AboutScreen(app));
            }
        });
        stage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });
        Image map = new Image(new TextureRegionDrawable(new Texture("map.png")), Scaling.fill, Align.center);
        mapGrp = new Group();
        mapGrp.addActor(map);
        for (int i = 0; i < Text.locations.size; i++){
            Button b = new Button(new TextureRegionDrawable(new Texture("images/ui/"+Text.locations.get(i).icon+".png")));
            int finalI = i;
            b.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    app.setScreen(new PlaceScreen(app, Text.locations.get(finalI)));
                }
            });
            mapGrp.addActor(b);
            b.setPosition(Text.locations.get(i).coordinates.x,Text.locations.get(i).coordinates.y);
        }
        DragListener mapDrag = new DragListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                float xAct = Gdx.input.getDeltaX(pointer);
                float yAct = -Gdx.input.getDeltaY(pointer);
                float xPos = mapGrp.getX()+xAct;
                float yPos = mapGrp.getY()+yAct;
                xAct = (xPos > 0 || xPos < (Gdx.graphics.getWidth()-7072))? 0 : xAct;
                yAct = (yPos > 0 || yPos < (Gdx.graphics.getHeight()-3772))? 0 : yAct;
                mapGrp.moveBy(xAct,yAct);
            }
        };
        mapGrp.addListener(mapDrag);
        stage.addActor(mapGrp);
        mapGrp.setPosition(-3300, -650);
        stage.addActor(title);
        stage.addActor(aboutBn);
    }

    @Override
    public void resize(int width, int height) {
        title.setBounds(0.2f*width,height-160,0.6f*width,128);
        languages.setPosition(448, 32);
        aboutBn.setBounds(64, 32, 320,148);
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
        title.setText(Text.main_title.get(app.language));
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
