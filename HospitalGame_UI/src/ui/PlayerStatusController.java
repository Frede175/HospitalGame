/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.IBusiness;
import common.IItem;
import common.IPlayer;
import common.IPowerUpItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author larsb
 */
public class PlayerStatusController implements Initializable {

    /**
     * Contains the business facade reference.
     */
    private IBusiness business;
    
    /**
     * Contains the player.
     */
    private IPlayer player;
    
    /**
     * Contains the reference to Image Resource.
     */
    private ImageResource imgRes;
    
    /**
     * The name label in gui.
     */
    @FXML
    private Label name;
    
    /**
     * The blood amount label in gui.
     */
    @FXML
    private Label bloodAmount;
    
    /**
     * The blood rate label in gui.
     */
    @FXML
    private Label bloodRate;
    
    /**
     * The blood type label in gui.
     */
    @FXML
    private Label bloodType;
    
    /**
     * The bandage count label in gui.
     */
    @FXML
    private Label bandageCount;
    
    /**
     * The morphine count label in gui.
     */
    @FXML
    private Label morphineCount;
    
    /**
     * The bondage imageview in gui.
     */
    @FXML
    private ImageView bandage;
    
    /**
     * The morphine imageview in gui
     */
    @FXML
    private ImageView morphine;    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        imgRes = UI.getInstance().getImageResource();
        player = business.getPlayer();
        setup();
    } 
    
    public void setup() {
        bandage.setImage(imgRes.getSprite(Sprites.BANDAGE));
        morphine.setImage(imgRes.getSprite(Sprites.MORPHINE));
        name.setText(player.getName());
        updatePlayerDataToGUI();
    }
    
    public void updatePlayerDataToGUI() {
        bloodAmount.setText("Blood amount : " + player.getBloodAmount());
        bloodRate.setText("Blood rate : " + player.getBloodRate());
        // TODO check if player bloodtype is known.
        bloodType.setText("Bloodtype : " + player.getBloodType());
        for(IPowerUpItem item : player.getActiveItems()) {
            
        }
    }    
}
