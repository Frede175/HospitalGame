package persistence;

import common.IDataObject;
import common.IHighScore;
import common.IInventory;
import common.INPC;
import common.IPersistence;
import common.IPlayer;
import common.IRoom;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Facade for the persistence layer
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class PersistenceFacade implements IPersistence {

    /**
     * The name all files start with
     */
    private final String commonName = "data_";

    /**
     * The file type for all files
     */
    private final String extension = ".ser";

    /**
     * Loads a high score from disk.
     *
     * @return returns a high score, if the no high score is found the function
     * returns null.
     */
    @Override
    public IHighScore getHighScore() {
        return load(DataHighScore.class);
    }

    /**
     * Saves a game to disk
     *
     * @param player The player in the game.
     * @param inventory All of the inventories in the game.
     * @param rooms All of the rooms in the game.
     * @param npcs All of the NPCs in the game.
     * @return if the game got saved, if an error occurs the function returns
     * false.
     */
    @Override
    public boolean saveGame(IPlayer player, IInventory[] inventory, IRoom[] rooms, INPC[] npcs) {
        return save(new DataObject(rooms, player, npcs, inventory));
    }

    /**
     * Saves a high scores to the disk
     *
     * @param highScore the high scores to save
     * @return if the high scores got saved to the disk, if an error occurs the
     * function returns false.
     */
    @Override
    public boolean saveHighScore(IHighScore highScore) {
        return save(new DataHighScore(highScore));
    }

    /**
     * Loads a game.
     *
     * @return a DataObject with all objects to load a game.
     */
    @Override
    public IDataObject load() {
        return load(DataObject.class);
    }

    /**
     * Check if there is a saved game on the file system.
     *
     * @return true if game i available.
     */
    @Override
    public boolean saveGameAvailable() {
        return fileExists(DataObject.class);
    }

    /**
     * Check if a file exists on the file system from the given type.
     *
     * @param <T>
     * @param type that need to see if a file exits
     * @return true if the file exits
     */
    private <T> boolean fileExists(Class<T> type) {
        return new File(commonName + type.getSimpleName() + extension).exists();
    }

    /**
     * Save the given object to the persistence store.
     *
     * @param object the given object to be stored
     * @return true if the object has been saved and false if object failed to
     * save.
     */
    private boolean save(Serializable object) {
        try (FileOutputStream fileOut = new FileOutputStream(commonName + object.getClass().getSimpleName() + extension)) {
            ObjectOutputStream stream = new ObjectOutputStream(fileOut);
            stream.writeObject(object);
            stream.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Get the given object form the persistence store.
     *
     * @param type The class that needs to be loaded
     * @return an object with the given class or null if an error occurs.
     */
    private <T extends Serializable> T load(Class<T> type) {
        if (!fileExists(type)) return null;
        T object;
        try (FileInputStream fileIn = new FileInputStream(commonName + type.getSimpleName() + extension)) {
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = (T) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException i) {
            return null;
        }
        return object;
    }
}
