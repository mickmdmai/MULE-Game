package backend.map.tiles;

import java.util.Random;

/**
 * Created by patrickcaruso on 12/1/15.
 */
public class TileFactory {

    public Tile nextTile() {
        Random rand = new Random();
        double val = Math.floor(Math.abs(rand.nextGaussian() * 2.5 ));
        if (val <= 1.75) {
            return generatePlainTile();
        } else if (val <= 2.55) {
            return generateMountain1Tile();
        } else if (val <= 3.5) {
            return generateMountain2Tile();
        } else if (val <= 4.75){
            return generateMountain3Tile();
        } else if (val <= 5.75){
            return generateForestTile();
        } else {
            return generateGoldTile();
        }
    }

    public GoldTile generateGoldTile() {
        return new GoldTile();
    }

    private Mountain1Tile generateMountain1Tile() {
        return new Mountain1Tile();
    }

    private Mountain2Tile generateMountain2Tile() {
        return new Mountain2Tile();
    }

    private Mountain3Tile generateMountain3Tile() {
        return new Mountain3Tile();
    }

    private PlainTile generatePlainTile() {
        return new PlainTile();
    }

    private ForestTile generateForestTile() {
        return new ForestTile();
    }
}
