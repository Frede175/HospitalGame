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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

/**
 * FXML Controller class
 *
 * @author larsb
 */
public class DeathController implements Initializable {

    private IBusiness business;
    
    private ImageResource imgRes;
    
    @FXML
    private VBox root;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
     
    public void setup() {
        UI.getInstance().getStage().setMaximized(true);
        imgRes = UI.getInstance().getImageResource();
        root.setBackground(new Background(new BackgroundImage(imgRes.getImage(Images.DEATHSCREEN), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(40, 40, true, true, true, false))));
    }

    public void injectBusiness(IBusiness business) {
        this.business = business;
    }
    
}
