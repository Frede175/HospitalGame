/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import common.IBusiness;
import common.IUI;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author fsr19
 */
public class UI extends Application implements IUI{
    
    private static UI ui;
    
    public static UI getInstance(){
        return ui;
    }
    
    private IBusiness business;
    
    private Stage stage;
    
    private Sprites sprites;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        UI.getInstance().stage = primaryStage;
        UI.getInstance().sprites = new Sprites();
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(1000);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    @Override
    public void injectBusiness(IBusiness business) {
        this.business = business;
    }

    @Override
    public void startApplication(String[] args) {
        ui = this;
        launch(args);
    }

    IBusiness getBusiness() {
        return business;
    }
    
    Stage getStage() {
        return stage;
    }
    
    Sprites getSprites() {
        return sprites;
    }
    
}
