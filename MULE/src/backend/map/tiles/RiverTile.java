package backend.map.tiles;

import backend.components.Production;

/**
 * Represents a river tile
 */
public class RiverTile extends Tile{

    private String customImage = "riverns";

    /**
     * Creates a new river tile, which is interpreted
     * by the map as a river
     */
    public RiverTile() {
        this.isRiver = true;
    }

    /**
     * @return the sound file associated with RiverTile
     * which is played when the RiverTile is clicked
     */
    @Override
    public String getSoundFile() {
        return "river.wav";
    }

    /**
     * Sets the river type depending on bend orientation
     * @param type the type of the river image
     */
    public void setRiverType(String type) {
        this.customImage = type;
    }

    /**
     * Adds 4 food and 2 energy to the tile's owner
     */
    @Override
    public void calculateProduction() {
        Production p = new Production();
        p.addResource("food", 4);
        p.addResource("energy", 2);
        getOwner().accept(p);
    }

    /**
     * Gets the Tile's image
     * @return the RiverTile's associated string file
     * as the file name
     */
    @Override
    public String getImage() {
        return customImage;
    }
}
