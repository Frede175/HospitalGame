package persistence;

import common.BloodType;
import common.IBloodBag;
import java.io.Serializable;

/**
 * Class to store blood bag
 * 
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public class DataBloodBag extends DataBonusPointItem implements IBloodBag, Serializable {

    /**
     * The blood type
     */
    private BloodType bloodType;

    /**
     * Constructor for creating a blood bag from the interface IBloodBag
     * @param item the blood bag.
     */
    public DataBloodBag(IBloodBag item) {
        super(item);
        bloodType = item.getBloodType();
    }

    /**
     * 
     * @return the blood type of the blood bag
     */
    @Override
    public BloodType getBloodType() {
        return bloodType;
    }

}
//public class DataBloodBag extend DataBonusItem implements IBloodBag {

