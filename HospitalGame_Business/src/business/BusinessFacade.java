/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.common.IItemFacade;
import business.common.INPCFacade;
import common.INPC;
import common.IPlayer;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class BusinessFacade {

    private IItemFacade itemFacade;
    private INPCFacade npcFacade;
    private IData dataFacade;
    private Player player;
    private Map map;
    private BusinessHighScore highScore;
    private boolean gameOver;

    public void injectItemFacade(IItemFacade itemFacade){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    public void injectNPCFacade(INPCFacade npcFacade){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    public void injectDataFacade(IData dataFacade){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
     public INPC[] getNPCs(){
        throw new UnsupportedOperationException("not yet implemented.");
        
    }
     
    public IPlayer getPLayer(){
        throw new UnsupportedOperationException("not yet implemented.");
    } 
    
    public void play(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    public String getWelcome(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    public String getHelp(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    public void quit(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    public int getHighScore(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    public Game getGameInstance(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    public void pause(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    public void resume(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    public void save(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    public void load(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    void createRooms(int numberOfRooms){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
    void setGameOver(){
        throw new UnsupportedOperationException("not yet implemented.");
    }
    
}
