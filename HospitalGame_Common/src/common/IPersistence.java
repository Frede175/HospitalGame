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

    /**
     * Save the given object to the persistence store
     *
     * @param object the given object to be stored
     * @return true if the object has been saved and false if object failed to
     * save.
     */
    boolean save(Object object);

    /**
     * Get the given object form the persistence store.
     *
     * @param <T>
     * @param type The class that needs to be loaded
     * @return the object with the given class or null if an error occurs.
     */
    <T> T load(Class<T> type);
}
