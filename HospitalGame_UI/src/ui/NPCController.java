/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author larsjorgensen
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
    
    INPC[] npcs;
    
    private UIType type;  
    
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
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setPadding(new Insets(5));
            Label label = new Label(npcs[i].getNPCID().toString());
            label.setPadding(new Insets(0, 0, 10, 0));
            if(selectedIndex == i && isFocussed) {
                vBox.setStyle("-fx-border-color: blue;");
            }
            vBox.getChildren().addAll(getImageOfNPC(npcs[i]), label);
            hBox.getChildren().add(vBox);
        }
    }
    
    public void injectMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    /**
     * Returns the npc type image for the given npc.
     * @param npc which npc type image to return
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
    
    public void setFocus(boolean focus) {
        this.isFocussed = focus;
    }
    
    public int getSelectedIndex() {
        return selectedIndex;
    }
    
    public void setSelectedIndex(int index) {
        System.out.println("npcs lenght: " + npcs.length);
        if(index >= 0 && index < npcs.length) {
            this.selectedIndex = index;
        }
    }
    
    public void setType(UIType type) {
        this.type = type;
    }
    
    public void interact() {
        System.out.println("Interact: " + business.interact(player, npcs[selectedIndex]));
        mainController.updateGUI();
    }
    
}
