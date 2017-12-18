package ui;

import common.Direction;
import common.IBusiness;
import common.IPlayer;
import common.IRoom;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;


/**
 * Main controller.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
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
    
    /**
     * Contains the PlayerStatusController
     */
    @FXML 
    private PlayerStatusController playerStatusController;
    
    /**
     * The root container element.
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
        inventoryPlayerController.setType(UIFocus.PLAYER);
        inventoryRoomController.setType(UIFocus.ROOM);
        npcController.setType(UIFocus.NPC);
        inventoryPlayerController.injectMainController(this);
        inventoryRoomController.injectMainController(this);
        npcController.injectMainController(this);
        updateGUI();
    }

    /**
     * Gets the scene.
     * @return The scene.
     */
    public Scene getScene() {
        return scene;
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
     * Checks if the player is kicked out by the Porter.
     * @param direction Which direction the player wants to move.
     * @return true if the player is kicked out.
     */
    public boolean kickedOut(Direction direction) {
        setInteractionText("");
        IRoom nextRoom = player.getCurrentRoom().getExit(direction);
        if (business.move(direction)) {
            if (nextRoom != player.getCurrentRoom()) return true;
        }
        return false;
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
        for(Direction dir : room.getExitDirections()) {
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
    public HBox createButton(Direction direction, boolean isLocked) {
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
                move(direction);
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
     * Moves the player, and setting the interact label to display if the player was kicked out by the Porter.
     * @param direction Which direction the player wants to move.
     */
    public void move(Direction direction) {
        if(kickedOut(direction)) {
            setInteractionText("You were kicked out by the porter");
        }
        updateGUI();
    }
    
    /**
     * Open the menu scene.
     */
    public void openMenu() {
        business.pause();
        try {
            Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Menu.fxml"));
            GridPane gridPane = loader.load();
            Scene winScene = new Scene(gridPane, screenSize.getWidth(), screenSize.getHeight());
            UI.getInstance().getStage().setScene(winScene);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * The setup function that calls the keylisteners.
     */
    public void setup() {
        scene.setOnKeyReleased(new KeyListener(this, inventoryPlayerController, inventoryRoomController, player, business, npcController));
        inventoryRoomController.setFocus(true);
        updateGUI();
    }
    
    /**
     * Sets the text in the interact label.
     * @param text The text to be shown.
     */
    public void setInteractionText(String text) {
        interactLabel.setText(text);
    }
    
    /**
     * Updates all the GUI, while checking if the game is lost or won.
     */
    public void updateGUI() {
        switch (business.getGameState()) {
            case PLAYING:
                npcController.updateNPCSToGUI(player.getCurrentRoom());
                playerStatusController.updatePlayerDataToGUI();
                inventoryPlayerController.updateItems(player.getInventory());
                inventoryRoomController.updateItems(player.getCurrentRoom().getInventory());
                addButtons(player.getCurrentRoom());
                mapController.drawMap();
                break;
            case LOST:
                openDeath();
                break;
            case WON:
                openWin();
                break; 
            default:
                throw new AssertionError();
        }
    }  
    
    /**
     * Opens the victoryscreen.
     */
    private void openWin() {
        try {
            Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Win.fxml"));
            VBox vBox = loader.load();
            WinController winController = loader.getController();
            winController.injectBusiness(business);
            winController.setup();
            Scene winScene = new Scene(vBox, screenSize.getWidth(), screenSize.getHeight());
            UI.getInstance().getStage().setMaximized(true);
            UI.getInstance().getStage().setScene(winScene);
            UI.getInstance().getStage().setMaximized(true);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Opens the deathscreen.
     */
    private void openDeath() {
        try {
            Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Death.fxml"));
            VBox vBox = loader.load();
            DeathController deathController = loader.getController();
            deathController.injectBusiness(business);
            deathController.setup();
            Scene winScene = new Scene(vBox, screenSize.getWidth(), screenSize.getHeight());
            UI.getInstance().getStage().setMaximized(true);
            UI.getInstance().getStage().setScene(winScene);
            UI.getInstance().getStage().setMaximized(true);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
