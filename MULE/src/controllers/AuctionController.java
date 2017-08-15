package controllers;

import backend.Game;
import backend.GameState;
import backend.View;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Edward on 9/25/2015.
 */
public class AuctionController implements Initializable {

    public final void initialize(URL fxmlLocation, ResourceBundle resources) {
        Game.getInstance().setState(GameState.AUCTION);
    }

    @FXML
    public final void back() {
        Game.getInstance().setState(GameState.LAND_SELECTION);
        View.getInstance().goTo("map");
    }
}
