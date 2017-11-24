/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Data;

import business.common.IDataObject;
import common.IInventory;
import common.INPC;
import common.IPlayer;
import common.IRoom;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataObject implements IDataObject {
    private IRoom[] room;
    private IPlayer player;
    private INPC[] npcs;
    private IInventory inventories;

    @Override
    public IPlayer getPlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IInventory[] getInventories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public INPC[] getNPCs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IRoom[] getRooms() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
