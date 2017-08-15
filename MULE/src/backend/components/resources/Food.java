package backend.components.resources;

import backend.components.Tradeable;

/**
 * Created by Edward on 12/1/2015.
 */
public class Food extends Resource {
    private static Food instance = new Food();
    private static String name = "food";
    private static int value = 30;

    private Food() {}

    public static Food getInstance() {
        return instance;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }
}
