/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalgame;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author larsjorgensen
 */
public interface GameConstants {
    double BANDAGE_BUFF = 1.0;
    int BANDAGE_WEIGHT = 100;
    long BANDAGE_TIME = 15;
    double MORPHINE_BUFF = 1.0;
    long MORPHINE_TIME = 15;
    int MORPHINE_WEIGHT = 50;
    int BLOODBAG_SIZE = 450;
    double PLAYER_BLOODRATE = 10.0;
    int PLAYER_BLOOD_AMOUNT = 5500;
    ArrayList<String> DIRECTIONS = new ArrayList<>(Arrays.asList("north", "east", "south", "west"));
}
