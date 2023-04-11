/**
 * @author Semyon Guretskiy.
 * ID: 336249255
 */
package instruments;
import java.awt.Color;
/**
 * @author Semyon Guretskiy
 * ID - 335249255
 * Archive of constant values.
 */
public class Archive {
    public static final Color COLOR_OF_LIMITS = Color.GRAY;
    public static final int WIDTH_OF_LIMITS = 15;
    public static final int WIDTH_OF_FRAME = 800;
    public static final int HEIGHT_OF_FRAME = 600;
    public static final int RADIUS = 4;
    public static final int PADDLE_HEIGHT = 10;
    public static final int FRAME_PER_SECOND = 60;
    public static final int MILISEC_PER_FRAME = 1000;
    public static final int COORDINATE_STEP = 5;
    public static final int DOUBLE = 2;
    public static final int PRINT_PANEL_HEIGHT = 20;
    public static final int TEXT_SIZE = 15;
    public static final int TWO = 2;
    public static final int BALL_DISTANCE = 4;
    public static final int ALL_BLOCK_SCORE = 100;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int LEVEL_NAME_X = 600;
    public static final int COUNTDOWN_SEC = 2;
    public static final int COUNTDOWN_NUM = 3;
    public static final double COUNTDOWN_FRAME = 1.5;

    /**
     * @param i - the number
     * @return the color in range.
     */
    public static java.awt.Color getColor(int i) {
        int cases = 8;
        i = i % cases;
        return switch (i) {      //color that return
            case 1 -> Color.BLUE;
            case 2 -> Color.GREEN;
            case 3 -> Color.MAGENTA;
            case 4 -> Color.RED;
            case 5 -> Color.ORANGE;
            case 6 -> Color.PINK;
            case 7 -> Color.DARK_GRAY;
            default -> Color.CYAN;
        };
    }

}