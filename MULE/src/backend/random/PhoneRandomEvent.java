package backend.random;

import backend.Data;
import backend.Game;
import backend.components.Player;
import backend.components.mules.Mule;
import javafx.collections.ObservableList;

/**
 * Created by patrickcaruso on 12/2/15.
 */
public class PhoneRandomEvent implements RandomEvent {
    @Override
    public void perform(int player) {
        Player current = Data.getPlayers().get(player);
        current.setMoney(current.getMoney() * 2);
        ObservableList<Mule> mules = current.getMules();
        for(int i = 0; i < mules.size(); i++) {
            String type = mules.get(i).getType();
            switch (type) {
                case "crystiteMule":
                    //do nothing, crystite not implemented.
                    break;
                case "energyMule":
                    current.setEnergy(current.getEnergy() + 2);
                    break;
                case "foodMule":
                    current.setFood(current.getFood() + 2);
                    break;
                case "goldMule":
                    current.setMoney(current.getMoney() + 2);
                    break;
                case "oreMule":
                    current.setOre(current.getOre() + 2);
                    break;
                case "woodMule":
                    current.setWood(current.getWood() + 2);
                    break;
            }
        }
        System.out.println(current.getName() + ": YOU FIND A CELLULAR DEVICE WHICH ALLOWS YOU TO INCREASE PRODUCTIVITY. " +
                "YOU RECEIVE +2 GOODS PER MULE");
    }
}
