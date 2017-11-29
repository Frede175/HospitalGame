/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.IBusiness;
import common.INPC;
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
    
    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        loadNPCImages();
    } 
    
    /**
     * Load npc images and adding them to the scene.
     */
    public void loadNPCImages() {
        hBox.getChildren().clear();
        INPC[] npcs = business.getNPCs();
        for(INPC npc : npcs) {
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setPadding(new Insets(5));
            Label label = new Label(npc.getNPCID().toString());
            label.setPadding(new Insets(0, 0, 10, 0));
            vBox.getChildren().addAll(getImageOfNPC(npc), label);
            hBox.getChildren().add(vBox);
        }
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
    
}
