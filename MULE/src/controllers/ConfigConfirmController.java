package controllers;

import backend.Data;
import backend.GameState;
import backend.components.Player;
import backend.Game;
import backend.View;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Edward on 9/17/2015.
 */
public class ConfigConfirmController implements Initializable {

    @FXML private Label difficulty;
    @FXML private Label players;
    @FXML private Label map;

    @FXML private HBox box;

    public void initialize(URL fxmlLocation, ResourceBundle resources) {
        for (int i = 0; i < Data.numPlayersProperty().get(); i++) {
            loadConfigConfirmSub(i);
        }
        difficulty.setText("Difficulty: " + Data.getDifficulty());
        players.setText("Players: " + Data.numPlayersProperty().get());
        map.setText("TileMap: " + Data.getMapType());

        Data.getPlayers().addListener(new ListChangeListener<Player>() {
            @Override
            public void onChanged(Change<? extends Player> c) {
                if (Data.getPlayers().size() > 0) {
                    box.getChildren().clear();
                    for (int i = 0; i < Data.numPlayersProperty().get(); i++) {
                        loadConfigConfirmSub(i);
                    }
                }
            }
        });

        Data.difficultyProperty().addListener(observable -> {
            difficulty.setText("Difficulty: " + Data.getDifficulty());
        });
        Data.numPlayersProperty().addListener(observable -> {
            players.setText("Players: " + Data.getNumPlayers());
        });
        Data.mapTypeProperty().addListener(observable -> {
            map.setText("TileMap: " + Data.getMapType());
        });
    }

    @FXML public void next() {
        Game.getInstance().startGame();
        View.getInstance().setShowStatus(true);
        View.getInstance().goTo("map");
        Game.getInstance().setState(GameState.LAND_SELECTION);
    }

    @FXML public void back() {
        View.getInstance().goTo("playerConfigMain");
        Game.getInstance().setState(GameState.SETTINGS_SELECTION);
    }

    private void loadConfigConfirmSub(int idx) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/fxml/configConfirmSub.fxml"));
        Parent scene = null;
        ConfigConfirmSubController controller = null;
        try {
            scene = loader.load();
            controller = loader.getController();
            controller.setValues(idx, Data.getPlayers().get(idx).getName(),
                    Data.getPlayers().get(idx).getRace(),
                    Data.getPlayers().get(idx).getColor());
            box.getChildren().add(scene);
        } catch (IOException e) {
            System.out.println("couldn't load configConfirmSub.fxml");
        }
    }
}
