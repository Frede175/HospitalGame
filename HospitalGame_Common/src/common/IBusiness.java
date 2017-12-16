package common;

/**
 * Business facade interface
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface IBusiness {

    /**
     * Injects the persistence layer.
     * @param persistence Which persistence instance to inject.
     */
    void injectPersistenceFacade(IPersistence persistence);

    /**
     * Gets all the NPC's in the room.
     * @param room Which room to look in
     * @return An array of INPC with all the NPC's in the room.
     */
    INPC[] getNPCsFromRoom(IRoom room);

    /**
     * Returns the player.
     * @return The player.
     */
    IPlayer getPlayer();

    /**
     * Starts the game and generating the map.
     */
    void play();

    /**
     * Quits the game.
     */
    void quit();

    /**
     * Returns the highscore.
     * @return The highscore.
     */
    IHighScore getHighScore();

    /**
     * Pauses the game.
     */
    void pause();

    /**
     * Resumes the game.
     */
    void resume();

    /**
     * Saves the current game.
     * @return True if the game was saved.
     */
    boolean save();

    /**
     * Loads a saved game
     * @return True if a game was loaded.
     */
    boolean load();
    
    /**
     * Checks if there is a saved game.
     * @return True if there is a saved game.
     */
    boolean saveGameAvailable();

    /**
     * Moves the player in the direction.
     * @param direction Which direction the player should move.
     * @return True if the player moved.
     */
    boolean move(Direction direction);

    /**
     * Uses an item from the players inventory.
     * @param index Which index of the item to use in the inventory.
     * @return True if the item was used.
     */
    boolean useItem(int index);

    /**
     * Drops an item from the player inventory to the current room inventory.
     * @param index Which index of the item to drop in the inventory.
     * @return True if the item was dropped.
     */
    boolean dropItem(int index);

    /**
     * Takes an item from the room inventory to the player inventory.
     * @param index Which index of the item to take in the room inventory.
     * @return True if the item was taken.
     */
    boolean takeItem(int index);
    
    /**
     * Gets the game state.
     * @return The game state.
     */
    GameState getGameState();
    
    /**
     * Interacts with an NPC.
     * @param player Which player that is interacting.
     * @param npc Which NPC to interact with.
     * @return The interact message.
     */
    String interact(IPlayer player, INPC npc);
    
    /**
     * Gets the score.
     * @return The score.
     */
    int getScore();
    
    /**
     * Checks if the score is eligible for an place on the highscore.
     * @return True if the score is eligible for an place on the highscore.
     */
    boolean eligibleForHighScore();
    
    /**
     * Checks if the name is already taken in the highscore.
     * @param name Which name to check.
     * @return True if the name is taken.
     */
    boolean isHighScoreNameTaken(String name);
    
    /**
     * Adds the highscore
     * @param name The name of the player that got the highscore
     * @return True if added.
     */
    boolean addHighScore(String name);
    
    /**
     * Checks if there has been added any new highscores, if true, then saves the highscore to the computer.
     */
    void closing();
    
    /**
     * 
     * @return Array of rooms
     */
    IRoom[] getRooms();
    
    
}
