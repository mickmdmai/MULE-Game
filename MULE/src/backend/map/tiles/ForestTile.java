package backend.map.tiles;

import backend.components.Production;

/**
 * Created by patrickcaruso on 12/2/15.
 */
public class ForestTile extends Tile {

    private static String image = "forest";

    @Override
    public String getSoundFile() {
        return "forest.wav";
    }

    @Override
    public void calculateProduction() {
        Production p = new Production();
        p.addResource("wood", 3);
        p.addResource("food", 1);
        p.addResource("energy", 1);
        getOwner().accept(p);
    }



    @Override
    public String getImage() {
        return image;
    }
}
