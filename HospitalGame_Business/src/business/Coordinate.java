/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Coordinate {
    /**
     * is the x value of coordinate
     */
    private final int x;
    
    /**
     * is the y value of coordinate
     */
    private final int y;
    
    /**
     * Constructor for coordinate
     * @param x the x value for coordinate to hold
     * @param y the y value for coordinate to hold
     */
    public Coordinate(int x ,int y) {
        this.x = x;
        this.y = y;
    }
      
    /**
     * @return x value of coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * @return y value of coordinate 
     */
    public int getY() {
        return y;
    }
    
    /**
     * adds two coordinates together
     * @param a first coordinate
     * @param b second coordinate
     * @return third new coordinate which is the sum of a + b
     */
    public static Coordinate add(Coordinate a, Coordinate b) {
        return new Coordinate(a.getX() + b.getX(), a.getY() + b.getY());
    }
    
    /**
     * Subtracts two coordinates
     * @param a first coordinate
     * @param b second coordinate
     * @return third new coordinate which is the sum of a - b
     */
    public static Coordinate subtract(Coordinate a, Coordinate b) {
        return new Coordinate (a.getX() - b.getX(), a.getY() - b.getY());
    } 
     
}
