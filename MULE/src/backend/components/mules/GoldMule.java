package backend.components.mules;

import backend.View;
import javafx.scene.image.Image;

/**
 * Created by Laurel on 12/3/15.
 */
public class GoldMule extends Mule {
    private static GoldMule instance = new GoldMule();
    private static int value = 10000;
    private static String type = "goldMule";

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Image getImage() {
        return View.getInstance().getIcon("gold");
    }

    public static GoldMule getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Gold Mule";
    }
}
