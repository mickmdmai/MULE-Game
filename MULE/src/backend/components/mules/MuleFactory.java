package backend.components.mules;

import backend.components.mules.*;

/**
 * Created by Edward on 12/1/2015.
 */
public class MuleFactory {
    private static MuleFactory instance = new MuleFactory();

    private MuleFactory() {}

    public Mule getMule(String type) {
        Mule result = null;
        switch (type) {
            case "energyMule":
                result = new EnergyMule();
                break;
            case "foodMule":
                result = new FoodMule();
                break;
            case "oreMule":
                result = new OreMule();
                break;
            case "crystiteMule":
                result = new CrystiteMule();
                break;
            case "plainMule":
                result = new PlainMule();
                break;
            case "woodMule":
                result = new WoodMule();
                break;
            case "goldMule":
                result = new GoldMule();
        }
        return result;
    }

    public static MuleFactory getInstance() {
        return instance;
    }
}
