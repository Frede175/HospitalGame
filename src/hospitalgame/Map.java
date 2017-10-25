package hospitalgame;

import hospitalgame.NPC.NPC;
import hospitalgame.item.Item;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Map class is handling everything with the map.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class Map {
    /**
     * Generate the rooms for the game.
     * @param roomCount The count of how many rooms that needs to be generated.
     * @param items ArrayList of items that needs to be set in the rooms.
     * @param NPCs ArrayList of NPCs that needs to be set in the rooms.
     * @return The starting room for the player.
     */
    public Room generateRoom(int roomCount, ArrayList<Item> items, ArrayList<NPC> NPCs) {
        // Creates the ArrayList that contains all the free rooms.
        ArrayList<Room> freeRooms = createRooms(roomCount);
        // Add every item to a random room.
        for (Item item : items) {
            freeRooms.get((int) Math.random() * roomCount).addItem(item);
        }
        // Sets the start room to the first free room.
        Room startRoom = freeRooms.get(0);
        // Creates the queue where all the rooms that needs to be processed is stored.
        Queue<Room> roomsToProcess = new LinkedList<>();
        // Adds the first free room to the queue.
        roomsToProcess.add(freeRooms.remove(0));
        // Aslong as the queue is not empty.
        while (!roomsToProcess.isEmpty()) {
            // Gets the next room in the queue.
            Room currentRoom = roomsToProcess.poll();
            int i = 0;
            // Generates a random number of exits the room have.
            int exitCount = (int)(Math.random() * 4) + 1;
            // Aslong as there is free rooms left and the room needs more exits.
            while (i <=  exitCount && !freeRooms.isEmpty()) {
                // Generates the random direction.
                int direction = (int)(Math.random() * 4);
                // Calculates the opponent direction.
                int oppoDirection = (direction + 2) % 4;
                // Random selecting the neighbor room.
                int neighbor = (int)(Math.random() * freeRooms.size());
                // If the room dosent have an exit at that direction
                if(currentRoom.getExit(GameConstants.DIRECTIONS.get(direction)) == null) {
                    // Sets an exit with the direction and the neighbor.
                    currentRoom.setExit(GameConstants.DIRECTIONS.get(direction), freeRooms.get(neighbor));
                    // Sets the neighbor rooms exit to be the current room.
                    freeRooms.get(neighbor).setExit(GameConstants.DIRECTIONS.get(oppoDirection), currentRoom);
                    // Adds the neighbor room to the queue.
                    roomsToProcess.add(freeRooms.get(neighbor));
                    // Remove the neighbor room from the free rooms ArrayList.
                    freeRooms.remove(freeRooms.get(neighbor));
                }
                i++;
            }  
        }
        return startRoom;
    }
    
    /**
     * Creates the rooms.
     * @param roomCount The count of how many rooms that will be generated.
     * @return A arraylist of all the generated rooms.
     */
    private ArrayList<Room> createRooms(int roomCount) {
        ArrayList<Room> rooms = new ArrayList<>();
        char a = 'a';
        for (int i = 0; i < roomCount; i++) {
            rooms.add(new Room(String.valueOf((char) (a + i))));
        }
        return rooms;
    }
}
