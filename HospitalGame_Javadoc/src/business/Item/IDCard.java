package business.Item;

import common.ItemName;

    /**
    * Class to execute all basic functions
    *
    * @author Frederik Schultz Rosenberg
    * @author Andreas Bøgh Mølgaard-Andersen
    * @author Lars Bjerregaard Jørgensen
    * @author Robert Francisti
    */

    /**
    * Constructor for IDcard, sets the weight and name
    * @author rober
    */
public class IDCard extends Item {
    
    /**
     * ID card holds a weight and a name
     * @param weight of the card
     * @param name of the card
     */
    public IDCard(int weight, ItemName name) {
        super(weight, name);
    }

}
