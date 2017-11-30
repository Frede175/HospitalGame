/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.IBusiness;
import common.IInventory;
import common.IItem;
import common.IPlayer;
import common.ItemName;
import static common.ItemName.BLOODBAG;
import static common.ItemName.IDCARD;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private int page = 1;
    
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        business = UI.getInstance().getBusiness();
        imgRes = UI.getInstance().getImageResource();
        player = business.getPlayer();
        nextBtn.setBackground(new Background(new BackgroundImage(imgRes.getSprite(Sprites.ARROW_RIGHT), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        previousBtn.setBackground(new Background(new BackgroundImage(imgRes.getSprite(Sprites.ARROW_LEFT), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
    }    
    
    /**
     * Update the items of the selected page to the GUI.
     */
    public void updateItems(IInventory inventory) {
        ArrayList<? extends IItem> items = inventory.getItems();
        if(items.size() > 9) {
            for (int i = 0; i <= items.size(); i++) {
                int column = i % 3;
                int row = ((int) i / 3);
                System.out.println(row);
            }
        } else {
            for (int i = 0; i < 9; i++) {
                
            }
        }
        
    }
    
    public VBox getGUIItem(IItem item) {
        VBox vBox = new VBox();
        ImageView img = new ImageView();
        Label name = new Label(item.getName().toString());
        switch (item.getName()) {
            case BANDAGE:
                img.setImage(imgRes.getSprite(Sprites.BANDAGE));
                break;
            case BLOODBAG:
                img.setImage(imgRes.getSprite(Sprites.BLOODBAG_A));
                break;
            case IDCARD:
                // TODO Add IDCARD to sprite
                img.setImage(imgRes.getSprite(Sprites.IDCARD));
                break;
            case MORPHINE:
                img.setImage(imgRes.getSprite(Sprites.MORPHINE));
                break;
            default:
                throw new AssertionError();
        }
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(img, name);
        return vBox;
    }
    
    /**
     * Changes inventory page to the next page.
     */
    public void nextPage() {
        System.out.println("Next page");
    }
    
    /**
     * Changes inventory page to the previous page.
     */
    public void previousPage() {
        System.out.println("Previous page");
    }
    
}
