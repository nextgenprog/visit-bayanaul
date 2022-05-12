package ngp.visit;

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
        topBlock = new NgpActor(Style.back,Gdx.graphics.getWidth(), 240);
        topBlock.setPosition(0, Gdx.graphics.getHeight()-240);
        stage.addActor(text);
        stage.addActor(topBlock);
        stage.addActor(title);
        stage.addActor(backBn);
        stage.addActor(navBn);
        stage.addActor(webBn);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void resize(int width, int height) {
        title.setBounds(0.2f * width, height - 160, 0.6f * width, 128);
        text.setBounds(0.1f * width, 120, 0.8f * width, 1400);
        backBn.setBounds(36, height - 132, 96, 96);
        navBn.setBounds(width - 132, height - 132, 96, 96);
        webBn.setBounds(width - 132, height - 264, 96, 96);
        languages.setPosition(448, 32);
    }
}
