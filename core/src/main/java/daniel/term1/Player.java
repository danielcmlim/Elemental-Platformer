package daniel.term1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Player {

    private World world;
    private Body body;

    private Texture idleSheet, runSheet, jumpSheet, deadSheet;
    private Animation<TextureRegion> idleAnim, runAnim, jumpAnim, deadAnim;

    private TextureRegion currentFrame;
    private float stateTime = 0;
    private float deathTime = 0;

    private float width = 38;     // sprite pixel width
    private float height = 67;    // sprite pixel height

    private boolean isJumping = false;
    private boolean facingRight = true;
    private boolean isDead = false;

    public Player(World world, float spawnX, float spawnY) {
        this.world = world;

        loadTextures();
        createAnimations();
        createBody(spawnX, spawnY);
    }

    private void loadTextures() {
        idleSheet = new Texture(Gdx.files.internal("player/Idle.png"));
        runSheet = new Texture(Gdx.files.internal("player/Run.png"));
        jumpSheet = new Texture(Gdx.files.internal("player/Jump.png"));
        deadSheet = new Texture(Gdx.files.internal("player/Dead.png"));
    }

    private void createAnimations() {
        int idleFrames = 7;
        int runFrames = 8;
        int jumpFrames = 9;
        int deadFrames = 6;

        int frameW = idleSheet.getWidth() / idleFrames;
        int frameH = idleSheet.getHeight();

        TextureRegion[][] idleCut = TextureRegion.split(idleSheet, frameW, frameH);
        TextureRegion[][] runCut = TextureRegion.split(runSheet, runSheet.getWidth() / runFrames, runSheet.getHeight());
        TextureRegion[][] jumpCut = TextureRegion.split(jumpSheet, jumpSheet.getWidth() / jumpFrames, jumpSheet.getHeight());
        TextureRegion[][] deadCut = TextureRegion.split(deadSheet, deadSheet.getWidth() / deadFrames, deadSheet.getHeight());

        idleAnim = new Animation<>(0.1f, idleCut[0]);
        runAnim = new Animation<>(0.07f, runCut[0]);
        jumpAnim = new Animation<>(0.12f, jumpCut[0]);
        deadAnim = new Animation<>(0.1f, deadCut[0]);

        idleAnim.setPlayMode(Animation.PlayMode.LOOP);
        runAnim.setPlayMode(Animation.PlayMode.LOOP);
        jumpAnim.setPlayMode(Animation.PlayMode.LOOP);
        deadAnim.setPlayMode(Animation.PlayMode.NORMAL); // Play once, don't loop
    }

    private void createBody(float spawnX, float spawnY) {
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(spawnX / Main.PPM, spawnY / Main.PPM);
        bd.fixedRotation = true;

        body = world.createBody(bd);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((width / 2f) / Main.PPM, (height / 2f) / Main.PPM);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1f;
        fd.friction = 2.6f;

        body.createFixture(fd).setUserData("player");
        shape.dispose();
    }

    public void update(float dt) {
        stateTime += dt;

        if (isDead) {
            deathTime += dt;
            // Stop the player from moving
            body.setLinearVelocity(0, 0);
            // Play death animation
            currentFrame = deadAnim.getKeyFrame(deathTime);
            return;
        }

        float moveSpeed = 4f;
        Vector2 vel = body.getLinearVelocity();
        Vector2 pos = body.getPosition();

        boolean moving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            body.setLinearVelocity(-moveSpeed, vel.y);
            facingRight = false;
            moving = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            body.setLinearVelocity(moveSpeed, vel.y);
            facingRight = true;
            moving = true;
        }

        // Jump
        if (Gdx.input.isKeyJustPressed(Input.Keys.W) && Math.abs(vel.y) < 0.01f) { //ground check
            body.applyLinearImpulse(new Vector2(0, 3f), pos, true);
            isJumping = true;
        }
        if (Math.abs(vel.y) < 0.01f)
            isJumping = false;

        // Pick animation
        if (isJumping) {
            currentFrame = jumpAnim.getKeyFrame(stateTime);
        } else if (moving) {
            currentFrame = runAnim.getKeyFrame(stateTime);
        } else {
            currentFrame = idleAnim.getKeyFrame(stateTime);
        }
    }

    public void render(SpriteBatch batch) {
        float drawX = body.getPosition().x * Main.PPM - width / 2f;
        float drawY = body.getPosition().y * Main.PPM - height / 2f;

        if (facingRight) {
            batch.draw(currentFrame, drawX, drawY, width, height);
        } else {
            batch.draw(currentFrame, drawX + width, drawY, -width, height);
        }
    }

    public void die() {
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }


    public void dispose() {
        idleSheet.dispose();
        runSheet.dispose();
        jumpSheet.dispose();
        deadSheet.dispose();
    }
}
