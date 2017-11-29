/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import javafx.scene.input.KeyEvent;

public class KeyListener implements javafx.event.EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                System.err.println("Go north");
                break;
            case S:
                System.err.println("Go south");
                break;
            case D:
                System.err.println("Go east");
                break;
            case A:
                System.err.println("Go west");
                break;
            case TAB:
                System.err.println("Switch Inventory");
                break;
            case E:
                System.err.println("Use selected item");
                break;
            case Q:
                System.err.println("Drop selected item");
                break;
            case SHIFT:
                System.err.println("Next page in selected inventory");
                break;
            case CONTROL:
                System.err.println("Previous page in selected inventory");
                break;
            case DIGIT1:
                System.err.println("1");
                break;
            case DIGIT2:
                System.err.println("2");
                break;
            case DIGIT3:
                System.err.println("3");
                break;
            case DIGIT4:
                System.err.println("4");
                break;
            case DIGIT5:
                System.err.println("5");
                break;
            case DIGIT6:
                System.err.println("6");
                break;
            case DIGIT7:
                System.err.println("7");
                break;
            case DIGIT8:
                System.err.println("8");
                break;
            case DIGIT9:
                System.err.println("9");
                break;
            default:
                System.err.println("Dont have anyhting to do on that button");
        }
    }

}
