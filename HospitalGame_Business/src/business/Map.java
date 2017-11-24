/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.common.IItemFacade;
import business.common.INPCFacade;
import common.IItem;
import common.INPC;
import common.IRoom;
import java.util.ArrayList;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Map {

    private IItemFacade itemFacade;
    private INPCFacade npcFacade;
    private ArrayList<IRoom> rooms;

    public void Map() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void injectItemFacade(IItemFacade itemFacade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void InjectNPCFacade(INPCFacade npcFacade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void generateMap(int roomCount, ArrayList<IItem> items, ArrayList<INPC> npcs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    private IRoom[] createRooms(int roomCount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String[] pathfinder(IRoom startRoom, IRoom endRoom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public IRoom getRoombyId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public IRoom[] getRooms(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
