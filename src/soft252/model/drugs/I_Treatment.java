package soft252.model.drugs;

import java.util.ArrayList;

/**
 * Defines the functions for a Treatment object.
 */
public interface I_Treatment {
    /**
     * @return the _name variable.
     */
    public String getName();

    /**
     * @return the _description variable.
     */
    public String getDescription();

    /**
     * @return the _sideEffects variable. This represents the known side effects of the drugs.
     */
    public ArrayList< String > getSideEffects();
}
