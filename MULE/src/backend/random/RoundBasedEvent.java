package backend.random;

import backend.Game;

/**
 * Created by Blackstone on 12/4/15.
 */
public class RoundBasedEvent {
    int moneyFactor;

    public RoundBasedEvent() {
        int round = Game.getInstance().rounds.get();
        switch (round) {
            case 1: case 2: case 3:
                moneyFactor = 25;
                break;
            case 4: case 5: case 6: case 7:
                moneyFactor = 50;
                break;
            case 8: case 9: case 10: case 11:
                moneyFactor = 75;
                break;
            case 12: default:
                moneyFactor = 100;
                break;
        }
    }
}
