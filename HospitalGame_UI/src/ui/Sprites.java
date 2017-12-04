package ui;

/**
 *
 * @author fsr19
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
    BUTTON_PRESSED(9),
    BUTTON_RELEASED(10),
    DOCTOR(11),
    PLAYER(12),
    PORTER(13),
    EXIT_RIGHT(14),
    EXIT_UP(15),
    EXIT_DOWN(16),
    EXIT_LEFT(17),
    LOCK(18),
    MORPHINE(19),
    COMPUTER(20),
    IDCARD(21);
        
    private int index;
    
    private Sprites(int index) {
        this.index = index;
    }

    int getIndex() {
        return index;
    }
    
}
