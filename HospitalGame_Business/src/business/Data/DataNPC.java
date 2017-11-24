/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Data;

import common.INPC;
import common.IRoom;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataNPC implements INPC {
    
    private int currentRoom;
    private String name;;
            

    @Override
    public IRoom getRoom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
