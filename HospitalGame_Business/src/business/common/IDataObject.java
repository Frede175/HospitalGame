/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.common;

import common.IInventory;
import common.INPC;
import common.IPlayer;
import common.IRoom;

/**
 *
 * @author andreasmolgaard-andersen
 */
public interface IDataObject {
    public IPlayer getPlayer();
    public IInventory[] getInventories();
    public INPC[] getNPCs();
    public IRoom[] getRooms(); 
}

