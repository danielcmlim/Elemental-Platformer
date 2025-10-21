package daniel.term1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main implements ApplicationListener {

    private SpriteBatch batch;
    private Music music;
    private LevelGenerator level;
    private Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        level = new LevelGenerator();
        player = new Player();

        music = Gdx.audio.newMusic(Gdx.files.internal("Audio/castle_music.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        player.update(delta);

        batch.begin();
        level.render(batch);
        player.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        level.dispose();
        player.dispose();
        music.dispose();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
}
