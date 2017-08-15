package backend.random;

import backend.Data;
import backend.components.Player;

/**
 * Created by Blackstone on 12/4/15.
 */
public class GalacticBattle implements RandomEvent {

    @Override
    public void perform(int player) {
        Player current = Data.getPlayers().get(player);
        current.setOre(current.getOre() + 4);
        current.setEnergy(current.getEnergy() + 4);
        System.out.println(current.getName().toString() + ": DEBRIS FROM A GALATIC BATTLE NEAR BY DROPS " +
                "RESOURCES NEARBY, YOU FIND 4 ORE AND 4 ENERGY.");
    }
}
