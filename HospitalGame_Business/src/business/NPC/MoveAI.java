package business.NPC;

import business.common.IMoveable;
import common.Direction;
import java.util.List;
import java.util.Random;
import java.util.Set;

    /**
    * Class to handle the moveAI
    *
    * @author Frederik Schultz Rosenberg
    * @author Andreas Bøgh Mølgaard-Andersen
    * @author Lars Bjerregaard Jørgensen
    * @author Robert Francisti
    */
public class MoveAI {
    
    //minimum value for moveAI
    private final int min = 5 * 1000;
    //maximum value for moveAI
    private final int max = 20 * 1000;
    //
    private final Random random = new Random();
    
    /**
     * Goes through all npcs and checks if they are of type IMoveable
     * @param npcs the npcs to check.
     */
    public void updateMoveableNPCs(List<NPC> npcs) {
        for (NPC npc : npcs) {
            if (npc instanceof IMoveable) {
                checkNPC((IMoveable)npc);
            }
        }
    }
    
    /**
     * If the last time the npc moved is greater than the random generated number the npc moves.
     * @param moveable the npc that needs to be checked.
     */
    private void checkNPC(IMoveable moveable) {
        int nextMove = random.nextInt(max - min);
        nextMove += min;
        
        long currentTime = System.currentTimeMillis();
        long diff = currentTime - moveable.getLastMove();
        
        if (diff > nextMove) {
            Set<Direction> dirs = moveable.getCurrentRoom().getExitDirections();
            
            int index = random.nextInt(dirs.size());
            
            int i = 0;
            for (Direction dir : dirs) {
                if (index == i) {
                    moveable.move(dir);
                    break;
                }
                i++;
            }   
        }
    }
}
