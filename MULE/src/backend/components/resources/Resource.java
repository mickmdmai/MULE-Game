package backend.components.resources;

import backend.components.Tradeable;
import backend.components.Visitor;

/**
 * Created by Edward on 12/1/2015.
 */
public abstract class Resource implements Tradeable {

    @Override
    public boolean equals (Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other instanceof Resource) {
            Resource that = (Resource) other;
            return this.getName().equals(that.getName())
                    && this.getValue() == that.getValue();
        } else {
            return false;
        }
    }
}
