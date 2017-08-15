package backend.map.tiles;

import backend.components.Production;

/**
 * Created by patrickcaruso on 12/1/15.
 */
public class Mountain2Tile extends Tile {

    private static String image = "medMountain";

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public String getSoundFile() {
        return "yodel.wav";
    }

    @Override
    public void calculateProduction() {
        Production p = new Production();
        p.addResource("food", 1);
        p.addResource("energy", 1);
        p.addResource("ore", 3);
        p.addResource("crystite", 2);
        getOwner().accept(p);
    }
}
