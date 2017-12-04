package ui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

/**
 *
 * @author larsjorgensen
 */
public class ImageResource {
    
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
     * The sprite sheet in the application.
     */
    private SpriteSheet spriteSheet;
    
    /**
     * Sprites constructor loading all images.
     */
    public ImageResource() {
        images = new HashMap<>();
        Images[] imgKeys = Images.values();
        for (int i = 0; i < names.length; i++) {
            URL url = getClass().getClassLoader().getResource("ui/images/" + names[i]);
            images.put(imgKeys[i], new Image(url.toString()));
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
