package persistence;

import common.ICoordinate;
import java.io.Serializable;

/**
 *
 * @author rober
 */
public class DataCoordinate implements ICoordinate, Serializable{
    
    /**
     * The coordinates x and y
     */
    private int x, y;
    
    /**
     * Constructor for creating a coordinate from the interface ICoordinate.
     * @param c 
     */
    public DataCoordinate(ICoordinate c) {
        x = c.getX();
        y = c.getY();
    }
    
    /**
     * 
     * @return the x coordinate
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * 
     * @return the y coordinate
     */
    @Override
    public int getY() {
        return y;
    }
    
}
