package backend.random;

import backend.Data;
import backend.Game;
import backend.components.Player;

/**
 * Created by patrickcaruso on 12/1/15.
 */
public class CatBugRandomEvent extends RoundBasedEvent implements RandomEvent {

    public CatBugRandomEvent() {
        super();
    }

    @Override
    public void perform(int player) {
        Player current = Data.getPlayers().get(player);
        current.setMoney(current.getMoney() - (4 * moneyFactor));
        System.out.println(current.getName() + ": FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $" + 4*moneyFactor + ".");
    }
}
