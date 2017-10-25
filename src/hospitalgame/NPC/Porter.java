package hospitalgame.NPC;

import hospitalgame.*;
import java.util.HashMap;
import java.util.Map;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Queue;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class Porter extends Move {

    /**
     * endRoom holds the location of the doctor
     */
    private Room endRoom;

    /**
     * Calls the NPC constructor through the super
     *
     * @param name name of the NPC moving
     * @param description decription of the NPC that moves
     */
    public Porter(String name, String description, Room endRoom) {
        super(name, description);
        this.endRoom = endRoom;
    }

    /**
     * Overrides the abstract method in NPC
     *
     * @param player is the player object
     */
    @Override
    public void interact(Player player) {

        System.out.print("These directions will lead you two rooms ahead ");
        List<String> path = pathfinder(player.getCurrentRoom(), endRoom);
        for (int i = 0; i < 2 && i < path.size(); i++) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println();
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
        Queue<Room> queue = new LinkedList<Room>();
        //Hashmap holds the checked rooms and what direction we came from, that points to startRoom.
        Map<Room, String> pathMap = new HashMap<>();
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
                    pathMap.put(r, key);
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
        for (String dir : path) {
            int index = GameConstants.DIRECTIONS.indexOf(dir);
            int newIndex = (index + 2) % 4;
            dir = GameConstants.DIRECTIONS.get(newIndex);
        }
        return path;
    }

}
