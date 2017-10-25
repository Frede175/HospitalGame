package hospitalgame;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Game represents the core game functionality.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 * @author Michael Kolling and David J. Barnes
 */
public class Game 
{
    /**
     * Contains the parser object.
     */
    private Parser parser;
    /**
     * Contains the current room the player is in.
     */
    private Room currentRoom;
    
    /**
     * Contains the map class, to generate the map
     */
    private Map map;
    
    /**
     * Contains the player
     */
    private Player player;
    
    /**
     * Contains all the NPCs
     */
    //private ArrayList<NPC> NPCs;
    
    /**
     * Construct and initialize the game.
     */
    public Game() 
    {
        map = new Map();
        createRooms();
        parser = new Parser();
    }
    
    /**
     * Creating the game rooms and setting the current room.
     */
    private void createRooms()
    {
        currentRoom = map.generateRoom(12);
       /* Room outside, theatre, pub, lab, office;
        
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;*/
    }

    /**
     * Starts the game.
     */
    public void play() 
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Printing the welcome message.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Checking what command the player wants to use.
     * @param command The player command.
     * @return If the player wants to quit.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    /**
     * Printing the help message.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Goes to the room based on the player command.
     * @param command The player command.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * Checking if the player want to quit.
     * @param command The player command.
     * @return True if the player wants to quit, false if the player dosen´t wants to quit.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * Moves the NPC.
     */
    private void moveNPC() {
        throw new NotImplementedException();
    }
    
    /**
     * Interacts with the NPC depending on the command.
     * @param command Is the command choosing which NPC that the player wants to interact with.
     */
    private void interact(Command command) {
        throw new NotImplementedException();
    }
}
