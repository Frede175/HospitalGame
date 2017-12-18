package business.NPC;

import common.INPC;
import common.IPlayer;
import common.NPCID;

    /**
    * Class to handle computer functions
    *
    * @author Frederik Schultz Rosenberg
    * @author Andreas Bøgh Mølgaard-Andersen
    * @author Lars Bjerregaard Jørgensen
    * @author Robert Francisti
    */
public class Computer extends NPC {
    
    /**
     * Constructor for Computer
     *
     * @param name name of the NPC
     * @param canMove boolean true if the NPC can move
     * @param currentRoomID the room the NPC being created to be in
     * @param npcId the NPCID of the NPC
     */
    public Computer(String name, boolean canMove, int currentRoomID, NPCID npcId) {
        super(name, canMove, currentRoomID, npcId);
    }

    /**
     * constructor for Computer
     *
     * @param npc is the npc to be constructed
     */
    public Computer(INPC npc) {
        super(npc.getName(), npc.canMove(), npc.getCurrentRoomID(), npc.getNPCID());
    }

    /**
     * interact with the computer
     *
     * @param player is the player to interact with
     * @return a String when interacting
     */
    @Override
    public String interact(IPlayer player) {
        business.playerBloodTypeKnown();
        String string = ("You have the blood type : " + player.getBloodType());
        return string;
    }

}
