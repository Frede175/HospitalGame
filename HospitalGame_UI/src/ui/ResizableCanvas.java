/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.scene.canvas.Canvas;

/**
 * Resizeable canvas.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class ResizableCanvas extends Canvas{
    
    /**
     * 
     * @return Returns true if the canvas is resizeable.
     */
    @Override
    public boolean isResizable(){
        return true;
    }
    
}
