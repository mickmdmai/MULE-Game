package backend.components;

import backend.Data;
import backend.components.mules.Mule;
import backend.components.resources.*;
import backend.map.tiles.Tile;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Player playing the game
 * @version 1.00
 */
public class Player implements Comparable<Player>, Visitable {

    private StringProperty name = new SimpleStringProperty();
    private ObjectProperty<Race> race = new SimpleObjectProperty<>();
    private ObjectProperty<Color> color = new SimpleObjectProperty<>();
    private Map<String, IntegerProperty> resources = new HashMap<>();
    private IntegerProperty money = new SimpleIntegerProperty();
    private IntegerProperty idx = new SimpleIntegerProperty();

    private ObservableList<Tile> ownedTiles = FXCollections
            .observableArrayList();

    private IntegerBinding score;
    private StringBinding disp;

    private ObjectProperty<Mule> mule = new SimpleObjectProperty<>();

    /**
     * Creates a Player with the given information
     * @param aName the name of the Player
     * @param aRace the race of the Player
     * @param aColor the color of the Player's sprite
     */
    public Player(String aName, Race aRace, Color aColor) {
        this.name.set(aName);
        this.race.set(aRace);
        this.color.set(aColor);

        initResources();

        if (this.race.get().equals(Race.HUMANOID)) {
            money.set(800);
        } else if (this.race.get().equals(Race.FLAPPER)) {
            money.set(1600);
        } else if (this.race.get().equals(Race.JOETHEBESTTAEVER)) {
            money.set(10000);
        } else {
            money.set(1000);
        }

        if (Data.getDifficulty() == 4) {
            money.set(money.get() / 2);
        }

        mule.set(null);

        score = new IntegerBinding() {
            {
                super.bind(oreProperty(), foodProperty(), energyProperty(),
                        crystiteProperty(), money, ownedTiles);
            }

            @Override
            protected int computeValue() {
                return Ore.getInstance().getValue() * getOre()
                       + Food.getInstance().getValue() * getFood()
                       + Energy.getInstance().getValue() * getEnergy()
                       + Crystite.getInstance().getValue() * getCrystite()
                       + Wood.getInstance().getValue() * getWood()
                       + ownedTiles.size() * 500
                       + money.get();
            }
        };

        disp = new StringBinding() {
            {
                super.bind(oreProperty(), foodProperty(), energyProperty(),
                        crystiteProperty(), money);
            }
            @Override
            protected String computeValue() {
                return this.toString();
            }
        };
    }

    private void initResources() {
        resources.put("ore", new SimpleIntegerProperty(0));
        resources.put("crystite", new SimpleIntegerProperty(0));
        resources.put("wood", new SimpleIntegerProperty(0));
        if (Data.getDifficulty() < 2) {
            resources.put("food", new SimpleIntegerProperty(8));
            resources.put("energy", new SimpleIntegerProperty(4));
        } else if (Data.getDifficulty() == 3){
            resources.put("food", new SimpleIntegerProperty(4));
            resources.put("energy", new SimpleIntegerProperty(2));
        }
    }


    public final StringProperty nameProperty() {
        return name;
    }

    public final String getName() {
        return name.get();
    }

    public final void setName(String aName) {
        this.name.set(aName);
    }

    public final ObjectProperty<Race> raceProperty() {
        return race;
    }

    public final Race getRace() {
        return race.get();
    }

    public final void setRace(Race aRace) {
        this.race.set(aRace);
    }

    public final ObjectProperty<Color> colorProperty() {
        return color;
    }

    public final Color getColor() {
        return color.get();
    }

    public final void setColor(Color aColor) {
        this.color.set(aColor);
    }

    public final IntegerProperty oreProperty() {
        return resources.get("ore");
    }

    public final int getOre() {
        return oreProperty().get();
    }

    public final void setOre(int ore) {
        oreProperty().set(ore);
    }

    public final IntegerProperty foodProperty() {
        return resources.get("food");
    }

    public final int getFood() {
        return foodProperty().get();
    }

    public final void setFood(int food) {
        foodProperty().set(food);
    }

    public final IntegerProperty energyProperty() {
        return resources.get("energy");
    }

    public final int getEnergy() {
        return energyProperty().get();
    }

    public final void setEnergy(int energy) {
        energyProperty().set(energy);
    }

    public final IntegerProperty crystiteProperty() {
        return resources.get("crystite");
    }

    public final int getCrystite() {
        return crystiteProperty().get();
    }

    public final void setCrystite(int crystite) {
        crystiteProperty().set(crystite);
    }

    public final int getWood() {
        return woodProperty().get();
    }

    public final IntegerProperty woodProperty() {
        return resources.get("wood");
    }

    public final void setWood(int wood) {
        woodProperty().set(wood);
    }

    public final IntegerProperty moneyProperty() {
        return money;
    }

    public final int getMoney() {
        return money.get();
    }

    public final void setMoney(int aMoney) {
        this.money.set(aMoney);
    }

    public final IntegerBinding scoreBinding() {
        return score;
    }

    public final int getScore() {
        return score.get();
    }

    public final boolean hasMule() {
        return mule.get() != null;
    }

    public final void removeMule() {
        mule.set(null);
    }
    /**
     * @return an array of the Mules
     */
    public final Mule getMule() {
        return mule.get();
    }

    public final ObjectProperty<Mule> muleProperty() {
        return mule;
    }

    public final void setMule(Mule m) {
        this.mule.set(m);
    }

    /**
     * a compare to of '0' DOES NOT mean equivalence according to the equals
     * method, it just means the scores are equal.
     * @param other the other player
     * @return a comparison
     */
    @Override
    public final int compareTo(Player other) {
        if (this.score.get() < other.getScore()) {
            return -1;
        } else if (this.score.get() > other.getScore()) {
            return 1;
        } else {
            return 0;
        }
    }

    public final void changeResoureQuantity(String item, int delta) {
        if (resources.containsKey(item)) {
            if (delta < 0 && Math.abs(delta) > resources.get(item).get()) {
                delta = -resources.get(item).get();
            }
            resources.get(item).set(resources.get(item).get() + delta);
        }
    }

    public final ObservableList<Tile> getOwnedTiles() {
        return ownedTiles;
    }

    public final ObservableList<Mule> getMules() {
        ObservableList<Mule> result = FXCollections.observableArrayList();
        for (Tile tile : ownedTiles) {
            if (tile.hasMule()) {
                result.add(tile.getMule());
            }
        }
        return result;
    }

    @Override
    public final String toString() {
        return "Name: " + name.get() + "\n" +
               "Race: " + race.get() + "\n" +
               "Money: " + money.get() + "\n" +
               "Food: " + getFood() + "\n" +
               "Energy: " + getEnergy() + "\n" +
               "Ore: " + getOre() + "\n" +
               "Wood" + getWood() + "\n" +
               "Crystite: " + getCrystite() + "\n" +
               "Mule: " + mule.get() + "\n" +
               "Num_Tiles: " + ownedTiles.size() + "\n" +
               "Score: " + score.get();
    }

    public final StringBinding dispProperty() {
        return disp;
    }

    public final IntegerProperty idxProperty() {
        return idx;
    }

    /**
     * Accepts a Visitor to access its properties and perform
     * certain operations
     * @param v the visitor that will access the Player
     */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    /**
     * Adds or subtracts the given amount of money
     * from the player's wallet
     * @param delta
     */
    public void changeMoney(int delta) {
        delta = Math.min(Math.abs(delta), money.get())
                * (delta < 0 ? -1 : 1);
        money.set(money.get() + delta);
    }
}
