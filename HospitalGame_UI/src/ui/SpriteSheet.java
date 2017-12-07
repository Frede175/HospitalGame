package ui;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * SpriteSheet handler class.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class SpriteSheet {
    
    /**
     * the image with the sprite sheet
     */
    private Image spriteSheet;
    
    /**
     * The size of each sprite on the sprite sheet
     */
    private int spriteSize;
    
    /**
     * The pixel reader of the sprite image
     */
    private PixelReader pixelReader;
    
    /**
     * A list of sprites from the sprite sheet.
     */
    private Image[] images;
    
    /**
     * How many sprites there is per row on the sprite sheet
     */
    private int spritesPerRow;
    
    /**
     * Constructor for the sprite sheet, this loads all the sprites from the sprite sheet.
     * @param spriteSheet image
     * @param spriteSize the size of each sprite
     * @param numberOfSprites how many sprites on the sprite sheet
     */
    public SpriteSheet(Image spriteSheet, int spriteSize, int numberOfSprites) {
        this.spriteSheet = spriteSheet;
        this.spriteSize = spriteSize;
        pixelReader = spriteSheet.getPixelReader();
        images = new Image[numberOfSprites];
        spritesPerRow = (int)(this.spriteSheet.getWidth() / spriteSize);
        
        for (int i = 0; i < numberOfSprites; i++) {
            images[i] = loadSprite(i);
        }
    }
    
    /**
     * Get the sprite with the given index from the sprite sheet.
     * @param index on the sprite sheet
     * @return the given image
     */
    public Image getSprite(int index) {
        return images[index];
    }
    
    /**
     * Loads the sprite from the sprite image
     * @param index of the picture on the image
     * @return image with a width and height of spriteSize.
     */
    private Image loadSprite(int index) {
        int x = index % spritesPerRow;
        int y = index / spritesPerRow;
        
        byte[] pixels = new byte[spriteSize * spriteSize * 4];
        //Reads all of the pixels in the given area, and sets the values of the pixels in the byte array: pixels.
        pixelReader.getPixels(x * spriteSize, y * spriteSize, spriteSize, spriteSize, PixelFormat.getByteBgraInstance(), pixels, 0, spriteSize * 4);
        WritableImage image = new WritableImage(spriteSize, spriteSize);
        PixelWriter pixelWriter = image.getPixelWriter();
        //Sets all the pixels from the byte array to the image.
        pixelWriter.setPixels(0, 0, spriteSize, spriteSize, PixelFormat.getByteBgraInstance(), pixels, 0, spriteSize * 4);
        return image;
    }
    
}
