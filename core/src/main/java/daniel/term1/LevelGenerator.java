package daniel.term1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class LevelGenerator {

    private Texture[] tiles;
    private Texture floor_tile;
    private Texture door_closed, door_opened, barrel, box, air_chest_closed;
    private Texture chain_forward, chain_reverse, spikes, spikes_roof, spikes_right, spikes_left, death_sign;

    private int cols, rows;
    private int currentLevel = 1;

    private World world;

    public LevelGenerator(World world) {
        this.world = world;
        loadTextures();
        generateLevel1();
        createWorldBorders();
    }

    private void loadTextures() {
        tiles = new Texture[] {
            new Texture(Gdx.files.internal("Background_Tiles/background_tile1.png")),
            new Texture(Gdx.files.internal("Background_Tiles/background_tile2.png")),
            new Texture(Gdx.files.internal("Background_Tiles/background_tile3.png")),
            new Texture(Gdx.files.internal("Background_Tiles/background_tile4.png")),
            new Texture(Gdx.files.internal("Background_Tiles/background_tile5.png"))
        };

        floor_tile = new Texture(Gdx.files.internal("Background_Tiles/red_brick.png"));
        door_closed = new Texture(Gdx.files.internal("Objects/door_closed.png"));
        door_opened = new Texture(Gdx.files.internal("Objects/door_opened.png"));
        barrel = new Texture(Gdx.files.internal("Objects/barrel.png"));
        box = new Texture(Gdx.files.internal("Objects/box.png"));
        air_chest_closed = new Texture(Gdx.files.internal("Objects/air_chest_closed.png"));
        chain_reverse = new Texture(Gdx.files.internal("Objects/chain_reverse.png"));
        chain_forward = new Texture(Gdx.files.internal("Objects/chain_forward.png"));
        spikes = new Texture(Gdx.files.internal("Objects/spikes.png"));
        spikes_roof = new Texture(Gdx.files.internal("Objects/spikes_roof.png"));
        spikes_left = new Texture(Gdx.files.internal("Objects/spikes_left.png"));
        spikes_right = new Texture(Gdx.files.internal("Objects/spikes_right.png"));
        death_sign = new Texture(Gdx.files.internal("Objects/death_sign.png"));
    }

    public void render(SpriteBatch batch) {

        int tileSize = tiles[0].getWidth();
        cols = Gdx.graphics.getWidth() / tileSize + 2;
        rows = Gdx.graphics.getHeight() / tileSize + 1;

        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                batch.draw(tiles[(row + col) % tiles.length], col * tileSize, row * tileSize);

        if (currentLevel == 1)
            drawLevel1(batch);
    }

    private void generateLevel1() {
        currentLevel = 1;
    }

    private void drawLevel1(SpriteBatch batch) {
        int tileW = floor_tile.getWidth();
        int tileH = floor_tile.getHeight();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // ----- FLOOR -----
        for (int x = 0; x < screenWidth; x += tileW)
            batch.draw(floor_tile, x, 0);
        createMergedPlatform(0, 0, screenWidth, tileH);

        // ----- CEILING -----
        float ceilingY = screenHeight - tileH;
        for (int x = 0; x < screenWidth; x += tileW)
            batch.draw(floor_tile, x, ceilingY);
        createMergedPlatform(0, ceilingY, screenWidth, tileH);

        // ----- PLATFORMS -----
        drawMergedPlatform(batch, 0, 140, tileW * 8);
        drawMergedPlatform(batch, 0, 344, tileW * 8);

        drawMergedRamp(batch, 336, 344, 10, true);

        drawMergedPlatform(batch, 574, 200, tileW * 16);
        drawMergedPlatform(batch, 100, 494, tileW * 8);

        drawMergedRamp(batch, 436, 494, 10, true);

        drawMergedPlatform(batch, 674, 350, tileW * 16);
        drawMergedPlatform(batch, 674, 494, tileW * 3);
        drawMergedPlatform(batch, 950, 494, tileW * 3);
        drawMergedPlatform(batch, 1190, 494, tileW * 4);

        // ----- OBJECTS -----
        int door_scale = 2, barrel_scale = 2, box_scale = 4, chest_scale = 2, chain_scale = 2;

        batch.draw(door_closed, 10, 7,
            door_closed.getWidth() * door_scale,
            door_closed.getHeight() * door_scale);

        batch.draw(door_opened, 1224, 501,
            door_opened.getWidth() * door_scale,
            door_opened.getHeight() * door_scale);

        batch.draw(barrel, 450, 10,
            barrel.getWidth() * barrel_scale,
            barrel.getHeight() * barrel_scale);

        batch.draw(box, 1330, -15,
            box.getWidth() * box_scale,
            box.getHeight() * box_scale);

        batch.draw(air_chest_closed, -400, 92,
            air_chest_closed.getWidth() * chest_scale,
            air_chest_closed.getHeight() * chest_scale);

        // ----- CHAINS -----
        int[] chainXs = {627, 734, 904, 1011, 1144, 1298};
        for (int x : chainXs) {
            batch.draw(chain_reverse, x, 499,
                chain_reverse.getHeight() * chain_scale,
                chain_reverse.getHeight() * chain_scale);
            batch.draw(chain_forward, x + 2, 597,
                chain_forward.getHeight() * chain_scale,
                chain_forward.getHeight() * chain_scale);
        }

        // ----- SPIKES -----
        float s = 0.5f;

        // Platform spikes (bottom)
        for (int i = 0; i < 4; i++) {
            float spikeX = 676 + i * 200;
            float spikeY = 14;
            batch.draw(spikes, spikeX, spikeY, spikes.getWidth() * s, spikes.getHeight() * s);
            createSpikeBody(spikeX, spikeY, spikes.getWidth() * s, spikes.getHeight() * s);
        }

        // Platform spikes (roof)
        for (int i = 0; i < 4; i++) {
            float spikeX = 676 + i * 200;
            float spikeY = 168;
            batch.draw(spikes_roof, spikeX, spikeY, spikes.getWidth() * s, spikes.getHeight() * s);
            createSpikeBody(spikeX, spikeY, spikes.getWidth() * s, spikes.getHeight() * s);
        }

        // Pit spikes
        float pitX = 674;
        float pitY = 362;
        float spikeW = spikes.getWidth() * s;

        while (pitX < screenWidth) {
            batch.draw(spikes, pitX, pitY, spikeW, spikes.getHeight() * s);
            createSpikeBody(pitX, pitY, spikeW, spikes.getHeight() * s);
            pitX += spikeW;
        }

        batch.draw(death_sign, 715, 508, 60, 60);
        batch.draw(death_sign, 990, 508, 60, 60);
    }

    // ---- SPIKE BODY ----
    private void createSpikeBody(float x, float y, float width, float height) {
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;

        float centerX = x + width / 2f;
        float centerY = y + height / 2f;
        bd.position.set(centerX / Main.PPM, centerY / Main.PPM);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2f / Main.PPM, height / 2f / Main.PPM);

        Body body = world.createBody(bd);
        Fixture f = body.createFixture(shape, 0);
        f.setUserData("spike"); // Mark as spike for collision detection
        f.setSensor(true); // Make it a sensor so it doesn't push the player

        shape.dispose();
    }

    // ---- PLATFORM ----
    private void drawMergedPlatform(SpriteBatch batch, float x, float y, float width) {
        for (float px = x; px < x + width; px += floor_tile.getWidth())
            batch.draw(floor_tile, px, y);

        createMergedPlatform(x, y, width, floor_tile.getHeight());
    }

    private void createMergedPlatform(float x, float y, float width, float height) {

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;

        float centerX = x + width / 2f;
        float centerY = y + height / 2f;
        bd.position.set(centerX / Main.PPM, centerY / Main.PPM);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2f / Main.PPM, height / 2f / Main.PPM);

        Body body = world.createBody(bd);
        Fixture f = body.createFixture(shape, 1);
        f.setUserData("platform");

        shape.dispose();
    }

    // ---- RAMP ----
    private void drawMergedRamp(SpriteBatch batch, float startX, float startY, int steps, boolean ascending) {

        float hSpacing = floor_tile.getWidth() / 2f;
        float vSpacing = floor_tile.getHeight() / 3f;

        float endX = startX + steps * hSpacing;
        float endY = startY + (ascending ? -steps * vSpacing : steps * vSpacing);

        for (int i = 0; i < steps; i++) {
            float x = startX + i * hSpacing;
            float y = startY + (ascending ? -i * vSpacing : i * vSpacing);
            batch.draw(floor_tile, x, y);
        }

        createRampBody(startX, startY, endX, endY);
    }

    private void createRampBody(float x1, float y1, float x2, float y2) {

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(0, 0);

        Body body = world.createBody(bd);

        EdgeShape edge = new EdgeShape();
        edge.set(
            new Vector2(x1 / Main.PPM, y1 / Main.PPM),
            new Vector2(x2 / Main.PPM, y2 / Main.PPM)
        );

        Fixture f = body.createFixture(edge, 1);
        f.setUserData("platform");

        edge.dispose();
    }

    // ---- WORLD BORDERS ----
    private void createWorldBorders() {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float thickness = 50;

        // LEFT WALL
        BodyDef leftBd = new BodyDef();
        leftBd.type = BodyDef.BodyType.StaticBody;
        leftBd.position.set((-thickness / 2f) / Main.PPM, screenHeight / 2f / Main.PPM);

        PolygonShape leftShape = new PolygonShape();
        leftShape.setAsBox(thickness / 2f / Main.PPM, screenHeight / 2f / Main.PPM);

        world.createBody(leftBd).createFixture(leftShape, 0).setUserData("border");
        leftShape.dispose();

        // RIGHT WALL
        BodyDef rightBd = new BodyDef();
        rightBd.type = BodyDef.BodyType.StaticBody;
        rightBd.position.set((screenWidth + thickness / 2f) / Main.PPM, screenHeight / 2f / Main.PPM);

        PolygonShape rightShape = new PolygonShape();
        rightShape.setAsBox(thickness / 2f / Main.PPM, screenHeight / 2f / Main.PPM);

        world.createBody(rightBd).createFixture(rightShape, 0).setUserData("border");
        rightShape.dispose();
    }

    public void dispose() {
        for (Texture t : tiles) t.dispose();
        floor_tile.dispose();
        door_closed.dispose();
        door_opened.dispose();
        barrel.dispose();
        box.dispose();
        air_chest_closed.dispose();
        chain_forward.dispose();
        chain_reverse.dispose();
        spikes.dispose();
        spikes_roof.dispose();
        spikes_left.dispose();
        spikes_right.dispose();
        death_sign.dispose();
    }
}
