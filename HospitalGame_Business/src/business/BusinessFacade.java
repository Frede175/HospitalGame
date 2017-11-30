/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.Item.BloodBag;
import business.Item.ItemFacade;
import business.Item.PowerUpItem;
import business.NPC.NPCFacade;
import business.common.IData;
import business.common.IItemFacade;
import business.common.INPCFacade;
import common.BloodType;
import common.Directions;
import common.GameConstants;
import common.IBusiness;
import common.IHighScore;
import common.IItem;
import common.INPC;
import common.IPersistence;
import common.IPlayer;
import common.ItemName;
import common.NPCID;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class BusinessFacade implements IBusiness {

    /**
     * to gain access to item facade
     */
    private IItemFacade itemFacade;

    /**
     * to gain access to npc facade
     */
    private INPCFacade npcFacade;

    /**
     * gives access to data facade
     */
    private IData dataFacade;

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
     * a
     */
    private boolean isGameOver = false;

    public BusinessFacade() {
        map = new Map();
        itemFacade = new ItemFacade();
        npcFacade = new NPCFacade();
        map.injectItemFacade(itemFacade);
        map.InjectNPCFacade(npcFacade);
        
    }

    private void createRooms(int numberOfRooms) {
        // Gets all the bloodtypes into a BloodType array.
        BloodType[] bloodType = BloodType.values();
        // Creates a new Random object.
        Random random = new Random();
        // Random picks the players bloodtype.
        BloodType playerBloodType = bloodType[random.nextInt(bloodType.length)];
        // Initialize a new player object.
        player = new Player(playerBloodType, GameConstants.PLAYER_BLOODRATE, GameConstants.PLAYER_BLOOD_AMOUNT, "Jakob", itemFacade);
        player.injectBusinessFacade(this);
        // Creates a new ArrayList to contain all items.
        ArrayList<IItem> items = new ArrayList<>();
        // Adds a new item with the same bloodtype as the player, so the game is always winable.
        items.add(new BloodBag(450, ItemName.BLOODBAG, 450, player.getBloodType()));
        // Loops as many times as roomCount * 1.5
        for (int i = 0; i < numberOfRooms * 1.5; i++) {
            // Generate a random int to define what type of item that will be generated.
            int itemType = random.nextInt(2);
            switch (itemType) {
                // If 0 adds a new bloodbag
                case 0:
                    items.add(new BloodBag(GameConstants.BLOODBAG_SIZE, ItemName.BLOODBAG, GameConstants.BLOODBAG_SIZE,bloodType[random.nextInt(bloodType.length)]));
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
        npcFacade.create(NPCID.DOCTOR, false, "doctor", null);
        npcFacade.create(NPCID.PORTER, false, "porter", null);
        npcFacade.create(NPCID.COMPUTER, false, "computer", null);

        // Sets the current room for the player, and generates the rooms.
        player.setCurrentRoom(map.generateMap(numberOfRooms, items, Arrays.asList(npcFacade.getNPCs())));
    }

    /**
     *
     * @return Array with INPCs
     */
    @Override
    public INPC[] getNPCs() {
        return npcFacade.getNPCs();
    }

    public IPlayer getPLayer() {
        return player;
    }

    @Override
    public void play() {
        createRooms(12);
    }

    @Override
    public void quit() {
        throw new UnsupportedOperationException("not yet implemented.");
    }

    @Override
    public IHighScore getHighScore() {
        return highScore;
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        throw new UnsupportedOperationException("not yet implemented.");
    }

    @Override
    public boolean save() {
        
        System.out.println("You saved the game, have a nice day!");
        return true;
    }

    @Override
    public boolean load() {
        throw new UnsupportedOperationException("not yet implemented.");
    }

    /**
     * sets the game over if called
     */
    void setGameOver() {
        isGameOver = true;
    }

    /**
     * injection of injectionFacade
     *
     * @param persistence the persistence facade to inject
     */
    @Override
    public void injectPersistenceFacade(IPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public IPlayer getPlayer() {
        return player;
    }

    @Override
    public void move(Directions direction) {
        player.move(direction);
    }
}
