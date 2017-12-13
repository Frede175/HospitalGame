/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.common;

import common.Direction;
import common.IRoom;

    /**
    * Class to handle all the interface for moveAble
    *
    * @author Frederik Schultz Rosenberg
    * @author Andreas Bøgh Mølgaard-Andersen
    * @author Lars Bjerregaard Jørgensen
    * @author Robert Francisti
    */
public interface IMoveable {

    /**
     * checks if the npc can move
     * 
     * @param direction in which direction npc is supposed to move
     * @return true if canMove
     */
    boolean move(Direction direction);
    
    /**
     * getter for getLastMove function
     * @return NA
     */
    long getLastMove();
    
    /**
     * getter for getCurrentRoom
     * @return NA
     */
    IRoom getCurrentRoom();
}
