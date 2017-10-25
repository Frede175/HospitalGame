package hospitalgame;

import hospitalgame.NPC.Computer;
import hospitalgame.NPC.Doctor;
import hospitalgame.NPC.NPC;
import hospitalgame.NPC.Porter;
import hospitalgame.item.BloodBag;
import hospitalgame.item.Item;
import hospitalgame.item.ItemName;
import hospitalgame.item.PowerUpItem;
import java.util.ArrayList;
import java.util.Random;
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
public class Game {
    
    
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
     * Contains the computer npc
     */
    private Computer computer;
    
    /**
     * Contains the porter npc
     */
    private Porter porter;
    
    /**
     * Contains the doctor npc
     */
    private Doctor doctor;
    
    /**
     * Construct and initialize the game.
     */
    public Game() 
    {
        map = new Map();
        createRooms(4);
        parser = new Parser();
    }
    
    /**
     * Creates the rooms and sets the current room for the player.
     * @param numberOfRooms Sets the count of rooms to be generated.
     */
    private void createRooms(int numberOfRooms)
    {
        // Gets all the bloodtypes into a BloodType array.
        BloodType[] bloodType = BloodType.values();
        // Creates a new Random object.
        Random random = new Random();
        // Random picks the players bloodtype.
        BloodType playerBloodType = bloodType[random.nextInt(bloodType.length)];
        // Initialize a new player object.
        player = new Player(playerBloodType, GameConstants.PLAYER_BLOODRATE, GameConstants.PLAYER_BLOOD_AMOUNT, "Lars");
        // Creates a new ArrayList to contain all items.
        ArrayList<Item> items = new ArrayList<>();
        // Adds a new item with the same bloodtype as the player, so the game is always winable.
        items.add(new BloodBag(playerBloodType, GameConstants.BLOODBAG_SIZE, ItemName.BLOODBAG, GameConstants.BLOODBAG_SIZE));
        // Loops as many times as roomCount * 1.5
        for (int i = 0; i < numberOfRooms * 1.5; i++) {
            // Generate a random int to define what type of item that will be generated.
            int itemType = random.nextInt(2);
            switch (itemType) {
                // If 0 adds a new bloodbag
                case 0:
                    items.add(new BloodBag(bloodType[random.nextInt(bloodType.length)], GameConstants.BLOODBAG_SIZE, ItemName.BLOODBAG, GameConstants.BLOODBAG_SIZE));
                    break;
                // If 1 adds a new bandage
                case 1:
                    items.add(new PowerUpItem(GameConstants.BANDAGE_BUFF, GameConstants.BANDAGE_TIME, ItemName.BANDAGE, GameConstants.BANDAGE_WEIGHT));
                    break;
                // If 2 adds a new morphine
                case 2:
                    items.add(new PowerUpItem(GameConstants.MORPHINE_BUFF, GameConstants.MORPHINE_TIME, ItemName.MORPHINE, GameConstants.MORPHINE_WEIGHT));
                    break;
                default:
                    throw new AssertionError();
            }
        }     
        // Gets current room and generates the rooms with items and npc
        computer = new Computer("Computer", "I´m a computer");
        porter = new Porter("Peter", "I´m a porter");
        doctor = new Doctor("Hans", "I´m a doctor");
        currentRoom = map.generateRoom(numberOfRooms, items, computer, porter, doctor);
        player.setCurrentRoom(currentRoom);
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
        currentRoom.showItem();
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
        } else if (commandWord == CommandWord.GO) {
            if(player.goRoom(command)) {
                moveNPC();
            }
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.TAKE) {
            player.takeItem(command);
        } else if (commandWord == CommandWord.SHOW) {
            player.showInventory();
        } else if (commandWord == CommandWord.DROP) {
            player.dropItem(command);
        } else if (commandWord == CommandWord.USE) {
            player.useItem(command);
        } else if (commandWord == CommandWord.INTERACT) {
            interact(command);
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
            player.setCurrentRoom(currentRoom);
            System.out.println(currentRoom.getLongDescription());
            currentRoom.showItem();
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
        porter.move();
    }
    
    /**
     * Interacts with the NPC depending on the command.
     * @param command Is the command choosing which NPC that the player wants to interact with.
     */
    private boolean interact(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Interact with who?");
            return false;
        }
        // The NPC the player wants to interact with.
        String npcInteract = command.getSecondWord();
        switch (npcInteract) {
            case "computer":
                if (player.getCurrentRoom().equals(computer.getCurrentRoom())) {
                    computer.interact(player);
                } else {
                    System.out.println("There is no computer in this room.");
                }
                break;
            case "doctor":
                if (player.getCurrentRoom().equals(porter.getCurrentRoom())) {
                    porter.interact(player);
                } else {
                    System.out.println("There is no doctor in this room.");
                }
                break;
            case "porter":
                if (player.getCurrentRoom().equals(doctor.getCurrentRoom())) {
                    doctor.interact(player);
                } else {
                    System.out.println("There is no porter in this room.");
                }
                break;
            default:
                System.out.println("I don´t know how to interact with that.");
        }
        return true;
    }
}
