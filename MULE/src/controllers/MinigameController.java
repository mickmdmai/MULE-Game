package controllers;

import backend.View;
import games.Catch.PlayCatch;
import games.FallDown.PlayFallDown;
import games.Frogger.PlayFrogger;
import games.Pong.PlayPong;
import games.Tetris.PlayTetris;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Blackstone on 12/3/15.
 */
public class MinigameController implements Initializable {

    @Override
    public void initialize(URL fxmlLocation, ResourceBundle resources) {

    }

    @FXML
    public void back() {
        View.getInstance().goTo("gameConfig");
    }

    @FXML
    public void playPong() {
        new PlayPong();
    }

    @FXML
    public void playFallDown() {
        new PlayFallDown();
    }

    @FXML
    public void playTetris() {
        new PlayTetris();
    }

    @FXML
    public void playFrogger() {
        new PlayFrogger();
    }

    @FXML
    public void playCatch() {
        new PlayCatch();
    }

}

