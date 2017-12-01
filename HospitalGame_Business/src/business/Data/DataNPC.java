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

    private IRoom currentRoom;
    private String name;
    private NPCID npcID;
    private boolean canMove;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IRoom getCurrentRoom() {
        return currentRoom;
    }

    @Override
    public NPCID getNPCID() {
        return npcID;
    }

    @Override
    public boolean canMove() {
        return canMove;
    }

}
