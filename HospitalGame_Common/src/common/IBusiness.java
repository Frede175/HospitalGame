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

    INPC[] getNPCsFromRoom(IRoom room);

    IPlayer getPlayer();

    void play();

    void quit();

    IHighScore getHighScore();

    void pause();

    void resume();

    boolean save();

    boolean load();

    void move(Directions direction);

    boolean useItem(int index);

    boolean dropItem(int index);

    boolean takeItem(int index);
    
    GameState getGameState();
    
    String interact(IPlayer player, INPC npc);
    
    int getScore();
    
    boolean eligibleForHighScore();
    
    boolean isHighScoreNameTaken(String name);
    
    boolean addHighScore(String name);
    
    void closing();
    
    
}
