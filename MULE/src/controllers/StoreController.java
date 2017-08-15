package controllers;

import backend.Game;
import backend.GameState;
import backend.View;
import backend.audio.AudioPlayer;
import backend.components.*;
import backend.components.mules.*;
import backend.components.resources.*;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Edward on 10/2/2015.
 */
public class StoreController implements Initializable {

    @FXML Slider sellFood;
    @FXML Slider sellEnergy;
    @FXML Slider sellSmithore;
    @FXML Slider sellCrystite;
    @FXML Slider sellWood;

    @FXML Text sellFoodAmt;
    @FXML Text sellEnergyAmt;
    @FXML Text sellOreAmt;
    @FXML Text sellCrystiteAmt;
    @FXML Text sellWoodAmt;

    @FXML Slider buyFood;
    @FXML Slider buyEnergy;
    @FXML Slider buySmithore;
    @FXML Slider buyCrystite;
    @FXML Slider buyWood;

    @FXML Text buyFoodAmt;
    @FXML Text buyEnergyAmt;
    @FXML Text buyOreAmt;
    @FXML Text buyCrystiteAmt;
    @FXML Text buyWoodAmt;

    @FXML RadioButton foodMule;
    @FXML RadioButton oreMule;
    @FXML RadioButton energyMule;
    @FXML RadioButton crystiteMule;
    @FXML RadioButton woodMule;
    @FXML RadioButton goldMule;
    @FXML ToggleGroup muleGroup;

    @FXML Text sellTotText;
    @FXML Text buyTotText;

    @FXML VBox main;

    private IntegerBinding buyTotal;
    private IntegerBinding sellTotal;
    private IntegerProperty maxFood = new SimpleIntegerProperty(0);
    private IntegerProperty maxEnergy = new SimpleIntegerProperty(0);
    private IntegerProperty maxSmithore = new SimpleIntegerProperty(0);
    private IntegerProperty maxCrystite = new SimpleIntegerProperty(0);
    private IntegerProperty maxWood = new SimpleIntegerProperty(0);

    private ObjectProperty<Player> curr;
    private Store store;

    @Override
    public void initialize(URL fxmlLocation, ResourceBundle resources) {
        curr = Game.getInstance().curr;
        store = Store.getInstance();
        bindInterface();
        calcMax();
        bindSell();
        Game.getInstance().curr.addListener(observable -> {
            calcMax();
            bindSell();
        });
    }

    private void calcMax() {
        int money = Game.getInstance().curr.get().getMoney();
        int available = money - buyTotal.get();

        int capFood = available / Food.getInstance().getValue() +
                      (int) buyFood.getValue();
        maxFood.set(Math.min(capFood, store.getAmt("food")));

        int capEnergy = available / Energy.getInstance().getValue() +
                        (int) buyEnergy.getValue();
        maxEnergy
                .set(Math.min(capEnergy, store.getAmt("energy")));

        int capOre = available / Ore.getInstance().getValue() +
                     (int) buySmithore.getValue();
        maxSmithore.set(Math.min(capOre, store.getAmt("ore")));

        int capCrystite = available / Crystite.getInstance().getValue() +
                          (int) buyCrystite.getValue();
        maxCrystite.set(Math.min(capCrystite, store.getAmt("crystite")));

        int capWood = available / Wood.getInstance().getValue() +
                      (int) buyWood.getValue();
        maxWood.set(Math.min(capWood, store.getAmt("wood")));
        goldMule.setDisable(available < GoldMule.getInstance().getValue());
        woodMule.setDisable(available < WoodMule.getInstance().getValue());
        crystiteMule.setDisable(available < CrystiteMule.getInstance().getValue());
        oreMule.setDisable(available < OreMule.getInstance().getValue());
        energyMule.setDisable(available < EnergyMule.getInstance().getValue());
        foodMule.setDisable(available < FoodMule.getInstance().getValue());
    }

