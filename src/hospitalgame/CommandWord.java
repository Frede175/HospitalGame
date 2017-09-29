package hospitalgame;

/**
 * CommandWord contains all the valid commands in the game.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 * @author Michael Kolling and David J. Barnes
 */
public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?");
    
    /**
     * Contains the command.
     */
    private String commandString;
    
    /**
     * Construct and initialize the CommandWord object.
     * @param commandString The command the player entered.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * Returns the string containing the player command.
     * @return Returns the command string as a String.
     */
    @Override
    public String toString()
    {
        return commandString;
    }
}
