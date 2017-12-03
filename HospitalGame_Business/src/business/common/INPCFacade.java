/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.common;

import business.BusinessFacade;
import business.Map;
import common.Directions;
import common.IBusiness;
import common.INPC;
import common.IPlayer;
import common.IRoom;
import common.NPCID;

/**
 *
 * @author andreasmolgaard-andersen
 */
public interface INPCFacade {

    public String interact(IPlayer player, INPC NPC);

    void updateMove();

    public void load(INPC[] npcs);

    public void create(NPCID id, boolean canMove, String name, int currentRoomID);
    
    public void create(NPCID id, boolean canMove, String name);
    
    void injectMap(Map map);
    
    void injectBusiness(BusinessFacade business);

    public INPC[] getNPCs();

    void setRoom(INPC npc, int roomID);

    public void setEndRoom(INPC porter, int currentRoomID);
    
    void porterCheckPlayer(IPlayer player);

    public INPC[] getNPCsFromRoom(IRoom room);
}
