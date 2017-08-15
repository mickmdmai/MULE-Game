package backend;

import javafx.application.Platform;

import java.util.TimerTask;

/**
 * Created by patrickcaruso on 12/4/15.
 */
public class ChatUpdater extends TimerTask {
    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        Platform.runLater(new Runnable() {
            public void run() {
                Chat.getInstance().reset();
            }
        });
    }
}

