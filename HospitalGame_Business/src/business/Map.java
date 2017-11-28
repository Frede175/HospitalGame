/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.common.IItemFacade;
import business.common.INPCFacade;
import common.Directions;
import common.IItem;
import common.INPC;
import common.IRoom;
import common.NPCID;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Map {

    /**
     * to gain access to item facade
     */
    private IItemFacade itemFacade;

    /**
     * to gain access to npc facade
     */
    private INPCFacade npcFacade;

    /**
     * an ArrayList of rooms
     */
    private ArrayList<IRoom> rooms;

    /**
     * Hash map that holds the processed rooms, along with coordinates
     */
    private HashMap<Coordinate, IRoom> gameMap;

    /**
     * no args constructor for map
     */
    public void Map() {

    }

    /**
     * injector for item Facade
     *
     * @param itemFacade is the itemFacade to be injected
     */
    public void injectItemFacade(IItemFacade itemFacade) {
        this.itemFacade = itemFacade;
    }

    /**
     * injector for npc facade
     *
     * @param npcFacade is the npcFacade to be injected
     */
    public void InjectNPCFacade(INPCFacade npcFacade) {
        this.npcFacade = npcFacade;
    }

    /**
     * generates the map for the game
     *
     * @param roomCount how many rooms are to be in the game
     * @param items which items are to be put in the game
     * @param npcs which npcs are to be put in the game
     */
    public void generateMap(int roomCount, ArrayList<IItem> items, ArrayList<INPC> npcs) {
        // Creates the ArrayList that contains all the free rooms.
        ArrayList<Room> freeRooms = createRooms(roomCount);
        // Add every item to a random room.
        for (IItem item : items) {
            freeRooms.get((int) (Math.random() * roomCount)).addItem(item);
        }
        // Adds the NPCs to random rooms.
        INPC porter = null;
        INPC doctor = null;

        for (INPC npc : npcs) {
            if (npc.getNPCID() == NPCID.DOCTOR) {
                doctor = npc;
            }
            if (npc.getNPCID() == NPCID.PORTER) {
                porter = npc;
            }

            npcFacade.setRoom(npc, freeRooms.get((int) (Math.random() * roomCount)));
        }

        // Sets the doctors room in the Porter object.
        if (porter != null && doctor != null) {
            npcFacade.setEndRoom(porter, doctor.getCurrentRoom());
        }

        Directions[] directions = Directions.values();
        // Sets the start room to the first free room.
        Room startRoom = freeRooms.get(0);
        startRoom.setCoordinate(new Coordinate(0, 0));
        // Creates the queue where all the rooms that needs to be processed is stored.
        Queue<Room> roomsToProcess = new LinkedList<>();
        // Adds the first free room to the queue.
        roomsToProcess.add(freeRooms.remove(0));
        Set<Coordinate> usedCoordinates = new HashSet<>();
        usedCoordinates.add(new Coordinate(0, 0));
        // As long as the queue is not empty.
        while (!roomsToProcess.isEmpty()) {
            // Gets the next room in the queue.
            Room currentRoom = roomsToProcess.poll();
            int i = 0;
            // Generates a random number of exits the room has.
            int exitCount = (int) (Math.random() * 4) + 1;
            // As long as there is free rooms left and the room needs more exits.
            while (i <= exitCount && !freeRooms.isEmpty()) {
                // Generates the random direction.
                int index = (int) (Math.random() * 4);
                Directions direction = directions[index];
                // Calculates the opponent direction.
                Directions oppoDirection = directions[(index + 2) % 4];
                // Random selecting the neighbor room.
                int neighbor = (int) (Math.random() * freeRooms.size());
                // If the room dosent have an exit at that direction
                Coordinate c = Coordinate.add(currentRoom.getCoordinate(), getCoordinateDirection(direction));
                if (currentRoom.getExit(direction) == null && !usedCoordinates.contains(c)) {
                    // Sets an exit with the direction and the neighbor.
                    currentRoom.setExit(direction, freeRooms.get(neighbor));
                    freeRooms.get(neighbor).setCoordinate(c);
                    usedCoordinates.add(c);
                    // Sets the neighbor rooms exit to be the current room.
                    freeRooms.get(neighbor).setExit(oppoDirection, currentRoom);
                    // Adds the neighbor room to the queue.
                    roomsToProcess.add(freeRooms.get(neighbor));
                    // Remove the neighbor room from the free rooms ArrayList.
                    freeRooms.remove(freeRooms.get(neighbor));

                }
                i++;
            }
        }

        // returns the start room.
    }

    /**
     * Creates the rooms.
     *
     * @param roomCount The count of how many rooms that will be generated.
     * @return An arraylist of all the generated rooms.
     */
    private ArrayList<Room> createRooms(int roomCount) {
        ArrayList<Room> rooms = new ArrayList<>();
        char a = 'a';
        for (int i = 0; i < roomCount; i++) {
            rooms.add(new Room(String.valueOf((char) (a + i))));
        }
        return rooms;
    }

    /**
     * used to find the shortest path towards the doctor NPC
     *
     * @param startRoom
     * @param endRoom
     * @return
     */
    public static List<Directions> pathfinder(Room startRoom, Room endRoom) {
        // Queue holds a list of the rooms that are going to be checked
        Queue<Room> queue = new LinkedList<>();
        //Hashmap holds the checked rooms and what direction we came from, that points to startRoom.
        java.util.Map<Room, Directions> pathMap = new HashMap<>();
        //Priming while loop by taking all the exits in the startRoom by adding it to queue. 
        for (Directions key : startRoom.getExitDirections()) {
            Room r = (Room) startRoom.getExit(key);
            queue.add(r);
        }
        pathMap.put(startRoom, null);
        while (!queue.isEmpty()) {
            Room room = queue.poll();
            for (Directions key : room.getExitDirections()) {
                Room r = (Room) room.getExit(key);
                //If the room already has been checked, it then doesnt add it to the room.
                if (pathMap.containsKey(r)) {
                    //puts currentRoom r into visited rooms with a direction pointing towards start.
                    pathMap.put(room, key);
                } else {
                    queue.add(r);
                }
            }
        }
        List<Directions> path = new ArrayList<>();
        Room currentRoom = endRoom;
        //going from endRoom to startRoom and storing directions. 
        while (currentRoom != startRoom) {
            Directions s = pathMap.get(currentRoom);
            path.add(s);
            currentRoom = (Room) currentRoom.getExit(s);

        }
        List<Directions> values = Arrays.asList(Directions.values());
        //reversing order of list.
        Collections.reverse(path);
        //reverses path i.e south to north
        for (int i = 0; i < path.size(); i++) {
            int index = values.indexOf(path.get(i));
            int newIndex = (index + 2) % 4;
            path.set(i, values.get(newIndex));
        }
        return path;
    }

    public IRoom[] getRooms() {
        IRoom[] array = new IRoom[rooms.size()];
        rooms.toArray(array);
        return array;
    }

    private Coordinate getCoordinateDirection(Directions d) {
        switch (d) {
            case SOUTH:
                return new Coordinate(0, -1);
            case NORTH:
                return new Coordinate(0, 1);
            case EAST:
                return new Coordinate(1, 0);
            case WEST:
                return new Coordinate(-1, 0);
            default:
                throw new AssertionError(d.name());

        }
    }
}
