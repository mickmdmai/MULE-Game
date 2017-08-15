package backend.components.mules;

import backend.View;
import javafx.scene.image.Image;

/**
 * Created by Edward on 12/1/2015.
 */
public class OreMule extends Mule {
    private static OreMule instance = new OreMule();
    private static int value = 175;
    private static String type = "oreMule";

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
        return View.getInstance().getIcon("ore");
    }

    public static OreMule getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Ore Mule";
    }
}
