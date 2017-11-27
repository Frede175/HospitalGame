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
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Tobias
 */
public class HighscoreController implements Initializable {

    private IBusiness business;
    @FXML
    private TableView ScoreTabel;

    /**
     * Initializes the controller class.
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

        ScoreTabel.getColumns().addAll(nameColumn, scoreColumn);

        loadScores();
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

        ScoreTabel.setItems(allData);
    }

    /**
     * Returns the user to the menu
     * @param event checks if the button has been pressed
     * @throws IOException if file not found
     */
    @FXML
    private void MenuButtonAction(ActionEvent event) throws IOException {
        GridPane pane = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));
        Scene scene = new Scene(pane);
        UI.getInstance().getStage().setScene(scene);
    }
}
