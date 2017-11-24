/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author fsr19
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
     * @return the an object with the given class or null if an error occurs.
     */
    <T> T load(Class<T> type);
}
