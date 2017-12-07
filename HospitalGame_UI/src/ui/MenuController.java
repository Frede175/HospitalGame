package ui;

 import common.GameState;
import common.IBusiness;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

/**
 * Menu Controller.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class MenuController implements Initializable {
    
    /**
     * Gets access to the business facade
     */
    private IBusiness business;
    
    /**
     * Contains the save button.
     */
    @FXML
    private Button saveBtn;
    
    /**
     * Contains the start button.
     */
    @FXML
    private Button startBtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        if(business.getGameState() == GameState.PAUSED ) {
            startBtn.setText("Resume");
            saveBtn.setDisable(false);
        } else {
            startBtn.setText("Play");
        }
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
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        VBox pane = FXMLLoader.load(getClass().getResource("fxml/HighScore.fxml"));
        Scene scene = new Scene(pane, screenSize.getWidth(), screenSize.getHeight());
        UI.getInstance().getStage().setScene(scene);
    }

    /**
     * load a saved game by calling the method called load() in the business facade
     * @param event checks if the button is pressed 
     */
    @FXML
    private void loadButtonAction(ActionEvent event) throws IOException {
        if(business.load()) {
            business.resume();
            loadMainGame();
        }
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
        if(business.getGameState() == GameState.PAUSED) {
            business.resume();
            loadMainGame();
        } else {
            business.play();
            loadMainGame();
        }
    }
    
    /**
     * Loads the main fxml and creates a scene and showing it in the primary stage.
     */
    private void loadMainGame() {
        saveBtn.setDisable(false);
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Main.fxml"));
        GridPane pane;
        try {
            pane = loader.load();
            MainController mainController = loader.getController();
            Scene scene = new Scene(pane, screenSize.getWidth(), screenSize.getHeight());
            mainController.injectScene(scene);
            mainController.setup();
            UI.getInstance().getStage().setMaximized(true);
            UI.getInstance().getStage().setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
