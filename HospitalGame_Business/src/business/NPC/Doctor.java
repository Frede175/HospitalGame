/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.NPC;

import business.BusinessFacade;
import business.Item.BloodBag;
import business.Item.Item;
import common.BloodType;
import common.INPC;
import common.IPlayer;
import common.ItemName;
import common.NPCID;

/**
 *
 * @author andreasmolgaard-andersen
 */
public class Doctor extends NPC {

    private BusinessFacade business;

    /**
     * Constructor for Doctor
     *
     * @param name name of the NPC
     * @param canMove boolean true if the NPC can move
     * @param currentRoomID the room the NPC being created to be in
     * @param npcId the NPCID of the NPC
     */
    public Doctor(String name, boolean canMove, int currentRoomID, NPCID npcId) {
        super(name, canMove, currentRoomID, npcId);
    }

    public Doctor(INPC npc) {
        super(npc.getName(), npc.canMove(), npc.getCurrentRoomID(), npc.getNPCID());
    }

    public void injectBusiness(BusinessFacade business) {
        this.business = business;
    }
    

    @Override
    public String interact(IPlayer player) {

        BloodType bloodType = player.getBloodType();
        Item[] bloodBags = (Item[]) player.getInventory().getItemsByName(ItemName.BLOODBAG);
        int points = (int) player.getBloodAmount();
        if (bloodBags.length != 0) {
            for (Item Item : bloodBags) {
                BloodBag bloodBag = (BloodBag) Item;
                if (!bloodType.canTransfuse(bloodBag.getBloodType())) {
                    business.setGameOver();
                    return ("You lost the game.");
                    
                } else {
                    points += bloodBag.getBonusPoints();
                }
            }
            business.setGameWon();
            return ("You've earned " + points + " points! ");
            
        } else {
            return ("You need to get a bloodbag");
        }
    }
}
