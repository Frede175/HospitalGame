/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import business.Map;
import business.Player;
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
public class Porter extends NPC {

    /**
     * to gain access to the end room
     */
    private IRoom endRoom;

    /**
     * constructor for the porter
     * @param name is the name of the porter
     * @param canMove if the npc can move
     * @param currentRoom the room for the npc to be set in
     * @param npcId ID of the NPC
     */
    public Porter(String name, boolean canMove, IRoom currentRoom, NPCID npcId) {
        super(name, canMove, currentRoom, npcId);
    }

    /**
     * constructor for the porter when loading
     * @param npc the object to be loaded into the game
     */
    public Porter(INPC npc) {
        super(npc.getName(), npc.canMove(), npc.getCurrentRoom(), npc.getNPCID());
    }

    /**
     * moves the porter
     * @param direction is the direction to move the porter
     * @return true if the porter has been moved
     */
    public boolean move(String direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * interact method for the porter
     * @param player is the player for the porter to interact with
     * @return a String when interacting
     */
    @Override
    public String interact(IPlayer player) {
        System.out.print("These directions will lead you two rooms ahead ");
        List<Directions> path = Map.pathfinder(player.getCurrentRoom(), endRoom);
        for (int i = 0; i < 2 && i < path.size(); i++) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println();
        return null;
    }
    
    /**
     * sets the endRoom so the porter knows where it is
     * @param room 
     */
    public void setEndRoom(IRoom room){
        endRoom = room;
    }

}
