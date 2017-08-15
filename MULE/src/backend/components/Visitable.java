package backend.components;

/**
 * Created by Edward on 12/2/2015.
 */
public interface Visitable {
    void accept(Visitor v);
}
