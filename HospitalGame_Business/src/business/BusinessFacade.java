package business;

import business.Item.BloodBag;
import business.Item.IDCard;
import business.Item.ItemFacade;
import business.Item.PowerUpItem;
import business.NPC.NPCFacade;
import business.common.IItemFacade;
import business.common.INPCFacade;
import common.BloodType;
import common.Directions;
import common.GameConstants;
import common.GameState;
import common.IBusiness;
import common.IHighScore;
import common.IItem;
import common.INPC;
import common.IPersistence;
import common.IPlayer;
import common.IRoom;
import common.ItemName;
import common.NPCID;
import common.IDataObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class BusinessFacade implements IBusiness {

    private ArrayList<Room> rooms = new ArrayList<>();

    /**
     * to gain access to item facade
     */
    private IItemFacade itemFacade;

    /**
     * to gain access to npc facade
     */
    private INPCFacade npcFacade;

    /**
     * is the player for business facade to manage
     */
    private Player player;

    /**
     * is the map of the game to hold
     */
    private Map map;

    /**
     * high score to save score of the game
     */
    private BusinessHighScore highScore;

    /**
     * to gain access to persistence
     */
    private IPersistence persistence;

    /**
     * The state of the game
     */
    private GameState gameState = GameState.NOT_STARTED;
    
    /**
     * Score of the game
     */
    private int score = 0;

    public BusinessFacade() {
        map = new Map();
        itemFacade = new ItemFacade();
        npcFacade = new NPCFacade();
        map.injectItemFacade(itemFacade);
        map.InjectNPCFacade(npcFacade);
        npcFacade.injectBusiness(this);
        npcFacade.injectMap(map);
        highScore = new BusinessHighScore();
    }

    private void createRooms(int numberOfRooms) {
        // Delete all stored objects
        reset();
        
        // Gets all the bloodtypes into a BloodType array.
        BloodType[] bloodType = BloodType.values();
        // Creates a new Random object.
        Random random = new Random();
        // Random picks the players bloodtype.
        BloodType playerBloodType = bloodType[random.nextInt(bloodType.length)];
        // Initialize a new player object.
        player = new Player(playerBloodType, GameConstants.PLAYER_BLOODRATE, GameConstants.PLAYER_BLOOD_AMOUNT, itemFacade);
        player.injectBusinessFacade(this);
        player.injectItemFacade(itemFacade);
        player.injectMap(map);
        // Creates a new ArrayList to contain all items.
        ArrayList<IItem> items = new ArrayList<>();
        // Adds a new item with the same bloodtype as the player, so the game is always winable.
        items.add(new BloodBag(450, ItemName.BLOODBAG, 450, player.getBloodType()));
        items.add(new IDCard(GameConstants.IDCARD_WEIGHT, ItemName.IDCARD));
        // Loops as many times as roomCount * 1.5
        for (int i = 0; i < numberOfRooms * 1.5; i++) {
            // Generate a random int to define what type of item that will be generated.
            int itemType = random.nextInt(3);
            switch (itemType) {
                // If 0 adds a new bloodbag
                case 0:
                    items.add(new BloodBag(GameConstants.BLOODBAG_SIZE, ItemName.BLOODBAG, GameConstants.BLOODBAG_SIZE, bloodType[random.nextInt(bloodType.length)]));
                    break;
                // If 1 adds a new bandage
                case 1:
                    items.add(new PowerUpItem(GameConstants.BANDAGE_BUFF, GameConstants.BANDAGE_TIME, ItemName.BANDAGE, GameConstants.BANDAGE_WEIGHT));
                    break;
                // If 2 adds a new morphine
                case 2:
                    items.add(new PowerUpItem(GameConstants.MORPHINE_BUFF, GameConstants.MORPHINE_TIME, ItemName.MORPHINE, GameConstants.MORPHINE_WEIGHT));
                    break;
                default:
                    throw new AssertionError();
            }
        }
        // Gets current room and generates the rooms with items and npc.
        npcFacade.create(NPCID.DOCTOR, false, "doctor");
        npcFacade.create(NPCID.PORTER, false, "porter");
        npcFacade.create(NPCID.COMPUTER, false, "computer");
        
        // Sets the current room for the player, and generates the rooms.
        player.setCurrentRoom(map.generateMap(numberOfRooms, items, Arrays.asList(npcFacade.getNPCs())).getRoomID());
    }
    
    private void reset() {
        itemFacade.reset();
        npcFacade.reset();
        map.reset();
        Room.reset();
        score = 0;
    }

    /**
     *
     * @return Array with INPCs
     */
    @Override
    public INPC[] getNPCsFromRoom(IRoom room) {
        return npcFacade.getNPCsFromRoom(room);
    }


    /**
     * 
     */
    @Override
    public void play() {
        createRooms(12);
        gameState = GameState.PLAYING;
    }

    /**
     * 
     */
    @Override
    public void quit() {
        
    }

    /**
     * 
     * @return 
     */
    @Override
    public IHighScore getHighScore() {
        return highScore;
    }

    /**
     * 
     */
    @Override
    public void pause() {
       gameState = GameState.PAUSED;
       player.pause();
    }

    /**
     * 
     */
    @Override
    public void resume() {
        gameState = GameState.PLAYING;
        player.resume();
    }

    /**
     * saves the game
     *
     * @return true if the game has been saved
     */
    @Override
    public boolean save() {
        player.pause();
        return persistence.saveGame(player, itemFacade.getInventories(), map.getRooms(), npcFacade.getNPCs());
    }

    /**
     * Load a saved game from the persistence layer.
     * @return true if the game got loaded.
     */
    @Override
    public boolean load() {
        IDataObject data = persistence.load();
        if (data == null) return false;
        reset();
        //loads in the player
        this.player = new Player(data.getPlayer());
        player.injectBusinessFacade(this);
        player.injectItemFacade(itemFacade);
        player.injectMap(map);

        //Loads the rooms
        map.load(data.getRooms());

        //loads in the inventories
        itemFacade.load(data.getInventories());

        //loads in the npcs
        npcFacade.load(data.getNPCs());
        
        return true;
    }

    /**
     * Sets the game state to lost
     */
    public void setGameOver() {
        gameState = GameState.LOST;
    }
    
    /**
     * Sets game state to won
     * @param score the score the player got in the game
     */
    public void setGameWon(int score) {
        if (gameState != GameState.LOST && gameState != GameState.WON) {
            this.score = score;
            gameState = GameState.WON;
        }
    }
    
    /**
     * injection of injectionFacade
     *
     * @param persistence the persistence facade to inject
     */
    @Override
    public void injectPersistenceFacade(IPersistence persistence) {
        this.persistence = persistence;
        highScore.load(persistence.getHighScore());
    }

    /**
     * returns the player
     *
     * @return player
     */
    @Override
    public IPlayer getPlayer() {
        return player;
    }

    /**
     * moves the player
     *
     * @param direction is the direction to move
     */
    @Override
    public boolean move(Directions direction) {
        boolean hasMoved = player.move(direction);
        npcFacade.porterCheckPlayer(player);
        return hasMoved;
    }
    
    public void porterMovePlayer(Directions direction) {
        player.move(direction);
    }

    /**
     * uses an item
     *
     * @param index is the index of the item to be used
     * @return true if the item has been used
     */
    @Override
    public boolean useItem(int index) {
        return player.useItem(index);
    }

    /**
     * drops an item from player to the room
     *
     * @param index is the index of the item to be dropped
     * @return true if the item has been dropped
     */
    @Override
    public boolean dropItem(int index) {
        return player.dropItem(itemFacade.getInventory(player.getInventoryID()).getItem(index));
    }

    /**
     * takes an item from the current room
     *
     * @param index of the item to be added to the
     * @return true if the item has been added to the player inventory
     */
    @Override
    public boolean takeItem(int index) {
        return player.takeItem(index);
    }

    /**
     * 
     * @return true if the game is over.
     */
    @Override
    public GameState getGameState() {
        npcFacade.update();
        if(player != null) player.update();
        return gameState;
    }
    
    /**
     * Make the player know its blood type.
     */
    public void playerBloodTypeKnown() {
        player.setBloodTypeKnown();
    }

    @Override
    public String interact(IPlayer player, INPC npc) {
        this.player.update();
        return npcFacade.interact(player, npc);
    }

    /**
     * 
     * @return the score if the game if won else it returns 0.
     */
    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean eligibleForHighScore() {
        return highScore.eligibleForHighscore(score);
    }

    @Override
    public boolean addHighScore(String name) {
        return highScore.addHighScore(name, score);
    }

    @Override
    public void closing() {
        if (highScore.isDirty()) {
            persistence.saveHighScore(highScore);
        }   
    }

    @Override
    public boolean isHighScoreNameTaken(String name) {
        return highScore.isNameTaken(name);
    }

    @Override
    public boolean saveGameAvailable() {
        return persistence.saveGameAvailable();
    }
    
}
