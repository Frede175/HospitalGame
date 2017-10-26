package hospitalgame;

/**
 * HospitalGame contains the main method
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 * @author Michael Kolling and David J. Barnes
 */
public class HospitalGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /**
         * Make the game run
         */
        Game game = Game.getGameInstance();
        game.play();
    }
    
}