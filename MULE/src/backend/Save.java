package backend;

import backend.components.Player;
import backend.components.Store;
import backend.map.tiles.Tile;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Blackstone on 11/2/15.
 */
public class Save {

    private Connection con = null;
    private PreparedStatement gamestmt = null;
    private PreparedStatement playerstmt = null;
    private PreparedStatement storestmt = null;
    private PreparedStatement mapstmt = null;

    private String url = "jdbc:mysql://localhost:8889/muleDB";
    private String user = "root";
    private String password = "root";

    private String gameQuery = "UPDATE GAME SET CURRENT_PLAYER=?, ROUND=?, DIFFICULTY=?, NUM_PLAYERS=? WHERE SAVE='save1';";

    private String playerQuery = "UPDATE PLAYERS SET NAME=?, RED=?, GREEN=?, BLUE=?, RACE=?, MONEYS=?, ORE=?, ENERGY=?, FOOD=?, MULE=?"
                        + ", CRYSTITE=? WHERE (SAVE, POSITION)=('save1', ?);";

    private String storeQuery = "UPDATE STORE SET ORE=?, ENERGY=?, FOOD=?, CRYSTITE=?, MULE=? WHERE SAVE='save1';";

    private String mapQuery = "UPDATE TILES SET MULE=?, OWNER=? WHERE (SAVE, X_POS, Y_POS)=('save1', ?, ?);";

    public Save() {
        try {
            con = DriverManager.getConnection(url, user, password);

            // Save general game info.
            gamestmt = con.prepareStatement(gameQuery);
            gamestmt.setInt(1, Game.getInstance().getIdx());
            gamestmt.setInt(2, Game.getInstance().rounds.get());
            gamestmt.setInt(3, Data.getDifficulty());
            gamestmt.setInt(4, Data.getNumPlayers());
            gamestmt.executeUpdate();

            //Save Players
            playerstmt = con.prepareStatement(playerQuery);
            for (int i = 0; i < Data.getNumPlayers(); i++) {
                Player curr = Data.getPlayers().get(i);
                playerstmt.setString(1, curr.getName());
                playerstmt.setDouble(2, curr.getColor().getRed());
                playerstmt.setDouble(3, curr.getColor().getGreen());
                playerstmt.setDouble(4, curr.getColor().getBlue());
                playerstmt.setString(5, curr.getRace().name());
                playerstmt.setInt(6, curr.getMoney());
                playerstmt.setInt(7, curr.getOre());
                playerstmt.setInt(8, curr.getEnergy());
                playerstmt.setInt(9, curr.getFood());
                if (curr.hasMule()) {
                    String type = curr.getMule().getType();
                    int numType = type.equals("foodMule") ? 1
                            : type.equals("energyMule") ? 2
                            : type.equals("oreMule") ? 3
                            : type.equals("crystiteMule") ? 4
                            : 0;
                    playerstmt.setInt(10, numType);
                } else {
                    playerstmt.setNull(10, Types.INTEGER);
                }
                playerstmt.setInt(11, curr.getCrystite());
                playerstmt.setInt(12, i);
                playerstmt.execute();
            }

            //Save Store
            Store store = Store.getInstance();
            storestmt = con.prepareStatement(storeQuery);
            storestmt.setInt(1, store.getAmt("ore"));
            storestmt.setInt(2, store.getAmt("energy"));
            storestmt.setInt(3, store.getAmt("food"));
            storestmt.setInt(4, store.getAmt("crystite"));
            storestmt.setInt(5, store.getAmt("mules"));
            storestmt.execute();

            //Save TileMap
            mapstmt = con.prepareStatement(mapQuery);
            for(int x = 0; x < 9; x++) {
                for (int y = 0; y < 5; y++) {
                    Tile tile = Data.getTileMap().getTile(y, x);

                    if (tile.hasMule()) {
                        String type = tile.getMule().getType();
                        int numType = type.equals("foodMule") ? 1
                                : type.equals("energyMule") ? 2
                                : type.equals("oreMule") ? 3
                                : type.equals("crystiteMule") ? 4
                                : 0;
                        mapstmt.setInt(1, numType);
                    } else {
                        mapstmt.setNull(1, Types.INTEGER);
                    }

                    if (tile.getOwner() != null) {
                        mapstmt.setInt(2, tile.getOwner().idxProperty().get());
                    } else {
                        mapstmt.setNull(2, Types.INTEGER);
                    }
                    mapstmt.setInt(3, x);
                    mapstmt.setInt(4, y);
                    mapstmt.execute();
                }
            }


        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Save.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (gamestmt != null) {
                    gamestmt.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Save.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }

    }
}
