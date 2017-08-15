package backend.components;

import backend.components.mules.MuleFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 12/1/2015.
 */
public class Purchase extends Transaction {

    public Purchase(Map<String, Integer> items, int cost) {
        super(items, cost);
    }
    @Override
    public void visit(Player p) {
        //add resources to player
        for (String curr : getItems().keySet()) {
            if (curr.toLowerCase().contains("mule")) {
                p.setMule(MuleFactory.getInstance().getMule(curr));
            } else {
                p.changeResoureQuantity(curr, getItems().get(curr));
            }
        }
        //decrement money
        p.changeMoney(-getCost());
    }

    @Override
    public void visit(Store s) {
        //remove resources from store
        for (String curr : getItems().keySet()) {
            if (curr.toLowerCase().contains("mule")) {
                s.changeAmt("mule", getItems().get(curr));
            } else {
                s.changeAmt(curr, -getItems().get(curr));
            }
        }
    }
}
