/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import business.common.INPCFacade;
import common.IInventory;
import common.INPC;
import common.IPlayer;
import common.IRoom;
import java.util.ArrayList;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class NPCFacade implements INPCFacade {

    private ArrayList<NPC> npcs = new ArrayList<>();
    
    @Override
    public IPlayer getPlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IRoom[] getRooms() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IInventory[] getInventories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public INPC[] getNPCs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
