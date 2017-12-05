/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import common.IDataObject;
import common.IInventory;
import common.INPC;
import common.IPlayer;
import common.IRoom;
import java.io.Serializable;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataObject implements IDataObject, Serializable {

    private DataRoom[] rooms;
    private DataPlayer player;
    private DataNPC[] npcs;
    private DataInventory[] inventories;
    

    public DataObject(IRoom[] rooms, IPlayer player, INPC[] npcs, IInventory[] inventories) {
        this.rooms = new DataRoom[rooms.length];
        for (int i = 0; i < rooms.length; i++) {
            this.rooms[i] = new DataRoom(rooms[i]);
        }
        this.player = new DataPlayer(player);
        this.npcs = new DataNPC[npcs.length];
        for (int i = 0; i < npcs.length; i++) {
            this.npcs[i] = new DataNPC(npcs[i]);
        }
        this.inventories = new DataInventory[inventories.length];
        for (int i = 0; i < inventories.length; i++) {
            this.inventories[i] = new DataInventory(inventories[i]);
        }
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
        return rooms;
    }

}
