package ngp.visit;

import static ngp.visit.Style.BACK;
import static ngp.visit.Style.BOX_H;
import static ngp.visit.Style.GMAP;
import static ngp.visit.Style.PHOTO_H;
import static ngp.visit.Style.SPACE;
import static ngp.visit.Style.TITLE_W;
import static ngp.visit.Style.WIDE_S;

import com.badlogic.gdx.Gdx;
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
    private TextButton title;
    private final Group imageStrip = new Group();
    private Button backBn, navBn;
    private Label text;
    private boolean scrollText;
    private int width;

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
        PadActor background = new PadActor();
        imageStrip.addActor(background);
        Array<Image> images = new Array<>();
        int photoH = Style.dims.get(PHOTO_H,0);
        int wideSpace = Style.dims.get(SPACE,0);
        width += wideSpace;
        for (int x = 1; x < 100; x++){
            Image img = null;
            String num = locData.imageLoc.concat(Integer.toString(x)).concat(".");
            String test = num+"jpg";
            try{img = new Image(new Texture(test));}
            catch(Exception e){
                test = num+"png";
                try{img = new Image(new Texture(test));}
                catch(Exception f){
                    test = num+"jpeg";
                    try{img = new Image(new Texture(test));}
                    catch(Exception g){
                        test = num+"JPG";
                        try{img = new Image(new Texture(test));}
                        catch(Exception h){
                            test = num+"PNG";
                            try{img = new Image(new Texture(test));}
                            catch(Exception i){
                                test = num+"JPEG";
                                try{img = new Image(new Texture(test));}
                                catch(Exception ignored){}}}}}}

            if (null!=img) {
                int width = (int) (img.getWidth()*photoH/img.getHeight());
                img.setSize(width, photoH);
                img.setScaling(Scaling.fill);
                images.add(img);
                imageStrip.addActor(images.get(x-1));
                images.get(x-1).setPosition(this.width,0);
                this.width += width+wideSpace;
            }
        }
        DragListener mapDrag = new DragListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                float xAct = Gdx.input.getDeltaX(pointer);
                float xPos = imageStrip.getX()+xAct;
                xAct = (xPos > 0 || xPos < (Gdx.graphics.getWidth()-width))? 0 : xAct;
                imageStrip.moveBy(xAct,0);
            }
        };
        imageStrip.addListener(mapDrag);
        stage.addActor(imageStrip);
        stage.addActor(title);
        stage.addActor(backBn);
        stage.addActor(navBn);
        background.setBounds(0,0,width,photoH + 4*wideSpace);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        refreshText();
        imageStrip.setPosition(0, Gdx.graphics.getHeight() - 2*Style.dims.get(SPACE,0)-Style.dims.get(BOX_H,0)- Style.dims.get(PHOTO_H,0));stage.addActor(text);

    }

    public void refreshText(){
        title.setText(locData.name.get(app.language));
        text.setText(locData.contents.get(app.language));
    }

    public void initUI() {
        title = new TextButton("", Style.styleTextLarge);
        text = new Label("", Style.styleLabel);
        text.setWrap(true);
        text.setAlignment(Align.topLeft);
        backBn = new Button(Style.buttons.get(BACK));
        backBn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(new MainScreen(app));
            }
        });
        navBn = new Button(Style.buttons.get(GMAP));
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
                    yAct = (yPos < 0 || yPos > (Gdx.graphics.getHeight() - Style.dims.get(TITLE_W,0))) ? 0 : yAct;
                    text.moveBy(0, -yAct);
                }
            }
        };
        stage.addListener(textDrag);
        stage.addActor(text);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void resize(int width, int height) {
        int wideSpace = Style.dims.get(WIDE_S,0);
        int space = Style.dims.get(SPACE,0);
        int size = Style.dims.get(BOX_H,0);
        int photoHeight = Style.dims.get(PHOTO_H,0);
        title.setBounds(wideSpace,height-space-size,width-2*wideSpace-space-size, size);
        backBn.setBounds(space,space,size,size);
        text.setBounds(wideSpace,0,width-2*wideSpace,height-photoHeight-space*3-size);
        navBn.setBounds(width-space-size,height-space-size,size,size);
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
