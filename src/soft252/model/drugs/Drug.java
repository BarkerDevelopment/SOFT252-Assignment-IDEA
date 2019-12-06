package soft252.model.drugs;

import java.util.ArrayList;

/**
 * An object that encapsulates a drug treatment.
 */
public class Drug
        implements I_Treatment{
    private String _name;
    private String _description;
    private ArrayList<String> _sideEffects;

    /**
     * Creates a drug object with an empty list of side effects. This could be used for an experimental drug with unknown
     * side effects.
     *
     * @param name the name of the drug.
     * @param description the description of the drug.
     */
    public Drug(String name, String description) {
        _name = name;
        _description = description;
        _sideEffects = new ArrayList<>();
    }

    /**
     * Creates a drug object.
     *
     * @param name the name of the drug.
     * @param description the description of the drug.
     * @param sideEffects the list of side effects.
     */
    public Drug(String name, String description, ArrayList<String> sideEffects) {
        _name = name;
        _description = description;
        _sideEffects = sideEffects;
    }

    /**
     * @return the _name variable.
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * @return the _description variable.
     */
    @Override
    public String getDescription() {
        return null;
    }

    /**
     * @return the _sideEffects variable. This represents the known side effects of the drugs.
     */
    @Override
    public ArrayList< String > getSideEffects() {
        return null;
    }

    /**
     * @param name the new contents to set _name to.
     */
    public void setName(String name){
        _name = name;
    }

    /**
     * @param description the new contents to set _description to.
     */
    public void setDescription(String description) {
        _description = description;
    }

    /**
     * @param sideEffects the new contents to set _sideEffects to.
     */
    public void setSideEffects(ArrayList< String > sideEffects) {
        _sideEffects = sideEffects;
    }
}
