/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import business.BusinessFacade;
import business.Map;
import business.common.INPCFacade;
import common.INPC;
import common.IPlayer;
import common.IRoom;
import common.NPCID;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle the NPCfacade
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class NPCFacade implements INPCFacade {

    private Map map;
    private BusinessFacade business;
    private MoveAI moveAI;
    private ArrayList<NPC> NPCs = new ArrayList<>();

    /**
     * Constructor for NPCFacade
     */
    public NPCFacade() {
        moveAI = new MoveAI();
    }

    /**
     * player interracts with npc
     *
     * @param player 
     * @param npc
     * @returns interract player with npc
     */
    @Override
    public String interact(IPlayer player, INPC npc) {
        return NPCs.get(NPCs.indexOf(npc)).interact(player);
    }

    /**
     * loads the npc rooms, names, if moveable.
     *
     * @param npcs
     */
    @Override
    public void load(INPC[] npcs) {
        for (int i = 0; i < npcs.length; i++) {
            create(npcs[i].getNPCID(), npcs[i].canMove(), npcs[i].getName(), npcs[i].getCurrentRoomID());
        }

    }
    /**
     * injector for map
     * @param map of the game
     */
    @Override
    public void injectMap(Map map) {
        this.map = map;
    }
    /**
     * Injector for business
     * @param business is businessfacade
     */
    @Override
    public void injectBusiness(BusinessFacade business) {
        this.business = business;
    }

    /**
     *
     * @param id is the id of the npc
     * @param canMove checks if the npc is able to move
     * @param name name of the npc
     * @param currentRoomID of the npc
     */
    @Override
    public void create(NPCID id, boolean canMove, String name, int currentRoomID) {
        NPC npc;
        switch (id) {
            case COMPUTER:
                npc = new Computer(name, canMove, currentRoomID, id);

                break;
            case DOCTOR:
                npc = new Doctor(name, canMove, currentRoomID, id);

                break;
            case PORTER:
                npc = new Porter(name, canMove, currentRoomID, id);
                break;
            default:
                throw new AssertionError();
        }
        npc.injectMap(map);
        npc.injectBusiness(business);
        NPCs.add(npc);
    }

    /**
     * Creates the npc
     *
     * @param id of the npc
     * @param canMove checks if the npc can move
     * @param name of the npc
     */
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

    /**
     * loops through the npc's to check if the npc is a porter. Porter then
     * notices and checks player
     *
     * @param player is the player in the game
     */
    @Override
    public void porterCheckPlayer(IPlayer player) {
        for (NPC npc : NPCs) {
            if (npc instanceof Porter) {
                ((Porter) npc).checkPlayer(player);
            }
        }
    }

    /**
     * Loops to get the npc's from a room.
     *
     * @param room
     * @return
     * @returns array of npc's in the room.
     */
    @Override
    public INPC[] getNPCsFromRoom(IRoom room) {
        List<INPC> npcsInRoom = new ArrayList<>();
        for (NPC npc : NPCs) {
            if (npc.getCurrentRoom() == room) {
                npcsInRoom.add(npc);
            }
        }
        INPC[] array = new INPC[npcsInRoom.size()];
        return npcsInRoom.toArray(array);
    }

    /**
     * updates the moveable NPC's in the game
     */
    @Override
    public void update() {
        moveAI.updateMoveableNPCs(NPCs);
    }
    
    /**
     * resets npcs
     */
    @Override
    public void reset() {
        NPCs.clear();
    }

}
