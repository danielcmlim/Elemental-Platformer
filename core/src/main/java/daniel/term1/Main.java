package daniel.term1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;

public class Main implements ApplicationListener {

    public static final float PPM = 100f; // pixels per meter
    private SpriteBatch batch;
    private Music music;
    private LevelGenerator level;
    private Player player;
    private World world;

    @Override
    public void create() {
        batch = new SpriteBatch();
        world = new World(new Vector2(0, -10f), true);

        // Add collision listener for spike detection
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fa = contact.getFixtureA();
                Fixture fb = contact.getFixtureB();

                if (fa.getUserData() == null || fb.getUserData() == null)
                    return;

                // Check if player hit spike
                if ((fa.getUserData().equals("player") && fb.getUserData().equals("spike")) ||
                    (fa.getUserData().equals("spike") && fb.getUserData().equals("player"))) {
                    player.die();
                }
            }

            @Override
            public void endContact(Contact contact) {}

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {}

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {}
        });

        level = new LevelGenerator(world);
        float spawnX = 10 + 32;
        float spawnY = 140 + 32;
        player = new Player(world, spawnX, spawnY);

        music = Gdx.audio.newMusic(Gdx.files.internal("Audio/castle_music.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        world.step(delta, 6, 2); // Box2D physics step
        player.update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        world.dispose();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
}
