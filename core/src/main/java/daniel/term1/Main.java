package daniel.term1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main implements ApplicationListener {

    private SpriteBatch batch;
    private Music music;
    private LevelGenerator level;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Initialize level
        level = new LevelGenerator();

        // Music setup
        music = Gdx.audio.newMusic(Gdx.files.internal("castle_music.mp3"));
        music.setLooping(true);
        music.play();
    }

    @Override
    public void render() {
        batch.begin();
        level.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        level.dispose();
        music.dispose();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
}
