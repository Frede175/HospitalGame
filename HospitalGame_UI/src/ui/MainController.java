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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

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
    
    private Scene scene;
    
    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        imgRes = UI.getInstance().getImageResource();
        // TODO Add buttons via addButton(IRoom room);
    }    
    
    public void injectScene(Scene scene) {
        this.scene = scene;
    }
    
    /**
     * Adds buttons to the main layout depending on the rooms direction.
     * @param room The room to get the directions from.
     */
    public void addButtons(IRoom room) {
        for(Directions dir : room.getExitDirections()) {
            switch (dir) {
                case NORTH:
                    root.add(createButton(dir), 1, 0);
                    break;
                case SOUTH:
                    root.add(createButton(dir), 1, 2);
                    break;
                case EAST:
                    root.add(createButton(dir), 2, 1);
                    break;
                case WEST:
                    root.add(createButton(dir), 0, 1);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    /**
     * Returns a button with the arrow with the direction, and on click goes to that direction.
     * @param direction The direction the arrow points and which direction to go.
     * @return A button with the direction given.
     */
    public HBox createButton(Directions direction) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Button btn = new Button();
        btn.setPrefHeight(70);
        btn.setPrefWidth(70);
        btn.setCursor(Cursor.HAND);
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
        hBox.getChildren().add(btn);
        return hBox;
    }
    
    public void openMenu() throws IOException {
        UI.getInstance().getStage().setMaximized(true);
        UI.getInstance().getStage().setScene(UI.getInstance().getMenuScene());
    }
    
    public void setup() {
        Directions[] directions = {
            Directions.EAST, Directions.NORTH, Directions.SOUTH, Directions.WEST
        };
        for(Directions dir : directions) {
            switch (dir) {
                case NORTH:
                    root.add(createButton(dir), 1, 0);
                    break;
                case SOUTH:
                    root.add(createButton(dir), 1, 2);
                    break;
                case EAST:
                    root.add(createButton(dir), 2, 1);
                    break;
                case WEST:
                    root.add(createButton(dir), 0, 1);
                    break;
                default:
                    throw new AssertionError();
            }
        }
        // Adding key listeners
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        System.err.println("Go north");
                        break;
                    case S:
                        System.err.println("Go south");
                        break;
                    case D:
                        System.err.println("Go east");
                        break;
                    case A:
                        System.err.println("Go west");
                        break;
                    case TAB:
                        System.err.println("Switch Inventory");
                        break;
                    case E:
                        System.err.println("Use selected item");
                        break;
                    case Q:
                        System.err.println("Drop selected item");
                        break;
                    case SHIFT:
                        System.err.println("Next page in selected inventory");
                        break;
                    case CONTROL:
                        System.err.println("Previous page in selected inventory");
                        break;
                    case DIGIT1:
                        System.err.println("1");
                        break;
                    case DIGIT2:
                        System.err.println("2");
                        break;
                    case DIGIT3:
                        System.err.println("3");
                        break;
                    case DIGIT4:
                        System.err.println("4");
                        break;
                    case DIGIT5:
                        System.err.println("5");
                        break;
                    case DIGIT6:
                        System.err.println("6");
                        break;
                    case DIGIT7:
                        System.err.println("7");
                        break;
                    case DIGIT8:
                        System.err.println("8");
                        break;
                    case DIGIT9:
                        System.err.println("9");
                        break;
                    default:
                        System.err.println("Dont have anyhting to do on that button");
                }
            }
        });
    }
    
}
