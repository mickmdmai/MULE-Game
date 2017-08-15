package backend.components;

import backend.components.mules.MuleFactory;

import java.util.Map;

/**
 * Created by Edward on 12/2/2015.
 */
public class Sale extends Transaction {

    public Sale(Map<String, Integer> items, int cost) {
        super(items, cost);
    }

    @Override
    public void visit(Player p) {
        //subtract resources from player
        for (String curr : getItems().keySet()) {
            p.changeResoureQuantity(curr, -getItems().get(curr));
        }
        // add money to player
        p.changeMoney(getCost());
    }

    @Override
    public void visit(Store s) {
        //add resources to store
        for (String curr : getItems().keySet()) {
                s.changeAmt(curr, getItems().get(curr));
        }
    }
}
