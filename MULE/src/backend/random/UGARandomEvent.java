package backend.random;

import backend.Data;
import backend.Game;
import backend.components.Player;

/**
 * Created by patrickcaruso on 12/2/15.
 */
public class UGARandomEvent implements RandomEvent {


    @Override
    public void perform(int player) {
        Player current = Data.getPlayers().get(player);
        current.setFood(current.getFood()/2);
        System.out.println(current.getName() + ": MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.");
    }
}
