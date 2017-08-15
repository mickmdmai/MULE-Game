package controllers;

import backend.*;
import backend.components.Player;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Created by Edward on 10/16/2015.
 */
public class StatusController implements Initializable {

    @FXML private BorderPane border;

    @FXML private ProgressBar remaining;
    @FXML private Label currPlayer;
    @FXML private Label money;
    @FXML private Label action;
    @FXML private Label food;
    @FXML private Label energy;
    @FXML private Label ore;
    @FXML private Label crystite;
    @FXML private Label wood;

    @FXML private Rectangle color;

    @FXML private HBox allInfo;
    @FXML private ListView<Player> list;
    @FXML private Text rounds;
    @FXML private Text skip;

    @FXML private Button pause;
    @FXML private Button save;

    @FXML private ListView<ChatMessage> chatHistory;
    @FXML private TextArea chatText;
    @FXML private Button send;

    public void initialize(URL fxmlLocation, ResourceBundle resources) {

        if (Game.debugMode) {
            allInfo.setVisible(true);
            list.setItems(Data.getPlayers());
            rounds.textProperty().bind(Game.getInstance().rounds.asString());
            skip.textProperty().bind(Game.getInstance().skipCounter.asString());
        } else {
            border.setBottom(null);
        }
        send.disableProperty().bind(chatText.textProperty().isEmpty());
        chatText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    send();
                    event.consume();
                }
            }
        });
        chatHistory.setItems(Chat.getInstance().getChat());
        chatHistory.setCellFactory(new Callback<ListView<ChatMessage>,
                ListCell<ChatMessage>>() {
            @Override
            public ListCell<ChatMessage> call(ListView<ChatMessage>
                    stringListView) {
                ListCell<ChatMessage> cell = new ListCell<ChatMessage>() {
                    protected void updateItem(ChatMessage item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            Text text = new Text(item.toString());
                            text.wrappingWidthProperty().bind(chatHistory
                                    .widthProperty
                                            ().subtract(20));
                            setGraphic(text);
                        }
                    }
                };
                return cell;
            }
        });
        remaining.progressProperty().bind(Game.getInstance().bar);
        currPlayer.textProperty().bind(Game.getInstance().curr.get()
                .nameProperty());
        money.textProperty().bind(Game.getInstance().curr.get()
                .moneyProperty().asString());
        food.textProperty().bind(Game.getInstance().curr.get().foodProperty()
                .asString());
        energy.textProperty().bind(Game.getInstance().curr.get()
                .energyProperty().asString());
        ore.textProperty().bind(Game.getInstance().curr.get().oreProperty()
                .asString());
        crystite.textProperty().bind(Game.getInstance().curr.get()
                .crystiteProperty().asString());
        wood.textProperty().bind(Game.getInstance().curr.get()
                .woodProperty().asString());
        color.fillProperty().bind(Game.getInstance().curr.get().colorProperty());
        Game.getInstance().curr.addListener(observable -> {
            currPlayer.textProperty().unbind();
            currPlayer.textProperty().bind(Game.getInstance().curr.get()
                    .nameProperty());
            money.textProperty().unbind();
            money.textProperty().bind(Game.getInstance().curr.get()
                    .moneyProperty().asString());
            food.textProperty().unbind();
            food.textProperty().bind(
                    Game.getInstance().curr.get().foodProperty()
                            .asString());
            energy.textProperty().unbind();
            energy.textProperty().bind(Game.getInstance().curr.get()
                    .energyProperty().asString());
            ore.textProperty().unbind();
            ore.textProperty().bind(Game.getInstance().curr.get().oreProperty()
                    .asString());
            crystite.textProperty().unbind();
            crystite.textProperty().bind(Game.getInstance().curr.get()
                    .crystiteProperty().asString());
            wood.textProperty().unbind();
            wood.textProperty().bind(Game.getInstance().curr.get()
                    .woodProperty().asString());
            color.fillProperty().unbind();
            color.fillProperty().bind(
                    Game.getInstance().curr.get().colorProperty());
        });
        action.textProperty().bind(Game.getInstance().actionInfoProperty());
    }

    @FXML public void save() {new Save();}

    @FXML public void pause() {
        if (pause.getText().equals("PAUSE")) {
            Game.getInstance().pauseTimer();
            border.getCenter().disableProperty().set(true);
            pause.setText("RESUME");
        } else {
            Game.getInstance().startTimer();
            border.getCenter().disableProperty().set(false);
            pause.setText("PAUSE");
        }
    }

    @FXML public void send() {
        ChatMessage message = new ChatMessage(chatText.getText(),
                LocalDateTime.now(), Data.getPlayers().get(0).getName());
        Chat.getInstance().postChatStream(message);
        Chat.getInstance().addMessage(message);
        chatText.clear();
    }
}
