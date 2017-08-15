package backend.components.resources;

import backend.components.Tradeable;

/**
 * Created by Edward on 12/1/2015.
 */
public class Ore extends Resource {
    private static Ore instance = new Ore();
    private static String name = "ore";
    private static int value = 50;

    private Ore() {}

    public static Ore getInstance() {
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
