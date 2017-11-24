/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author fsr19
 */
public interface IBusiness {
    void injectPersistenceFacade(IPersistence persistence);
    INPC[] getNPCs();
    IPlayer getPlayer();
    void play();
    void quit();
    IHighScore getHighScore();
    void pause();
    void resume();
    boolean save();
    boolean load();
}
