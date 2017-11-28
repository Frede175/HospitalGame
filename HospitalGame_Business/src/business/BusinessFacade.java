/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.common.IData;
import business.common.IItemFacade;
import business.common.INPCFacade;
import common.IBusiness;
import common.IHighScore;
import common.INPC;
import common.IPersistence;
import common.IPlayer;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class BusinessFacade implements IBusiness {

    /**
     * to gain access to item facade
     */
    private IItemFacade itemFacade;
    
    /**
     * to gain access to npc facade
     */
    private INPCFacade npcFacade;
    
    /**
     * gives access to data facade
     */
    private IData dataFacade;
    
    /**
     * is the player for business facade to manage
     */
    private Player player;
    
    /**
     * is the map of the game to hold
     */
    private Map map;
    
    /**
     * high score to save score of the game
     */
    private BusinessHighScore highScore;
    
    /**
     * to gain access to persistence
     */
    private IPersistence persistence;
    
    /**
     * a
     */
    private boolean isGameOver = false;
    
    /**
     * 
     * @return Array with INPCs
     */
     public INPC[] getNPCs(){
        return npcFacade.getNPCs();
    }
     
    public IPlayer getPLayer(){
        return player;
    } 
    
    @Override
    public void play(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    public String getWelcome(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    public String getHelp(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    @Override
    public void quit(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    @Override
    public IHighScore getHighScore(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    @Override
    public void pause(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    @Override
    public void resume(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    @Override
    public boolean save(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    @Override
    public boolean load(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    void createRooms(int numberOfRooms){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    /**
     * sets the game over if called
     */
    void setGameOver(){
        isGameOver = true;
    }

    /**
     * injection of injectionFacade
     * @param persistence the persistence facade to inject
     */
    @Override
    public void injectPersistenceFacade(IPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public IPlayer getPlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}
