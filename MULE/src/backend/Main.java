package backend;

import backend.audio.AudioPlayer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Timer;

public class Main extends Application {

    private static boolean debugMode = false;

    public final void start(Stage primaryStage) throws java.lang.Exception {
        AudioPlayer player = new AudioPlayer("music.wav", .1f);
        player.play();

        primaryStage.setTitle("M.U.L.E.");
        primaryStage.setScene(new Scene(View.getInstance()));
        if (debugMode) {
            Game.debugMode();
        }
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        Timer timer = new Timer();
        timer.schedule(new ChatUpdater(), 0, 3000);
    }

    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("debug")) {
            debugMode = true;
        }
        launch(args);
    }
}
