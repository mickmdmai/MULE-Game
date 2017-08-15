package backend.map.config;

import backend.components.Player;
import backend.map.tiles.Tile;

import java.util.ArrayList;

/**
 * I think it's going to be a lot easier down the road if we switch game
 * tiles to an ObservableArrayList. This will allow the fxml controllers to
 * be bound to changes to the map.
 */

public class TileMap {

    Tile[][] gameTiles;

    /**
     * Initiates a new map with a 5 x 9 Tile array
     * @param tileInfo the type of Tile each tiles is
     */
    public TileMap(Tile[][] tileInfo) {
        if (tileInfo.length > 9 || tileInfo.length > 5) {
            throw new IndexOutOfBoundsException("Tile info array is not the correct size");
        }
        gameTiles = tileInfo;
    }

    /**
     * Initiates a new map with the default layout
     */
    public TileMap() {
         this(MapGenerator.getInstance().defaultMap());
    }

    /**
     * Returns the tiles at the given x, y location
     * @param x the x location to be returned
     *          Domain: [0, 4]
     * @param y the y location to be returned
     *          Domain: [0, 8]
     * @return the tiles at the given coordinate
     */
    public final Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x > 4 || y > 8) {
            throw new IndexOutOfBoundsException(x + ", " + y + " is out of bounds on map!");
        }
        return gameTiles[x][y];
    }

    /**
     * All tiles in the map field
     * @return a two-dimensional array of type Tile
     */
    public final Tile[][] getTiles() {
        return gameTiles;
    }

    /**
     * Gets the tiles owned by a single player as a one-dimensional array of Tile
     * @param player the player to search
     * @return all tiles owned by the given player
     */
    public final Tile[] getTilesOwnedBy(Player player) {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].getOwner().equals(player)) {
                    tiles.add(gameTiles[i][j]);
                }
            }
        }
        return tiles.toArray(new Tile[tiles.size()]);
    }
}
