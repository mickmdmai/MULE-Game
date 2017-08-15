import backend.Game;
import backend.components.Player;
import backend.components.Race;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Blackstone on 11/11/15.
 */
public class blackstoneTests {
    public static final int TIMEOUT = 200;
    Game game;
    Player player;

    @Before
    public void setUp() throws Exception {
        game = Game.getInstance();
        player = new Player("Test", Race.BONZOID, Color.GREEN);
        game.curr = new SimpleObjectProperty<>(player);
    }


    // Player has enough food for each round requirement.
    @Test(timeout = TIMEOUT)

    public void testNewTimeZero() throws Exception {
        game.rounds = new SimpleIntegerProperty(0);
        game.curr.get().setFood(10);
        game.newTime();
        assertEquals(60000, game.getTurnDuration());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeOne() throws Exception {
        game.rounds = new SimpleIntegerProperty(1);
        game.curr.get().setFood(10);
        game.newTime();
        assertEquals(60000, game.getTurnDuration());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeFour() throws Exception {
        game.rounds = new SimpleIntegerProperty(4);
        game.curr.get().setFood(10);
        game.newTime();
        assertEquals(60000, game.getTurnDuration());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeFive() throws Exception {
        game.rounds = new SimpleIntegerProperty(5);
        game.curr.get().setFood(10);
        game.newTime();
        assertEquals(60000, game.getTurnDuration());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeEight() throws Exception {
        game.rounds = new SimpleIntegerProperty(8);
        game.curr.get().setFood(10);
        game.newTime();
        assertEquals(60000, game.getTurnDuration());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeNine() throws Exception {
        game.rounds = new SimpleIntegerProperty(9);
        game.curr.get().setFood(10);
        game.newTime();
        assertEquals(60000, game.getTurnDuration());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeTwelve() throws Exception {
        game.rounds = new SimpleIntegerProperty(12);
        game.curr.get().setFood(10);
        game.newTime();
        assertEquals(60000, game.getTurnDuration());
    }


    //Player doesn't have enough food, but has more than 0, for each round requirement.
    @Test(timeout = TIMEOUT)
    public void testNewTimeZeroNo() throws Exception { //Out of bounds, tests default.
        game.rounds = new SimpleIntegerProperty(0);
        game.curr.get().setFood(2);
        game.newTime();
        assertEquals(30000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeOneNo() throws Exception {
        game.rounds = new SimpleIntegerProperty(1);
        game.curr.get().setFood(2);
        game.newTime();
        assertEquals(30000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeFourNo() throws Exception {
        game.rounds = new SimpleIntegerProperty(4);
        game.curr.get().setFood(2);
        game.newTime();
        assertEquals(30000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeFiveNo() throws Exception {
        game.rounds = new SimpleIntegerProperty(5);
        game.curr.get().setFood(2);
        game.newTime();
        assertEquals(30000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeEightNo() throws Exception {
        game.rounds = new SimpleIntegerProperty(8);
        game.curr.get().setFood(2);
        game.newTime();
        assertEquals(30000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeNineNo() throws Exception {
        game.rounds = new SimpleIntegerProperty(9);
        game.curr.get().setFood(2);
        game.newTime();
        assertEquals(30000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeTwelveNo() throws Exception {
        game.rounds = new SimpleIntegerProperty(12);
        game.curr.get().setFood(2);
        game.newTime();
        assertEquals(30000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    //Test for each round case, when player has 0 food.
    @Test(timeout = TIMEOUT)
    public void testNewTimeZeroNone() throws Exception { //Out of bounds, tests default.
        game.rounds = new SimpleIntegerProperty(0);
        game.curr.get().setFood(0);
        game.debugMode = false;
        game.newTime();
        assertEquals(5000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeOneNone() throws Exception {
        game.rounds = new SimpleIntegerProperty(1);
        game.curr.get().setFood(0);
        game.debugMode = false;
        game.newTime();
        assertEquals(5000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeFourNone() throws Exception {
        game.rounds = new SimpleIntegerProperty(4);
        game.curr.get().setFood(0);
        game.debugMode = false;
        game.newTime();
        assertEquals(5000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeFiveNone() throws Exception {
        game.rounds = new SimpleIntegerProperty(5);
        game.curr.get().setFood(0);
        game.debugMode = false;
        game.newTime();
        assertEquals(5000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeEightNone() throws Exception {
        game.rounds = new SimpleIntegerProperty(8);
        game.curr.get().setFood(0);
        game.debugMode = false;
        game.newTime();
        assertEquals(5000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeNineNone() throws Exception {
        game.rounds = new SimpleIntegerProperty(9);
        game.curr.get().setFood(0);
        game.debugMode = false;
        game.newTime();
        assertEquals(5000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeTwelveNone() throws Exception {
        game.rounds = new SimpleIntegerProperty(12);
        game.curr.get().setFood(0);
        game.debugMode = false;
        game.newTime();
        assertEquals(5000, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    // Test for each round case when player has 0 food and we are in debug mode.
    @Test(timeout = TIMEOUT)
    public void testNewTimeZeroNoneD() throws Exception { //Out of bounds, tests default.
        game.rounds = new SimpleIntegerProperty(0);
        game.curr.get().setFood(0);
        game.debugMode = true;
        game.newTime();
        assertEquals(6500, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeOneNoneD() throws Exception {
        game.rounds = new SimpleIntegerProperty(1);
        game.curr.get().setFood(0);
        game.debugMode = true;
        game.newTime();
        assertEquals(6500, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeFourNoneD() throws Exception {
        game.rounds = new SimpleIntegerProperty(4);
        game.curr.get().setFood(0);
        game.debugMode = true;
        game.newTime();
        assertEquals(6500, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeFiveNoneD() throws Exception {
        game.rounds = new SimpleIntegerProperty(5);
        game.curr.get().setFood(0);
        game.debugMode = true;
        game.newTime();
        assertEquals(6500, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeEightNoneD() throws Exception {
        game.rounds = new SimpleIntegerProperty(8);
        game.curr.get().setFood(0);
        game.debugMode = true;
        game.newTime();
        assertEquals(6500, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeNineNoneD() throws Exception {
        game.rounds = new SimpleIntegerProperty(9);
        game.curr.get().setFood(0);
        game.debugMode = true;
        game.newTime();
        assertEquals(6500, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }

    @Test(timeout = TIMEOUT)
    public void testNewTimeTwelveNoneD() throws Exception {
        game.rounds = new SimpleIntegerProperty(12);
        game.curr.get().setFood(0);
        game.debugMode = true;
        game.newTime();
        assertEquals(6500, game.getTurnDuration());
        assertEquals(0, game.curr.get().getFood());
    }
}