package ui;

/**
 * Enum for different images.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
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
    
    /**
     * Contains the image name.
     */
    private String name;
    
    /**
     * Constructs and image name.
     * @param name Which name to use.
     */
    private Images(String name) {
        this.name = name;
    }

    /**
     * Gets the name.
     * @return The name.
     */
    @Override
    public String toString() {
        return name;
    }
    
}
