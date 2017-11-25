/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import business.Player;
import common.INPC;
import common.IRoom;
import common.NPCID;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Computer extends NPC {

    /**
     * Constructor for Computer
     * @param name name of the NPC
     * @param canMove boolean true if the NPC can move
     * @param currentRoom the room the NPC being created to be in
     * @param npcId the NPCID of the NPC
     */
    public Computer(String name, boolean canMove, IRoom currentRoom, NPCID npcId) {
        super(name, canMove, currentRoom, npcId);
    }

    public Computer(INPC npc) {
        super(((INPC)npc).getName(), ((INPC)npc).canMove(), ((INPC)npc).getCurrentRoom(), ((INPC)npc).getNPCID());
    }

    public void interact(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
    }

}
