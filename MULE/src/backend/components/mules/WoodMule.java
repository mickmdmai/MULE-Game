package backend.components.mules;

import backend.View;
import javafx.scene.image.Image;

/**
 * Created by Laurel on 12/3/15.
 */
public class WoodMule extends Mule {
    private static WoodMule instance = new WoodMule();
    private static int value = 225;
    private static String type = "woodMule";

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
        return View.getInstance().getIcon("wood");
    }

    public static WoodMule getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Wood Mule";
    }
}
