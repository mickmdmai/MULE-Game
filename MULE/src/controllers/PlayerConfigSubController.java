package controllers;

import backend.Data;
import backend.components.Player;
import backend.components.Race;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Edward on 9/10/2015.
 *
 * Controller for individual player configuration
 */
public class  PlayerConfigSubController implements Initializable {

    @FXML private Text player;
    @FXML private TextField name;
    @FXML private ComboBox<Race> race;
    @FXML private ColorPicker color;

    private IntegerProperty idx = new SimpleIntegerProperty();

    private BooleanBinding submittable;

    public final void initialize(URL fxmlLocation, ResourceBundle resources) {
        submittable = new BooleanBinding() {
            {
                super.bind(name.textProperty(), race.valueProperty(), color.valueProperty());
            }
            @Override
            protected boolean computeValue() {
                return name.getText() != null
                       && !name.getText().isEmpty()
                       && race.getValue() != null
                       && color.getValue() != null
                       && !color.getValue().equals(Color.BLACK);
            }
        };
        race.getItems().addAll(Race.values());
        player.textProperty().bind(new StringBinding() {
            {
                super.bind(idx);
            }

            @Override
            protected String computeValue() {
                return "Player " + (idx.get() + 1) + ":";
            }
        });
    }

    public final void setIdx(int ind) {
        this.idx.set(ind);
    }

    public final BooleanBinding getSubmittable() {
        return submittable;
    }

    public final TextField getName() {
        return name;
    }

    public final ComboBox<Race> getRace() {
        return race;
    }

    public final ColorPicker getColor() {
        return color;
    }

    public final void submit() {
        Player newPlayer = new Player(name.getText(), race
                .getValue(), color.getValue());
        newPlayer.idxProperty().set(idx.get());
        Data.getPlayers().add(idx.get(), newPlayer);
    }
}
