package common;

/**
 * Persistence interface
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface IPersistence {
    
    public IHighScore getHighScore();

    public boolean saveGame(IPlayer player, IInventory[] inventory, IRoom[] rooms, INPC[] npcs);

    public boolean saveHighScore(IHighScore highScore);

    public IDataObject load();
}
