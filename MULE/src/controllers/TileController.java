package controllers;

import backend.Game;
import backend.GameState;
import backend.View;
import backend.audio.AudioPlayer;
import backend.map.tiles.RiverTile;
import backend.map.tiles.Tile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Edward on 9/13/2015.
 */
public class TileController implements Initializable {

    @FXML private StackPane pane;
    @FXML private Rectangle rect;
    @FXML private ImageView bg;
    @FXML private ImageView icon;
    @FXML private Label label;

    //@FXML private Label text;

    private Color color = Color.BLACK;
    private Tile tile;

    private int landCost = 300;

    private GridPane parent;
    private int[] parentSize = {5, 9};

    /**
     * fxml calls this method on mouse click
     */
    @FXML public final void select() {
        Game.getInstance().notSkipped();
        if ((Game.getInstance().getState() == GameState.LAND_SELECTION
            || Game.getInstance().getState() == GameState.MOVE_MULE)
            && tile.isTown()) {
                Game.getInstance().setState(GameState.TOWN);
                View.getInstance().goTo("town");
                return;
        }

        if (Game.getInstance().getState() == GameState.LAND_SELECTION
                && tile.getOwner() == null) {
                selectLand();
        }

        if (Game.getInstance().getState() == GameState.MOVE_MULE) {
            moveMule();
        }
    }

    public final void selectLand() {
        if (Game.getInstance().freeLand.get()) {
            AudioPlayer player = new AudioPlayer(tile.getSoundFile(), 5f);
            player.play();
            tile.setOwner(Game.getInstance().curr.get());
            this.color = Game.getInstance().curr.get().getColor();
            rect.setStroke(color);
            Game.getInstance().curr.get().getOwnedTiles().add(
                    tile);
            Game.getInstance().notSkipped();
            Game.getInstance().nextTurn();
        } else if (Game.getInstance().curr.get().getMoney() >= landCost) {
            tile.setOwner(Game.getInstance().curr.get());
            this.color = Game.getInstance().curr.get().getColor();
            rect.setStroke(color);
            Game.getInstance().curr.get().setMoney(
                    Game.getInstance().curr.get().getMoney() - landCost);
            Game.getInstance().curr.get().getOwnedTiles().add(
                    tile);
            Game.getInstance().notSkipped();
            Game.getInstance().nextTurn();
        }
    }

    public final void moveMule() {
        if (tile.getOwner() != null && tile.getOwner().equals(Game
                .getInstance().curr.get()) && !tile.hasMule()) {
            if (Game.getInstance().curr.get().hasMule()) {
                AudioPlayer player = new AudioPlayer("mule.wav", 5f);
                player.play();
                tile.setMule(Game.getInstance().curr.get().getMule());
                icon.setImage(
                        Game.getInstance().curr.get().getMule().getImage());
                label.setText(Game.getInstance().curr.get().getMule().toString());
                Game.getInstance().curr.get().removeMule();
            }
        } else {
            Game.getInstance().curr.get().removeMule();
            //System.out.println("mule lost");
        }
        Game.getInstance().setState(GameState.LAND_SELECTION);
    }

    public final void setTile(Tile aTile) {
        this.tile = aTile;
        selectBG();
    }

    public final void setParent(GridPane aParent) {
        this.parent = aParent;
        rect.heightProperty().bind(parent.heightProperty().divide
                (parentSize[0]));
        rect.widthProperty().bind(parent.widthProperty().divide(parentSize[1]));
        pane.prefHeightProperty().bind(parent.heightProperty().divide
                (parentSize[0]));
        pane.prefHeightProperty().bind(parent.widthProperty().divide(parentSize[1]));
        bg.fitHeightProperty().bind(parent.heightProperty().divide
                (parentSize[0]));
        bg.fitWidthProperty().bind(parent.widthProperty().divide
                (parentSize[1]));
    }

    private void selectBG() {
        if (tile instanceof RiverTile) {
            System.out.println("hi: " + tile.getImage());
        }
            bg.setImage(View.getInstance().getImage(tile.getImage()));
    }

    @FXML public final void mouseEntered() {
        rect.setStroke(color);
    }

    @FXML public final void mouseExited() {
        if (tile.getOwner() == null) {
            rect.setStroke(Color.TRANSPARENT);
        }
    }

    public final void paintBorder(Color aColor) {
        this.color = aColor;
        rect.setStroke(color);
    }

    public final void setMule() {
        rect.setFill(new Color(0, 0, 0, .5));
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
