package ngp.visit;

import static ngp.visit.Style.ENGLISH;
import static ngp.visit.Style.ICON;
import static ngp.visit.Style.KAZAKH;
import static ngp.visit.Style.RUSSIAN;
import static ngp.visit.Style.SPACE;

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
    protected final NgpActor langSelector;

    protected NgpScreen(TourApp app) {
        this.app = app;

        int flagH = Style.dims.get(ICON,0);
        int space = Style.dims.get(SPACE,0);
        int selec = space/4;

        en = new Button(Style.buttons.get(ENGLISH));
        kz = new Button(Style.buttons.get(KAZAKH));
        ru = new Button(Style.buttons.get(RUSSIAN));
        langSelector = new NgpActor(Color.DARK_GRAY,2*(flagH+selec),2*selec+flagH);
        languages.addActor(langSelector);
        languages.addActor(en);
        languages.addActor(kz);
        languages.addActor(ru);
        en.setBounds(0,0,2*flagH,flagH);
        kz.setBounds(2*flagH+space,0,2*flagH,flagH);
        ru.setBounds(4*flagH+2*space,0,2*flagH,flagH);

        en.addListener(new ClickListener(){
            @Override public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y); setLanguage(ENGLISH);
        }
        });
        kz.addListener(new ClickListener(){
            @Override public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y); setLanguage(KAZAKH);
        }
        });
        ru.addListener(new ClickListener(){
            @Override public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y); setLanguage(RUSSIAN);
        }
        });
        langSelector.setPosition(app.language*(2*flagH+space)-selec,-selec);
        languages.setPosition(Gdx.graphics.getWidth()-6*flagH-3*space-selec, space + selec);
        initUI();
        stage.addActor(languages);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(stage);
    }

    protected void setLanguage(int langCode){
        if (app.language != langCode) {
            int flagH = Style.dims.get(ICON,0);
            int space = Style.dims.get(SPACE,0);
            int selec = space/4;
            app.setLanguage(langCode);
            langSelector.setPosition(langCode*(2*flagH+space)-selec,-selec);
        }
    }

    protected abstract void initUI();

    public abstract void refreshText();

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(Style.back.r, Style.back.g, Style.back.b, Style.back.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {stage.dispose();}
}
