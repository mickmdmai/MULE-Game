package backend.map.tiles;

import backend.components.Production;

/**
 * Created by patrickcaruso on 12/1/15.
 */
public class PlainTile extends Tile {

    private static String image = "plains";

    @Override
    public String getSoundFile() {
        return "plains.wav";
    }

    @Override
    public void calculateProduction() {
        Production p = new Production();
        p.addResource("food", 2);
        p.addResource("energy", 3);
        p.addResource("ore", 1);
        getOwner().accept(p);
    }

    @Override
    public String getImage() {
        return image;
    }
}
