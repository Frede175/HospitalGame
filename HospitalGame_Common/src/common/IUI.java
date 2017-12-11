package common;

/**
 * UI interface
 *
 * @author Frederik Schultz Rosenberg
 * @author Andreas Bøgh Mølgaard-Andersen
 * @author Lars Bjerregaard Jørgensen
 * @author Robert Francisti
 */
public interface IUI {

    /**
     * Injects a business instance.
     * @param business Which business instance to inject.
     */
    void injectBusiness(IBusiness business);

    /**
     * Starts the appliction.
     * @param args 
     */
    void startApplication(String[] args);
}
