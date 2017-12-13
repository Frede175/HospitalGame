/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 * UIFocus is which fragment that can have focus.
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public enum UIFocus {
    PLAYER("Player"),
    ROOM("Room"),
    NPC("NPC");
    
    private String focus;

    private UIFocus(String focus) {
        this.focus = focus;
    }

    @Override
    public String toString() {
        return focus;
    }
}
