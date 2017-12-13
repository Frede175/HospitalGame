/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.common.IItemFacade;
import business.common.INPCFacade;
import common.Direction;
import common.ICoordinate;
import common.IItem;
import common.INPC;
import common.IRoom;
import common.ItemName;
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
    private ArrayList<Room> rooms;

    /**
     * no args constructor for map
     */
    public Map() {
        rooms = new ArrayList<>();
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
    public Room generateMap(int roomCount, List<IItem> items, List<INPC> npcs) {

        // Creates the ArrayList that contains all the free rooms.
        ArrayList<Room> freeRooms = createRooms(roomCount);

        rooms.addAll(freeRooms);

        Direction[] directions = Direction.values();
        
        // Sets the start room to the first free room.
        Room startRoom = freeRooms.get(0);
        startRoom.setInspected();
        startRoom.setCoordinate(new Coordinate(0, 0));
        // Creates the queue where all the rooms that needs to be processed is stored.
        Queue<Room> roomsToProcess = new LinkedList<>();
        // Adds the first free room to the queue.
        roomsToProcess.add(freeRooms.remove(0));
        Set<ICoordinate> usedCoordinates = new HashSet<>();
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
                Direction direction = directions[index];
                // Calculates the opponent direction.
                Direction oppoDirection = directions[(index + 2) % 4];
                // Random selecting the neighbor room.
                int neighbor = (int) (Math.random() * freeRooms.size());
                // If the room dosent have an exit at that direction
                Coordinate c = Coordinate.add((Coordinate) currentRoom.getCoordinate(), getCoordinateDirection(direction));
                if (currentRoom.getExit(direction) == null && !usedCoordinates.contains(c)) {
                    // Sets an exit with the direction and the neighbor.
                    currentRoom.setExit(direction, freeRooms.get(neighbor));
                    // sets coordinates to every freeroom.
                    freeRooms.get(neighbor).setCoordinate((Coordinate) c);
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

        Room locked = rooms.get(0);
        //Find a room that only has one exit:
        for (Room room : rooms) {
            if (room.getExitDirections().size() == 1 && room != startRoom) {
                locked = room;
                break;
            }
        }

        // Add every item to a random room.
        locked.setLocked(true);

        Collections.shuffle(items);

        for (IItem item : items) {
            if (item.getName() == ItemName.BLOODBAG) {
                locked.addItem(item);
            } else {
                Room room;
                while ((room = rooms.get((int) (Math.random() * roomCount))) == locked) {
                }
                room.addItem(item);
            }

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

            Room room;
            while ((room = rooms.get((int) (Math.random() * roomCount))) == locked) {
            }
            npcFacade.setRoom(npc, room.getRoomID());
        }

        // Sets the doctors room in the Porter object.
        if (porter != null && doctor != null) {
            npcFacade.setEndRoom(porter, doctor.getCurrentRoomID());
        }

        // returns the start room.
        return startRoom;
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
            Room room = new Room(String.valueOf((char) (a + i)));
            room.injectItemFacade(itemFacade);
            room.injectMap(this);
            rooms.add(room);
        }
        return rooms;
    }

    /**
     * used to find the shortest path towards the doctor NPC
     *
     * @param startRoomID is the room where you start.
     * @param endRoomID is the room where you end.
     * @return rooms.
     */
    public List<Direction> pathfinder(int startRoomID, int endRoomID) {
        Room startRoom = rooms.get(startRoomID);
        Room endRoom = rooms.get(endRoomID);
        // Queue holds a list of the rooms that are going to be checked
        Queue<Room> queue = new LinkedList<>();
        //Hashmap holds the checked rooms and what direction we came from, that points to startRoom.
        java.util.Map<Room, Direction> pathMap = new HashMap<>();
        //Priming while loop by taking all the exits in the startRoom by adding it to queue. 
        for (Direction key : startRoom.getExitDirections()) {
            Room r = (Room) startRoom.getExit(key);
            queue.add(r);
        }

        pathMap.put((Room) startRoom, null);
        while (!queue.isEmpty()) {
            Room room = queue.poll();
            for (Direction key : room.getExitDirections()) {
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
        List<Direction> path = new ArrayList<>();
        Room currentRoom = (Room) endRoom;
        //going from endRoom to startRoom and storing directions. 
        while (currentRoom != startRoom) {
            Direction s = pathMap.get(currentRoom);
            path.add(s);
            currentRoom = (Room) currentRoom.getExit(s);

        }
        List<Direction> values = Arrays.asList(Direction.values());
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
    /**
     * 
     * @return an array containing all the rooms
     */

    public IRoom[] getRooms() {
        IRoom[] array = new IRoom[rooms.size()];
        rooms.toArray(array);
        return array;
    }

    /**
     *
     * @param d holds the coordinates to the directions in which room you're at.
     * @returns the SOUTH,EAST,WEST,NORTH coordinates.
     */
    private Coordinate getCoordinateDirection(Direction d) {
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
/**
 * checks if roomID is a valid ID
 * @param ID is the ID of a room
 * @return rooms by their Identification
 */
    public Room getRoomByID(int ID) {
        if (rooms.get(ID) == null) {
            return null;
        }
        if (rooms.get(ID).getRoomID() == ID) {
            return (Room) rooms.get(ID);
        }
        return null;
    }
/**
 * loads the rooms
 * @param arrayRooms array with rooms
 */
    public void load(IRoom[] arrayRooms) {
        for (IRoom room : arrayRooms) {
            Room r = new Room(room);
            r.injectItemFacade(itemFacade);
            r.injectMap(this);
            this.rooms.add(r);
        }

    }

    public void reset() {
        rooms.clear();
    }
}
