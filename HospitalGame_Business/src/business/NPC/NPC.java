/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import common.INPC;
import common.IPlayer;
import common.IRoom;
import business.common.NPCID;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class NPC implements INPC {

    private String name;
    private String description;
    private boolean CanMove;
    
    public NPC(String name, boolean canMove, IRoom currentRoom){
        
    }
    
    public NPC(INPC npc){
        
    }
    public void serCurrentRoom(IRoom currentRoom){
        
    }
    
    public String getDescription() {
        return null;  
    }
    
    public String interact(IPlayer player){
        return null;  
    }
    
    @Override
    public IRoom getCurrentRoom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NPCID getNPCID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

