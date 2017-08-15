package backend.components;

import backend.components.mules.Mule;
import backend.components.resources.Resource;

/**
 * Created by Edward on 12/2/2015.
 */
public interface Visitor {
    void visit(Player p);

    void visit(Store s);
}
