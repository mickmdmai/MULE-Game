package controllers;

import backend.Game;
import backend.GameState;
import backend.components.Race;
import backend.Data;
import backend.View;
import javafx.beans.Observable;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Edward on 9/10/2015.
 *
 * Controller for the player configuration container screen. This scene will
 * load the appropriate number of individual player configuration nodes and
 * add them to the scene.
 */
public class PlayerConfigMainController implements Initializable {

    @FXML private Button submit;
    @FXML private Button back;
    @FXML private HBox box;

    private ObservableList<PlayerConfigSubController> subControllers =
            FXCollections.observableArrayList(
                    new Callback<PlayerConfigSubController, Observable[]>() {
                        @Override
                        public Observable[] call(
                                PlayerConfigSubController param) {
                            return new Observable[]{param.getSubmittable()};
                        }
                    });

    private BooleanBinding submittable = new BooleanBinding() {
        {
            super.bind(subControllers);
        }
        @Override
        protected boolean computeValue() {
            ArrayList<String> names = new ArrayList<>();
            ArrayList<Race> races = new ArrayList<>();
            ArrayList<Color> colors = new ArrayList<>();
            for (PlayerConfigSubController curr : subControllers) {
                if (!curr.getSubmittable().get()) {
                    return false;
                } else if (names.contains(curr.getName().getText().toLowerCase())
                           || races.contains(curr.getRace().getValue())
                           || colors.contains(curr.getColor().getValue())) {
                    return false;
                } else {
                    names.add(curr.getName().getText().toLowerCase());
                    races.add(curr.getRace().getValue());
                    colors.add(curr.getColor().getValue());
                }
            }
            return true;
        }
    };

    public void initialize(URL fxmlLocation, ResourceBundle resources) {
        for (int i = 0; i < Data.numPlayersProperty().get(); i++) {
            loadPlayerConfigSub(i);
        }
        Data.numPlayersProperty().addListener(observable -> {
            Data.getPlayers().clear();
            subControllers.clear();
            box.getChildren().clear();
            for (int i = 0; i < Data.numPlayersProperty().get(); i++) {
                loadPlayerConfigSub(i);
            }
        });

        submit.disableProperty().bind(submittable.not());
    }

    private void loadPlayerConfigSub(int idx) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/fxml/playerConfigSub.fxml"));
        Parent scene = null; //dummy value so compiler will shut up
        Initializable controller = null;
        try {
            scene = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            System.out.println("Couldn't load playerConfigSub.fxml");
            e.printStackTrace();
        }
        subControllers.add(idx, (PlayerConfigSubController) controller);
        subControllers.get(idx).setIdx(idx);
        box.getChildren().add(scene);
    }

    //called on submit button pressed
    //only called when data is submittable
    @FXML public void next() {
        submitData();
        Game.getInstance().setState(GameState.SETTINGS_CONFIRMATION);
        View.getInstance().goTo("configConfirm");
    }

    /**
     * calling this method on mouse click handled by fxml
     */
    @FXML public void back() {
        Game.getInstance().setState(GameState.SETTINGS_SELECTION);
        View.getInstance().goTo("gameConfig");
    }

    private void submitData() {
        for (PlayerConfigSubController curr : subControllers) {
            curr.submit();
        }
    }
}
