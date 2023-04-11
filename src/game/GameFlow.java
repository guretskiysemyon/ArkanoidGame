package game;

import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.GameOver;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import instruments.Archive;
import instruments.Counter;
import level.LevelInformation;
import animation.YouWin;
import java.util.List;

/**
 * Game Flow for managing and running the game levels and game itself.
 */
public class GameFlow {
    private GUI gui;
    private AnimationRunner animationRunner;
    private Counter score;

    /**
     * Constructor.
     * @param g - the gui of the game.
     */
    public GameFlow(GUI g) {
        this.gui = g;
        this.animationRunner = new AnimationRunner(60, this.gui);
        this.score = new Counter();
    }

    /**
     * Run list of the levels in the game.
     * @param levels the list of the levels.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.gui.getKeyboardSensor(),
                    this.animationRunner, this.score);
            level.initialize();     //initialize level
            AnimationRunner count = new AnimationRunner(Archive.COUNTDOWN_FRAME, this.gui);
            count.run(new CountdownAnimation(Archive.COUNTDOWN_SEC, Archive.COUNTDOWN_NUM, level.getSprites()));
            //run the level
            while (!level.shouldStop()) {
                level.run();
            }
            //if the player lose all balls
            if (level.getBalls().isEmpty()) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.gui.getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY, new GameOver(this.gui.getKeyboardSensor(), this.score)));
                this.gui.close();
                break;
            }
        }
        //if he finished the game
        this.animationRunner.run(new KeyPressStoppableAnimation(this.gui.getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY, new YouWin(this.gui.getKeyboardSensor(), this.score)));
        this.gui.close();

    }

}