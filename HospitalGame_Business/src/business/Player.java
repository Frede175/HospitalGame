/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.Item.PowerUpItem;
import business.common.IItemFacade;
import common.BloodType;
import common.Directions;
import common.IInventory;
import common.IItem;
import common.IPlayer;
import common.IRoom;
import common.ItemName;
import java.util.ArrayList;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Player implements IPlayer {

    private IItemFacade itemFacade;
    private String name;
    private double bloodAmount;
    private double bloodRate;
    private long lastUpdate;
    private PowerUpItem[] activeItems;
    private BusinessFacade businessFacade;
    private int inventoryID;
    private int currentRoom;
    

    @Override
    public IRoom getCurrentRoom() {
        return currentRoom;
    }

    @Override
    public BloodType getBloodType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getBloodAmount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getBloodLoss() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<IItem> getActiveItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Player(BloodType bloodType, double bloodRate, double bloodAmount, String name, BusinessFacade businessFacade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
    }

    public void Player(IPlayer player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void injectItemFacade(IItemFacade itemFacade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean useItem(IItem item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean move(Directions direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean takeItem(IItem item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean dropItem(IItem item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setCurrentRoom(IRoom currentRoom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void printStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void getNumberOfItemInActiveItems(ItemName item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double calculateLoss() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getInventoryID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IInventory getInventory() {
        return itemFacade.getInventory(inventoryID);
    }

}
