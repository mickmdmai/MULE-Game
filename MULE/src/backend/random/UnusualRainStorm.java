package backend.random;

import backend.Data;
import backend.Game;
import backend.components.Player;
import backend.components.mules.FoodMule;
import backend.components.mules.Mule;
import backend.map.tiles.Tile;
import javafx.collections.ObservableList;

/**
 * Created by Blackstone on 12/4/15.
 */
public class UnusualRainStorm extends RoundBasedEvent implements RandomEvent {

    @Override
    public void perform(int player) {
        Player current = Data.getPlayers().get(player);
        ObservableList<Mule> mules = current.getMules();
        int numFoodMules = 0;
        for(int i = 0; i < mules.size(); i++) {
            if(mules.get(i).getType().equals("foodMule") ) {
                numFoodMules++;
            }
        }

        current.setFood(current.getFood() + (numFoodMules * Game.getInstance().rounds.get()));

        System.out.println(current.getName().toString() + ": THERE WAS AN UNUSUAL RAIN STORM AND CROPS HAVE FLOURISHED." +
                "YOU GAINED " + Game.getInstance().rounds.get() + "FOOD PER LAND WITH FOOD MULE. A TOTAL OF " +
                numFoodMules * Game.getInstance().rounds.get() + "FOOD." );
    }
}
