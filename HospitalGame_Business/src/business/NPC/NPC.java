/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import business.BusinessFacade;
import business.Map;
import common.INPC;
import common.IPlayer;
import common.IRoom;
import common.NPCID;

/**
 *
 * @author andreasmolgaard-andersen
 */
public abstract class NPC implements INPC {

    /**
     * the current room of the NPC
     */
    private int currentRoomID;

    /**
     * is the name of the NPC
     */
    private String name;

    /**
     * is the description of the NPC
     */
    private String description;

    /**
     * if the NPC can move is true, false if not
     */
    private boolean canMove;

    /**
     * is the ID of the NPC
     */
    private NPCID npcId;
    
    /**
     * a reference to map
     * Protected since porter need to call map.pathFinder.
     */
    protected Map map;
    
    /**
     * 
     */
    protected BusinessFacade business;

    /**
     * Constructor for NPC
     *
     * @param name name of the NPC
     * @param canMove boolean true if the NPC can move
     * @param currentRoomID the room ID the NPC being created to be in
     * @param npcId the NPCID of the NPC
     */
    public NPC(String name, boolean canMove, int currentRoomID, NPCID npcId) {
        this.name = name;
        this.canMove = canMove;
        this.currentRoomID = currentRoomID;
        this.npcId = npcId;
    }
    
    public void injectMap(Map map) {
        this.map = map;
    }
    
    public void injectBusiness(BusinessFacade business) {
        this.business = business;
    }

    /**
     * Construcfor for NPC
     *
     * @param npc is the INPC npc object being constructed to be an NPC instead
     */
    public NPC(INPC npc) {
        name = npc.getName();
        canMove = npc.canMove();
        currentRoomID = npc.getCurrentRoomID();
        npcId = npc.getNPCID();
    }

    /**
     * sets the room of an NPC
     *
     * @param currentRoomID the new room ID
     */
    public void setCurrentRoom(int currentRoomID) {
        this.currentRoomID = currentRoomID;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * interact method for the npcs
     *
     * @param player is the payer to interact with the npc
     * @return a String when interacting
     */
    public abstract String interact(IPlayer player);

    /**
     *
     * @return the currentRoom of NPC
     */
    @Override
    public IRoom getCurrentRoom() {
        return map.getRoomByID(currentRoomID);
    }
    
    @Override
    public int getCurrentRoomID() {
        return currentRoomID;
    }

    /**
     *
     * @return the name of NPC
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @return the NPCID of the NPC
     */
    @Override
    public NPCID getNPCID() {
        return npcId;
    }

    /**
     *
     * @return if the NPC can move.
     */
    @Override
    public boolean canMove() {
        return canMove;
    }

}
