package backend;

import backend.components.Player;
import backend.map.config.TileMap;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 9/11/2015.
 *
 * Contains public values from the menu screens to allow other classes access
 * to these values and allow changes without accessing the controllers. The
 * values are bidirectionally bound to their corresponding element in the
 * fxml scenes.
 */
public abstract class Data {
    private static IntegerProperty numPlayers = new SimpleIntegerProperty();
    private static StringProperty mapType = new SimpleStringProperty();
    private static IntegerProperty difficulty = new SimpleIntegerProperty();
    private static ObservableList<Player> players = FXCollections
            .observableArrayList(new Callback<Player, Observable[]>() {
                @Override
                public Observable[] call(Player param) {
                    return new Observable[]{param.colorProperty(),
                            param.nameProperty(), param.raceProperty(),
                            param.scoreBinding(), param.muleProperty(),
                            param.getOwnedTiles()};
                }
            });
    private static TileMap tileMap = new TileMap();

    public static int getNumPlayers() {
        return numPlayers.get();
    }

    public static IntegerProperty numPlayersProperty() {
        return numPlayers;
    }

    public static String getMapType() {
        return mapType.get();
    }

    public static StringProperty mapTypeProperty() {
        return mapType;
    }

    public static int getDifficulty() {
        return difficulty.get();
    }

    public static IntegerProperty difficultyProperty() {
        return difficulty;
    }

    public static ObservableList<Player> getPlayers() {
        return players;
    }

    public static TileMap getTileMap() {
        return tileMap;
    }

    public static void setTileMap(TileMap newTileMap) {
        tileMap = newTileMap;
    }
}
