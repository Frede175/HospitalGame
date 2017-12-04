package persistence;

import common.IDataObject;
import common.IHighScore;
import common.IInventory;
import common.INPC;
import common.IPersistence;
import common.IPlayer;
import common.IRoom;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Facade for the persistence layer
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class PersistenceFacade implements IPersistence {

    /**
     * The JSON parser implantation
     */
    static JsonParser parser;

    /**
     *
     */
    public PersistenceFacade() {
        parser = new JsonParser();
    }

    @Override
    public IHighScore getHighScore() {
        return load(DataHighScore.class);
    }

    @Override
    public boolean saveGame(IPlayer player, IInventory[] inventory, IRoom[] rooms, INPC[] npcs) {

        try {
            DataObject object = new DataObject(rooms, player, npcs, inventory);
            FileOutputStream fileOut = new FileOutputStream("dataObject.ser");
            ObjectOutputStream stream = new ObjectOutputStream(fileOut);

            stream.writeObject(object);
            return true;

        } catch (IOException ex) {
            Logger.getLogger(PersistenceFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean saveHighScore(IHighScore highScore) {
        return save(highScore);
    }

    @Override
    public IDataObject load() {
        DataObject object;
        try {
            FileInputStream fileIn = new FileInputStream("dataObject.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = (DataObject) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("DataObject class not found");
            c.printStackTrace();
            return null;
        }
        return object;
        //return load(DataObject.class);
    }

    /**
     * Save the given object to the persistence store.
     *
     * @param object the given object to be stored
     * @return true if the object has been saved and false if object failed to
     * save.
     */
    @Override
    public boolean save(Object object) {
        return parser.save(object);
    }

    /**
     * Get the given object form the persistence store.
     *
     * @param <T>
     * @param type The class that needs to be loaded
     * @return an object with the given class or null if an error occurs.
     */
    @Override
    public <T> T load(Class<T> type) {
        return parser.load(type);
    }

}
