package common;

/**
 *
 * @author larsjorgensen
 */
public enum Images {
    DEATHSCREEN("DeathScreen"), DOCTOR("Doctor"), PLAYER("Player"), 
    PORTER("Porter"), COMPUTER("Computer"), SPRITE("Sprite"), VICTORYSCREEN("VictoryScreen");
    
    private String name;
    
    private Images(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
