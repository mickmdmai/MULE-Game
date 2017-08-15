package backend.components.mules;

import backend.View;
import javafx.scene.image.Image;

/**
 * Created by Edward on 12/1/2015.
 */
public class FoodMule extends Mule {
    private static FoodMule instance = new FoodMule();
    private static int value = 125;
    private static String type = "foodMule";

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
        return View.getInstance().getIcon("food");
    }

    public static FoodMule getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Food Mule";
    }
}
