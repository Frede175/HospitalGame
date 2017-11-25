/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import business.Map;
import business.Player;
import common.INPC;
import common.IRoom;
import common.NPCID;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Porter extends NPC {

    public Porter(String name, boolean canMove, IRoom currentRoom, NPCID npcId) {
        super(name, canMove, currentRoom, npcId);
    }

    public Porter(INPC npc) {
        super(npc.getName(), npc.canMove(), npc.getCurrentRoom(), npc.getNPCID());
    }
    
    public void interact(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean move(String direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
