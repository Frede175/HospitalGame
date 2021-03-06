package business;

import common.ICoordinate;

    /**
    * Class to handle coordinates in rooms
    *
    *  @author Frederik Schultz Rosenberg
    * @author Andreas Bøgh Mølgaard-Andersen
    * @author Lars Bjerregaard Jørgensen
    * @author Robert Francisti
    */
public class Coordinate implements ICoordinate {

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
     *
     * @param x the x value for coordinate to hold
     * @param y the y value for coordinate to hold
     */
    public Coordinate(int x, int y) {
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
     *
     * @param a first coordinate
     * @param b second coordinate
     * @return third new coordinate which is the sum of a + b
     */
    public static Coordinate add(Coordinate a, Coordinate b) {
        return new Coordinate(a.getX() + b.getX(), a.getY() + b.getY());
    }

    /**
     * Subtracts two coordinates
     *
     * @param a first coordinate
     * @param b second coordinate
     * @return third new coordinate which is the sum of a - b
     */
    public static Coordinate subtract(Coordinate a, Coordinate b) {
        return new Coordinate(a.getX() - b.getX(), a.getY() - b.getY());
    }
    /**
    * checks if object is a coordinate. 
    * 
    * @param obj is an object
    * @return coordinates to object
    */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate c = (Coordinate) obj;
            return x == c.getX() && y == c.getY();
        }
        return false;
    }
    /**
     * generates a random hashcode
     * @return a random hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.x;
        hash = 79 * hash + this.y;
        return hash;
    }

}
