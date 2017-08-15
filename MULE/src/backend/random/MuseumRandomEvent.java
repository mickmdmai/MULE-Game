package backend.random;

import backend.Data;
import backend.Game;
import backend.components.Player;

/**
 * Created by patrickcaruso on 12/1/15.
 */
public class MuseumRandomEvent extends RoundBasedEvent implements RandomEvent {

    public MuseumRandomEvent() {
        super();
    }

    @Override
    public void perform(int player) {
        Player current = Data.getPlayers().get(player);
        System.out.println(current.getName() + ": THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $" + 8*moneyFactor + ".");
        current.setMoney(current.getMoney() + (8 * moneyFactor));
    }
}
