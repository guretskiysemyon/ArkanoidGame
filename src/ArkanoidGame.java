
/**
 * @author Semyon Guretskiy
 * ID - 335249255
 */
import biuoop.GUI;
import game.GameFlow;
import instruments.Archive;
import level.BearLevel;
import level.GodEyeLevel;
import level.LevelInformation;
import level.QrLevel;
import level.TriangleLevel;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/** main class for Game.
 */
public class ArkanoidGame {
    /**
     * main function.
     * @param args by user - no use.
     */
    public static void main(String[] args) {

        GUI gui = new GUI("Arkanoid", Archive.WIDTH_OF_FRAME, Archive.HEIGHT_OF_FRAME);
        LevelInformation levelOne = new GodEyeLevel();
        LevelInformation levelTwo = new BearLevel();
        LevelInformation levelThree = new QrLevel();
        LevelInformation levelFour = new TriangleLevel();
        TreeMap<String, LevelInformation> map  = new TreeMap<>();
        map.put("1", levelOne);
        map.put("2", levelTwo);
        map.put("3", levelThree);
        map.put("4", levelFour);
        List<LevelInformation> levels = new ArrayList<>();

        for (String x : args) {
            if (map.containsKey(x)) {
                levels.add(map.get(x));
            }
        }
        if (levels.size() == 0) {
            levels.addAll(map.values());
        }
        GameFlow flow = new GameFlow(gui);
        flow.runLevels(levels);

    }
}