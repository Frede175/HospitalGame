/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.IBusiness;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

/**
 * Victory Controller.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
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
    
    @FXML
    private Label highScoreLabel;
    
    private ImageResource imgRes;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setup() {
        UI.getInstance().getStage().setMaximized(true);
        imgRes = UI.getInstance().getImageResource();
        root.setBackground(new Background(new BackgroundImage(imgRes.getImage(Images.VICTORYSCREEN), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(40, 40,true, true, true, false))));
        scoreLabel.setText("Your score was " + business.getScore() + "!");
        if(business.eligibleForHighScore()) {
            highScore.setVisible(true);
            highScore.setDisable(false);
        }
    }

    public void injectBusiness(IBusiness business) {
        this.business = business;
    }
    
    public void submitHighScore() {
        if(!business.isHighScoreNameTaken(nameTF.getText())) {
            business.addHighScore(nameTF.getText());
            openMenu();
        } else {
            highScoreLabel.setText("That name is already taken, please enter a new one");
        }
    }  
    
    public void openMenu() {
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
}
