/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fsr19
 */
public interface IPlayer {

    IRoom getCurrentRoom();

    BloodType getBloodType();

    int getBloodAmount();

    double getBloodRate();

    List<? extends IPowerUpItem> getActiveItems();

    int getInventoryID();

    IInventory getInventory();

    public int getCurrentRoomID();
    
    boolean isBloodTypeKnown();

}
