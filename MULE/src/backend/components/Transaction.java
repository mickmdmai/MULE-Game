package backend.components;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 12/1/2015.
 */
public abstract class Transaction implements Visitor {
    private Map<String, Integer> transactions = new HashMap<>();
    private int cost;

    public Transaction() {}

    public Transaction(String item, int amt, int cost) {
        transactions.put(item, amt);
        this.cost = cost;
    }

    public Transaction(Map<String, Integer> newItems, int cost) {
        transactions.putAll(newItems);
        this.cost = cost;
    }

    public void addResource(String s, Integer i) {
        transactions.put(s, i);
    }

    public Integer getResource(String s) {
        return transactions.get(s);
    }
    public int getCost() {
        return cost;
    }

    public Map<String, Integer> getItems() {
        return transactions;
    }
}
