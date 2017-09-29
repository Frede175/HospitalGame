package hospitalgame;

/** 
 * Command is handling core command functions.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 * @author Michael Kolling and David J. Barnes
 */

public class Command
{
    /**
     * Contains the CommandWord.
     */
    private CommandWord commandWord;
    /**
     * Contains the second word the player entered.
     */
    private String secondWord;

    /**
     * Construct and initialize the Command object.
     * @param commandWord The command from the player as a CommandWord.
     * @param secondWord The second string from the player as a String.
     */
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    /**
     * Gets the command.
     * @return The command as an CommandWord.
     */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    /**
     * Get the second word.
     * @return The second word as an String.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * Checking if the command is unkvown.
     * @return True if the command is unknown, else false.
     */
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * Checking if there is a second word in the player command.
     * @return True if there is a second word, else false.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

