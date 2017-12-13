/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.IBusiness;
import common.IPlayer;
import common.IPowerUpItem;
import common.ItemName;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Player Status Controller.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
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
    
    /**
     * Sets up the images, and runs the initial update.
     */
    public void setup() {
        bandage.setImage(imgRes.getSprite(Sprites.BANDAGE));
        morphine.setImage(imgRes.getSprite(Sprites.MORPHINE));
        updatePlayerDataToGUI();
    }
    
    /**
     * Updates all the player data to the gui.
     */
    public void updatePlayerDataToGUI() {
        bloodAmount.setText("Blood amount : " + player.getBloodAmount());
        bloodRate.setText("Blood rate : " + player.getBloodRate());
        if(player.isBloodTypeKnown()) {
            bloodType.setText("Bloodtype : " + player.getBloodType());
        } else {
            bloodType.setText("Bloodtype : ?");
        }
        int bandageCount = 0;
        int morphineCount = 0;
        for(IPowerUpItem item : player.getActiveItems()) {
            if(item.getName() == ItemName.BANDAGE) {
                bandageCount++;
            } else if(item.getName() == ItemName.MORPHINE) {
                morphineCount++;
            }
        }
        this.bandageCount.setText("x" + bandageCount);
        this.morphineCount.setText("x" + morphineCount);
    }    
}
