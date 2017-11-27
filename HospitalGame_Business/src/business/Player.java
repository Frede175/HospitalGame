/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

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
    private ArrayList<IItem> activeItems = new ArrayList<>();
    private BusinessFacade businessFacade;
    private int inventoryID;
    private Room currentRoom;
    private BloodType bloodType;
    

    @Override
    public IRoom getCurrentRoom() {
        return currentRoom;
    }

    @Override
    public BloodType getBloodType() {
        return bloodType;
        
    }
        
    @Override
    public int getBloodAmount() {
        return (int)bloodAmount;
    }

    @Override
    public double getBloodLoss() {
        return bloodRate;
    }

    @Override
    public ArrayList<IItem> getActiveItems() {
        return activeItems;
    }

    public Player(BloodType bloodType, double bloodRate, double bloodAmount, String name, BusinessFacade businessFacade) {
        this.bloodType = bloodType;
        this.bloodRate = bloodRate;
        this.bloodAmount = bloodAmount;
        this.name = name;
        this.businessFacade = businessFacade;
        
        inventoryID = itemFacade.createInventory(2000);

        lastUpdate = System.currentTimeMillis();

        activeItems = new ArrayList<>();
    }
/*
    public Player(IPlayer player) {
        this.bloodType = player.getBloodType();
        this.bloodRate = player.getBloodLoss();
        this.bloodAmount = player.getBloodAmount();
        this.name = player.getName();
        this.businessFacade = player.getBusinessFacade();
    }
*/

    public void injectItemFacade(IItemFacade itemFacade) {
        this.itemFacade = itemFacade;
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

    @Override
    public String getName() {
        return name;
    }

}
