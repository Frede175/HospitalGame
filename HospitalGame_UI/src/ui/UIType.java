/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author larsb
 */
public enum UIType {
    PLAYER("Player"),
    ROOM("Room"),
    NPC("NPC");
    
    private String focus;

    private UIType(String focus) {
        this.focus = focus;
    }

    @Override
    public String toString() {
        return focus;
    }
}
