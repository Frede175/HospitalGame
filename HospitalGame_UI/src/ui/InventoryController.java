/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.IBloodBag;
import common.IBusiness;
import common.IInventory;
import common.IItem;
import common.IPlayer;
import static common.ItemName.BLOODBAG;
import static common.ItemName.IDCARD;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author larsjorgensen
 */
public class InventoryController implements Initializable {

    /**
     * Contains the reference to the page label.
     */
    @FXML
    private Label pageLabel;
    
    /**
     * Contains the reference to the next page button.
     */
    @FXML
    private Button nextBtn;
    
    /**
     * Contains the reference to the previous page button.
     */
    @FXML
    private Button previousBtn;
    
    /**
     * Contains the business facade reference.
     */
    private IBusiness business;
    
    /**
     * Contains reference to the Image Ressource class.
     */
    private ImageResource imgRes;
    
    /**
     * Contains which page the inventory is showing.
     */
    private int page = 0;
    
    /**
     * Contains how many items there is per page in the inventory.
     */
    private final int ITEMS_PER_PAGE = 6;
    
    /**
     * Contains if the inventory is focussed.
     */
    private boolean isFocussed = false;
    
    /**
     * Contains the player reference.
     */
    private IPlayer player;
    
    /**
     * Contains the reference to the gridpane.
     */
    @FXML
    private GridPane inventoryGrid;
    
    /**
     * Contains the selected index in the inventory.
     */
    private int selectedIndex = 0;
    
    /**
     * Contains all the items in the inventory.
     */
    private List<? extends IItem> items;
    
    /**
     * Contains the inventory.
     */
    private IInventory inventory;
    
    /**
     * Contains all the VBox´ that gets generated.
     */
    private ArrayList<VBox> itemContainers;
    
    private UIType type;
    
    private MainController mainController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        imgRes = UI.getInstance().getImageResource();
        player = business.getPlayer();
        items = new ArrayList<>();
        itemContainers = new ArrayList<>();
        nextBtn.setBackground(new Background(new BackgroundImage(imgRes.getSprite(Sprites.ARROW_RIGHT), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        previousBtn.setBackground(new Background(new BackgroundImage(imgRes.getSprite(Sprites.ARROW_LEFT), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        setSelectedIndex(0);
    }  
    
    public void injectMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    /**
     * Update the items of the selected page to the GUI.
     */
    public void updateItems(IInventory inventory) {
        clearGrid();
        itemContainers.clear();
        this.inventory = inventory;
        items = this.inventory.getItems();
        refreshPageButtons();
        
        if (page > getPageCount()) page = getPageCount();
        
        
        if(items.size() == 0) {
            pageLabel.setText("0/" + (getPageCount()));
        } else {
            pageLabel.setText((page + 1) + "/" + (getPageCount() + 1));
        }

        int calc = 0;
        for (int i = (page) * ITEMS_PER_PAGE; i < (page) * ITEMS_PER_PAGE + ITEMS_PER_PAGE && i < items.size(); i++) {
            int column = calc % 3;
            int row = calc / 3;
            if(i != selectedIndex) {
                inventoryGrid.add(getGUIItem(items.get(i), false, i), column, row + 1);
            } else {
                inventoryGrid.add(getGUIItem(items.get(i), true, i), column, row + 1);
            }
            calc++;
        }
    }
    
    /**
     * Checking if some of the buttons should be disabled or not.
     */
    private void refreshPageButtons() {
        if(getPageCount() == 0){
            nextBtn.setDisable(true);
            previousBtn.setDisable(true);
        }else if(getPageCount() > page){ //problem here
            nextBtn.setDisable(false);
            previousBtn.setDisable(true);
        }else if(getPageCount() == page){
            nextBtn.setDisable(true);
            previousBtn.setDisable(false);
        }else{
            nextBtn.setDisable(false);
            previousBtn.setDisable(false);
        }
    }
    
    /**
     * Calculates page count.
     * @return Count of pages.
     */
    private int getPageCount() {
        return ((items.size() == 0 ? 0 : items.size() - 1) / ITEMS_PER_PAGE);
    }
    
    /**
     * Get the inventory.
     * @return The inventory.
     */
    public IInventory getInventory() {
        return inventory;
    }
    
    public void setFocus(boolean focus) {
        this.isFocussed = focus;
    }
    
    /**
     * Clears the inventory gridpane for items.
     */
    private void clearGrid() {
        if(!itemContainers.isEmpty()) {
            inventoryGrid.getChildren().removeAll(itemContainers);
        }
    }
    
    /**
     * Returning an VBox with a item icon and a name of the item.
     * @param item Which item it should return an image of.
     * @param selected If the item is selected by the user.
     * @return Return an VBox containing an image of the item and a label with the name.
     */
    public VBox getGUIItem(IItem item, boolean selected, int index) {
        VBox vBox = new VBox();
        if(selected && isFocussed) {
            vBox.setStyle("-fx-border-color: blue;");
        }
        ImageView img = new ImageView();
        Label name = new Label(item.getName().toString());
        switch (item.getName()) {
            case BANDAGE:
                img.setImage(imgRes.getSprite(Sprites.BANDAGE));
                break;
            case BLOODBAG:
                switch (((IBloodBag) item).getBloodType()) {
                    case A:
                        img.setImage(imgRes.getSprite(Sprites.BLOODBAG_A));
                        break;
                    case AB:
                        img.setImage(imgRes.getSprite(Sprites.BLOODBAG_AB));
                        break;
                    case B:
                        img.setImage(imgRes.getSprite(Sprites.BLOODBAG_B));
                        break;
                    case O:
                        img.setImage(imgRes.getSprite(Sprites.BLOODBAG_O));
                        break;
                    default:
                        throw new AssertionError();
                }
                break;
            case IDCARD:
                img.setImage(imgRes.getSprite(Sprites.IDCARD));
                break;
            case MORPHINE:
                img.setImage(imgRes.getSprite(Sprites.MORPHINE));
                break;
            default:
                throw new AssertionError();
        }
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(img, name);
        vBox.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if(e.isPrimaryButtonDown()) {
                selectedIndex = index;
                if(type == UIType.PLAYER) {
                    business.useItem(selectedIndex);
                } else if(type == UIType.ROOM) {
                    business.takeItem(selectedIndex);
                }
                mainController.updateGUI();
            } else if(e.isSecondaryButtonDown()) {
                selectedIndex = index;
                if(type == UIType.PLAYER) {
                    business.dropItem(selectedIndex);
                    mainController.updateGUI();
                }
            }
        });
        itemContainers.add(vBox);
        return vBox;
    }
    
    public void setType(UIType type) {
        this.type = type;
    }
    
    /**
     * Setting the selected index by a number 0-5.
     * @param index 
     */
    public void setSelectedIndex(int index) {
        if(index + (page) * ITEMS_PER_PAGE < items.size()) {
            selectedIndex = index + (page) * ITEMS_PER_PAGE;
        }
    }
    
    /**
     * Changes inventory page to the next page.
     */
    public void nextPage() {
        if(page < getPageCount() && page != getPageCount()) {
            page++;
            System.out.println("Page " + page);
            setSelectedIndex(0);
            updateItems(inventory);
        }
    }
    
    /**
     * Changes inventory page to the previous page.
     */
    public void previousPage() {
        if(page != 0 && page > 0) {
            page--;
            setSelectedIndex(0);
            updateItems(inventory);
        }
    }

    public int getSelectedIndex() {
        System.out.println("Index " + selectedIndex);
        return selectedIndex;
    }
}
