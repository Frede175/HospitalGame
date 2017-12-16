package ui;

import common.Direction;
import common.IBusiness;
import common.IRoom;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * FXML map controller
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class MapController implements Initializable {

    /**
     * Reference to business
     */
    private IBusiness business;

    /**
     * The canvas to be drawn on
     */
    private ResizableCanvas roomCanvas;

    /**
     * The graphics context for the canvas
     */
    private GraphicsContext graphicsContext;

    /**
     * A default size for the rooms (drawn). The is changed later.
     */
    private int size = 30;
    
    /**
     * Storing the max and min of both x and y coordinates from the rooms in the map
     */
    private int minX, maxX, minY, maxY;
    
    @FXML
    private StackPane stackPane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        
        ChangeListener<Number> stackPaneSizeListener = new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                calculateSizeAndDraw();
            }
        };

        stackPane.widthProperty().addListener(stackPaneSizeListener);
        stackPane.heightProperty().addListener(stackPaneSizeListener);
        
        createCanvas();
    }
    
    /**
     * When the map is changed this should be called.
     */
    public void mapUpdate() {
        updateMinMax();
        calculateSizeAndDraw();
    }

    /**
     * Draw the map for the game
     */
    public void drawMap() {
        graphicsContext.clearRect(0, 0, roomCanvas.getWidth(), roomCanvas.getHeight());
        IRoom currentRoom = business.getPlayer().getCurrentRoom();
        int border = size / 10;
        border = border < 1 ? 1 : border;
        
        for (IRoom room : business.getRooms()) {
            //Only draw the room if the player has been to it
            if (room.isInspected()) {
                int x = room.getCoordinate().getX();
                //The y coordinate from the room is inverted since 0,0 is different when generation and drawing
                int y = room.getCoordinate().getY() * -1;
                //Offset is used since the 0,0 should in the top left cornor but the start room has 0,0.
                //Therefore the coordinates need to be shifted
                int xOffset = x + minX * -1;
                int yOffset = y + minY * -1;
                //Since there is finite amount of space, there can be gaps. This is for centering the map on screen
                int sizeOffsetX = (int)((roomCanvas.getWidth() - (size * (maxX - minX + 1))) / 2);
                int sizeOffsetY = (int)((roomCanvas.getHeight() - (size * (maxY - minY + 1))) / 2);
                //The coordiantes used to draw with
                int xStart = xOffset * size + sizeOffsetX;
                int yStart = yOffset * size + sizeOffsetY;
                
                //If the player is in this room draw it as red
                if (room.getRoomID() == currentRoom.getRoomID()) {
                    graphicsContext.setFill(Color.RED);
                } else {
                    graphicsContext.setFill(Color.BLACK);
                }
                
                //Draw the rect on screen and clear out the middel
                graphicsContext.fillRect(xStart, yStart, size, size);
                graphicsContext.clearRect(xStart + border, yStart + border, size - border * 2, size - border * 2);
                
                //This is for clearing the doors of the rooms
                for (Direction d : room.getExitDirections()) {
                    switch (d) {
                        case SOUTH:
                            graphicsContext.clearRect(xStart + size / 2 - size / 8, yStart + size - border, size / 4, border);
                            break;
                        case NORTH:
                            graphicsContext.clearRect(xStart + size / 2 - size / 8, yStart, size / 4, border);
                            break;
                        case EAST:
                            graphicsContext.clearRect(xStart + size - border, yStart + size / 2 - size / 8, border, size / 4);
                            break;
                        case WEST:
                            graphicsContext.clearRect(xStart, yStart + size / 2 - size / 8, border, size / 4);
                            break;
                        default:
                            throw new AssertionError(d.name());
                    }
                }
            }
        }
    }
    
    /**
     * Updates the size variable and class drawMap()
     */
    private void calculateSizeAndDraw(){
        int differenceX = maxX - minX + 1;
        int differenceY = maxY - minY + 1;
        
        int sizeX = (int)stackPane.getWidth() / differenceX;
        int sizeY = (int)stackPane.getHeight() / differenceY;
        
        if(sizeX < sizeY)
            size = sizeX;
        else
            size = sizeY;
        
        drawMap();
    }
    
    /**
     * Updates the min and max for both x and y, from the rooms coordinates
     */
    private void updateMinMax(){
        minX = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        minY = Integer.MAX_VALUE;
        maxY = Integer.MIN_VALUE;
        
        IRoom currentRoom = business.getPlayer().getCurrentRoom();
        
        
        for(IRoom room : business.getRooms()){
            int x = room.getCoordinate().getX();
            int y = room.getCoordinate().getY() * -1;
            
            if(x > maxX)
                maxX = x;
            if(x < minX)
                minX = x;
            if(y > maxY)
                maxY = y;
            if(y < minY)
                minY = y;
        }
        
        
    }
    
    /**
     * Creates a canvas
     */
    private void createCanvas(){
        roomCanvas = new ResizableCanvas();
        roomCanvas.widthProperty().bind(stackPane.widthProperty());
        roomCanvas.heightProperty().bind(stackPane.heightProperty());
        graphicsContext = roomCanvas.getGraphicsContext2D();
        stackPane.getChildren().add(roomCanvas);
        updateMinMax();
        calculateSizeAndDraw();
    }

}