    private void bindInterface() {
        buyTotal = new IntegerBinding() {
            {
                super.bind(buyFood.valueProperty(), buyEnergy.valueProperty(),
                        buySmithore.valueProperty(),
                        buyCrystite.valueProperty(),
                        buyWood.valueProperty(),
                        foodMule.selectedProperty(),
                        energyMule.selectedProperty(),
                        oreMule.selectedProperty(),
                        crystiteMule.selectedProperty(),
                        woodMule.selectedProperty(),
                        goldMule.selectedProperty());
            }

            @Override
            protected int computeValue() {
                return (int) (Math.round(buyFood.getValue()) *
                              Food.getInstance().getValue()
                              + Math.round(buyEnergy.getValue()) *
                                Energy.getInstance().getValue()
                              + Math.round(buySmithore.getValue()) *
                                Ore.getInstance().getValue()
                              + Math.round(buyCrystite.getValue()) *
                                Crystite.getInstance().getValue()
                              + Math.round(buyWood.getValue()) *
                                Wood.getInstance().getValue()
                              + (foodMule.isSelected() ? 1 : 0) *
                                FoodMule.getInstance().getValue()
                              + (energyMule.isSelected() ? 1 : 0) *
                                EnergyMule.getInstance().getValue()
                              + (oreMule.isSelected() ? 1 : 0) *
                                OreMule.getInstance().getValue()
                              + (crystiteMule.isSelected() ? 1 : 0) *
                                CrystiteMule.getInstance().getValue()
                              + (woodMule.isSelected() ? 1 : 0) *
                                WoodMule.getInstance().getValue()
                              + (goldMule.isSelected() ? 1 : 0) *
                                GoldMule.getInstance().getValue());
            }
        };
        buyTotal.addListener((observable, oldVal, newVal) -> {
            if (!oldVal.equals(newVal)) {
                calcMax();
            }
        });
        sellTotal = new IntegerBinding() {
            {
                super.bind(sellFood.valueProperty(), sellEnergy.valueProperty(),
                        sellSmithore.valueProperty(),
                        sellCrystite.valueProperty(),
                        sellWood.valueProperty());
            }

            @Override
            protected int computeValue() {
                return (int) (Math.round(sellFood.getValue()) *
                              Food.getInstance().getValue()
                              + Math.round(sellEnergy.getValue()) *
                                Energy.getInstance().getValue()
                              + Math.round(sellSmithore.getValue()) *
                                Ore.getInstance().getValue()
                              + Math.round(sellCrystite.getValue()) *
                                Crystite.getInstance().getValue()
                              + Math.round(sellWood.getValue()) *
                                Wood.getInstance().getValue());
            }
        };
        sellFoodAmt.textProperty().bind(new StringBinding() {
            {
                super.bind(sellFood.valueProperty());
            }

            @Override
            protected String computeValue() {
                Integer rounded = (int) Math.round(sellFood.valueProperty().get
                        ());
                return rounded.toString();
            }
        });
        sellEnergyAmt.textProperty().bind(new StringBinding() {
            {
                super.bind(sellEnergy.valueProperty());
            }

            @Override
            protected String computeValue() {
                Integer rounded = (int) Math.round(sellEnergy.valueProperty()
                        .get());
                return rounded.toString();
            }
        });
        sellOreAmt.textProperty().bind(new StringBinding() {
            {
                super.bind(sellSmithore.valueProperty());
            }

            @Override
            protected String computeValue() {
                Integer rounded =
                        (int) Math.round(sellSmithore.valueProperty().get
                                ());
                return rounded.toString();
            }
        });
        sellCrystiteAmt.textProperty().bind(new StringBinding() {
            {
                super.bind(sellCrystite.valueProperty());
            }

            @Override
            protected String computeValue() {
                Integer rounded =
                        (int) Math.round(sellCrystite.valueProperty().get
                                ());
                return rounded.toString();
            }
        });
        sellWoodAmt.textProperty().bind(new StringBinding() {
            {
                super.bind(sellWood.valueProperty());
            }

            @Override
            protected String computeValue() {
                Integer rounded =
                        (int) Math.round(sellWood.valueProperty().get
                                ());
                return rounded.toString();
            }
        });

        buyFoodAmt.textProperty().bind(new StringBinding() {
            {
                super.bind(buyFood.valueProperty());
            }

            @Override
            protected String computeValue() {
                Integer rounded = (int) Math.round(buyFood.valueProperty().get
                        ());
                return rounded.toString();
            }
        });
        buyEnergyAmt.textProperty().bind(new StringBinding() {
            {
                super.bind(buyEnergy.valueProperty());
            }

            @Override
            protected String computeValue() {
                Integer rounded = (int) Math.round(buyEnergy.valueProperty().get
                        ());
                return rounded.toString();
            }
        });
        buyOreAmt.textProperty().bind(new StringBinding() {
            {
                super.bind(buySmithore.valueProperty());
            }

            @Override
            protected String computeValue() {
                Integer rounded =
                        (int) Math.round(buySmithore.valueProperty().get
                                ());
                return rounded.toString();
            }
        });
        buyCrystiteAmt.textProperty().bind(new StringBinding() {
            {
                super.bind(buyCrystite.valueProperty());
            }

            @Override
            protected String computeValue() {
                Integer rounded =
                        (int) Math.round(buyCrystite.valueProperty().get
                                ());
                return rounded.toString();
            }
        });
        buyWoodAmt.textProperty().bind(new StringBinding() {
            {
                super.bind(buyWood.valueProperty());
            }

            @Override
            protected String computeValue() {
                Integer rounded =
                        (int) Math.round(buyWood.valueProperty().get
                                ());
                return rounded.toString();
            }
        });
        buyTotText.textProperty().bind(buyTotal.asString());
        sellTotText.textProperty().bind(sellTotal.asString());
        buyFood.maxProperty().bind(maxFood);
        buyEnergy.maxProperty().bind(maxEnergy);
        buySmithore.maxProperty().bind(maxSmithore);
        buyCrystite.maxProperty().bind(maxCrystite);
        buyWood.maxProperty().bind(maxWood);
    }

