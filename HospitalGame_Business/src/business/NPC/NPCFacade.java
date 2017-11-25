/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import business.common.INPCFacade;
import common.Directions;
import common.INPC;
import common.IPlayer;
import common.IRoom;
import common.NPCID;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class NPCFacade implements INPCFacade {

    private ArrayList<NPC> NPCs = new ArrayList<>();
    
    @Override
    public String interact(IPlayer player, INPC NPC) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean move(INPC npc, Directions dir) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(Objects[] objects) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void create(NPCID id, boolean canMove, String name, IRoom currentRoom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
    }

    /**
     * getter method for all NPCs
     * @return an Array with INPCs 
     */
    @Override
    public INPC[] getNPCs() {
        INPC[] ArrayNPCs = new INPC[NPCs.size()];
        NPCs.toArray(ArrayNPCs);
        return ArrayNPCs;
    }
    
    
}


