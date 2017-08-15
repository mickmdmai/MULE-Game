package backend;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Edward on 9/10/2015.
 *
 * Possibly relevant article
 * https://blogs.oracle.com/acaicedo/entry/managing_multiple_screens_in_javafx1
 *
 * This class extends StackPane so it can be directly added to the scene
 * graph in the Main class. It has a HashMap that contains all the different
 * scenes of the game. The hash map keys are the name of the fxml files for
 * that scene (without '.fxml' at the end)
 */
public class View extends StackPane {
    private static View instance = new View();
    private HashMap<String, Node> scenes = new HashMap<>();
    private HashMap<String, Image> mapTiles = new HashMap<>();
    private HashMap<String, Image> icons = new HashMap<>();

    private StringProperty curr = new SimpleStringProperty();

    private BorderPane status;
    private BooleanProperty showStatus = new SimpleBooleanProperty(false);

    private View() {
        status = (BorderPane) scenes.get("status");
        mapTiles.put("smallMountain", new Image(getClass().getResource
                ("/assets/images/tiles/smallMountain.png").toExternalForm()));
        mapTiles.put("medMountain", new Image(getClass().getResource
                ("/assets/images/tiles/medMountain.png").toExternalForm()));
        mapTiles.put("bigMountain", new Image(getClass().getResource
                ("/assets/images/tiles/bigMountain.png").toExternalForm()));
        mapTiles.put("plains", new Image(getClass().getResource
                ("/assets/images/tiles/plains.png").toExternalForm()));
        mapTiles.put("river", new Image(getClass().getResource
                ("/assets/images/tiles/riverns.png").toExternalForm()));
        mapTiles.put("town", new Image(getClass().getResource
                ("/assets/images/tiles/town.png").toExternalForm()));
        mapTiles.put("forest", new Image(getClass().getResource
                ("/assets/images/tiles/forest.png").toExternalForm()));
        mapTiles.put("gold", new Image(getClass().getResource
                ("/assets/images/tiles/gold.png").toExternalForm()));

        icons.put("crystite", new Image(getClass().getResource
                ("/assets/images/icons/crystite.png").toExternalForm()));
        icons.put("energy", new Image(getClass().getResource
                ("/assets/images/icons/energy.png").toExternalForm()));
        icons.put("food", new Image(getClass().getResource
                ("/assets/images/icons/food.png").toExternalForm()));
        icons.put("gold", new Image(getClass().getResource
                ("/assets/images/icons/gold.png").toExternalForm()));
        icons.put("mule", new Image(getClass().getResource
                ("/assets/images/icons/mule.png").toExternalForm()));
        icons.put("ore", new Image(getClass().getResource
                ("/assets/images/icons/ore.png").toExternalForm()));
        icons.put("wood", new Image(getClass().getResource
                ("/assets/images/icons/wood.png").toExternalForm()));


        mapTiles.put("riverew", new Image(getClass().getResource
                ("/assets/images/tiles/riverew.png").toExternalForm()));
        mapTiles.put("riverne", new Image(getClass().getResource
                ("/assets/images/tiles/riverne.png").toExternalForm()));
        mapTiles.put("riverns", new Image(getClass().getResource
                ("/assets/images/tiles/riverns.png").toExternalForm()));
        mapTiles.put("rivernw", new Image(getClass().getResource
                ("/assets/images/tiles/rivernw.png").toExternalForm()));
        mapTiles.put("riverse", new Image(getClass().getResource
                ("/assets/images/tiles/riverse.png").toExternalForm()));
        mapTiles.put("riversw", new Image(getClass().getResource
                ("/assets/images/tiles/riversw.png").toExternalForm()));

        goTo("gameConfig");
        Game.getInstance().setState(GameState.SETTINGS_SELECTION);
        showStatus.addListener((observable, oldVal, newVal) -> {
            if (newVal) {
                goTo(curr.get());
            }
        });
    }

    /**
     * The method that switches scenes. If the requested scene is already in
     * the hash map, it is set as the current scene. If the requested scene
     * is not in the map, it is loaded, added to the map and set as the
     * current scene.
     * @param fxml The fxml file to be navigated to. DO NOT include '.fxml'
     *             at the end of the filename.
     */
    public void goTo(String fxml) {
        if (!scenes.containsKey(fxml)) {
            load(fxml);
        }
        if (showStatus.get()) {
            if (status == null) {
                load("status");
                status = (BorderPane) scenes.get("status");
            }
            getChildren().setAll(status);
            status.setCenter(scenes.get(fxml));
        } else {
            getChildren().setAll(scenes.get(fxml));
        }
        curr.set(fxml);
    }

    /**
     * Helper method for goTo(). Performs the loading of fxml files if they
     * are not already in the hashmap. Adds loaded scenes to the backing map.
     * @param fxml The fxml file to be loaded. DO NOT include '.fxml' at the
     *             end of the filename.
     */
    private void load(String fxml) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("/fxml/" + fxml + ".fxml"));
        Node scene = null;
        try {
            scene = loader.load();
            loader.getController();
        } catch (IOException e) {
            System.out.println("Couldn't load " + fxml + ".fxml");
            e.printStackTrace();
        }
        scenes.put(fxml, scene);
    }

    public Image getImage(String key) {
        return mapTiles.get(key);
    }

    public Image getIcon(String key) {
        return icons.get(key);
    }

    public static View getInstance() {
        return instance;
    }

    public void setShowStatus(boolean status) {
        showStatus.set(status);
    }

    public HashMap<String, Node> getScenes() {
        return scenes;
    }

    public HashMap<String, Image> getMapTiles() {
        return mapTiles;
    }
}
