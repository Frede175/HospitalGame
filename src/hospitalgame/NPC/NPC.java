package hospitalgame.NPC;
import hospitalgame.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Scanner;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public abstract class NPC {
    /**
     * Name of the NPC
     */
    
    private String name;
    Scanner scanner = new Scanner(System.in);
    String porterName = "Porter";
    String doctorName = "Doctor";
    String ComputerName = "Computer";
    
    
    /**
     * Description of the NPC
     */
    private String description;
    
    /**
     * Room object to tell which the NPC is in
     */
    private Room currentRoom;
    
    /**
     * Constructor of the NPC objects
     * @param currentRoom is which room the NPC is in
     * @param name is the name of the NPC
     * @param description gives a decription of the NPC
     */
    public NPC(Room currentRoom, String name, String description){

    }
    
    /**
     * 
     * @return which room the NPC is in
     */
    public Room getCurrentRoom(){
        return Room;
//throw new NotImplementedException();
    }
    
    /**
     * 
     * @return the name of the NPC
     */
    public String getName(){
        //throw new NotImplementedException();
        return name;
        
    }
    
    /**
     * 
     * @return the description of which NPC the player is interacting with
     */
    public String getDescription(){
        //throw new NotImplementedException();
        return description;
    }
    
    /**
     * has no function, but will call a method of the same name at a object
     * @param player is a reference to the player
     */
    public abstract void interact(Player player);
}
