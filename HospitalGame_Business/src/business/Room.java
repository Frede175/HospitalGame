/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.common.IItemFacade;
import common.Directions;
import common.IItem;
import common.IRoom;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Room implements IRoom {
    private IItemFacade itemFacade;
    private String name;
    private HashMap<String, IRoom> exits;
    private boolean inspected;
    
    public Room(String name){
        
    }
    
    public void injectItemFacade(IItemFacade itemFacade){
        
    }
    
    public void setExit(Directions direction, IRoom roomNeighbour) {
        
    }
   
    public boolean addItem(IItem item) {
        return false;
    }
    
    public boolean removeItem(IItem item) {
        return false;
    }
    
    public IItem getItem(int index) {
        return null;
    }
    
    public Set getKeySet() {
        return null;
    }
    
    @Override
    public IRoom getExit(Directions direction){
        return null;   
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> getExitStrings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isLocked() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isInspected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
