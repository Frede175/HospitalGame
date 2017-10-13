package hospitalgame;

/**
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Tobias Ahrenschneider Sztuk
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public enum BloodType {
        A, B, AB, O;
        
    /*
    O can give blood to everyone & can only recieve from itself
    A can give blood to A & AB
    B can give blood to B & AB
    */
    public boolean canTransfuse(BloodType giving){
        
        switch(giving){
            case A:
                return this == A || this == AB;
            case B:
                return this == B || this == AB;
            case O:
                return this == O;
            default:
                return true;
        }
    }
    
}