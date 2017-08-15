package backend.components.mules;

import backend.components.Player;
import backend.components.Tradeable;
import backend.components.Visitor;
import backend.map.tiles.Tile;
import javafx.scene.image.Image;

public abstract class Mule implements Tradeable {

    protected Player owner;
    protected Tile tile;
    protected String name = "mule";

    public Mule() {}

    public Mule(Player owner, Tile tile) {
        this.owner = owner;
        this.tile = tile;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    /**
     * @return the Player that owns this Mule
     */
    public final Player getOwner() {
        return owner;
    }

    /**
     * Gets the tile the Mule is on. Use this for adding
     * resources for numPlayers. Iterate through the Player's
     * Mules, fetch the tiles, and append their resource
     * data
     * @return the Tile the Mule is associated to
     */
    public final Tile getTile() {
        return tile;
    }

    public final String getName() {
        return name;
    }

    @Override
    public abstract int getValue();

    public abstract String getType();

    public abstract Image getImage();

    /*
     * A plain mule is equivalent to any other type of mule
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (other instanceof Mule) {
            if (this instanceof PlainMule || other instanceof PlainMule) {
                return true;
            } else {
                Mule that = (Mule) other;
                return this.getType().equals(that.getType());
            }
        }
        return false;
    }
}
