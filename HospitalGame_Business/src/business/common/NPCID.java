/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.common;

/**
 *
 * @author andreasmolgaard-andersen
 */
public enum NPCID {
    DOCTOR("Doctor"), 
    COMPUTER("Computer"), 
    PORTER("Porter");
    
    private String name;
    
    private NPCID(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}


