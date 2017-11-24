/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.Set;

/**
 *
 * @author fsr19
 */
public interface IRoom {
    IRoom getExit(Directions dir);
    String getName();
    Set<String> getExitStrings();
    boolean isLocked();
    boolean isInspected();
}