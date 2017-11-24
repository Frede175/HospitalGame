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
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author larsjorgensen
 */
public class NPCController implements Initializable {

    private IBusiness business;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        loadNPCImages();
    } 
    
    public void loadNPCImages() {
        INPC[] npcs = business.getNPCs();
        for(INPC npc : npcs) {
            
        }
    }
    
    public ImageView getImageOfNPC() {
        ImageView img = new ImageView();
        img.setImage(UI.getInstance().getSprites().getImage(1));
        return img;
    }
    
}
