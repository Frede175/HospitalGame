/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.Directions;
import common.IBusiness;
import common.IPlayer;
import common.IRoom;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author larsjorgensen
 */
public class MainController implements Initializable {

    /**
     * Contains business reference.
     */
    private IBusiness business;
    
    /**
     * Contains ImageResource.
     */
    private ImageResource imgRes;
    
    /**
     * Contains the npc controller.
     */
    @FXML
    private NPCController npcController;
    
    @FXML 
    private PlayerStatusController playerStatusController;
    
    /**
     * The root container element
     */
    @FXML
    private GridPane root;
    
    /**
     * Contains the inventory controller for player.
     */
    @FXML
    private InventoryController inventoryPlayerController;
    
    /**
     * Contains the inventory controller for current room.
     */
    @FXML
    private InventoryController inventoryRoomController;
    
    /**
     * Contains the map controller.
     */
    @FXML
    private MapController mapController;
    
    /**
     * Contains the reference to the interact label.
     */
    @FXML
    private Label interactLabel;
    
    /**
     * Contains all the current direction buttons showed on the screen.
     */
    private ArrayList<HBox> buttons;
    
    /**
     * Contains the injected scene.
     */
    private Scene scene;
    
    /**
     * Contains the IPlayer reference.
     */
    private IPlayer player;

    
    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        imgRes = UI.getInstance().getImageResource();
        player = business.getPlayer();
        buttons = new ArrayList<>();
        inventoryPlayerController.setType(UIType.PLAYER);
        inventoryRoomController.setType(UIType.ROOM);
        npcController.setType(UIType.NPC);
        inventoryPlayerController.injectMainController(this);
        inventoryRoomController.injectMainController(this);
        npcController.injectMainController(this);
        updateGUI();
    }
    
    /**
     * Inject a scene into the controller.
     * @param scene 
     */
    public void injectScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Gets the inventory controller for the player.
     * @return The inventory controller for the player.
     */
    public InventoryController getInventoryPlayerController() {
        return inventoryPlayerController;
    }

    /**
     * Gets the inventory controller for the room.
     * @return The inventory controller for the room.
     */
    public InventoryController getInventoryRoomController() {
        return inventoryRoomController;
    }
    
    /**
     * Adds buttons to the main layout depending on the rooms direction.
     * @param room The room to get the directions from.
     */
    public void addButtons(IRoom room) {
        root.getChildren().removeAll(buttons);
        for(Directions dir : room.getExitDirections()) {
            switch (dir) {
                case NORTH:
                    root.add(createButton(dir, room.getExit(dir).isLocked()), 1, 0);
                    break;
                case SOUTH:
                    root.add(createButton(dir, room.getExit(dir).isLocked()), 1, 2);
                    break;
                case EAST:
                    root.add(createButton(dir, room.getExit(dir).isLocked()), 2, 1);
                    break;
                case WEST:
                    root.add(createButton(dir, room.getExit(dir).isLocked()), 0, 1);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    /**
     * Returns a button with the arrow with the direction, and on click goes to that direction.
     * @param direction The direction the arrow points and which direction to go.
     * @return A button with the direction given.
     */
    public HBox createButton(Directions direction, boolean isLocked) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Button btn = new Button();
        btn.setPrefHeight(70);
        btn.setPrefWidth(70);
        btn.setCursor(Cursor.HAND);
        Image img;
        switch (direction) {
            case EAST:
                img = imgRes.getSprite(Sprites.ARROW_RIGHT);
                break;
            case WEST:
                img = imgRes.getSprite(Sprites.ARROW_LEFT);
                break;
            case NORTH:
                img = imgRes.getSprite(Sprites.ARROW_UP);
                break;
            case SOUTH:
                img = imgRes.getSprite(Sprites.ARROW_DOWN);
                break;
            default:
                throw new AssertionError();
        }
        BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        btn.setBackground(background);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(direction.toString());
                business.move(direction);
                updateGUI();
            }
        });
        hBox.getChildren().add(btn);
        if(isLocked) {
            ImageView imageView = new ImageView(imgRes.getSprite(Sprites.LOCK));
            hBox.getChildren().add(imageView);
        }
        buttons.add(hBox);
        return hBox;
    }
    
    /**
     * Open the menu scene.
     * @throws IOException 
     */
    public void openMenu() throws IOException {
        UI.getInstance().getStage().setMaximized(true);
        UI.getInstance().getStage().setScene(UI.getInstance().getMenuScene());
    }
    
    /**
     * The setup function that calls the keylisteners.
     */
    public void setup() {
        scene.setOnKeyReleased(new KeyListener(this, inventoryPlayerController, inventoryRoomController, player, business, npcController));
        inventoryRoomController.setFocus(true);
        updateGUI();
    }
    
    public void setInteractionText(String text) {
        interactLabel.setText(text);
    }
    
    /**
     * Updates all the GUI, while checking if the game is lost or won.
     */
    public void updateGUI() {
        switch (business.getGameState()) {
            case PLAYING:
                setInteractionText("");
                npcController.updateNPCSToGUI(player.getCurrentRoom());
                playerStatusController.updatePlayerDataToGUI();
                inventoryPlayerController.updateItems(player.getInventory());
                inventoryRoomController.updateItems(player.getCurrentRoom().getInventory());
                addButtons(player.getCurrentRoom());
                mapController.drawMap();
                break;
            case LOST:
                // TODO SHOW LOST SCREEN
                break;
            case WON:
                // TODO SHOW WIN SCREEN
                System.out.println("derdpfeprpefpepfep");
                AnchorPane pane = new AnchorPane();
                pane.setBackground(new Background(new BackgroundImage(imgRes.getImage(Images.VICTORYSCREEN), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
                
                break; 
            default:
                throw new AssertionError();
        }
    }    
}
