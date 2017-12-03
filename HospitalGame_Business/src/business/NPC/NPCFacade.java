/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import business.BusinessFacade;
import business.Map;
import business.common.IMoveable;
import business.common.INPCFacade;
import common.Directions;
import common.IBusiness;
import common.INPC;
import common.IPlayer;
import common.NPCID;
import java.util.ArrayList;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class NPCFacade implements INPCFacade {

    private Map map;
    private BusinessFacade business;
    
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
        NPCs.clear();
        for (int i = 0; i < npcs.length; i++) {
            create(npcs[i].getNPCID(), npcs[i].canMove(), npcs[i].getName(), npcs[i].getCurrentRoomID());
        }

    }
    
    @Override
    public void injectMap(Map map) {
        this.map = map;
    }
    
    @Override
    public void injectBusiness(BusinessFacade business) {
        this.business = business;
    }

    @Override
    public void create(NPCID id, boolean canMove, String name, int currentRoomID) {

        switch (id) {
            case COMPUTER:
                Computer computer = new Computer(name, canMove, currentRoomID, id);
                computer.injectMap(map);
                computer.injectBusiness(business);
                NPCs.add(computer);
                break;
            case DOCTOR:
                Doctor doctor = new Doctor(name, canMove, currentRoomID, id);
                doctor.injectMap(map);
                doctor.injectBusiness(business);
                NPCs.add(doctor);
                break;
            case PORTER:
                Porter porter = new Porter(name, canMove, currentRoomID, id);
                porter.injectMap(map);
                NPCs.add(porter);
                break;
            default:
                throw new AssertionError();
        }

    }
    
    
    @Override
    public void create(NPCID id, boolean canMove, String name) {
        create(id, canMove, name, -1);
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
     * @param roomID is the given room ID to set the npc in
     */
    @Override
    public void setRoom(INPC npc, int roomID) {

        NPCs.get(NPCs.indexOf(npc)).setCurrentRoom(roomID);
    }

    /**
     * sets an npc in the end room
     *
     * @param npc is the npc to be set in the end room
     * @param endRoomID is the end room ID
     */
    @Override
    public void setEndRoom(INPC npc, int endRoomID) {

        if (npc instanceof Porter) {
            ((Porter) npc).setEndRoom(endRoomID);
        }
    }

}
