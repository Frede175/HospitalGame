/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.common;

import common.Directions;
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
    public boolean move(INPC npc, Directions dir);
    public void load(INPC[] npcs);
    public void create(NPCID id, boolean canMove, String name, IRoom currentRoom);
    public INPC[] getNPCs();
    void setRoom(INPC npc, IRoom room);
    public void setEndRoom(INPC porter, IRoom currentRoom);
}
