package backend.random;

import backend.Game;
import backend.components.Player;

/**
 * Created by Blackstone on 12/4/15.
 */
public class BurriedTreasureEvent extends RoundBasedEvent implements RandomEvent {

    public BurriedTreasureEvent() {
        super();
    }

    @Override
    public void perform(int player) {
        Player current = Game.getInstance().getCurrentPlayer();
        current.setMoney(current.getMoney() + (10 * moneyFactor));
        System.out.println(current.getName() + ": YOU FOUND BURIED TREASURE! YOU GET " + 10 * moneyFactor + ".");
    }
}

