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
    
    private boolean playersInventory;
    
    public KeyListener(MainController mainController, InventoryController playerInventoryController, InventoryController roomInventoryController, IPlayer player, IBusiness business) {
        this.mainController = mainController;
        this.playerInventoryController = playerInventoryController;
        this.roomInventoryController = roomInventoryController;
        this.player = player;
        this.business = business;
    }
    
    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                business.move(Directions.NORTH);
                mainController.updateGUI();
                break;
            case S:
                business.move(Directions.SOUTH);
                mainController.updateGUI();
                break;
            case D:
                business.move(Directions.EAST);
                mainController.updateGUI();
                break;
            case A:
                business.move(Directions.WEST);
                mainController.updateGUI();
                break;
            case TAB:
                playersInventory = ((playersInventory == true) ? false : true);
                if(playersInventory) {
                    playerInventoryController.setFocus(true);
                    roomInventoryController.setFocus(false);
                } else {
                    playerInventoryController.setFocus(false);
                    roomInventoryController.setFocus(true);
                }
                playerInventoryController.updateItems(playerInventoryController.getInventory());
                roomInventoryController.updateItems(roomInventoryController.getInventory());
                break;
            case E:
                if(playersInventory) {
                    business.useItem(playerInventoryController.getSelectedIndex());
                    mainController.updateGUI();
                } else {
                    
                }
                break;
            case Q:
                if(playersInventory) {
                    business.dropItem(playerInventoryController.getSelectedIndex());
                    mainController.updateGUI();
                } else {
                    
                }
                
                break;
            case SHIFT:
                if(playersInventory) {
                    playerInventoryController.nextPage();
                } else {
                    roomInventoryController.nextPage();
                }
                break;
            case CONTROL:
                if(playersInventory) {
                    playerInventoryController.previousPage();
                } else {
                    roomInventoryController.previousPage();
                }
                break;
            case DIGIT1:
                if(playersInventory) {
                    playerInventoryController.setSelectedIndex(0);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else {
                    roomInventoryController.setSelectedIndex(0);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                }
                break;
            case DIGIT2:
                if(playersInventory) {
                    playerInventoryController.setSelectedIndex(1);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else {
                    roomInventoryController.setSelectedIndex(1);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                }
                break;
            case DIGIT3:
                if(playersInventory) {
                    playerInventoryController.setSelectedIndex(2);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else {
                    roomInventoryController.setSelectedIndex(2);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                }
                break;
            case DIGIT4:
                if(playersInventory) {
                    playerInventoryController.setSelectedIndex(3);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else {
                    roomInventoryController.setSelectedIndex(3);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                }
                break;
            case DIGIT5:
                if(playersInventory) {
                    playerInventoryController.setSelectedIndex(4);
                    playerInventoryController.updateItems(playerInventoryController.getInventory());
                } else {
                    roomInventoryController.setSelectedIndex(4);
                    roomInventoryController.updateItems(roomInventoryController.getInventory());
                }
                break;
            case DIGIT6:
                if(playersInventory) {
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
