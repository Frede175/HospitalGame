package hospitalgame;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *Containing all game constants.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface GameConstants {
    // Times is in milliseconds
    double BANDAGE_BUFF = 1.0;
    int BANDAGE_WEIGHT = 100;
    long BANDAGE_TIME = 15000;
    double MORPHINE_BUFF = 1.0;
    long MORPHINE_TIME = 15000;
    int MORPHINE_WEIGHT = 50;
    int BLOODBAG_SIZE = 450;
    double PLAYER_BLOODRATE = 10.0;
    int PLAYER_BLOOD_AMOUNT = 5500;
    int INVENTORY_MAX_WEIGHT = 2000;
    ArrayList<String> DIRECTIONS = new ArrayList<>(Arrays.asList("north", "east", "south", "west"));
}
