/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Animation to run the all animation that need press symbol.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param sensor - keyboard.
     * @param key - the symbol to press.
     * @param animation - animation to call.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
            this.keyboard = sensor;
            this.key = key;
            this.animation = animation;
            this.stop = false;
            this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!this.keyboard.isPressed(key) && isAlreadyPressed) {
            this.isAlreadyPressed = false;
        }

        if (this.keyboard.isPressed(key) && !isAlreadyPressed) {
            stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
