package backend.components.resources;

import backend.components.Tradeable;

/**
 * Created by Edward on 12/1/2015.
 */
public class Crystite extends Resource {
    private static Crystite instance = new Crystite();
    private String name = "crystite";
    private int value = 100;

    private Crystite() {}

    public static Crystite getInstance() {
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
