/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.IBusiness;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

/**
 * FXML Controller class
 *
 * @author larsb
 */
public class WinController implements Initializable {
    
    /**
     * Contains the reference to business.
     */
    private IBusiness business;
    
    @FXML
    private VBox root;
    
    @FXML
    private VBox highScore;
    
    @FXML
    private TextField nameTF;
    
    @FXML
    private Label scoreLabel;
    
    private ImageResource imgRes;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UI.getInstance().getStage().setMaximized(true);
        imgRes = UI.getInstance().getImageResource();
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        root.setBackground(new Background(new BackgroundImage(imgRes.getImage(Images.VICTORYSCREEN), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(40, 40,true, true, true, false))));
    }

    public void injectBusiness(IBusiness business) {
        this.business = business;
    }
    
    public void submitHighScore() {
        
    }
    
}
