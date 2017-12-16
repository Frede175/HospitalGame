package common;

/**
 * Persistence interface
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface IPersistence {

    /**
     * Loads a high score from disk.
     * @return returns a high score, if the no high score is found the function returns null.
     */
    public IHighScore getHighScore();

    /**
     * Saves a high scores to the disk
     * @param highScore the high scores to save
     * @return if the high scores got saved to the disk, if an error occurs the function returns false. 
     */
    public boolean saveHighScore(IHighScore highScore);

    /**
     * Check if there is a saved game on the file system.
     * @return true if game i available.
     */
    public boolean saveGameAvailable();

    /**
     * Saves a game to disk
     * @param player The player in the game. 
     * @param inventory All of the inventories in the game.
     * @param rooms All of the rooms in the game.
     * @param npcs All of the NPCs in the game.
     * @return if the game got saved, if an error occurs the function returns false. 
     */
    public boolean saveGame(IPlayer player, IInventory[] inventory, IRoom[] rooms, INPC[] npcs);

    /**
     * Loads a game.
     *
     * @return a DataObject with all objects to load a game.
     */
    public IDataObject load();
}
