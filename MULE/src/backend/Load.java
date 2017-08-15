package backend;

import backend.components.Player;
import backend.components.Race;
import backend.components.Store;
import backend.components.mules.Mule;
import backend.components.mules.MuleFactory;
import backend.map.tiles.Tile;
import controllers.MapController;
import javafx.scene.paint.Color;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Blackstone on 11/2/15.
 */
public class Load {

    private Connection con = null;
    private Statement gamestmt = null;
    private PreparedStatement playerstmt = null;
    private Statement storestmt = null;
    private Statement mapstmt = null;

    private String url = "jdbc:mysql://localhost:8889/muleDB";
    private String user = "root";
    private String password = "root";

    private String gameQuery = "SELECT * FROM GAME WHERE SAVE='save1';";

    private String playerQuery = "SELECT * FROM PLAYERS WHERE (SAVE, POSITION)=('save1', ?);";

    private String storeQuery = "SELECT * FROM STORE WHERE SAVE='save1';";

    private String mapQuery = "SELECT * FROM TILES WHERE SAVE='save1';";

    public Load() {

        try {
            con = DriverManager.getConnection(url, user, password);

            // Load Gen Game Details
            gamestmt = con.createStatement();
            ResultSet gamers = gamestmt.executeQuery(gameQuery);
            if (gamers.next()) {
                Data.numPlayersProperty().set(gamers.getInt("NUM_PLAYERS"));
                Data.mapTypeProperty().set("Standard");
                Data.difficultyProperty().set(gamers.getInt("DIFFICULTY"));
                Game.getInstance().rounds.set(gamers.getInt("ROUND"));
                Game.getInstance().setIdx(gamers.getInt("CURRENT_PLAYER"));
            }
            gamers.close();

            // Load Store details
            storestmt = con.createStatement();
            ResultSet storeRS = storestmt.executeQuery(storeQuery);
            if (storeRS.next()) {
                Store store = Store.getInstance();
                store.setAmt("ore", storeRS.getInt("ORE"));
                store.setAmt("food", storeRS.getInt("FOOD"));
                store.setAmt("energy", storeRS.getInt("ENERGY"));
                store.setAmt("mules", storeRS.getInt("MULE"));
                store.setAmt("crystite", storeRS.getInt("CRYSTITE"));
            }
            storeRS.close();
//
//
            // Load Player Details
            playerstmt = con.prepareStatement(playerQuery);
            ResultSet playerRS;
            Player curr;
            Race race;
            String name;
            Double r, g, b;
            Color color;
            int mule;
            for (int i = 0; i < Data.getNumPlayers(); i++) {
                playerstmt.setInt(1, i);
                playerRS = playerstmt.executeQuery();

                if (playerRS.next()) {
                    name = playerRS.getString("NAME");
                    race = Race.valueOf(playerRS.getString("RACE"));
                    r = playerRS.getDouble("RED");
                    g = playerRS.getDouble("GREEN");
                    b = playerRS.getDouble("BLUE");
                    color = Color.color(r, g, b);
                    curr = new Player(name, race, color);

                    curr.setEnergy(playerRS.getInt("ENERGY"));
                    curr.setFood(playerRS.getInt("FOOD"));
                    curr.setMoney(playerRS.getInt("MONEYS"));
                    curr.setCrystite(playerRS.getInt("CRYSTITE"));
                    curr.setOre(playerRS.getInt("ORE"));
                    mule = playerRS.getInt("MULE");
                    if (!playerRS.wasNull()) {
                        String type = mule == 1 ? "foodMule"
                                : mule == 2 ? "energyMule"
                                : mule == 3 ? "oreMule"
                                : mule == 4 ? "crystiteMule"
                                : "plainMule";
                        curr.setMule(MuleFactory.getInstance().getMule(type));
                    }
                    Data.getPlayers().add(i, curr);
                }
                playerRS.close();
            }

            Game.debugMode = true;
            Game.getInstance().startGame();
            Game.getInstance().setState(GameState.LAND_SELECTION);
            View.getInstance().setShowStatus(true);
            View.getInstance().goTo("map");

            // Load map details
            mapstmt = con.createStatement();
            ResultSet mapRS = mapstmt.executeQuery(mapQuery);
            int x, y, typeNum, owner;
            Tile tile;
            while(mapRS.next()) {
                x = mapRS.getInt("X_POS");
                y = mapRS.getInt("Y_POS");
                tile = Data.getTileMap().getTile(y, x);

                typeNum = mapRS.getInt("MULE");
                String type = typeNum == 1 ? "foodMule"
                        : typeNum == 2 ? "energyMule"
                        : typeNum == 3 ? "oreMule"
                        : typeNum == 4 ? "crystiteMule"
                        : "plainMule";
                if (!mapRS.wasNull()) {
                    tile.setMule(MuleFactory.getInstance().getMule(type));
                    MapController.getTileController(y, x).setMule();
                }
                owner = mapRS.getInt("OWNER");
                if (!mapRS.wasNull()) {
                    tile.setOwner(Data.getPlayers().get(owner));
                    Data.getPlayers().get(owner).getOwnedTiles().add(tile);
                    MapController.getTileController(y, x).paintBorder(Data.getPlayers()
                            .get(owner).getColor());
                }

            }
            mapRS.close();



        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Save.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {

                if (gamestmt != null) {
                    gamestmt.close();
                    playerstmt.close();
                    mapstmt.close();
                    storestmt.close();
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
