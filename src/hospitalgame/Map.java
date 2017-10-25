package hospitalgame;

import java.util.ArrayList;
import java.util.Arrays;
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
     * @return The starting room for the player.
     */
    public Room generateRoom(int roomCount) {
        // TODO Lav array der indeholder forskellige room desc.
        String[] directions = {"north", "east", "south", "west"};
        ArrayList<Room> freeRooms = createRooms(roomCount);
        Room startRoom = freeRooms.get(0);
        Queue<Room> roomsToProcess = new LinkedList<>();
        
        roomsToProcess.add(freeRooms.remove(0));
        while (!roomsToProcess.isEmpty()) {
            Room currentRoom = roomsToProcess.poll();
            int i = 0;
            int exitCount = (int)(Math.random() * 4) + 1;
            while (i <=  exitCount && !freeRooms.isEmpty()) {
                int direction = (int)(Math.random() * 4);
                int oppoDirection = (direction + 2) % 4;
                int neighbor = (int)(Math.random() * freeRooms.size());
                if(currentRoom.getExit(directions[direction]) == null) {
                    currentRoom.setExit(directions[direction], freeRooms.get(neighbor));
                    freeRooms.get(neighbor).setExit(directions[oppoDirection], currentRoom);
                    roomsToProcess.add(freeRooms.get(neighbor));
                    freeRooms.remove(freeRooms.get(neighbor));
                }
                i++;
            }  
        }
        return startRoom;
    }
    
    private ArrayList<Room> createRooms(int roomCount) {
        ArrayList<Room> rooms = new ArrayList<>();
        char a = 'a';
        for (int i = 0; i < roomCount; i++) {
            rooms.add(new Room(String.valueOf((char) (a + i))));
        }
        return rooms;
    }
}
