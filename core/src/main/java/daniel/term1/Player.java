package daniel.term1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private Texture idleSheet, runSheet, jumpSheet;
    private Animation<TextureRegion> idleAnim, runAnim, jumpAnim;

    private float stateTime;
    private String currentState = "IDLE";

    private Vector2 position;
    private Vector2 velocity;

    private float moveSpeed = 200f;
    private float jumpPower = 550f;
    private float gravity = -1000f;
    private boolean isOnGround = false;
    private boolean isJumping = false;

    private float scale = 1.5f;
    private boolean facingRight = true;

    public Player() {
        loadAnimations();
        position = new Vector2(100, 150);
        velocity = new Vector2(0, 0);
        stateTime = 0f;
    }

    private void loadAnimations() {
        idleSheet = new Texture(Gdx.files.internal("Player/Idle.png"));
        runSheet = new Texture(Gdx.files.internal("Player/Run.png"));
        jumpSheet = new Texture(Gdx.files.internal("Player/Jump.png"));

        idleAnim = createAnimation(idleSheet, 5, 0.15f);
        runAnim = createAnimation(runSheet, 8, 0.08f);
        jumpAnim = createAnimation(jumpSheet, 3, 0.12f);
    }

    private Animation<TextureRegion> createAnimation(Texture sheet, int frameCount, float frameDuration) {
        int frameWidth = sheet.getWidth() / frameCount;
        int frameHeight = sheet.getHeight();
        TextureRegion[][] tmp = TextureRegion.split(sheet, frameWidth, frameHeight);
        TextureRegion[] frames = new TextureRegion[frameCount];
        for (int i = 0; i < frameCount; i++)
            frames[i] = tmp[0][i];
        return new Animation<>(frameDuration, frames);
    }

    public void update(float delta) {
        handleInput();
        velocity.y += gravity * delta;
        position.mulAdd(velocity, delta);

        if (position.y <= 140) {
            position.y = 140;
            velocity.y = 0;
            isOnGround = true;
            isJumping = false;
        }

        if (position.x < 0) position.x = 0;
        if (position.x > Gdx.graphics.getWidth() - idleSheet.getWidth() * scale / 5f)
            position.x = Gdx.graphics.getWidth() - idleSheet.getWidth() * scale / 5f;

        stateTime += delta;
    }

    private void handleInput() {
        velocity.x = 0;
        String newState = "IDLE";

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velocity.x = moveSpeed;
            newState = "RUN";
            facingRight = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocity.x = -moveSpeed;
            newState = "RUN";
            facingRight = false;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && isOnGround) {
            velocity.y = jumpPower;
            isOnGround = false;
            isJumping = true;
            newState = "JUMP";
        }

        if (isJumping) newState = "JUMP";

        currentState = newState;
    }

    public void render(SpriteBatch batch) {
        TextureRegion frame;

        switch (currentState) {
            case "RUN":
                frame = runAnim.getKeyFrame(stateTime, true);
                break;
            case "JUMP":
                frame = jumpAnim.getKeyFrame(stateTime, true);
                break;
            default:
                frame = idleAnim.getKeyFrame(stateTime, true);
                break;
        }

        if (!facingRight && !frame.isFlipX()) frame.flip(true, false);
        if (facingRight && frame.isFlipX()) frame.flip(true, false);

        batch.draw(frame, position.x, position.y, frame.getRegionWidth() * scale, frame.getRegionHeight() * scale);
    }

    public void dispose() {
        idleSheet.dispose();
        runSheet.dispose();
        jumpSheet.dispose();
    }
}
