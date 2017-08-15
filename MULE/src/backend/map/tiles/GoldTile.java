package backend.map.tiles;

import backend.components.Production;

/**
 * Created by patrickcaruso on 12/3/15.
 */
public class GoldTile extends Tile {
    @Override
    public String getSoundFile() {
        return "gold.wav";
    }

    @Override
    public void calculateProduction() {
        Production p = new Production();
        p.addResource("crystite", 15);
        getOwner().accept(p);
    }

    @Override
    public String getImage() {
        return "gold";
    }
}
