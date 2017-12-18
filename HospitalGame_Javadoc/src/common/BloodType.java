package common;

/**
 * Contains all the different bloodtypes
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public enum BloodType {
    A,
    B,
    AB,
    O;

    /**
     * Checks if bloodtype can transfuse
     * 
     * @param giving if it can give blood
     * @return true if can transfuse.
     */
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
