package backend.map.tiles;

import backend.components.Player;
import backend.components.mules.Mule;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class Tile {

    private ObjectProperty<Player> owner = new SimpleObjectProperty<>();
    private Mule mule;

    protected boolean isRiver;
    protected boolean isTown;

    /**
     * Changes the owner of a Tile
     * @param anOwner the new owner of the map
     */
    public final void setOwner(Player anOwner) {
        this.owner.set(anOwner);
    }

    /**
     * @return the owner of the given Tile as a Player
     */
    public final Player getOwner() {
        return owner.get();
    }

    /**
     * Update the Mule
     * @param m the mule to assign to the Tile
     */
    public final void setMule(Mule m) {
        this.mule = m;
    }

    /**
     * Checks whether a tiles has a Mule
     * @return whether the Tile has a Mule
     */
    public final boolean hasMule() {
        return mule != null;
    }

    public final Mule getMule() {
        return mule;
    }

    public boolean isRiver() {
        return isRiver;
    }

    public boolean isTown() {
        return isTown;
    }

    public abstract void calculateProduction();
    public abstract String getImage();
    public abstract String getSoundFile();
}
