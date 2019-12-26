package soft252.models.drugs;

import soft252.exceptions.StockLevelException;
import soft252.models.I_Observable;
import soft252.models.I_Observer;
import soft252.models.I_Repository;
import soft252.models.request.PrescriptionRequest;
import soft252.services.serialization.I_SerializationHandler;
import soft252.services.serialization.RepositoryDeserializationHandler;
import soft252.services.serialization.RepositorySerializationHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * A repository of all the drugs in the system.
 */
public class DrugRepository
        implements I_Repository<Drug> {

    /**
     * A class that encapsulates a drug's stock level.
     */
    @SuppressWarnings("InnerClassMayBeStatic")
    private class DrugStockListing
            implements I_Observable< Integer > {
        private Drug _drug;
        private int _stock;
        private ArrayList< I_Observer< Integer > > _observers;

        /**
         * Default constructor.
         *
         * @param drug the drug.
         * @param stock the starting stock level.
         */
        public DrugStockListing(Drug drug, int stock) {
            _drug = drug;
            _stock = stock;
            _observers = new ArrayList<>();
        }

        /**
         * @return the _drug variable.
         */
        public Drug getDrug() {
            return _drug;
        }

        /**
         * @return the _stock variable.
         */
        public int getStock() {
            return _stock;
        }

        /**
         * @param stock the new stock level.
         */
        public void setStock(int stock) {
            _stock = stock;
            publish();
        }

        /**
         * Subscribes an observer to the observable.
         *
         * @param o the observer wishing to observe the observable.
         */
        @Override
        public void subscribe(I_Observer< Integer > o) {
            if(!_observers.contains(o)) _observers.add(o);
        }

        /**
         * Unsubscribes an observer from the observable.
         *
         * @param o the observer wishing to stop observing the observable.
         */
        @Override
        public void unsubscribe(I_Observer< Integer > o) {
            _observers.remove(o);
        }

        /**
         * Notifies all observers.
         */
        @Override
        public void publish() {
            _observers.forEach(o -> o.update(_stock));
        }
    }

    private static String FILE_NAME = "drugs";
    private static DrugRepository INSTANCE;

    private final I_SerializationHandler _serializationHandler;
    private final I_SerializationHandler _deserializationHandler;

    private ArrayList<DrugStockListing> _drugs;

    /**
     * Singleton constructor.
     */
    private DrugRepository() {
        _serializationHandler = new RepositorySerializationHandler(FILE_NAME, this);
        _deserializationHandler = new RepositoryDeserializationHandler(FILE_NAME, this);

        _drugs = new ArrayList<>();
    }

    /**
     * DrugRepository implements the Singleton pattern to increase data integrity.
     *
     * @return the instance of the repository.
     */
    public static DrugRepository getInstance(){
        if(INSTANCE == null) INSTANCE = new DrugRepository();

        return INSTANCE;
    }

    /**
     * Wipes the contents of the repository and replaces it with the contents of the object passed. Primarily used by
     * RepositoryDeserializationHandler.
     *
     * @param objects the object the RepositoryDeserializationHandler passes.
     */
    @Override
    public void initialise(Object objects) {
        clear();
        ArrayList< Drug > drugs = (ArrayList< Drug >) objects;

        addAll(drugs);
    }

    /**
     * @return the list of all items in the repo.
     */
    @Override
    public ArrayList< Drug > get() {

        return new ArrayList<>(_drugs.stream().map(dl -> dl.getDrug()).collect(Collectors.toList()));
    }

    /**
     * Finds the drug of the passed in name.
     *
     * @param name the target drug's name.
     * @return if the repository contains a drug with that name, return it. Otherwise return null.
     */
    public Drug get(String name) {
        for (DrugStockListing dl : _drugs) {
            Drug d = dl.getDrug();
            if(d.getName().equals(name)) return d;
        }

        return null;
    }

    /**
     * Adds an item to the repository.
     *
     * @param item the new item.
     */
    @Override
    public void add(Drug item) {
        _drugs.add(new DrugStockListing(item, 0));
    }

    /**
     * Adds a collection of items to the repository.
     *
     * @param items the new items.
     */
    @Override
    public void addAll(ArrayList< Drug > items) {
        items.forEach(i -> _drugs.add(new DrugStockListing(i, 0)));
    }

    /**
     * Adds an item to the repository but with an initial stock value.
     *
     * @param item the new item.
     * @param stock the new item's initial stock value.
     */
    public void add(Drug item, int stock) {
        _drugs.add(new DrugStockListing(item, stock));
    }

    /**
     * Remove an item from the repository.
     *
     * @param item the target item.
     */
    @Override
    public void remove(Drug item) {
        _drugs.removeIf(dl -> dl.getDrug().equals(item));
    }
    
    /**
     * Checks if the repository contains the item.
     *
     * @param item the target item.
     * @return TRUE if repository contains the item, otherwise FALSE.
     */
    @Override
    public boolean contains(Drug item) {
        return get(item) != null;
    }

    /**
     * Checks if the repository contains a drug based on the name passed in.
     *
     * @param name the target drug's name.
     * @return TRUE if repository contains the item, otherwise FALSE.
     */
    public boolean contains(String name) {
        for (DrugStockListing dl : _drugs) if(dl.getDrug().getName().equals(name)) return true;

        return false;
    }

    /**
     * Clears the repo of all items. This is mainly for testing purposes.
     */
    @Override
    public void clear() {
        _drugs.clear();
    }

    /**
     * Sets the stock of a drug.
     * @param drug the target drug.
     * @param stock the stock level.
     */
    public void setStock(Drug drug, int stock){
        get(drug).setStock(stock);
    }

    /**
     * @return the list of all items in the repo.
     */
    public HashMap<Drug, Integer> getStock() {
        HashMap<Drug, Integer> stock = new HashMap<>();
        _drugs.forEach(dl -> stock.put(dl.getDrug(), dl.getStock()));

        return stock;
    }

    /**
     * Gets the stock of the specific drug.
     *
     * @param drug the target drug.
     * @return the number of drug in stock.
     */
    public int getStock(Drug drug){
        return get(drug).getStock();
    }

    /**
     * Changes the stock levels of a drug. This change can be either positive or negative.
     *
     * @param drug the target drug.
     * @param stockChange the change in stock requested.
     * @throws StockLevelException if the change in stock results in a negative stock value.
     */
    public void updateStock(Drug drug, int stockChange) throws StockLevelException{
        DrugStockListing dl = get(drug);

        int newStockLevel = dl.getStock() + stockChange;
        if(newStockLevel > 0) {
            dl.setStock(newStockLevel);

        }else{
            throw new StockLevelException("Resulting stock change will produce a negative stock level.");
        }
    }

    /**
     * Subscribes an o to the observable.
     *
     * @param o the o wishing to observe the observable.
     */
    public void subscribe(PrescriptionRequest o) {
        Drug drug = (Drug) (o).getPrescription().getTreatment();

        get(drug).subscribe(o);
    }

    /**
     * Unsubscribes an o from the observable.
     *
     * @param o the o wishing to stop observing the observable.
     */
    public void unsubscribe(PrescriptionRequest o) {
        Drug drug = (Drug) (o).getPrescription().getTreatment();

        get(drug).unsubscribe(o);
    }

    /**
     * Gets a DrugStockListing for a specific drug.
     *
     * @param item the target drug.
     * @return the drug's DrugStockListingObject.
     */
    private DrugStockListing get(Drug item){
        for (DrugStockListing dl : _drugs) if(dl.getDrug().equals(item)) return dl;

        return null;
    }

    /**
     * Load stored information.
     */
    @Override
    public void load() {
        _deserializationHandler.action();
    }

    /**
     * Save stored information.
     */
    @Override
    public void save() {
        _serializationHandler.action();
    }
}
