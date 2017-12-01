/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Data;

import business.common.IData;
import business.common.IDataObject;
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
public class DataFacade implements IData {

    private IPersistence persistence;

    @Override
    public void injectPersistence(IPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public IHighScore getHighScore() {
        return persistence.load(DataHighScore.class);
    }

    @Override
    public boolean saveGame(IPlayer player, IInventory[] inventory, IRoom[] rooms, INPC[] npcs) {
        return persistence.save(new DataObject(rooms, player, npcs, inventory));
    }

    @Override
    public boolean saveHighScore(IHighScore highScore) {
        return persistence.save(highScore);
    }

    @Override
    public IDataObject load() {
        return persistence.load(DataObject.class);
    }
}
