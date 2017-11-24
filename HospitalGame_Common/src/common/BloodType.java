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
public enum BloodType {
    A,
    B,
    AB,
    O;

    public boolean canTransfuse(BloodType giving) {
        switch (giving) {
            case A:
                return this == A || this == AB;
            case B:
                return this == B || this == AB;
            case AB:
                return this == AB;
            default:
                return true;
        }
    }
}
