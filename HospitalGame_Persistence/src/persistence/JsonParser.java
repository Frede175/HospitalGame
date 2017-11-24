/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author fsr19
 */
public class JsonParser {

    /**
     * GSON library 
     */
    private Gson gson;

    /**
     * Prefix to the file names
     */
    private String fileCommon = "data_";

    public JsonParser() {
        gson = new Gson();
    }

    /**
     * Save the given object to the file.
     *
     * @param object the given object to be stored
     * @return true if the object has been saved and false if object failed to
     * save.
     */
    public boolean save(Object object) {
        String fileName = getFileName(object.getClass());
        String json = gson.toJson(object);
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(json);
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    /**
     * Get the given object form the file.
     *
     * @param <T>
     * @param type The class that needs to be loaded
     * @return the an object with the given class or null if an error occurs.
     */
    public <T> T load(Class<T> type) {
        String fileName = getFileName(type);
        T object;
        try (FileReader reader = new FileReader(fileName)) {
            object = gson.fromJson(reader, type);
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
        
        return object;
    }

    /**
     * Get the file name for file given a class
     * @param <T>
     * @param type the class 
     * @return the file name for the class
     */
    private <T> String getFileName(Class<T> type) {
        return String.format("%s%s.txt", fileCommon, type.getSimpleName());
    }
}
