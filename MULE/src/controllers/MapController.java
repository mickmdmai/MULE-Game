package controllers;

import backend.Data;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Edward on 9/14/2015.
 */
public class MapController implements Initializable {

    @FXML private GridPane grid; // 5x9 grid
    private static TileController[][] tileControllers;

    public final void initialize(URL fxmlLocation, ResourceBundle resources) {
        tileControllers = new TileController[5][9];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 9; c++) {
                loadTile(r, c);
            }
        }
    }

    private void loadTile(int row, int col) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/fxml/tile.fxml"));
        Node scene;
        TileController controller;
        try {
            scene = loader.load();
            controller = loader.getController();
            tileControllers[row][col] = controller;
            controller.setTile(Data.getTileMap().getTile(row, col));
            controller.setParent(grid);
            grid.add(scene, col, row);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static TileController getTileController(int row, int col) {
        return tileControllers[row][col];
    }
}
