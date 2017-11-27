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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author larsjorgensen
 */
public class NPCController implements Initializable {

    private IBusiness business;
    
    @FXML
    private VBox vBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        loadNPCImages();
    } 
    
    public void loadNPCImages() {
        INPC[] npcs = business.getNPCs();
        for(INPC npc : npcs) {
            Label label = new Label(npc.getNPCID().toString());
            label.setPadding(new Insets(0, 0, 10, 0));
            vBox.getChildren().addAll(getImageOfNPC(npc), label);
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