    private void bindSell() {
        ObjectProperty<Player> curr = Game.getInstance().curr;
        sellFood.maxProperty().bind(curr.get().foodProperty());
        sellEnergy.maxProperty().bind(curr.get().energyProperty());
        sellSmithore.maxProperty().bind(curr.get().oreProperty());
        sellCrystite.maxProperty().bind(curr.get().crystiteProperty());
        sellWood.maxProperty().bind(curr.get().woodProperty());
    }

    @FXML
    private void reset() {
        sellFood.setValue(0);
        sellEnergy.setValue(0);
        sellSmithore.setValue(0);
        sellCrystite.setValue(0);
        sellWood.setValue(0);
        buyFood.setValue(0);
        buyEnergy.setValue(0);
        buySmithore.setValue(0);
        buyCrystite.setValue(0);
        buyWood.setValue(0);
        foodMule.setSelected(false);
        energyMule.setSelected(false);
        oreMule.setSelected(false);
        crystiteMule.setSelected(false);
        woodMule.setSelected(false);
        goldMule.setSelected(false);
        foodMule.setDisable(false);
        energyMule.setDisable(false);
        oreMule.setDisable(false);
        crystiteMule.setDisable(false);
        woodMule.setDisable(false);
    }

    @FXML
    public void back() {
        reset();
        View.getInstance().goTo("town");
        Game.getInstance().setState(GameState.TOWN);
    }

    @FXML
    private void confirm() {
        if (buyTotal.get() == 0 && sellTotal.get() == 0) { // not doing anything
            back();
        } else if (buyTotal.get() == 0) { // just selling stuff
            confirmSell();
        } else if (sellTotal.get() == 0) { // just buying stuff
            confirmBuy();
        } else { // buying and selling
            confirmBuy();
            confirmSell();
        }
    }

    private void confirmSell() {
        Map<String, Integer> sale = new HashMap<>();
        sale.put("food", (int) sellFood.valueProperty().get());
        sale.put("energy", (int) sellEnergy.valueProperty().get());
        sale.put("ore", (int) sellSmithore.valueProperty().get());
        sale.put("crystite", (int) sellCrystite.valueProperty().get());
        sale.put("wood", (int) sellWood.valueProperty().get());
        Transaction result = new Sale(sale, sellTotal.get());
        store.accept(result);
        curr.get().accept(result);
    }

    private void confirmBuy() {
        Map<String, Integer> purchase = new HashMap<>();
        purchase.put("food", (int) buyFood.getValue());
        purchase.put("energy", (int) buyEnergy.getValue());
        purchase.put("ore", (int) buySmithore.getValue());
        purchase.put("crystite", (int) buyCrystite.getValue());
        purchase.put("wood", (int) buyWood.getValue());
        if (foodMule.isSelected()) {
            purchase.put("foodMule", 1);
        } else if (energyMule.isSelected()) {
            purchase.put("energyMule", 1);
        } else if (oreMule.isSelected()) {
            purchase.put("oreMule", 1);
        } else if (crystiteMule.isSelected()) {
            purchase.put("crystiteMule", 1);
        } else if (woodMule.isSelected()) {
            purchase.put("woodMule", 1);
        } else if (goldMule.isSelected()) {
            purchase.put("goldMule", 1);
        }
        Transaction result = new Purchase(purchase, buyTotal.get());
        store.accept(result);
        curr.get().accept(result);

        AudioPlayer player = new AudioPlayer("gold.wav", 5f);
        player.play();
        if (muleGroup.getSelectedToggle() != null) {
            placeMule();
        } else {
            back();
        }
    }

    private void placeMule() {
        Game.getInstance().setState(GameState.MOVE_MULE);
        View.getInstance().goTo("map");
        reset();
    }

}