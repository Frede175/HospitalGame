/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.IBusiness;
import common.IRoom;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Tobias
 */
public class MapController implements Initializable {

    private ImageView mapImageView;
    
    private IRoom currentRoom;
    
    private IBusiness business;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
    }  
    
    public void setRoom(IRoom currentRoom){
        
    }
    
}
