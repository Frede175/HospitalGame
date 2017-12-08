package ui;

import common.Direction;
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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Tobias
 */
public class MapController implements Initializable {

    private IBusiness business;

    private ResizableCanvas roomCanvas;

    private GraphicsContext graphicsContext;

    private int size = 30;
    
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    
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

    public void drawMap() {
        graphicsContext.clearRect(0, 0, roomCanvas.getWidth(), roomCanvas.getHeight());
        IRoom currentRoom = business.getPlayer().getCurrentRoom();
        Set<IRoom> rooms = getRoomsInRoom(new HashSet<>(), currentRoom);
        int border = size / 10;
        border = border < 1 ? 1 : border;
        
        for (IRoom room : rooms) {
            if (room.isInspected()) {
                int x = room.getCoordinate().getX();
                int y = room.getCoordinate().getY() * -1;
                int xOffset = x + minX * -1;
                int yOffset = y + minY * -1;
                int sizeOffsetX = (int)((roomCanvas.getWidth() - (size * (maxX - minX + 1))) / 2);
                int sizeOffsetY = (int)((roomCanvas.getHeight() - (size * (maxY - minY + 1))) / 2);
                int xStart = xOffset * size + sizeOffsetX;
                int yStart = yOffset * size + sizeOffsetY;
                
                if (room.getCoordinate().getX() == currentRoom.getCoordinate().getX() && room.getCoordinate().getY() == currentRoom.getCoordinate().getY()) {
                    graphicsContext.setFill(Color.RED);
                } else {
                    graphicsContext.setFill(Color.BLACK);
                }

                graphicsContext.fillRect(xStart, yStart, size, size);
                graphicsContext.clearRect(xStart + border, yStart + border, size - border * 2, size - border * 2);
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
    
    private void updateMinMax(){
        minX = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        minY = Integer.MAX_VALUE;
        maxY = Integer.MIN_VALUE;
        
        IRoom currentRoom = business.getPlayer().getCurrentRoom();
        Set<IRoom> rooms = getRoomsInRoom(new HashSet<>(), currentRoom);
        
        for(IRoom room : rooms){
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
    
    private void createCanvas(){
        roomCanvas = new ResizableCanvas();
        roomCanvas.widthProperty().bind(stackPane.widthProperty());
        roomCanvas.heightProperty().bind(stackPane.heightProperty());
        graphicsContext = roomCanvas.getGraphicsContext2D();
        stackPane.getChildren().add(roomCanvas);
        updateMinMax();
        calculateSizeAndDraw();
    }

    private Set<IRoom> getRoomsInRoom(Set<IRoom> roomSet, IRoom nextRoom) {
        if (!roomSet.contains(nextRoom)) {
            roomSet.add(nextRoom);
            for (Direction d : nextRoom.getExitDirections()) {
                roomSet.addAll(getRoomsInRoom(roomSet, nextRoom.getExit(d)));
            }
        }

        return roomSet;
    }
}
