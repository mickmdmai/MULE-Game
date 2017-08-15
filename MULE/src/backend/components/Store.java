package backend.components;

import backend.Data;
import backend.components.mules.MuleFactory;
import backend.components.mules.PlainMule;
import backend.components.resources.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 9/13/2015.
 */
public class Store implements Visitable {
    private static Store instance = new Store();
    private Map<String, Integer> inventory = new HashMap<>();

    private Store() {
        setDefaults();
        Data.difficultyProperty().addListener((observable, oldVal, newVal) -> {
            setDefaults();
        });
    }

    private void setDefaults() {
        if (Data.difficultyProperty().getValue().equals(1)) {
            inventory.clear();
            inventory.put("food", 16);
            inventory.put("ore", 0);
            inventory.put("energy", 16);
            inventory.put("crystite", 0);
            inventory.put("wood", 10);
            inventory.put("mule", 25);
        } else if (Data.difficultyProperty().getValue().equals(2) || Data
                .difficultyProperty().getValue().equals(3)) {
            inventory.clear();
            inventory.put("food", 8);
            inventory.put("ore", 8);
            inventory.put("energy", 8);
            inventory.put("crystite", 0);
            inventory.put("wood", 5);
            inventory.put("mule", 14);
        }
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public int getAmt(String item) {
        Integer result = inventory.get(item);
        if (result == null) {
            return -1;
        }
        else {
            return result;
        }
    }

    public void setAmt(String item, int amt) {
        inventory.replace(item, amt);
    }

    public void changeAmt(String item, int delta) {
        if (inventory.containsKey(item)) {
            delta = Math.min(Math.abs(delta), inventory.get(item))
                    * (delta < 0 ? -1 : 1);
            inventory.replace(item, inventory.get(item) + delta);
        }
    }

    public static Store getInstance() {
        return instance;
    }
}