/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.Directions;
import common.IBusiness;
import common.IPlayer;
import javafx.scene.input.KeyEvent;

public class KeyListener implements javafx.event.EventHandler<KeyEvent> {
    
    private MainController mainController;
    
    private InventoryController playerInventoryController;
    
    private InventoryController roomInventoryController;
    
    private IPlayer player;
    
    private IBusiness business;
    
    private NPCController npcController;
    
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
                mainController.move(Directions.NORTH);
                break;
            case S:
                mainController.move(Directions.SOUTH);
                break;
            case D:
                mainController.move(Directions.EAST);
                break;
            case A:
                mainController.move(Directions.WEST);
                break;
            case TAB:
                switch (UI.getInstance().getFocus()) {
                    case ROOM:
                        UI.getInstance().setFocus(UIType.NPC);
                        npcController.setFocus(true);
                        playerInventoryController.setFocus(false);
                        roomInventoryController.setFocus(false);
                        break;
                    case NPC:
                        UI.getInstance().setFocus(UIType.PLAYER);
                        npcController.setFocus(false);
                        playerInventoryController.setFocus(true);
                        roomInventoryController.setFocus(false);
                        break;
                    case PLAYER:
                        UI.getInstance().setFocus(UIType.ROOM);
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
                if(UI.getInstance().getFocus() == UIType.PLAYER) {
                    business.useItem(playerInventoryController.getSelectedIndex());
                    playerInventoryController.setSelectedIndex(0);
                    mainController.updateGUI();
                } else if(UI.getInstance().getFocus() == UIType.ROOM){
                    business.takeItem(roomInventoryController.getSelectedIndex());
                    roomInventoryController.setSelectedIndex(0);
                    mainController.updateGUI();
                } else {
                    npcController.interact();
                }
                break;
            case Q:
                if(UI.getInstance().getFocus() == UIType.PLAYER) {
                    business.dropItem(playerInventoryController.getSelectedIndex());
                    playerInventoryController.setSelectedIndex(0);
                    mainController.updateGUI();
                }
                break;
            case SHIFT:
                if(UI.getInstance().getFocus() == UIType.PLAYER) {
                    playerInventoryController.nextPage();
                } else {
                    roomInventoryController.nextPage();
                }
                break;
            case CONTROL:
                if(UI.getInstance().getFocus() == UIType.PLAYER) {
                    playerInventoryController.previousPage();
                } else {
                    roomInventoryController.previousPage();
                }
                break;
            case DIGIT1:
                if(UI.getInstance().getFocus() == UIType.PLAYER) {
                    playerInventoryController.setSelectedIndex(0);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else if(UI.getInstance().getFocus() == UIType.ROOM) {
                    roomInventoryController.setSelectedIndex(0);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                } else {
                    npcController.setSelectedIndex(0);
                    npcController.updateNPCSToGUI(player.getCurrentRoom());
                }
                break;
            case DIGIT2:
                if(UI.getInstance().getFocus() == UIType.PLAYER) {
                    playerInventoryController.setSelectedIndex(1);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else if(UI.getInstance().getFocus() == UIType.ROOM) {
                    roomInventoryController.setSelectedIndex(1);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                } else {
                    npcController.setSelectedIndex(1);
                    npcController.updateNPCSToGUI(player.getCurrentRoom());
                }
                break;
            case DIGIT3:
                if(UI.getInstance().getFocus() == UIType.PLAYER) {
                    playerInventoryController.setSelectedIndex(2);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else if(UI.getInstance().getFocus() == UIType.ROOM) {
                    roomInventoryController.setSelectedIndex(2);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                } else {
                    npcController.setSelectedIndex(2);
                    npcController.updateNPCSToGUI(player.getCurrentRoom());
                }
                break;
            case DIGIT4:
                if(UI.getInstance().getFocus() == UIType.PLAYER) {
                    playerInventoryController.setSelectedIndex(3);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else {
                    roomInventoryController.setSelectedIndex(3);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                }
                break;
            case DIGIT5:
                if(UI.getInstance().getFocus() == UIType.PLAYER) {
                    playerInventoryController.setSelectedIndex(4);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else {
                    roomInventoryController.setSelectedIndex(4);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                }
                break;
            case DIGIT6:
                if(UI.getInstance().getFocus() == UIType.PLAYER) {
                    playerInventoryController.setSelectedIndex(5);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else {
                    roomInventoryController.setSelectedIndex(5);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                }
            default:
                System.err.println("Dont have anyhting to do on that button");
        }
    }

}
