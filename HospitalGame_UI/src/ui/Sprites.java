package ui;

import javafx.scene.image.Image;

/**
 *
 * @author larsjorgensen
 */
public class Sprites {
    
    /**
     * Contains all the names of the images
     */
    String[] names = {
        "deathScreen.png",
        "doctor.png",
        "player.png",
        "porter.png",
        "sprite.png",
        "victoryScreen.png"
    };
    
    /**
     * Contains all the images.
     */
    Image[] images;
    
    /**
     * Sprites constructor loading all images.
     */
    public Sprites() {
        images = new Image[names.length];
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("../ui/images/" + names[i]);
        }
    }
    
    /**
     * Gets an image from the images.
     * @param i is the index of which image that needs to be returned.
     * @return the requested image.
     */
    public Image getImage(int i) {
        return images[i];
    }
}
