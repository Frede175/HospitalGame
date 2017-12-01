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
public enum ItemName {
    BLOODBAG("Blood bag"),
    BANDAGE("Bandage"),
    MORPHINE("Morphine"),
    IDCARD("ID card");

    private String name;

    private ItemName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
