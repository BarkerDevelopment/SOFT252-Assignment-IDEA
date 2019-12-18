package soft252.model.drugs;

import soft252.model.I_Observable;
import soft252.model.I_Observer;

import java.util.ArrayList;

/**
 * A class that encapsulates a drug's stock level.
 */
public class DrugStockListing
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
