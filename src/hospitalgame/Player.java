package hospitalgame;

import hospitalgame.item.Inventory;
import hospitalgame.item.Item;
import hospitalgame.item.ItemName;
import hospitalgame.item.PowerUpItem;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class Player {

    /**
     * The players blood type
     */
    private BloodType bloodType;

    /**
     * The room the player is currently in
     */
    private Room currentRoom;

    /**
     * Players inventory that holds the players items
     */
    private Inventory inventory;

    /**
     * The player name
     */
    private String name;

    /**
     * The amount of blood the player has left
     */
    private double bloodAmount;

    /**
     * The default amount of blood the player loses
     */
    private double bloodRate;

    /**
     * When the last update to bloodAmount happened
     */
    private long lastUpdate;

    /**
     * The items that are active, and only PowerUpItems can be used. These items
     * apply effects on the player.
     */
    private ArrayList<PowerUpItem> activeItems;

    /**
     * Constructor for player
     *
     * @param bloodType the player have.
     * @param bloodRate the default amount of blood the player losses.
     * @param bloodAmount the amount of blood the player.
     * @param name player name.
     */
    public Player(BloodType bloodType, double bloodRate, double bloodAmount, String name) {
        this.bloodType = bloodType;
        this.bloodRate = bloodRate;
        this.bloodAmount = bloodAmount;
        this.name = name;

        inventory = new Inventory(2000);

        lastUpdate = System.currentTimeMillis();

        activeItems = new ArrayList<>();
    }

    /**
     * Sets the room of the player.
     *
     * @param currentRoom the room that the player is in.
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Prints the status of the player Blood amount Blood loss Active items
     */
    public void printStatus() {
        System.out.println("You have " + (int)bloodAmount + " ml. blood left");
        System.out.println("You are losing " + calculateLoss() + " blood every second");
        if (!activeItems.isEmpty()) {
            System.out.println("Currently active items: ");
            for (PowerUpItem item : activeItems) {
                System.out.printf("%15s (%.2f ml. blood/sec)", item, item.getBuff());
            }
        } else {
            System.out.println("You have no active items!");
        }

    }

    /**
     * Uses the item that is specified in the command.
     *
     * @param command the use item command
     */
    public void useItem(Command command) {
        if (command.hasSecondWord()) {
            int index;

            //Getting the index(int) from the command
            try {
                index = Integer.parseInt(command.getSecondWord());
            } catch (NumberFormatException ex) {
                System.out.println("That is not a number!");
                return;
            }

            Item item = inventory.getItem(index);

            if (item != null) { //Checking if the given index has an item
                if (item instanceof PowerUpItem) { //refactor? item isstandsof PowerUpItem
                    PowerUpItem power = (PowerUpItem) item;
                    power.startBuff(System.currentTimeMillis());
                    activeItems.add(power);
                    inventory.removeItem(item);
                    if (getNumberOfItemInActiveItems(ItemName.MORPHINE) >= 3) {
                        System.out.println("You died from an overdose of morphine");
                        Game.getGameInstance().setGameOver();
                    } else {
                        System.out.println("Activated " + power.getName()); //Other way to print item maybe a function?
                    }
                } else {
                    System.out.println("You can't use that item!");
                }
            } else {
                System.out.println("Can't find that item!");
            }
        } else { //No index for item
            System.out.println("Use what?");
        }
    }

    /**
     * The number of item of the item type in the active item list.
     *
     * @param item the item to look for.
     * @return the number of items with that type.
     */
    private int getNumberOfItemInActiveItems(ItemName item) {
        int count = 0;
        for (PowerUpItem activeItem : activeItems) {
            if (activeItem.getName() == item) {
                count++;
            }
        }
        return count;
    }

    /**
     * Added the item to the inventory.
     *
     * @param item The item the be added
     * @return true if the item is successfully added to the inventory
     */
    public boolean addItem(Item item) {
        return inventory.addItem(item);
    }

    /**
     * Removes the current item
     *
     * @param item the item to be removed
     * @return true if the item is removed successfully from the inventory.
     */
    public boolean removeItem(Item item) {
        return inventory.removeItem(item);
    }

    /**
     * Get the current room that the player is in.
     *
     * @return the current room.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * The blood amount the player has.
     *
     * @return the blood amount.
     */
    public double getBloodAmount() {
        return bloodAmount;
    }

    /**
     *
     * @return the blood type the player has
     */
    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     * Print the contents of the inventory to the console.
     */
    public void showInventory() {
        if (inventory.getTotalCount() <= 0) {
            System.out.println("You have no items");
        } else {
            System.out.println("Items in player's inventory:");
            inventory.showItems();
        }
    }

    /**
     * Gets the item the specified name.
     *
     * @param name The name of items
     * @return an array of items that has the specified name.
     */
    public Item[] getItemsByName(ItemName name) {
        return inventory.getItemsByName(name);
    }

    /**
     * Goes the to next room with the given exit.
     *
     * @param command the go command
     * @return true if the player was able to go the requested room
     */
    public boolean goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return false;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
            return false;
        } else {
            currentRoom = nextRoom;
            //Showing items in room and inventory
            currentRoom.showItem();
            showInventory();
            return true;
        }
    }

    /**
     * Process the take item command.
     *
     * @param command the take item command
     */
    public void takeItem(Command command) {
        if (command.hasSecondWord()) {
            int index;

            //Getting the index(int) from the command
            try {
                index = Integer.parseInt(command.getSecondWord());
            } catch (NumberFormatException ex) {
                System.out.println("That is not a number!");
                return;
            }

            Item item = currentRoom.getItem(index);

            if (item != null) { //Checking if the given index has an item
                if (addItem(item)) { //If addItem returns false, then the item has not been added to the inventory
                    currentRoom.removeItem(item);
                    System.out.println("Picked up " + item.getName());
                } else {
                    System.out.println("You don't have room for that");
                }
            } else {
                System.out.println("Can't find that item!");
            }
        } else { //No index for item
            System.out.println("Take what?");
        }
    }

    /**
     * Process the drop item command. Drops the item from the inventory and puts
     * it in the current room that the player is in.
     *
     * @param command the drop item command
     */
    public void dropItem(Command command) {
        if (command.hasSecondWord()) {
            int index;

            //Getting the index(int) from the command
            try {
                index = Integer.parseInt(command.getSecondWord());
            } catch (NumberFormatException ex) {
                System.out.println("That is not a number!");
                return;
            }

            Item item = inventory.getItem(index);

            if (item != null) { //Checking if the given index has an item
                if (removeItem(item)) {
                    currentRoom.addItem(item);
                    System.out.println("Dropped " + item.getName());
                } else {
                    System.out.println("Can't remove item from inventory.");
                }
            } else {
                System.out.println("Can't find that item!");
            }
        } else { //No index for item
            System.out.println("Drop what?");
        }
    }

    /**
     * Calculates the current blood loss and updates the players blood amount.
     */
    public void update() {
        long current = System.currentTimeMillis();
        long diff = current - lastUpdate;
        lastUpdate = current;

        //The player loses bloodRate every sec.
        double loss = bloodRate * diff / 1000;

        //Using iterator to loop through, since we need to be able to remove a item the from the list
        for (Iterator<PowerUpItem> iterator = activeItems.iterator(); iterator.hasNext();) {
            PowerUpItem item = iterator.next(); //Getting the next item in the list

            long timeLeftBeforeUpdate = item.getTimeLeftOfBuff();

            item.update(current);

            //using an if to see if the value is negativ, since we don't want to affect the remaing time that need to taking care of.
            long powerDiff = timeLeftBeforeUpdate - (item.getTimeLeftOfBuff() < 0 ? 0 : item.getTimeLeftOfBuff());

            loss -= item.getBuff() * powerDiff / 1000;

            if (item.getTimeLeftOfBuff() <= 0) { // Buff is no longer active!
                iterator.remove();
            }
        }
        bloodAmount -= loss;

        if (bloodAmount <= 0) {
            System.out.println("You died from blood loss!");
            Game.getGameInstance().setGameOver();
        }
    }

    /**
     * Returns the blood loss that happens every sec (this is calculated from
     * the last update)
     *
     * @return the blood loss per sec.
     */
    private double calculateLoss() {
        double loss = bloodRate;

        for (PowerUpItem item : activeItems) {
            loss -= item.getBuff();
        }
        return loss;
    }
}
