/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

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
    private IRoom currentRoom;

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
     * Constructor for NPC
     *
     * @param name name of the NPC
     * @param canMove boolean true if the NPC can move
     * @param currentRoom the room the NPC being created to be in
     * @param npcId the NPCID of the NPC
     */
    public NPC(String name, boolean canMove, IRoom currentRoom, NPCID npcId) {
        this.name = name;
        this.canMove = canMove;
        this.currentRoom = currentRoom;
        this.npcId = npcId;
    }

    /**
     * Construcfor for NPC
     *
     * @param npc is the INPC npc object being constructed to be an NPC instead
     */
    public NPC(INPC npc) {
        name = npc.getName();
        canMove = npc.canMove();
        currentRoom = npc.getCurrentRoom();
        npcId = npc.getNPCID();
    }

    /**
     * sets the room of an NPC
     *
     * @param currentRoom the new room
     */
    public void setCurrentRoom(IRoom currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    public abstract String interact(IPlayer player);
        

    /**
     *
     * @return the currentRoom of NPC
     */
    @Override
    public IRoom getCurrentRoom() {
        return currentRoom;
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
