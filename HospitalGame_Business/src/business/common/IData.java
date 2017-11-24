/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.common;

import common.IHighScore;
import common.IInventory;
import common.INPC;
import common.IPersistence;
import common.IPlayer;
import common.IRoom;

/**
 *
 * @author andreasmolgaard-andersen
 */
public interface IData {
    public void injectPersistence(IPersistence persistence);
    public IHighScore getHighScore();
    public boolean saveGame(IPlayer player, IInventory[] inventory, IRoom[] rooms, INPC[] npcs);
    public boolean saveHighScore(IHighScore highScore);
    public IDataObject load();
}
