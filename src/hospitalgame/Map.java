package hospitalgame;

import hospitalgame.NPC.Computer;
import hospitalgame.NPC.Doctor;
import hospitalgame.NPC.Porter;
import hospitalgame.item.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
    public Room generateRoom(int roomCount, ArrayList<Item> items, Computer computer, Porter porter, Doctor doctor) {
        // Creates the ArrayList that contains all the free rooms.
        ArrayList<Room> freeRooms = createRooms(roomCount);
        // Add every item to a random room.
        for (Item item : items) {
            freeRooms.get((int) (Math.random() * roomCount)).addItem(item);
        }
        // Adds the NPCs to random rooms.
        computer.setRoom(freeRooms.get((int) (Math.random() * roomCount)));
        porter.setRoom(freeRooms.get((int) (Math.random() * roomCount)));
        doctor.setRoom(freeRooms.get((int) (Math.random() * roomCount)));
        // Sets the doctors room in the Porter object.
        porter.setEndRoom(doctor.getCurrentRoom());
        
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
        // returns the start room.
        return startRoom;
    }
    
    /**
     * Creates the rooms.
     * @param roomCount The count of how many rooms that will be generated.
     * @return A arraylist of all the generated rooms.
     */
    private ArrayList<Room> createRooms(int roomCount) {
        // TODO Create room descs array
        ArrayList<Room> rooms = new ArrayList<>();
        char a = 'a';
        for (int i = 0; i < roomCount; i++) {
            rooms.add(new Room(String.valueOf((char) (a + i))));
        }
        return rooms;
    }
    
    /**
     * pathfinder finds the way from startRoom to endRoom.
     *
     * @param startRoom startRoom is the room where the player starts.
     * @param endRoom endRoom is the location of the doctor
     * @return returns all directions to endRoom
     */
    public static List<String> pathfinder(Room startRoom, Room endRoom) {
        // Queue holds a list of the rooms that are going to be checked
        Queue<Room> queue = new LinkedList<>();
        //Hashmap holds the checked rooms and what direction we came from, that points to startRoom.
        java.util.Map<Room, String> pathMap = new HashMap<>();
        //Priming while loop by taking all the exits in the startRoom by adding it to queue. 
        for (String key : startRoom.getKeySet()) {
            Room r = startRoom.getExit(key);
            queue.add(r);
        }
        pathMap.put(startRoom, "start");
        while (!queue.isEmpty()) {
            Room room = queue.poll();
            for (String key : room.getKeySet()) {
                Room r = room.getExit(key);
                //If the room already has been checked, it then doesnt add it to the room.
                if (pathMap.containsKey(r)) {
                    //puts currentRoom r into visited rooms with a direction pointing towards start.
                    pathMap.put(room, key);
                } else {
                    queue.add(r);
                }
            }
        }
        List<String> path = new ArrayList<>();
        Room currentRoom = endRoom;
        //going from endRoom to startRoom and storing directions. 
        while (currentRoom != startRoom) {
            String s = pathMap.get(currentRoom);
            path.add(s);
            currentRoom = currentRoom.getExit(s);

        }
        //reversing order of list.
        Collections.reverse(path);
        //reverses path i.e south to north
        for (int i = 0; i < path.size(); i++) {
            int index = GameConstants.DIRECTIONS.indexOf(path.get(i));
            int newIndex = (index + 2) % 4;
            path.set(i, GameConstants.DIRECTIONS.get(newIndex));
        }
        return path;
    }
}
