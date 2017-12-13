package ui;

import common.IBusiness;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;

/**
 * Controller for the highscore screen.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class HighScoreController implements Initializable {

    /**
     * Contains the business facade instance
     */
    private IBusiness business;
    
    /**
     * Contains the scoretable in the view
     */
    @FXML
    private TableView scoreTable;

    /**
     * Initializes the controller class.
     * Where it is creating the columns for the tableview, and sorting it by points descending.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        TableColumn nameColumn = new TableColumn("Name");
        TableColumn scoreColumn = new TableColumn("Score");
        nameColumn.setPrefWidth(500);
        scoreColumn.setPrefWidth(500);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        scoreTable.getColumns().addAll(nameColumn, scoreColumn);

        loadScores();
        
        scoreColumn.setSortType(TableColumn.SortType.DESCENDING);
        scoreTable.getSortOrder().add(scoreColumn);
        scoreTable.sort();
    }

    /**
     * Creates an oberservable list in order to show all the scores. By adding a
     * new UIHighScore it takes a IHighscore parameter which makes it possible
     * to get the name and the given score to that person.
     */
    private void loadScores() {
        ObservableList<Score> allData = FXCollections.observableArrayList();

        UIHighScore highScore = new UIHighScore(business.getHighScore()); 
        List<Score> scores = highScore.getScoreList();
        
        if(scores != null){
            allData.addAll(scores);
        }

        scoreTable.setItems(allData);
    }

    /**
     * Returns the user to the menu
     * @param event checks if the button has been pressed
     * @throws IOException if file not found
     */
    @FXML
    private void MenuButtonAction(ActionEvent event) throws IOException {
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
