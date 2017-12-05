/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import common.ICoordinate;
import java.io.Serializable;

/**
 *
 * @author rober
 */
public class DataCoordinate implements ICoordinate, Serializable{
    
    private int x, y;
    
    public DataCoordinate(ICoordinate c) {
        x = c.getX();
        y = c.getY();
    }
    
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    
}
