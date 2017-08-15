package backend.map.tiles;

import backend.components.Production;

/**
 * Created by patrickcaruso on 12/1/15.
 */
public class Mountain1Tile extends Tile {

    private static String image = "smallMountain";

    @Override
    public String getSoundFile() {
        return "yodel.wav";
    }

    @Override
    public void calculateProduction() {
        Production p = new Production();
        p.addResource("food", 1);
        p.addResource("energy", 1);
        p.addResource("ore", 2);
        p.addResource("crystite", 1);
        getOwner().accept(p);
    }

    @Override
    public String getImage() {
        return image;
    }
}
