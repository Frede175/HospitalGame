/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.Direction;
import common.IBusiness;
import common.IPlayer;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyEvent;

/**
 * Eventhandler.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class KeyListener implements javafx.event.EventHandler<KeyEvent> {
    /**
     * Contains the main controller instance
     */
    private MainController mainController;
    
    /**
     * Contains the player inventory controller instance
     */
    private InventoryController playerInventoryController;
    
    /**
     * Contains the room inventory controller instance
     */
    private InventoryController roomInventoryController;
    
    /**
     * Contains the player instance
     */
    private IPlayer player;
    
    /**
     * Contains the business instance
     */
    private IBusiness business;
    
    /**
     * Contains the npc controller instance
     */
    private NPCController npcController;
    
    /**
     * Constructor
     * @param mainController MainController to be used.
     * @param playerInventoryController PlayerInventoryController to be used.
     * @param roomInventoryController RoomInventoryController to be used.
     * @param player Player to be used.
     * @param business Business to be used.
     * @param npcController NPCController to be used.
     */
    public KeyListener(MainController mainController, InventoryController playerInventoryController, InventoryController roomInventoryController, IPlayer player, IBusiness business, NPCController npcController) {
        this.mainController = mainController;
        this.playerInventoryController = playerInventoryController;
        this.roomInventoryController = roomInventoryController;
        this.player = player;
        this.business = business;
        this.npcController = npcController;
    }
    
    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                mainController.move(Direction.NORTH);
                break;
            case S:
                mainController.move(Direction.SOUTH);
                break;
            case D:
                mainController.move(Direction.EAST);
                break;
            case A:
                mainController.move(Direction.WEST);
                break;
            case TAB:
                switch (UI.getInstance().getFocus()) {
                    case ROOM:
                        UI.getInstance().setFocus(UIFocus.NPC);
                        npcController.setFocus(true);
                        playerInventoryController.setFocus(false);
                        roomInventoryController.setFocus(false);
                        break;
                    case NPC:
                        UI.getInstance().setFocus(UIFocus.PLAYER);
                        npcController.setFocus(false);
                        playerInventoryController.setFocus(true);
                        roomInventoryController.setFocus(false);
                        break;
                    case PLAYER:
                        UI.getInstance().setFocus(UIFocus.ROOM);
                        npcController.setFocus(false);
                        playerInventoryController.setFocus(false);
                        roomInventoryController.setFocus(true);
                        break;
                    default:
                        throw new AssertionError();
                }
                playerInventoryController.updateItems(playerInventoryController.getInventory());
                roomInventoryController.updateItems(roomInventoryController.getInventory());
                npcController.updateNPCSToGUI(player.getCurrentRoom());
                break;
            case E:
                if(UI.getInstance().getFocus() == UIFocus.PLAYER) {
                    business.useItem(playerInventoryController.getSelectedIndex());
                    playerInventoryController.setSelectedIndex(0);
                    mainController.updateGUI();
                } else if(UI.getInstance().getFocus() == UIFocus.ROOM){
                    business.takeItem(roomInventoryController.getSelectedIndex());
                    roomInventoryController.setSelectedIndex(0);
                    mainController.updateGUI();
                } else {
                    npcController.interact();
                }
                break;
            case Q:
                if(UI.getInstance().getFocus() == UIFocus.PLAYER) {
                    business.dropItem(playerInventoryController.getSelectedIndex());
                    playerInventoryController.setSelectedIndex(0);
                    mainController.updateGUI();
                }
                break;
            case SHIFT:
                if(UI.getInstance().getFocus() == UIFocus.PLAYER) {
                    playerInventoryController.nextPage();
                } else {
                    roomInventoryController.nextPage();
                }
                break;
            case CONTROL:
                if(UI.getInstance().getFocus() == UIFocus.PLAYER) {
                    playerInventoryController.previousPage();
                } else {
                    roomInventoryController.previousPage();
                }
                break;
            case DIGIT1:
                if(UI.getInstance().getFocus() == UIFocus.PLAYER) {
                    playerInventoryController.setSelectedIndex(0);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else if(UI.getInstance().getFocus() == UIFocus.ROOM) {
                    roomInventoryController.setSelectedIndex(0);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                } else {
                    npcController.setSelectedIndex(0);
                    npcController.updateNPCSToGUI(player.getCurrentRoom());
                }
                break;
            case DIGIT2:
                if(UI.getInstance().getFocus() == UIFocus.PLAYER) {
                    playerInventoryController.setSelectedIndex(1);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else if(UI.getInstance().getFocus() == UIFocus.ROOM) {
                    roomInventoryController.setSelectedIndex(1);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                } else {
                    npcController.setSelectedIndex(1);
                    npcController.updateNPCSToGUI(player.getCurrentRoom());
                }
                break;
            case DIGIT3:
                if(UI.getInstance().getFocus() == UIFocus.PLAYER) {
                    playerInventoryController.setSelectedIndex(2);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else if(UI.getInstance().getFocus() == UIFocus.ROOM) {
                    roomInventoryController.setSelectedIndex(2);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                } else {
                    npcController.setSelectedIndex(2);
                    npcController.updateNPCSToGUI(player.getCurrentRoom());
                }
                break;
            case DIGIT4:
                if(UI.getInstance().getFocus() == UIFocus.PLAYER) {
                    playerInventoryController.setSelectedIndex(3);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else {
                    roomInventoryController.setSelectedIndex(3);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                }
                break;
            case DIGIT5:
                if(UI.getInstance().getFocus() == UIFocus.PLAYER) {
                    playerInventoryController.setSelectedIndex(4);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else {
                    roomInventoryController.setSelectedIndex(4);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                }
                break;
            case DIGIT6:
                if(UI.getInstance().getFocus() == UIFocus.PLAYER) {
                    playerInventoryController.setSelectedIndex(5);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else {
                    roomInventoryController.setSelectedIndex(5);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                }
            case ESCAPE:
                if(UI.getInstance().getStage().getScene().equals(mainController.getScene())) {
                    mainController.openMenu();
                }
        }
    }

}
