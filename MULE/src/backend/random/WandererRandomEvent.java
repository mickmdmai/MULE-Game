package backend.random;

import backend.Data;
import backend.Game;
import backend.components.Player;

/**
 * Created by patrickcaruso on 12/1/15.
 */
public class WandererRandomEvent implements RandomEvent {

    @Override
    public void perform(int player) {
        Player current = Data.getPlayers().get(player);
        current.setOre(current.getOre() + 2);
        System.out.println(current.getName() + ": A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.");
    }
}
