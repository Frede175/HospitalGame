/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.ArrayList;

/**
 *
 * @author fsr19
 */
public interface IPlayer {

    IRoom getCurrentRoom();

    BloodType getBloodType();

    int getBloodAmount();

    double getBloodRate();

    ArrayList<? extends IPowerUpItem> getActiveItems();

    int getInventoryID();

    IInventory getInventory();

    String getName();

}
