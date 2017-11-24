/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import common.IPersistence;
import java.lang.reflect.Type;

/**
 * 
 * @author fsr19
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

    @Override
    public boolean save(Object object) {
        return parser.save(object);
    }

    @Override
    public <T> T load(Class<T> type) {
        return parser.load(type);
    }
    
    
    
    
}
