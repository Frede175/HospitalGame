/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.scene.canvas.Canvas;

/**
 *
 * @author Tobias
 */
public class ResizeableCanvas extends Canvas{
    
    public ResizeableCanvas(int width, int height){
        super(width, height);
    }
    
    public boolean isResizeable(){
        return true;
    }
    
}
