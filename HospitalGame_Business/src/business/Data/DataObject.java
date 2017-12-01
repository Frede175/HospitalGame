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
    private IInventory[] inventories;

    public DataObject(IRoom[] room, IPlayer player, INPC[] npcs, IInventory[] inventories) {
        this.room = room;
        this.player = player;
        this.npcs = npcs;
        this.inventories = inventories;
    }

    @Override
    public IPlayer getPlayer() {
        return player;
    }

    @Override
    public IInventory[] getInventories() {
        return inventories;
    }

    @Override
    public INPC[] getNPCs() {
        return npcs;
    }

    @Override
    public IRoom[] getRooms() {
        return room;
    }

}
