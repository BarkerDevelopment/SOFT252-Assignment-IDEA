package soft252.model.drugs;

import soft252.I_Builder;

import java.util.ArrayList;

/**
 * A class that encapsulates a drug treatment.
 */
public class Drug
        implements I_Treatment{
    /**
     * Drug Builder.
     */
    public static class Builder implements I_Builder<Drug> {
        private final String name;
        private String description;
        private ArrayList<String> sideEffects;
        private int stock;

        /**
         * Builder constructor.
         * @param name the drug name.
         */
        public Builder(String name){
            this.name = name;
            sideEffects = new ArrayList<>();
            stock = 0;
        }

        /**
         * Add a drug description.
         *
         * @param description the drug description.
         */
        public Builder setDescription(String description){
             this.description = description;
             return this;
        }

        /**
         * Add side effects.
         *
         * @param sideEffect a side effect of the drug.
         */
        public Builder setSideEffects(ArrayList<String> sideEffect){
            sideEffects = sideEffect;
            return this;
        }

        /**
         * Add a side effect.
         * NOTE: this can be stacked.
         *
         * @param sideEffect a side effect of the drug.
         */
        public Builder addSideEffect(String sideEffect){
            sideEffects.add(sideEffect);
            return this;
        }

        /**
         * Add the stock of the drug.
         *
         * @param stock the stock of the drug.
         */
        public Builder setStock(int stock){
            this.stock = stock;
            return this;
        }

        /**
         * Builds the final object and returns it.
         *
         * @return the object.
         */
        @Override
        public Drug build() {
            return new Drug(this);
        }
    }

    private String _name;
    private String _description;
    private ArrayList<String> _sideEffects;

    /**
     * Constructor used by Builder.
     * Additionally, this constructor adds the resultant object to its corresponding repository: RequestRepository.
     *
     * @param builder the drug builder object.
     */
    private Drug(Builder builder) {
        _name = builder.name;
        _description = builder.description;
        _sideEffects = builder.sideEffects;

        DrugRepository.getInstance().add(this, builder.stock);
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
