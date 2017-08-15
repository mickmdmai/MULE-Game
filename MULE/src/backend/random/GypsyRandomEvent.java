package backend.random;

import backend.Data;
import backend.Game;
import backend.components.Player;

/**
 * Created by patrickcaruso on 12/1/15.
 */
public class GypsyRandomEvent extends RoundBasedEvent implements RandomEvent {

    public GypsyRandomEvent() {
        super();
    }

    @Override
    public void perform(int player) {
        Player current = Data.getPlayers().get(player);
        current.setMoney(current.getMoney() - (6 * moneyFactor));
        System.out.println(current.getName() + ": YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $" + 6*moneyFactor + " TO CLEAN IT UP.");
    }
}
