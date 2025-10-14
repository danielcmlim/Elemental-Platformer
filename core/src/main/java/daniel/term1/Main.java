package daniel.term1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main implements ApplicationListener {

    private Texture[] tiles;
    private Texture floor_tile, door_closed, door_opened, barrel, box, air_chest_closed, chain_forward, chain_reverse, spikes, death_sign;
    private SpriteBatch batch;
    private Music music;

    private int[][] tileMap;
    private int cols, rows;

    @Override
    public void create() {
        batch = new SpriteBatch();

        tiles = new Texture[] {
            new Texture("background_tile1.png"),
            new Texture("background_tile2.png"),
            new Texture("background_tile3.png"),
            new Texture("background_tile4.png"),
            new Texture("background_tile5.png")
        };

        floor_tile = new Texture("red_brick.png");

        door_closed = new Texture("door_closed.png");
        door_opened = new Texture("door_opened.png");

        barrel = new Texture("barrel.png");
        box = new Texture("box.png");
        air_chest_closed = new Texture("air_chest_closed.png");

        chain_reverse = new Texture("chain_reverse.png");
        chain_forward = new Texture("chain_forward.png");

        spikes = new Texture("spikes.png");
        death_sign = new Texture("death_sign.png");

        int tileSize = tiles[0].getWidth();
        cols = Gdx.graphics.getWidth() / tileSize + 1;
        rows = Gdx.graphics.getHeight() / tileSize + 1;

        tileMap = new int[rows][cols];
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                tileMap[row][col] = (int)(Math.random() * tiles.length);

        music = Gdx.audio.newMusic(Gdx.files.internal("castle_music.mp3"));
        music.setLooping(true);
        music.play();
    }

    @Override
    public void render() {
        batch.begin();
        int tileSize = tiles[0].getWidth();

        // Draw background
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                batch.draw(tiles[tileMap[row][col]], col * tileSize, row * tileSize);

        // Draw floors
        for (int col = 0; col < cols; col++) {
            batch.draw(floor_tile, col * tileSize, 0);
            int topY = (rows - 1) * tileSize - 6;
            batch.draw(floor_tile, col * tileSize, topY);
        }


        // Floor 1
        int floor1_X = 0, floor1_Y = 140;
        for (int i = 0; i < 8; i++) batch.draw(floor_tile, floor1_X + i * tileSize, floor1_Y);

        // Floor 2
        int floor2a_X = 0, floor2a_Y = 344;
        for (int i = 0; i < 8; i++) batch.draw(floor_tile, floor2a_X + i * tileSize, floor2a_Y);

        int ramp2a_steps = 10;
        int ramp2a_startX = 336, ramp2a_startY = 344;
        float ramp2a_vSpacing = floor_tile.getHeight() / 3f;
        float ramp2a_hSpacing = floor_tile.getWidth() / 2f;
        for (int i = 0; i < ramp2a_steps; i++)
            batch.draw(floor_tile, ramp2a_startX + i * ramp2a_hSpacing, ramp2a_startY - i * ramp2a_vSpacing);

        int floor2b_X = 574, floor2b_Y = 200;
        for (int i = 0; i < 16; i++) batch.draw(floor_tile, floor2b_X + i * tileSize, floor2b_Y);

        // Floor 3
        int floor3a_X = 100, floor3a_Y = 494;
        for (int i = 0; i < 8; i++) batch.draw(floor_tile, floor3a_X + i * tileSize, floor3a_Y);

        int ramp3a_steps = 10;
        int ramp3a_startX = 436, ramp3a_startY = 494;
        float ramp3a_vSpacing = floor_tile.getHeight() / 3f;
        float ramp3a_hSpacing = floor_tile.getWidth() / 2f;
        for (int i = 0; i < ramp3a_steps; i++)
            batch.draw(floor_tile, ramp3a_startX + i * ramp3a_hSpacing, ramp3a_startY - i * ramp3a_vSpacing);

        int floor3b_X = 674, floor3b_Y = 350;
        for (int i = 0; i < 16; i++) batch.draw(floor_tile, floor3b_X + i * tileSize, floor3b_Y);

        // Floor 4
        int floor4a_X = 674, floor4a_Y = 494;
        for (int i = 0; i < 3; i++) batch.draw(floor_tile, floor4a_X + i * tileSize, floor4a_Y);
        int floor4b_X = 950, floor4b_Y = 494;
        for (int i = 0; i < 3; i++) batch.draw(floor_tile, floor4b_X + i * tileSize, floor4b_Y);
        int floor4c_X = 1190, floor4c_Y = 494;
        for (int i = 0; i < 4; i++) batch.draw(floor_tile, floor4c_X + i * tileSize, floor4c_Y);

        // Objects
        int door_scale = 2, barrel_scale = 2, box_scale = 4, chest_scale = 2, chain_scale = 2;

        //------------------------------DOORS--------------------------
        batch.draw(door_closed, 10, 7, door_closed.getWidth() * door_scale, door_closed.getHeight() * door_scale);
        batch.draw(door_opened, 1224, 501, door_opened.getWidth() * door_scale, door_opened.getHeight() * door_scale);

        batch.draw(barrel, 450, 10, barrel.getWidth() * barrel_scale, barrel.getHeight() * barrel_scale);
        batch.draw(box, 1330, -15, box.getWidth() * box_scale, box.getHeight() * box_scale);
        batch.draw(air_chest_closed,-400,92,air_chest_closed.getWidth() * chest_scale, air_chest_closed.getHeight() * chest_scale);

        // ---------------------CHAINS-------------------------------
        batch.draw(chain_reverse, 627, 499, chain_reverse.getHeight()*chain_scale, chain_reverse.getHeight() * chain_scale);
        batch.draw(chain_forward, 629, 597, chain_forward.getHeight()*chain_scale, chain_forward.getHeight() * chain_scale);

        batch.draw(chain_reverse, 734, 499, chain_reverse.getHeight()*chain_scale, chain_reverse.getHeight() * chain_scale);
        batch.draw(chain_forward, 736, 597, chain_forward.getHeight()*chain_scale, chain_forward.getHeight() * chain_scale);

        batch.draw(chain_reverse, 904, 499, chain_reverse.getHeight()*chain_scale, chain_reverse.getHeight() * chain_scale);
        batch.draw(chain_forward, 906, 597, chain_forward.getHeight()*chain_scale, chain_forward.getHeight() * chain_scale);

        batch.draw(chain_reverse, 1011, 499, chain_reverse.getHeight()*chain_scale, chain_reverse.getHeight() * chain_scale);
        batch.draw(chain_forward, 1013, 597, chain_forward.getHeight()*chain_scale, chain_forward.getHeight() * chain_scale);

        batch.draw(chain_reverse, 1144, 499, chain_reverse.getHeight()*chain_scale, chain_reverse.getHeight() * chain_scale);
        batch.draw(chain_forward, 1146, 597, chain_forward.getHeight()*chain_scale, chain_forward.getHeight() * chain_scale);

        batch.draw(chain_reverse, 1298, 499, chain_reverse.getHeight()*chain_scale, chain_reverse.getHeight() * chain_scale);
        batch.draw(chain_forward, 1301, 597, chain_forward.getHeight()*chain_scale, chain_forward.getHeight() * chain_scale);

        //--------------------------------SPIKES-------------------------------------------
        float spikes_scale = 0.5f;

        //Floor Spikes
        int startX = 676;
        int y = 14;
        int spacing = 200;
        int spikeCount = 4;

        for (int i = 0; i < spikeCount; i++) {
            batch.draw(spikes, startX + i * spacing, y, spikes.getWidth() * spikes_scale, spikes.getHeight() * spikes_scale);
        }

        //Spiek Pit
        float spike_pitX = 674;
        float spike_pitY = 362;
        float spikeWidth = spikes.getWidth() * spikes_scale;

        while (spike_pitX < Gdx.graphics.getWidth()) {
            batch.draw(spikes, spike_pitX, spike_pitY, spikeWidth, spikes.getHeight() * spikes_scale);
            spike_pitX += spikeWidth;
        }


        //---------------------------SIGNS---------------------
        batch.draw(death_sign, 715, 508, 60, 60);
        batch.draw(death_sign, 990, 508, 60, 60);




        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        for (Texture t : tiles) t.dispose();
        floor_tile.dispose();
        door_closed.dispose();
        door_opened.dispose();
        barrel.dispose();
        box.dispose();
        chain_forward.dispose();
        chain_reverse.dispose();
        spikes.dispose();
        death_sign.dispose();
//        chest_closed.dispose();
        music.dispose();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
}
