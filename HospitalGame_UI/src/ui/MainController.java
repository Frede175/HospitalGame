/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.Directions;
import common.IBusiness;
import common.IRoom;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;

/**
 * FXML Controller class
 *
 * @author larsjorgensen
 */
public class MainController implements Initializable {

    /**
     * Contains business reference.
     */
    private IBusiness business;
    
    /**
     * Contains ImageResource.
     */
    private ImageResource imgRes;
    
    /**
     * Contains the npc controller.
     */
    @FXML
    private NPCController npcController;
    
    /**
     * The root container element
     */
    @FXML
    private GridPane root;
    
    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        imgRes = UI.getInstance().getImageResource();
        root.add(createButton(Directions.NORTH), 1, 1);
    }    
    
    /**
     * Adds buttons to the main layout depending on the rooms direction.
     * @param room The room to get the directions from.
     */
    public void addButtons(IRoom room) {
        
    }
    
    /**
     * Returns a button with the arrow with the direction, and on click goes to that direction.
     * @param direction The direction the arrow points and which direction to go.
     * @return A button with the direction given.
     */
    public Button createButton(Directions direction) {
        Button btn = new Button();
        btn.setPrefHeight(100);
        btn.setPrefWidth(100);
        Image img;
        switch (direction) {
            case EAST:
                img = imgRes.getSprite(Sprites.ARROW_RIGHT);
                break;
            case WEST:
                img = imgRes.getSprite(Sprites.ARROW_LEFT);
                break;
            case NORTH:
                img = imgRes.getSprite(Sprites.ARROW_UP);
                break;
            case SOUTH:
                img = imgRes.getSprite(Sprites.ARROW_DOWN);
                break;
            default:
                throw new AssertionError();
        }
        BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        btn.setBackground(background);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Create move function business Facade.
                System.err.println("Need move");
            }
        });
        return btn;
    }
    
    public void openMenu() throws IOException {
        UI.getInstance().getStage().setMaximized(true);
        UI.getInstance().getStage().setScene(UI.getInstance().getMenuScene());
    }
    
}
