/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.common;

import common.Direction;
import common.IRoom;

/**
 *
 * @author rober
 */
public interface IMoveable {

    /**
     *
     * @param direction
     * @return
     */
    boolean move(Direction direction);
    
    long getLastMove();
    
    IRoom getCurrentRoom();
}
