package backend.components;

/**
 * Created by patrickcaruso on 12/3/15.
 */
public class Production extends Transaction {

    @Override
    public void visit(Player p) {
        for (String s: this.getItems().keySet()) {
            p.changeResoureQuantity(s, getResource(s));
        }
    }

    @Override
    public void visit(Store s) { }
}
