package soft252.model.drugs;

import soft252.exceptions.StockLevelException;
import soft252.model.I_Repository;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A repository of all the drugs in the system.
 */
public class DrugRepository
        implements I_Repository<Drug> {

    private static DrugRepository _instance;
    private HashMap<Drug, Integer> _drugs;

    /**
     * Singleton constructor.
     */
    private DrugRepository() {
        _drugs = new HashMap<>();
    }

    /**
     * DrugRepository implements the Singleton pattern to increase data integrity.
     *
     * @return the instance of the repository.
     */
    public static DrugRepository getInstance(){
        if(_instance == null) _instance = new DrugRepository();

        return _instance;
    }

    /**
     * @return the list of all items in the repo.
     */
    @Override
    public ArrayList< Drug > getAll() {

        return new ArrayList<>(_drugs.keySet());
    }

    /**
     * Finds the drug of the passed in name.
     *
     * @param name the target drug's name.
     * @return if the repository contains a drug with that name, return it. Otherwise return null.
     */
    public Drug get(String name) {
        for (Drug d : _drugs.keySet()) if(d.getName().equals(name)) return d;

        return null;
    }

    /**
     * Adds an item to the repository.
     *
     * @param item the new item.
     */
    @Override
    public void add(Drug item) {
        _drugs.put(item, 0);
    }

    /**
     * Adds an item to the repository but with an initial stock value.
     *
     * @param item the new item.
     * @param stock the new item's initial stock value.
     */
    public void add(Drug item, int stock) {
        _drugs.put(item, stock);
    }

    /**
     * Remove an item from the repository.
     *
     * @param item the target item.
     */
    @Override
    public void remove(Drug item) {
        _drugs.remove(item);
    }
    
    /**
     * Checks if the repository contains the item.
     *
     * @param item the target item.
     * @return TRUE if repository contains the item, otherwise FALSE.
     */
    @Override
    public boolean contains(Drug item) {
        return _drugs.containsKey(item);
    }

    /**
     * Checks if the repository contains a drug based on the name passed in.
     *
     * @param name the target drug's name.
     * @return TRUE if repository contains the item, otherwise FALSE.
     */
    public boolean contains(String name) {
        for (Drug d : _drugs.keySet()) if(d.getName().equals(name)) return true;

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
     * @return the list of all items in the repo.
     */
    public HashMap<Drug, Integer> getStock() {
        return _drugs;
    }

    /**
     * Gets the stock of the specific drug.
     *
     * @param drug the target drug.
     * @return the number of drug in stock.
     */
    public int getStock(Drug drug){
        return _drugs.get(drug);
    }

    /**
     * Changes the stock levels of a drug. This change can be either positive or negative.
     *
     * @param drug the target drug.
     * @param stockChange the change in stock requested.
     * @throws StockLevelException if the change in stock results in a negative stock value.
     */
    public void updateStock(Drug drug, int stockChange) throws StockLevelException, NullPointerException{
        int newStockLevel = _drugs.get(drug) + stockChange;
        if(newStockLevel > 0) {
            _drugs.replace(drug, newStockLevel);

        }else{
            throw new StockLevelException("Resulting stock change will produce a negative stock level.");
        }
    }
}
