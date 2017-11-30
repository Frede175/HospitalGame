/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.Directions;
import common.IBusiness;
import common.IRoom;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Tobias
 */
public class MapController implements Initializable {

    private IBusiness business;

    private ResizeableCanvas roomCanvas;

    private GraphicsContext graphicsContext;
    
    @FXML
    private AnchorPane anchor;

    private int size = 30;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        
        ChangeListener<Number> anchorSizeListener = new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //anchor.autosize();
                //System.out.println("anchor width: " + anchor.getLayoutX() + " anchor height: " + anchor.getHeight());
                createCanvas((int)anchor.getWidth(), (int)anchor.getHeight());
                
                System.out.println("anchor width: " + anchor.widthProperty().getValue() + " anchor height: " + anchor.getHeight());              
            }
        };

        UI.getInstance().getStage().widthProperty().addListener(anchorSizeListener);
        UI.getInstance().getStage().heightProperty().addListener(anchorSizeListener);

        System.out.println("MapController");
        createCanvas((int)anchor.getWidth(), (int)anchor.getHeight());
    }

    public void updateMap() {
        IRoom currentRoom = business.getPlayer().getCurrentRoom();
        Set<IRoom> rooms = getRoomsInRoom(new HashSet<>(), currentRoom);
        //System.out.println(rooms.size());

        for (IRoom room : rooms) {
            if (!room.isInspected()) {
                //System.out.println("X: " + room.getCoordinate().getX() + " Y:" + room.getCoordinate().getY());
                if (room.getCoordinate().getX() == currentRoom.getCoordinate().getX() && room.getCoordinate().getY() == currentRoom.getCoordinate().getY()) {
                    graphicsContext.setFill(Color.RED);
                } else {
                    graphicsContext.setFill(Color.BLACK);
                }

                graphicsContext.fillRect(room.getCoordinate().getX() * size + size * 12, room.getCoordinate().getY() * size + size * 12, size, size);
                graphicsContext.clearRect(room.getCoordinate().getX() * size + size * 12 + 5, room.getCoordinate().getY() * size + size * 12 + 5, size - 10, size - 10);
                for (Directions d : room.getExitDirections()) {
                    switch (d) {
                        case SOUTH:
                            graphicsContext.clearRect(room.getCoordinate().getX() * size + size * 12 + size / 2 - size / 8, room.getCoordinate().getY() * size + size * 12 + size - 5, size / 4, 5);
                            break;
                        case NORTH:
                            graphicsContext.clearRect(room.getCoordinate().getX() * size + size * 12 + size / 2 - size / 8, room.getCoordinate().getY() * size + size * 12, size / 4, 5);
                            break;
                        case EAST:
                            graphicsContext.clearRect(room.getCoordinate().getX() * size + size * 12 + size - 5, room.getCoordinate().getY() * size + size * 12 + size / 2 - size / 8, 5, size / 4);
                            break;
                        case WEST:
                            graphicsContext.clearRect(room.getCoordinate().getX() * size + size * 12, room.getCoordinate().getY() * size + size * 12 + size / 2 - size / 8, 5, size / 4);
                            break;
                        default:
                            throw new AssertionError(d.name());
                    }
                }
            }
        }
    }
    
    private void calculateSize(){
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        
        IRoom currentRoom = business.getPlayer().getCurrentRoom();
        Set<IRoom> rooms = getRoomsInRoom(new HashSet<>(), currentRoom);
        
        for(IRoom room : rooms){
            if(room.getCoordinate().getX() > maxX)
                maxX = room.getCoordinate().getX();
            else if(room.getCoordinate().getX() < minX)
                minX = room.getCoordinate().getX();
            else if(room.getCoordinate().getY() > maxY)
                maxY = room.getCoordinate().getY();
            else if(room.getCoordinate().getY() < minY)
                minY = room.getCoordinate().getY();
        }
        
        int differenceX = maxX - minX;
        int differenceY = maxY - minY;
        
        int sizeX = (int)anchor.getWidth() / differenceX;
        int sizeY = (int)anchor.getHeight() / differenceY;
        
        if(sizeX < sizeY)
            size = sizeX;
        else
            size = sizeY;
        
        System.out.println(size);
    }
    
    private void createCanvas(int width, int height){
        roomCanvas = new ResizeableCanvas(width, height);
        //roomCanvas = new Canvas(width, height);
        roomCanvas.widthProperty().bind(anchor.widthProperty());
        roomCanvas.heightProperty().bind(anchor.heightProperty());
        graphicsContext = roomCanvas.getGraphicsContext2D();
        anchor.getChildren().clear();
        anchor.getChildren().add(roomCanvas);
        calculateSize();
        updateMap();
    }

    private Set<IRoom> getRoomsInRoom(Set<IRoom> roomSet, IRoom nextRoom) {
        if (!roomSet.contains(nextRoom)) {
            //System.out.print(nextRoom.getCoordinate().getX() + ", " + nextRoom.getCoordinate().getY() + ": ");
            roomSet.add(nextRoom);
            for (Directions d : nextRoom.getExitDirections()) {
                //System.out.print(d + " , ");
            }
            //System.out.println("");
            for (Directions d : nextRoom.getExitDirections()) {
                roomSet.addAll(getRoomsInRoom(roomSet, nextRoom.getExit(d)));
            }
        }

        return roomSet;
    }
}
