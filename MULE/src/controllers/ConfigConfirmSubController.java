package controllers;

import backend.components.Race;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Edward on 9/17/2015.
 * TODO might want to clean this up so that it doesn't have to reload every
 * time there is a change to the players
 */
public class ConfigConfirmSubController implements Initializable {

    @FXML private Text player;
    @FXML private Label name;
    @FXML private Label race;
    @FXML private Rectangle color;

    public void initialize(URL fxmlLocation, ResourceBundle resources) {
    }

    public final void setValues(int val, String newName, Race newRace, Color
            newColor) {
        this.player.setText("Player " + (val + 1) + ":");
        this.name.setText("Name: " + newName);
        this.race.setText("Race: " + newRace);
        this.color.setFill(newColor);
    }
}
