package backend.random;

import backend.Data;
import backend.components.Player;

/**
 * Created by Blackstone on 12/4/15.
 */
public class AlienRaiders implements RandomEvent {
    @Override
    public void perform(int player) {
        Player current = Data.getPlayers().get(player);
        current.setFood(current.getFood() - 4);
        current.setEnergy(current.getEnergy() - 2);
        current.setOre(current.getOre() - 2);

        if(current.getOre() < 0) {
            current.setOre(0);
        }

        if(current.getFood() < 0) {
            current.setFood(0);
        }
        if(current.getEnergy() < 0) {
            current.setEnergy(0);
        }
        System.out.println(current.getName().toString() + ": YOU LOST 4 FOOD, 2 ORE, AND 2 ENERGY.");
    }
}
