package backend.components.resources;

import backend.components.Tradeable;

/**
 * Created by Edward on 12/1/2015.
 */
public class Energy extends Resource {
    private static Energy instance = new Energy();
    private static String name = "energy";
    private static int value = 100;

    private Energy() {}

    public static Energy getInstance() {
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
