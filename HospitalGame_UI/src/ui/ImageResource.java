package ui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

/**
 * Controller for the death screen.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class ImageResource {   
    /**
     * Contains all the images.
     */
    Map<Images, Image> images;
    
    /**
     * The sprite sheet in the application.
     */
    private SpriteSheet spriteSheet;
    
    /**
     * Sprites constructor loading all images.
     */
    public ImageResource() {
        images = new HashMap<>();
        for(Images img : Images.values()) {
            URL url = getClass().getClassLoader().getResource("ui/images/" + img.toString());
            images.put(img, new Image(url.toString()));
        }
        spriteSheet = new SpriteSheet(getImage(Images.SPRITE), 64, 22);
    }
    
    /**
     * Returns the image required.
     * @param image defines which image.
     * @return an image of the selected image.
     */
    public Image getImage(Images image) {
        return images.get(image);
    }
    
    /**
     * Returns the sprite required.
     * @param sprite defines which sprite.
     * @return an image of the selected sprite.
     */
    public Image getSprite(Sprites sprite) {
        return spriteSheet.getSprite(sprite.getIndex());
    }
    
}
