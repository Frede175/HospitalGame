package hospitalgame;

import java.util.Scanner;

/**
 * Parser is reading the player input and parsing it to the right command.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 * @author Michael Kolling and David J. Barnes
 */
public class Parser 
{
    /**
     * Contains the CommandWords object.
     */
    private CommandWords commands;
    /**
     * Contains the scanner to read player input.
     */
    private Scanner reader;

    /**
     * Construct and initialize the parser
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * Reads the player command.
     * @return A Command object with the player command.
     */
    public Command getCommand() 
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        return new Command(commands.getCommandWord(word1), word2);
    }

    /**
     * Prints all the commands to the player.
     */
    public void showCommands()
    {
        commands.showAll();
    }
}
