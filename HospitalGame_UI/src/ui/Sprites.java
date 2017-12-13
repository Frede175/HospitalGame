package ui;

/**
 * Sprites enum.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public enum Sprites {
    ARROW_RIGHT(0),
    ARROW_UP(1),
    ARROW_DOWN(2),
    ARROW_LEFT(3),
    BANDAGE(4),
    BLOODBAG_A(5),
    BLOODBAG_AB(6),
    BLOODBAG_B(7),
    BLOODBAG_O(8),
    COMPUTER(9),
    LOCK(10),
    DOCTOR(11),
    MORPHINE(12),
    PORTER(13),
    IDCARD(14);
        
    private int index;
    
    private Sprites(int index) {
        this.index = index;
    }

    int getIndex() {
        return index;
    }
    
}
