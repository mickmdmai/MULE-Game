package backend.components.mules;

import backend.View;
import javafx.scene.image.Image;

/**
 * Created by Edward on 12/1/2015.
 */
public class EnergyMule extends Mule {
    private static EnergyMule instance = new EnergyMule();
    private static int value = 150;
    private static String type = "energyMule";

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
        return View.getInstance().getIcon("energy");
    }

    public static EnergyMule getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Energy Mule";
    }
}
