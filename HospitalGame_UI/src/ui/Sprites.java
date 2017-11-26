package ui;

import common.Images;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
        "computer.png",
        "sprite.png",
        "victoryScreen.png"
    };
    
    /**
     * Contains all the images.
     */
    Map<Images, Image> images;
    
    /**
     * Sprites constructor loading all images.
     */
    public Sprites() {
        images = new HashMap<>();
        Images[] imgKeys = Images.values();
        for (int i = 0; i < names.length; i++) {
            URL url = getClass().getClassLoader().getResource("ui/images/" + names[i]);
            images.put(imgKeys[i], new Image(url.toString()));
        }
    }
    
    /**
     * Returns the image required.
     * @param image defines which image.
     * @return an image of the selected image.
     */
    public Image getImage(Images image) {
        return images.get(image);
    }
}
