package backend.components.mules;

import backend.View;
import javafx.scene.image.Image;

/**
 * Created by Edward on 12/1/2015.
 */
public class PlainMule extends Mule {
    private static PlainMule instance = new PlainMule();
    private static int value = 100;
    private static String type = "plainMule";

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
        return null;
    }

    public static PlainMule getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Plain Mule";
    }
}
