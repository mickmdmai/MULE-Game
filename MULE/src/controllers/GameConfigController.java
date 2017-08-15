package controllers;

import backend.*;
import backend.map.config.MapGenerator;
import backend.map.config.TileMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the game configuration screen
 */
public class GameConfigController implements Initializable{

    //@FXML private Button submit;
    @FXML private Slider difficulty;
    @FXML private Slider players;
    @FXML private ComboBox<String> map;
    //@FXML private Button loadBtn;

    //TODO use setLabelFormatter() on difficulty slider to change labels
    public final void initialize(URL fxmlLocation, ResourceBundle resources) {
        Game.getInstance().setState(GameState.SETTINGS_SELECTION);
        map.getItems().addAll("Standard", "Random");
        map.setValue("Standard");
        Data.difficultyProperty().bindBidirectional(difficulty.valueProperty());
        Data.numPlayersProperty().bindBidirectional(players.valueProperty());
        Data.mapTypeProperty().bindBidirectional(map.valueProperty());
    }

    /**
     * Calling this method on mouse click is handled by fxml
     */
    @FXML public final void next() {
        Game.getInstance().setState(GameState.SETTINGS_SELECTION);
        View.getInstance().goTo("playerConfigMain");
        Data.setTileMap(new TileMap());
        if (Data.getMapType().equals("Random")) {
            Data.setTileMap(new TileMap(MapGenerator.getInstance().generateRandomMap()));
        } else {
            Data.setTileMap(new TileMap(MapGenerator.getInstance().defaultMap()));
        }
    }

    @FXML public void back() {
        //main screen does not have back
    }

    @FXML public final void load() {
        //loadBtn a previous stateß
        new Load();
    }

    @FXML public void minigames() {
        View.getInstance().goTo("minigames");
    }

    public final int numPlayers() {
        return (int) players.getValue();
    }
}
