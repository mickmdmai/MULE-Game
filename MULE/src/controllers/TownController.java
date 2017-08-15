package controllers;

import backend.Game;
import backend.GameState;
import backend.View;
import interfaces.Menu;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Edward on 9/17/2015.
 */
public class TownController implements Initializable, Menu {

    public void initialize(URL fxmlLocation, ResourceBundle resources) {}

    @FXML public final void clickPub() {
        Game.getInstance().visitPub();
        //System.out.println("clicked Pub");
    }

    @FXML public final void clickStore() {
        Game.getInstance().setState(GameState.STORE);
        View.getInstance().goTo("store");
    }

    @FXML public final void clickLandOffice() {
        //System.out.println("clicked Land Office");
    }

    public void next() {

    }

    @FXML public void back() {
        Game.getInstance().setState(GameState.LAND_SELECTION);
        View.getInstance().goTo("map");
    }
}
