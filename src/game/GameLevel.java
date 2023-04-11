/**
 * @author Semyon Guretskiy.
 * ID - 335249255
 */
package game;
import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import instruments.Velocity;
import geometry.Point;
import geometry.Rectangle;
import level.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import instruments.Archive;
import instruments.Counter;
import parts.Ball;
import parts.Block;
import parts.Paddle;
import parts.ScoreIndicator;
import parts.LevelName;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * Game by itself. Initialize and run the game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keyboard;
    private Paddle paddle;
    private List<Ball> balls;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter counterScore;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInfo;

    /**
     * Constructor.
     * @param li - level information for initialization of the level.
     * @param keyboard - the keyboard of the game.
     * @param runner - the runner animation.
     * @param score - the score now in the game.
     */
    public GameLevel(LevelInformation li, KeyboardSensor keyboard, AnimationRunner runner, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.balls = new ArrayList<>();
        this.keyboard = keyboard;
        this.runner = runner;
        this.levelInfo = li;
        this.sprites.addSprite(this.levelInfo.getBackground());
        this.counterScore = score;
        this.running = true;
    }
    /**
     * add collidable to Game environment.
     * @param c new collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add new sprite.
     * @param s new sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * get this Paddle.
     * @return the paddle in the game.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * return list of balls in a game.
     * @return balls in a game.
     */
    public List<Ball> getBalls() {
        return this.balls;
    }

    /**
     * start the limit blocks of the game.
     */
    private void initializeLimits() {
        Color color = Archive.COLOR_OF_LIMITS;
        int widthLimits = Archive.WIDTH_OF_LIMITS;   //width of blocks.
        int widthFrame = Archive.WIDTH_OF_FRAME;         //the screen width
        int heightFrame = Archive.HEIGHT_OF_FRAME;   //the screen height
        int printPanel = Archive.PRINT_PANEL_HEIGHT;
        int start = 0;
        /*
         * The 4 block are situated by the edge of the game.
         */
        Block printBlock = new Block(new Rectangle(new Point(start, start), widthFrame, printPanel), Color.WHITE);
        Block bUpper = new Block(new Rectangle(new Point(start, printPanel), widthFrame, widthLimits), color);
        Block bLeft = new Block(new Rectangle(new Point(start, printPanel),
                widthLimits, heightFrame - widthLimits), color);
        Block bRight = new Block(new Rectangle(new Point(widthFrame - widthLimits, printPanel),
                widthLimits, heightFrame - widthLimits), color);
        Block bDown = new Block(new Rectangle(new Point(widthLimits, heightFrame),
                widthFrame - Archive.DOUBLE * widthLimits, widthLimits), color);
        printBlock.addToGame(this);
        bUpper.addToGame(this); //add every one to collidable and sprite
        bLeft.addToGame(this);
        bRight.addToGame(this);
        this.counterBalls = new Counter(this.levelInfo.numberOfBalls());  //add the ball counter
        BallRemover bl = new BallRemover(this, this.counterBalls);  //and listeners to down block.
        bDown.addToGame(this);
        bDown.addHitListener(bl);
    }

    /**
     * Initialize the line of blocks.
     * @param bl - the block remover (listener) that we add to every block.
     * @param sl - the score tracking listener.
     */
    private void initializeBlocks(BlockRemover bl, ScoreTrackingListener sl) {
        List<Block> blocks = levelInfo.blocks();
        for (Block b : blocks) {
            b.addToGame(this);
            b.addHitListener(bl);
            b.addHitListener(sl);
        }
    }

    /**
     * Initialize ball.
     * @param point the start location
     */
    private void initializeBalls(Point point) {
        Color color = this.levelInfo.getColorBalls();
        List<Velocity> velocities = this.levelInfo.initialBallVelocities();
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(point, Archive.RADIUS, color); //ball
            ball.setVelocity(velocities.get(i));    //velocity
            ball.setGameEnvironment(environment);  //set environment
            ball.setGame(this); //add to game
            ball.addToGame(this);
            this.balls.add(ball);
        }
    }

    /**
     * Initialize paddle.
     */
    private void initializePaddle() {
        int height = Archive.PADDLE_HEIGHT; //parameters
        int width = this.levelInfo.paddleWidth();
        int widthFrame = Archive.WIDTH_OF_FRAME;
        double x = (double) widthFrame / Archive.TWO - width / Archive.TWO;    //start location  middle of set
        double y = Archive.HEIGHT_OF_FRAME - Archive.WIDTH_OF_LIMITS - height;
        Block b = new Block(new Rectangle(new Point(x, y), width, height), Color.ORANGE); //TODO
        Paddle pad = new Paddle(b, this.keyboard); //start paddle
        pad.setdX(this.levelInfo.paddleSpeed()); //set speed
        pad.setGame(this);   //set the game
        pad.addToGame(this); //add to game
        this.paddle = pad;
    }



    /**
     * Initialize all game.
     */
    public void initialize() {
        int step = Archive.COORDINATE_STEP;
        initializeLimits(); //limits
        initializePaddle(); //paddle
        Rectangle r = this.paddle.getCollisionRectangle();
        Point p = new Point((Archive.DOUBLE * r.getUpperLeft().getX() + r.getWidth()) / Archive.TWO,
                r.getUpperLeft().getY() - step);
        initializeBalls(p);
        this.counterBlocks = new Counter(this.levelInfo.numberOfBlocksToRemove());
        BlockRemover bl = new BlockRemover(this, this.counterBlocks);
        ScoreTrackingListener scl = new ScoreTrackingListener(this.counterScore);
        initializeBlocks(bl, scl);
        ScoreIndicator si = new ScoreIndicator(this.counterScore);
        this.sprites.addSprite(si);
        this.sprites.addSprite(new LevelName(this.levelInfo.levelName()));
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.counterBlocks.getValue() == 0) { //finish to the game
            this.counterScore.increase(Archive.ALL_BLOCK_SCORE);
            this.running = false;
        }
        if (this.counterBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                            new PauseScreen(this.keyboard)));
        }
    }
    /**
     * Run the game.
     */
    public void run() {
       this.runner.run(this);
    }

    /**
     * Remove Block from the game.
     * @param b - the block that needed to be removed
     */
    public void removeBlock(Block b) {
        this.sprites.removeSprite(b);
        this.environment.removeCollidable(b);
    }

    /**
     * Remove Ball from the game.
     * @param b - the ball that needed to be removed.
     */
    public void removeBall(Ball b) {
        this.sprites.removeSprite(b);
        this.balls.remove(b);
    }

    /**
     * Return this sprite collection.
     * @return SpriteCollection
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }
}