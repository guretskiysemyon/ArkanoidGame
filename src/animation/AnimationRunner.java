/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import instruments.Archive;

/**
 * Run the animation.
 */
public class AnimationRunner {
    private GUI gui;
    private double framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     * @param time - frame per second
     * @param g - the gui
     */
    public AnimationRunner(double time, GUI g) {
        this.sleeper = new Sleeper();
        this.framesPerSecond = time;
        this.gui = g;
    }

    /**
     * Run the animation.
     * @param animation to run.
     */
    public void run(Animation animation) {
        double millisecondsPerFrame = (double) Archive.MILISEC_PER_FRAME / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = (long) millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
