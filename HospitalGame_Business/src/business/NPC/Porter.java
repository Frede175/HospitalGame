/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import business.common.IMoveable;
import common.Direction;
import common.INPC;
import common.IPlayer;
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
     * Last time the porter moved
     */
    private long lastMove;

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
        lastMove = System.currentTimeMillis();
    }

    /**
     * constructor for the porter when loading
     *
     * @param npc the object to be loaded into the game
     */
    public Porter(INPC npc) {
        super(npc.getName(), npc.canMove(), npc.getCurrentRoomID(), npc.getNPCID());
        lastMove = System.currentTimeMillis();
    }
    

    /**
     * moves the porter
     *
     * @param direction is the direction to move the porter
     * @return true if the porter has been moved
     */
    @Override
    public boolean move(Direction direction) {
        setCurrentRoom(getCurrentRoom().getExit(direction).getRoomID());
        checkPlayer(business.getPlayer());
        lastMove = System.currentTimeMillis();
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
        List<Direction> path = map.pathfinder(player.getCurrentRoomID(), endRoomID);
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
    
    /**
     * Check if the player is a room that is locked and if so it moves the player to a room that is not locked.
     * @param player the current player
     */
    public void checkPlayer(IPlayer player) {
        if (getCurrentRoom() == player.getCurrentRoom()) {
            if (player.getCurrentRoom().isLocked()) {
                for (Direction dir : player.getCurrentRoom().getExitDirections()) {
                    if (!player.getCurrentRoom().getExit(dir).isLocked()) {
                        business.porterMovePlayer(dir);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public long getLastMove() {
        return lastMove;
    }

}
