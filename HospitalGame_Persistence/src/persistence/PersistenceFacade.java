package persistence;

import common.IPersistence;
import java.lang.reflect.Type;

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
    private JsonParser parser;
    
    /**
     * 
     */
    public PersistenceFacade() {
        parser = new JsonParser();
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
