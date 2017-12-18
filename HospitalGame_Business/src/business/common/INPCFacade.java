package business.common;

import business.BusinessFacade;
import business.Map;
import common.INPC;
import common.IPlayer;
import common.IRoom;
import common.NPCID;

    /**
    * Class to handle all the interface for itemFacade
    *
    * @author Frederik Schultz Rosenberg
    * @author Andreas Bøgh Mølgaard-Andersen
    * @author Lars Bjerregaard Jørgensen
    * @author Robert Francisti
    */
public interface INPCFacade {
    
    /**
     * player interracts with npc
     *
     * @param player
     * @param NPC
     * @return interract player with npc
     */
    public String interact(IPlayer player, INPC NPC);
    
    /**
     * updates the moveable NPC's in the game
     */
    void update();

    /**
     * loads the npc rooms, names, if moveable.
     *
     * @param npcs
     */
    public void load(INPC[] npcs);

    /**
     *
     * @param id is the id of the npc
     * @param canMove checks if the npc is able to move
     * @param name name of the npc
     * @param currentRoomID of the npc
     */
    public void create(NPCID id, boolean canMove, String name, int currentRoomID);
    
     /**
     * Creates the npc
     *
     * @param id of the npc
     * @param canMove checks if the npc can move
     * @param name of the npc
     */
    public void create(NPCID id, boolean canMove, String name);
    
    /**
     * injector for map
     * @param map of the game
     */
    void injectMap(Map map);
     
    /**
     * Injector for business
     * @param business is businessfacade
     */ 
    void injectBusiness(BusinessFacade business);
    
    /**
     * getter method for all NPCs
     *
     * @return an Array with INPCs
     */
    public INPC[] getNPCs();

    /**
     * sets the room of an npc
     *
     * @param npc is the npc to set in a given room
     * @param roomID is the given room ID to set the npc in
     */
    void setRoom(INPC npc, int roomID);

     /**
     * sets an npc in the end room
     *
     * @param porter is the npc to be set in the end room
     * @param currentRoomID is the end room ID
     */
    public void setEndRoom(INPC porter, int currentRoomID);
    
    /**
     * porter checks and inspects player
     * @param player of the game
     */
    void porterCheckPlayer(IPlayer player);

     /**
     * Loops to get the npc's from a room.
     *
     * @param room
     * @return
     * @return array of npc's in the room.
     */
    public INPC[] getNPCsFromRoom(IRoom room);

    /**
     * resets npcs
     */
    public void reset();
}
