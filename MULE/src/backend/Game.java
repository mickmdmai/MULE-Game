package backend;

import backend.components.Player;
import backend.components.Race;
import backend.map.tiles.Tile;
import backend.random.RandomEvent;
import backend.random.RandomEventFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Collections;

/**
 * Created by Edward on 9/16/2015.
 */
public class Game {

    public static boolean debugMode = false;

    private static Game instance = new Game();
    public ObjectProperty<Player> curr = new SimpleObjectProperty<>();
    private int nextIdx = 0;
    private int turnDuration = 10000;
    private static final int barGranularity = 50;
    private long startTime = 0;
    private Timeline timer = new Timeline(new KeyFrame(Duration.millis(barGranularity),
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    decrementBar();
                }
            }));
    public DoubleProperty bar = new SimpleDoubleProperty(1);
    public BooleanProperty freeLand = new SimpleBooleanProperty(true);
    public IntegerProperty rounds = new SimpleIntegerProperty(0);
    public IntegerProperty skipCounter = new SimpleIntegerProperty(0);
    private ObjectProperty<GameState> state = new SimpleObjectProperty<>();
    private StringProperty actionInfo = new SimpleStringProperty("");

    private Game() {
        state.addListener(observable -> {
            actionInfo.set(state.get().toString());
            if (state.get().equals(GameState.LAND_SELECTION)
                    || state.get().equals(GameState.MOVE_MULE)) {
                timer.play();
                System.out.println("timer resumed");
            } else {
                timer.pause();
                System.out.println("timer paused");
            }
        });
    }

    public void startTimer() {
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public void nextTurn() {
        new Save();
        if (nextIdx <= 0) {
            calcProduction();
            System.out.println("production calculated");
            Collections.sort(Data.getPlayers());
            for (int i = 0; i < Data.getPlayers().size(); i++) {
                Player curr = Data.getPlayers().get(i);
                curr.idxProperty().set(i);
            }
            System.out.println("players sorted");
            if (rounds.get() > 0 && !freeLand.get()) {
                randomEvent();
                roundEvent();
            }
            rounds.set(rounds.get() + 1);
        }
        curr.set(Data.getPlayers().get(nextIdx));
        nextIdx = (nextIdx + 1) % Data.getPlayers().size();
        if (!freeLand.get()) {
            newTime();
        }
        bar.set(1);
        startTime = System.currentTimeMillis();
    }

    public void startGame() {
        nextTurn();
        startTimer();
        bar.addListener(observable -> {
            if (bar.get() <= (double) barGranularity / 1000) {
                skipped();
                nextTurn();
            }
        });
        rounds.addListener(observable -> {
            if (rounds.get() > 2 && freeLand.get()) {
                freeLand.set(false);
                rounds.set(1);
            }
        });
        skipCounter.addListener(observable -> {
            if (skipCounter.get() >= Data.getNumPlayers() * 2) {
                View.getInstance().goTo("auction");
                skipCounter.set(0);
            }
        });
    }

    private void decrementBar() {
        double next = bar.get() - 1.0 / (((double) turnDuration) / ((double) barGranularity));
        if (next < 0) {
            bar.set(1);
        } else {
            bar.set(next);
        }
    }

    public static Game getInstance() {
        return instance;
    }

    public void skipped() {
        skipCounter.set(skipCounter.get() + 1);
    }

    public void notSkipped() {
        skipCounter.set(0);
    }

    /**
     * @return time remaining in turn in seconds
     */
    public int timeRemaining() {
        double result =  (double) (startTime - System.currentTimeMillis())
                         / 1000;
        return (int) result;
    }

    public void newTime() { // call in next turn, set turn duration at end.
        int reqFood = 0;
        switch (rounds.get()) {
            case 1: case 2: case 3: case 4:
                reqFood = 3;
                break;
            case 5: case 6: case 7: case 8:
                reqFood = 4;
                break;
            case 9: case 10: case 11: case 12:
                reqFood = 5;
                break;
            default:
                System.out.println("Went too many rounds");
                reqFood = 3;
        }

        if (curr.get().getFood() == 0) {
            turnDuration = 5000 + (debugMode ? 1500 : 0);
        } else {
            curr.get().setFood(curr.get().getFood() - reqFood);
            if (curr.get().getFood() < 0) {
                curr.get().setFood(0);
                turnDuration = 30000;
            } else {
                turnDuration = 60000;
            }
        }
    }

    public void setState(GameState state) {
        this.state.set(state);
    }

    public GameState getState() {
        return state.get();
    }

    public ObjectProperty<GameState> gameStateProperty() {
        return state;
    }

    /**
     * handles current player visiting the pub
     */
    public void visitPub() {
        int currMoney = curr.get().getMoney();
        int turn = rounds.get();
        int roundBonus;

        if (turn < 4) {
            roundBonus = 50;
        } else if (turn >=  4 && turn <= 7) {
            roundBonus = 100;
        } else if (turn >= 8 && turn <= 11) {
            roundBonus = 150;
        } else {
            roundBonus = 200;
        }

        int time = timeRemaining();
        int timeBonus;

        if (time >= 37) {
            timeBonus = 200;
        } else if (time >=25 && time <= 36) {
            timeBonus = 150;
        } else if (time >= 12 && time <= 25) {
            timeBonus = 100;
        } else {
            timeBonus = 50;
        }

        int newMoney = (int) (roundBonus + (timeBonus*Math.random()));

        if (newMoney > 250) {
            newMoney = 250;
        }
        curr.get().setMoney(newMoney + currMoney);
        View.getInstance().goTo("map");
        Game.getInstance().setState(GameState.LAND_SELECTION);
        nextTurn();

    }

    public void pauseTimer() {
        timer.pause();
    }

    public void resumeTimer() {
        timer.play();
    }

    public static void debugMode() {
        debugMode = true;
        Data.numPlayersProperty().set(3);
        Data.mapTypeProperty().set("Standard");
        Data.difficultyProperty().set(1);
        Data.getPlayers().addAll(new Player("Edward", Race.BONZOID, Color.RED),
                new Player("Laurel", Race.GOLLUMER, Color.BLUE),
                new Player("Chris", Race.PACKER, Color.YELLOW));
        for (int i = 0; i < Data.getPlayers().size(); i++) {
            Player curr = Data.getPlayers().get(i);
            curr.idxProperty().set(i);
        }
        Game.getInstance().startGame();
        Game.getInstance().setState(GameState.LAND_SELECTION);
        View.getInstance().setShowStatus(true);
        View.getInstance().goTo("map");
    }

    public void setActionInfo(String info) {
        actionInfo.set(info);
    }

    public StringProperty actionInfoProperty() {
        return actionInfo;
    }

    private void calcProduction() {
        for (Player p : Data.getPlayers()) {
            if (p.getMules().size() > p.getEnergy()) {
                break;
            }
            for (Tile t : p.getOwnedTiles()) {
                t.calculateProduction();
            }
        }
    }

    private void randomEvent() {

        for (int i = 0; i < Data.getPlayers().size(); i++) {
            if (Math.random() <= .27) {
                RandomEventFactory.getInstance().getNextRandomEvent().perform(i);
            }
        }
    }

    private void roundEvent() {
        if(Math.random() <= .3) {
            RandomEvent event = RandomEventFactory.getInstance().getNextRoundEvent();
            for (int i = 0; i < Data.getPlayers().size(); i++) {
                event.perform(i);
            }
        }

    }

    public int getIdx() {
        return nextIdx - 1;
    }

    public void setIdx(int idx) {
        if (idx < 0) {
            nextIdx = Data.getNumPlayers() - 1;
        } else {
            nextIdx = idx;
        }
    }

    public int getTurnDuration() {
        return turnDuration;
    }

    public Player getCurrentPlayer() {
        return curr.get();
    }

}
