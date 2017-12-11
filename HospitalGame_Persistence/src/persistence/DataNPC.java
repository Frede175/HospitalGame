package persistence;

import common.INPC;
import common.IRoom;
import common.NPCID;
import java.io.Serializable;

/**
 * Class to store NPC
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class DataNPC implements INPC, Serializable {

    /**
     * The ID of the room the NPC is in
     */
    private int currentRoom;
    
    /**
     * The name of the NPC
     */
    private String name;
    
    /**
     * What type of NPC it is.
     */
    private NPCID npcID;
    
    /**
     * If the NPC can move or not.
     */
    private boolean canMove;

    /**
     * Constructor for NPC from the interface INPC
     * @param npc the given NPC
     */
    DataNPC(INPC npc) {
        currentRoom = npc.getCurrentRoomID();
        name = npc.getName();
        npcID = npc.getNPCID();
        canMove = npc.canMove();
        
    }

    /**
     * 
     * @return the name of the NPC
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * NOT SUPPORTED IN DATA CLASS
     * @return 
     */
    @Override
    public IRoom getCurrentRoom() {
        throw new UnsupportedOperationException("Invalid operation for data object.");
    }

    /**
     * 
     * @return the type of NPC
     */
    @Override
    public NPCID getNPCID() {
        return npcID;
    }

    /**
     * 
     * @return if the NPC can move or not
     */
    @Override
    public boolean canMove() {
        return canMove;
    }

    /**
     * 
     * @return the ID of the room that the NPC is in
     */
    @Override
    public int getCurrentRoomID() {
        return currentRoom;
    }

}
