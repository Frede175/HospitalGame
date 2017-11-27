package ui;

import common.IBusiness;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Tobias
 */
public class MenuController implements Initializable {
    
    /**
     * gets access to the business facade
     */
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

    /**
     * closes the program completely
     * @param event checks if the button is pressed 
     */
    @FXML
    private void quitButtonAction(ActionEvent event) {
        Platform.exit();
    }

    /**
     * changes scene to show the highscore
     * @param event checks if the button is pressed 
     * @throws IOException if a file was not found 
     */
    @FXML
    private void highscoreButtonAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml/Highscore.fxml"));
        Scene scene = new Scene(pane);
        UI.getInstance().getStage().setScene(scene);
    }

    /**
     * load a saved game by calling the method called load() in the business facade
     * @param event checks if the button is pressed 
     */
    @FXML
    private void loadButtonAction(ActionEvent event) {
        business.load();
    }

    /**
     * saves the current game
     * @param event checks if the button is pressed  
     */
    @FXML
    private void saveButtonAction(ActionEvent event) {
        business.save();
    }

    /**
     * starts the game
     * @param event checks if the button is pressed 
     * @throws IOException if a file is not found 
     */
    @FXML
    private void playButtonAction(ActionEvent event) throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
        Scene scene = new Scene(pane);
        UI.getInstance().getStage().setScene(scene);
    }
    
}
