package ngp.visit;

import static ngp.visit.Style.BOXHEIGHT;
import static ngp.visit.Style.SPACING;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public abstract class NgpScreen implements Screen {
    protected final TourApp app;
    protected final Stage stage = new Stage();
    protected final Group languages = new Group();
    protected final Button en, kz, ru;
    protected final NgpActor selector;

    protected NgpScreen(TourApp app) {
        this.app = app;

        en = new Button(app.langBns.get(Style.ENGLISH));
        kz = new Button(app.langBns.get(Style.KAZAKH));
        ru = new Button(app.langBns.get(Style.RUSSIAN));

        selector = new NgpActor(Color.DARK_GRAY,148,84);

        en.addListener(new ClickListener(){@Override public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            if (app.language != Style.ENGLISH) {app.setLanguage(Style.ENGLISH); selector.setPosition(0,0);}
        }
        });
        kz.addListener(new ClickListener(){@Override public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            if (app.language != Style.KAZAKH) {app.setLanguage(Style.KAZAKH); selector.setPosition(160,0);}
        }
        });
        ru.addListener(new ClickListener(){@Override public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            if (app.language != Style.RUSSIAN) {app.setLanguage(Style.RUSSIAN); selector.setPosition(320,0);}
        }
        });
        languages.addActor(selector);
        selector.setPosition(app.language*160,0);
        languages.addActor(en);
        languages.addActor(kz);
        languages.addActor(ru);
        en.setPosition(10,10);kz.setPosition(170,10);ru.setPosition(330,10);
        languages.setPosition(Gdx.graphics.getWidth()-Style.dims.get(SPACING,0)-468, Style.dims.get(SPACING,0));
        initUI();
        stage.addActor(languages);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(stage);
    }

    protected abstract void initUI();

    public abstract void refreshText();

    @Override
    public void show() {

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

    }
}
