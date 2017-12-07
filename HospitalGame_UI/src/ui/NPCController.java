 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.GameState;
import common.IBusiness;
import common.INPC;
import common.IPlayer;
import common.IRoom;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * NPC Controller.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class NPCController implements Initializable {

    /**
     * Contains the reference to the business facade.
     */
    private IBusiness business;
    
    /**
     * Containing all the npc images in the scene.
     */
    @FXML
    private HBox hBox;
    
    /**
     * Contains the IPlayer reference.
     */
    @FXML
    private IPlayer player;
    
    /**
     * Contains which npc that is selected
     */
    private int selectedIndex = 0;
    
    /**
     * Contains if the npc fragment is selected
     */
    private boolean isFocussed = false;
    
    /**
     * Array containing which NPCs currently shown on screen.
     */
    INPC[] npcs;
    
    /**
     * Which UIFocus this controller is.
     */
    private UIFocus type;  
    
    /**
     * Contains the main controller instance.
     */
    private MainController mainController;
    
    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        player = business.getPlayer();
        updateNPCSToGUI(player.getCurrentRoom());
    } 
    
    /**
     * Updates the npc images of the npcs in the players current room.
     * @param room Which room to check for npcs, typically the players current room.
     */
    public void updateNPCSToGUI(IRoom room) {
        hBox.getChildren().clear();
        npcs = business.getNPCsFromRoom(room);
        for(int i = 0; i < npcs.length; i++) {
            hBox.getChildren().add(createNPCUI(i));
        }
    }
    
    /**
     * Generates a VBox containing a NPC icon, a label and an onClick interact.
     * @param index Which NPC in the room from the NPC array.
     * @return A VBox containing a NPC icon, a label with the name and an onClick interact.
     */
    public VBox createNPCUI(int index) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5));
        Label label = new Label(npcs[index].getNPCID().toString());
        label.setPadding(new Insets(0, 0, 10, 0));
        if(selectedIndex == index && isFocussed) {
            vBox.setStyle("-fx-border-color: blue;");
        }
        vBox.getChildren().addAll(getImageOfNPC(npcs[index]), label);
        vBox.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if(e.isPrimaryButtonDown()) {
                mainController.setInteractionText(business.interact(player, npcs[index]));
                mainController.updateGUI();
            }
        });
        return vBox;
    }
    
    /**
     * Injects a MainController.
     * @param mainController Which MainController to inject.
     */
    public void injectMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    /**
     * Returns the npc type image for the given npc.
     * @param npc which npc type image to return.
     * @return the given npc type image.
     */
    public ImageView getImageOfNPC(INPC npc) {
        ImageView img = new ImageView();
        img.setPreserveRatio(true);
        img.setFitHeight(70);
        img.setFitWidth(70);
        switch (npc.getNPCID()) {
            case COMPUTER:
                img.setImage(UI.getInstance().getImageResource().getImage(Images.COMPUTER));
                break;
            case DOCTOR:
                img.setImage(UI.getInstance().getImageResource().getImage(Images.DOCTOR));
                break;
            case PORTER:
                img.setImage(UI.getInstance().getImageResource().getImage(Images.PORTER));
                break;
            default:
                throw new AssertionError();
        }
        return img;
    }
    
    /**
     * Sets this fragment to be focussed.
     * @param focus True if this fragment is focussed.
     */
    public void setFocus(boolean focus) {
        this.isFocussed = focus;
    }
    
    /**
     * Gets the selected index.
     * @return The selected index.
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }
    
    /**
     * Sets the selected index.
     * @param index Which index to be selected.
     */
    public void setSelectedIndex(int index) {
        if(index >= 0 && index < npcs.length) {
            this.selectedIndex = index;
        }
    }
    
    /**
     * Sets this fragments type
     * @param type Which type this fragment is.
     */
    public void setType(UIFocus type) {
        this.type = type;
    }
    
    /**
     * Interacts with the NPC and player, an setting the interact String to the interact label.
     */
    public void interact() {
        mainController.setInteractionText(business.interact(player, npcs[selectedIndex]));
        mainController.updateGUI();
    }
    
}
