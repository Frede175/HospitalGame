package ui;

import common.IBusiness;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Tobias
 */
public class MenuController implements Initializable {

    @FXML
    private GridPane gridPaneMenu;
    
    private IBusiness business;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
    }    

    @FXML
    private void quitButtonAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void highscoreButtonAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml/Help.fxml"));
        Scene scene = new Scene(pane);
        UI.getInstance().getStage().setScene(scene);
    }

    @FXML
    private void loadButtonAction(ActionEvent event) {
        business.load();
    }

    @FXML
    private void saveButtonAction(ActionEvent event) {
        business.save();
    }

    @FXML
    private void playButtonAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml/Help.fxml"));
        Scene scene = new Scene(pane);
        UI.getInstance().getStage().setScene(scene);
    }
    
}
