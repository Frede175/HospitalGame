/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import business.Map;
import business.common.IMoveable;
import common.Directions;
import common.INPC;
import common.IPlayer;
import common.IRoom;
import common.NPCID;
import java.util.List;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Porter extends NPC implements IMoveable {
    
    /**
     * to gain access to the end room
     */
    private int endRoomID;

    /**
     * constructor for the porter
     *
     * @param name is the name of the porter
     * @param canMove if the npc can move
     * @param currentRoomID the room ID for the npc to be set in
     * @param npcId ID of the NPC
     */
    public Porter(String name, boolean canMove, int currentRoomID, NPCID npcId) {
        super(name, canMove, currentRoomID, npcId);
    }

    /**
     * constructor for the porter when loading
     *
     * @param npc the object to be loaded into the game
     */
    public Porter(INPC npc) {
        super(npc.getName(), npc.canMove(), npc.getCurrentRoomID(), npc.getNPCID());
    }
    

    /**
     * moves the porter
     *
     * @param direction is the direction to move the porter
     * @return true if the porter has been moved
     */
    @Override
    public boolean move(Directions direction) {
        setCurrentRoom(getCurrentRoom().getExit(direction).getRoomID());
        return true;
    }

    /**
     * interact method for the porter
     *
     * @param player is the player for the porter to interact with
     * @return a String when interacting
     */
    @Override
    public String interact(IPlayer player) {
        String output = "These directions will lead you two rooms ahead ";
        List<Directions> path = map.pathfinder(player.getCurrentRoomID(), endRoomID);
        for (int i = 0; i < 2 && i < path.size(); i++) {
            output += path.get(i) + " ";
        }
        return output;
    }

    /**
     * sets the endRoom so the porter knows where it is
     *
     * @param roomID the room ID where to doctor is
     */
    public void setEndRoom(int roomID) {
        endRoomID = roomID;
    }

    @Override
    public int getCurrentRoomID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
