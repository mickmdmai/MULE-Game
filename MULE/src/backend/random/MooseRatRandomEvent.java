package backend.random;

import backend.Data;
import backend.Game;
import backend.components.Player;

/**
 * Created by patrickcaruso on 12/1/15.
 */
public class MooseRatRandomEvent extends RoundBasedEvent implements RandomEvent {

    public MooseRatRandomEvent() {
        super();
    }

    @Override
    public void perform(int player) {
        Player current = Data.getPlayers().get(player);
        System.out.println(current.getName() + ": YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $" + 2*moneyFactor + ".");
        current.setMoney(current.getMoney() + (2 * moneyFactor));
    }
}
