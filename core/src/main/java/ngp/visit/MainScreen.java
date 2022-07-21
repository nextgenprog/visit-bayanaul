package ngp.visit;

import static ngp.visit.Style.BOXHEIGHT;
import static ngp.visit.Style.SPACING;

import com.badlogic.gdx.Gdx;
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

public class MainScreen extends NgpScreen {
    private TextButton aboutBn;
    private Button title;
    private Group mapGrp;

    public MainScreen(TourApp app) {
        super(app);
    }

    public void initUI() {
        title = new Button(new TextureRegionDrawable(new Texture("title.png")));
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
        Image map = new Image(new TextureRegionDrawable(new Texture("map.jpg")), Scaling.fill, Align.center);
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
                app.x+=xAct;
                app.y+=yAct;
            }
        };
        mapGrp.addListener(mapDrag);
        stage.addActor(mapGrp);
        mapGrp.setPosition(app.x, app.y);
        stage.addActor(title);
        stage.addActor(aboutBn);
        stage.addActor(languages);
        refreshText();
    }

    @Override
    public void resize(int width, int height) {
        int titleW = width-2* SPACING;
        int titleH = (int)(10*titleW/43f);
        title.setBounds(SPACING,height- SPACING-titleH, titleW, titleH);
        languages.setPosition(width-Style.dims.get(SPACING,0)-468, Style.dims.get(SPACING,0));
        aboutBn.setBounds(0.5f*(width-668-Style.dims.get(SPACING,0)), Style.dims.get(SPACING,0), 200,Style.dims.get(BOXHEIGHT,0));
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
