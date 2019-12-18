package soft252.model.drugs;

import soft252.exceptions.StockLevelException;
import soft252.model.I_Repository;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A repository of all the drugs in the system.
 */
public class DrugRepository
        implements I_Repository<Drug> {

    private static DrugRepository _instance;
    private ArrayList<DrugStockListing> _drugs;

    /**
     * Singleton constructor.
     */
    private DrugRepository() {
        _drugs = new ArrayList<>();
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

        return new ArrayList<>(_drugs.stream().map(dl -> dl.getDrug()).collect(Collectors.toList()));
    }

    public DrugStockListing get(Drug item){
        for (DrugStockListing dl : _drugs) if(dl.getDrug().equals(item)) return dl;

        return null;
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
     * @return the list of all items in the repo.
     */
    public ArrayList<DrugStockListing> getStock() {
        return _drugs;
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
    public void updateStock(Drug drug, int stockChange) throws StockLevelException, NullPointerException{
        DrugStockListing dl = get(drug);

        int newStockLevel = dl.getStock() + stockChange;
        if(newStockLevel > 0) {
            dl.setStock(newStockLevel);

        }else{
            throw new StockLevelException("Resulting stock change will produce a negative stock level.");
        }
    }
}
