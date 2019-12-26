package test.models.appointments;

import soft252.models.drugs.I_Treatment;

import java.util.ArrayList;

/**
 *
 */
public class DummyTreatment implements I_Treatment {
    private String _name;
    private String _description;
    private ArrayList< String > _sideEffects;

    /**
     * Default constructor.
     *
     * @param name
     * @param description
     */
    public DummyTreatment(String name, String description) {
        _name = name;
        _description = description;
        _sideEffects = new ArrayList<>();
    }

    /**
     * @return the _name variable.
     */
    @Override
    public String getName() {
        return _name;
    }

    /**
     * @return the _description variable.
     */
    @Override
    public String getDescription() {
        return _description;
    }

    /**
     * @return the _sideEffects variable. This represents the known side effects of the drugs.
     */
    @Override
    public ArrayList< String > getSideEffects() {
        return _sideEffects;
    }
}
