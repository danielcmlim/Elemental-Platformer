package daniel.term1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main implements ApplicationListener {

    private Texture[] tiles;
    private Texture floor_tile, door_closed, door_opened, barrel, box, chest_closed;
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
        chest_closed = new Texture("chest_closed.png");

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

        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                batch.draw(tiles[tileMap[row][col]], col * tileSize, row * tileSize);

        for (int col = 0; col < cols; col++) {
            batch.draw(floor_tile, col * tileSize, 0);
            int topY = (rows - 1) * tileSize - 6;
            batch.draw(floor_tile, col * tileSize, topY);
        }

        // Floor 1
        int floor1_X = 0, floor1_Y = 140;
        for (int i = 0; i < 8; i++) batch.draw(floor_tile, floor1_X + i * tileSize, floor1_Y);

        //Floor 2
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
        for (int i = 0; i < ramp2a_steps; i++)
            batch.draw(floor_tile, ramp3a_startX + i * ramp3a_hSpacing, ramp3a_startY - i * ramp3a_vSpacing);

        int floor3b_X = 674, floor3b_Y = 350;
        for (int i = 0; i < 16; i++) batch.draw(floor_tile, floor3b_X + i * tileSize, floor3b_Y);

        // Floor 4
        int floor4a_X = 674, floor4a_Y = 494;
        for (int i = 0; i < 3; i++) batch.draw(floor_tile, floor4a_X + i * tileSize, floor4a_Y);

        int floor4b_X = 950, floor4b_Y = 494;
        for (int i = 0; i < 3; i++) batch.draw(floor_tile, floor4b_X + i * tileSize, floor4b_Y);

        int floor4c_X = 1226, floor4c_Y = 494;
        for (int i = 0; i < 3; i++) batch.draw(floor_tile, floor4c_X + i * tileSize, floor4c_Y);


        // Objects
        int door_scale = 2, barrel_scale = 2, box_scale = 4, chest_scale = 2;

        batch.draw(door_closed, 10, 7,
            door_closed.getWidth() * door_scale,
            door_closed.getHeight() * door_scale);

        batch.draw(door_opened, 1232, 501,
            door_opened.getWidth() * door_scale,
            door_opened.getHeight() * door_scale);

        batch.draw(barrel, 450, 10,
            barrel.getWidth() * barrel_scale,
            barrel.getHeight() * barrel_scale);

        batch.draw(box, 1330, -15,
            box.getWidth() * box_scale,
            box.getHeight() * box_scale);

//        int chestX = floor1_X + (4 * tileSize) - (chest_closed.getWidth() * chest_scale) / 2;
//        int chestY = floor1_Y + floor_tile.getHeight();
//        batch.draw(chest_closed, chestX, chestY,
//            chest_closed.getWidth() * chest_scale,
//            chest_closed.getHeight() * chest_scale);

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
//        chest_closed.dispose();
        music.dispose();
    }

    @Override public void resize(int width, int height) { }
    @Override public void pause() { }
    @Override public void resume() { }
}
