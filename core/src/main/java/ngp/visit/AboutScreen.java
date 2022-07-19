package ngp.visit;

import static ngp.visit.Style.BOXHEIGHT;
import static ngp.visit.Style.PHOTOHEIGHT;
import static ngp.visit.Style.SPACING;
import static ngp.visit.Style.WIDESPACE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class AboutScreen extends NgpScreen {
    private TextButton title;
    private Button backBn, navBn, webBn;
    private Label text;
    private NgpActor topBlock;

    public AboutScreen(TourApp app) {
        super(app);
    }

    public void refreshText() {
        title.setText(Text.about_title.get(app.language));
        text.setText(Text.about_text.get(app.language));
        text.setBounds(0.1f * Gdx.graphics.getWidth(), 120, 0.8f * Gdx.graphics.getWidth(), 1400);
    }

    protected void initUI() {
        title = new TextButton(Text.about_title.get(app.language), Style.styleTextLarge);
        text = new Label(Text.about_text.get(app.language), Style.styleLabelLarge);
        text.setWrap(true);
        text.setAlignment(Align.topLeft);
        DragListener textDrag = new DragListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                float yAct = Gdx.input.getDeltaY(pointer);
                float yPos = text.getY()-yAct;
                yAct = (yPos < 0 || yPos > (Gdx.graphics.getHeight()+2400))? 0 : yAct;
                text.moveBy(0, -yAct);
            }
        };
        stage.addListener(textDrag);
        backBn = new Button(new TextureRegionDrawable(new Texture("images/ui/return.png")));
        backBn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(new MainScreen(app));
            }
        });
        navBn = new Button(new TextureRegionDrawable(new Texture("images/ui/ghub.png")));
        navBn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.net.openURI("https://github.com/nextgenprog/visit-bayanaul");
                event.handle();
            }
        });
        webBn = new Button(new TextureRegionDrawable(new Texture("images/ui/web.png")));
        webBn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.net.openURI("https://www.nextgenprog.org/");
                event.handle();
            }
        });
        stage.addActor(text);
        stage.addActor(title);
        stage.addActor(backBn);
        stage.addActor(navBn);
        stage.addActor(webBn);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void resize(int width, int height) {
        int wideSpace = Style.dims.get(WIDESPACE,0);
        int space = Style.dims.get(SPACING,0);
        int size = Style.dims.get(BOXHEIGHT,0);
        title.setBounds(wideSpace,height-space-size,width-2*wideSpace-space-size, size);
        backBn.setBounds(space,space,size,size);
        text.setBounds(wideSpace, wideSpace, width-wideSpace*2, height - space*2 -size -wideSpace);
        navBn.setBounds(width - (space+size), height - (space+size), size, size);
        webBn.setBounds(width - (space+size), height - 2*(space+size), size, size);
    }
}
