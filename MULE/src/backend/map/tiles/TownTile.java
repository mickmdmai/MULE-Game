package backend.map.tiles;

/**
 * Created by patrickcaruso on 12/1/15.
 */
public class TownTile extends Tile {

    private static String image = "town";

    public TownTile() {
        this.isTown = true;
    }

    @Override
    public String getSoundFile() {
        return "town.wav";
    }

    @Override
    public void calculateProduction() { }

    @Override
    public String getImage() {
        return image;
    }
}
