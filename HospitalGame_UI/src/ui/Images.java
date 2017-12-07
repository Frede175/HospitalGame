package ui;

/**
 * Enum for different images.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public enum Images {
    /**
     * The death image.
     */
    DEATHSCREEN("deathScreen.png"), 
    /**
     * The spritesheet.
     */
    SPRITE("sprite.png"), 
    /**
     * The victory image.
     */
    VICTORYSCREEN("victoryScreen.png"),
    /**
     * The logo
     */
    LOGO("logo.png");
    
    private String name;
    
    private Images(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
