/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import business.common.IMoveable;
import business.common.INPCFacade;
import common.Directions;
import common.INPC;
import common.IPlayer;
import common.IRoom;
import common.NPCID;
import java.util.ArrayList;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class NPCFacade implements INPCFacade {

    private ArrayList<NPC> NPCs = new ArrayList<>();

    @Override
    public String interact(IPlayer player, INPC npc) {
        return NPCs.get(NPCs.indexOf(npc)).interact(player);
    }

    @Override
    public boolean move(INPC npc, Directions dir) {
        if (npc.canMove()) {
            IMoveable imoveable = (IMoveable) NPCs.get(NPCs.indexOf(npc));
            imoveable.move(dir);
            return true;

        }
        return false;
    }

    @Override
    public void load(INPC[] npcs) {
        for (int i = 0; i < npcs.length; i++) {
            NPCID id;
            id = npcs[i].getNPCID();
            switch (id) {
            case COMPUTER:
                Computer computer = new Computer(npcs[i]);
                NPCs.add(computer);
                break;
            case DOCTOR:
                Doctor docter = new Doctor(npcs[i]);
                NPCs.add(docter);
                break;
            case PORTER:
                Porter porter = new Porter(npcs[i]);
                NPCs.add(porter);
                break;
            default:
                throw new AssertionError();
        }
                    
                    
        }
       

    }

    @Override
    public void create(NPCID id, boolean canMove, String name, IRoom currentRoom) {

        switch (id) {
            case COMPUTER:
                Computer computer = new Computer(name, canMove, currentRoom, id);
                NPCs.add(computer);
                break;
            case DOCTOR:
                Doctor docter = new Doctor(name, canMove, currentRoom, id);
                NPCs.add(docter);
                break;
            case PORTER:
                Porter porter = new Porter(name, canMove, currentRoom, id);
                NPCs.add(porter);
                break;
            default:
                throw new AssertionError();
        }

    }

    /**
     * getter method for all NPCs
     *
     * @return an Array with INPCs
     */
    @Override
    public INPC[] getNPCs() {
        INPC[] ArrayNPCs = new INPC[NPCs.size()];
        NPCs.toArray(ArrayNPCs);
        return ArrayNPCs;
    }

    /**
     * sets the room of an npc
     *
     * @param npc is the npc to set in a given room
     * @param room is the given room to set the npc in
     */
    @Override
    public void setRoom(INPC npc, IRoom room) {

        NPCs.get(NPCs.indexOf(npc)).setCurrentRoom(room);
    }

    /**
     * sets an npc in the end room
     *
     * @param npc is the npc to be set in the end room
     * @param endRoom is the end room
     */
    @Override
    public void setEndRoom(INPC npc, IRoom endRoom) {

        if (npc instanceof Porter) {
            ((Porter) npc).setEndRoom(endRoom);
        }
    }

}
