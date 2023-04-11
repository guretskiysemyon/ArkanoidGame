/**
 * @author Semyon Guretskiy.
 * ID - 335249255
 */
package parts;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.util.List;

import game.Collidable;
import game.GameLevel;
import game.Sprite;
import geometry.Point;
import geometry.Rectangle;
import instruments.Archive;
import instruments.Calculations;
import instruments.Velocity;

/** Class Paddle is implement of Sprite and Collidable. Run the paddle in the game.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard; //keyboard
    private Block paddle;   //block of paddle
    private double dX;  //speed
    private Rectangle gameForm; //screens size
    private GameLevel gameLevel;  //the game

    /**
     * The constructor of the paddle.
     * @param b  - the block
     * @param keyboard  - the keyboard.
     */
    public Paddle(Block b, KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.paddle = b;
        Point p = new Point(Archive.WIDTH_OF_LIMITS, Archive.WIDTH_OF_LIMITS); //make set the screen size
        int width = Archive.WIDTH_OF_FRAME - Archive.DOUBLE * Archive.WIDTH_OF_LIMITS;
        int height = Archive.HEIGHT_OF_FRAME - Archive.DOUBLE * Archive.WIDTH_OF_LIMITS;
        this.gameForm = new Rectangle(p, width, height);
    }

    /**
     * Return speed.
     * @return dx
     */
    public double getDx() {
        return this.dX;
    }

    /**
     * set the speed.
     * @param dx the speed
     */
    public void setdX(double dx) {
        this.dX = dx;
    }

    /**
     * set the game.
     * @param g the game.
     */
    public void setGame(GameLevel g) {
        this.gameLevel = g;
    }

    /**
     * Move paddle left.
     */
    public void moveLeft() {
        double xNew = paddle.getCollisionRectangle().getUpperLeft().getX() - this.dX;   //new x and y
        double yNew = paddle.getCollisionRectangle().getUpperLeft().getY();
        List<Ball> p = this.gameLevel.getBalls();  //get all balls

        for (Ball x : p) {
            int d = x.getSize() * Archive.DOUBLE;  //diameter
            if (xNew < (this.gameForm.getUpperLeft().getX() + d)    //next x could stack the ball
                    && x.getX() < this.paddle.getCollisionRectangle().getUpperLeft().getX()   //the ball close to limit
                    && x.getY() > this.paddle.getCollisionRectangle().getUpperLeft().getY()) {
                xNew = this.gameForm.getUpperLeft().getX() + d; //then stop before crushing the ball
            }
        }
        //if next x after limits, came back.
        if (xNew < this.gameForm.getUpperLeft().getX()) {
            xNew = this.gameForm.getUpperLeft().getX();
        }
        this.paddle.setLocation(new Point(xNew, yNew)); //set new location.
    }

    /**
     * Move paddle to right.
     */
    public void moveRight() {
        double xNew = paddle.getCollisionRectangle().getUpperLeft().getX() + this.dX;
        double yNew = paddle.getCollisionRectangle().getUpperLeft().getY();
        List<Ball> p = this.gameLevel.getBalls();  //get all balls
        double rigthLimit = this.gameForm.getUpperLeft().getX() - this.paddle.getCollisionRectangle().getWidth()
                + this.gameForm.getWidth(); //right wall of paddle almost to limit
        double rightWall = this.paddle.getCollisionRectangle().getUpperLeft().getX()
                - this.paddle.getCollisionRectangle().getWidth();   //right wall
        for (Ball x : p) {
            int d = Archive.DOUBLE * x.getSize(); //diameter
            if (xNew > (rigthLimit - d) //next x is almost to limit
                    && x.getX() > rightWall //ball between wall and paddle
                    && x.getY() > this.paddle.getCollisionRectangle().getUpperLeft().getY()) {
                xNew = this.gameForm.getUpperLeft().getX() + this.gameForm.getWidth() - d  //then not to crush ball
                        - this.paddle.getCollisionRectangle().getWidth();
            }
        }
        if (xNew > (this.gameForm.getUpperLeft().getX() + this.gameForm.getWidth()  //paddle cross the limit
                - this.paddle.getCollisionRectangle().getWidth())) {
            xNew = this.gameForm.getUpperLeft().getX() + this.gameForm.getWidth()
                    - this.paddle.getCollisionRectangle().getWidth();
       }
        this.paddle.setLocation(new Point(xNew, yNew));
    }

    /**
     * Notify the sprites that time passed.
     */
    @Override
    public void timePassed() {
        KeyboardSensor key = this.keyboard; //check which direction to move
        if (key.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (key.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Draw the paddle.
     * @param d - the surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }

    /**
     * get the form of paddle.
     * @return the Rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    /**
     * Return the velocity after collision.
     * @param hitter - tha ball that hit
     * @param collisionPoint - the point of collision.
     * @param currentVelocity - the curentVelocity
     * @return velocity of the ball
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null) {    //No collision
            return null;
        }
        String ans = Calculations.witchWall(this.paddle.getCollisionRectangle(), collisionPoint);   //check which wall.
         if (ans.equals("Vertical")) {    //vertical
            Velocity v = currentVelocity.turn(ans);
            return v;
        }   //horizontal
        int section = Calculations.paddleSection(this, collisionPoint);
        Velocity vNew = Calculations.paddleVelocity(section, currentVelocity);
        return vNew;
    }

    /**
     * add to game.
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}