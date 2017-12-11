package common;

/**
 * All the game constants.
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface GameConstants {

    // Times is in milliseconds
    /**
     * How much the bandage gives in bloodrate reduction.
     */
    double BANDAGE_BUFF = 25.0;
    
    /**
     * How much the bandage weights in the inventory.
     */
    int BANDAGE_WEIGHT = 100;
    
    /**
     * How long the buff lasts.
     */
    long BANDAGE_TIME = 15000;
    
    /**
     * How much the morphine gives in bloodrate reduction.
     */
    double MORPHINE_BUFF = 50.0;
    
    /**
     * How much the morphine weights in the inventory.
     */
    int MORPHINE_WEIGHT = 50;
    
    /**
     * How long the morphine lasts.
     */
    long MORPHINE_TIME = 15000;
    
    /**
     * How much a bloodbag contains.
     */
    int BLOODBAG_SIZE = 450;
    
    /**
     * The bloodrate for the player aka. how much the player looses every second.
     */
    double PLAYER_BLOODRATE = 200.0;
    
    /**
     * How much blood the player starts with
     */
    int PLAYER_BLOOD_AMOUNT = 5500;
    
    /**
     * How much weight there can be in an inventory.
     */
    int INVENTORY_MAX_WEIGHT = 2000;
    
    /**
     * The weight of the ID card.
     */
    int IDCARD_WEIGHT = 50;
}
