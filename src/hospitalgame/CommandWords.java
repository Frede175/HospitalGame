package hospitalgame;

import java.util.HashMap;

/**
 * CommandWords contain and validate the player commands.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 * @author Michael Kolling and David J. Barnes
 */
public class CommandWords
{
    /**
     * Contains all the valid commands from the player.
     */
    private HashMap<String, CommandWord> validCommands;

    /**
     * Construct and initialize the CommandWords object.
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Gets the command word.
     * @param commandWord The command from the player.
     * @return The command the player entered, if its not valid it returns unknown.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Validating if the command is an actual command.
     * @param aString The commands as a String. 
     * @return True if the command is valid, else false.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }
    
    /**
     * Prints all the commands to the player.
     */
    public void showAll() 
    {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
