package backend.components.mules;

import backend.View;
import javafx.scene.image.Image;

/**
 * Created by Edward on 12/2/2015.
 */
public class CrystiteMule extends Mule {
    private static CrystiteMule instance = new CrystiteMule();
    private static int value = 200;
    private static String type = "crystiteMule";

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
        return View.getInstance().getIcon("crystite");
    }

    public static CrystiteMule getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Crystite Mule";
    }
}
