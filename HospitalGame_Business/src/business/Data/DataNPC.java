/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Data;

import common.INPC;
import common.IRoom;
import common.NPCID;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class DataNPC implements INPC {

    private int currentRoom;
    private String name;
    private NPCID npcID;
    private boolean canMove;

    DataNPC(INPC npc) {
        currentRoom = npc.getCurrentRoomID();
        name = npc.getName();
        npcID = npc.getNPCID();
        canMove = npc.canMove();
        
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IRoom getCurrentRoom() {
        throw new UnsupportedOperationException("Invalid operation for data object.");
    }

    @Override
    public NPCID getNPCID() {
        return npcID;
    }

    @Override
    public boolean canMove() {
        return canMove;
    }

    @Override
    public int getCurrentRoomID() {
        return currentRoom;
    }

}
