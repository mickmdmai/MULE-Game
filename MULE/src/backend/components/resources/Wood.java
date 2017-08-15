package backend.components.resources;

/**
 * Created by Edward on 12/3/2015.
 */
public class Wood extends Resource {
    private static Wood instance = new Wood();
    private static String name = "wood";
    private static int value = 100;

    private Wood() {}

    public static Wood getInstance() {
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
