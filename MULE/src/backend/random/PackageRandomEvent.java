package backend.random;

import backend.Data;
import backend.Game;
import backend.components.Player;

/**
 * Created by patrickcaruso on 12/1/15.
 */
public class PackageRandomEvent implements RandomEvent {

    @Override
    public void perform(int player) {
        Player current = Data.getPlayers().get(player);
        current.setMoney(current.getMoney() + 3);
        current.setEnergy(current.getEnergy() + 2);
        System.out.println(current.getName() + ": YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.");
    }
}
