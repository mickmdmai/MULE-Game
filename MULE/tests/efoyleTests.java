import backend.Data;
import backend.Game;
import backend.Main;
import backend.View;
import backend.components.Player;
import backend.components.Race;
import backend.components.Store;
import controllers.StoreController;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

public class efoyleTests {
    public static final int TIMEOUT = 200;
    static Thread t;
    static View view;

    public static class NonApp extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            //no op
        }
    }

    @BeforeClass
    public static void initJFX() throws InterruptedException {
        t = new Thread("JFX Thread") {
            public void run() {
                Application.launch(NonApp.class, "Main");
            }
        };
        t.setDaemon(true);
        t.start();
        System.out.println("JFX Thread started");
        Thread.sleep(500);
    }

    @Before
    public void setUp() throws InterruptedException{
        initJFX();
        view = View.getInstance();
    }

    @After
    public void destroyThread() {
        t.interrupt();
    }

    @Test
    public void testInitialize() {
        assertEquals(6, view.getMapTiles().size());
        assertEquals(1, view.getScenes().size());
        assertTrue(view.getScenes().containsKey("gameConfig"));
        assertEquals(1, view.getChildren().size());
    }

    @Test
    public void testGoToSame() {
        //view.goTo("gameConfig");
        assertEquals(1, view.getScenes().size());
        assertTrue(view.getScenes().containsKey("gameConfig"));
        assertEquals(1, view.getChildren().size());
    }

    @Test
    public void testGoToDiff() {
        view.goTo("map");
        assertEquals(2, view.getScenes().size());
        assertTrue(view.getScenes().containsKey("map"));
        assertEquals(1, view.getChildren().size());
    }

    @Test
    public void testGoToMany() {
        assertEquals(1, view.getScenes().size());
        view.goTo("map");
        assertEquals(2, view.getScenes().size());
        view.goTo("town");
        assertEquals(3, view.getScenes().size());
        view.goTo("gameConfig");
        assertEquals(3, view.getScenes().size());
        view.goTo("map");
        assertEquals(3, view.getScenes().size());
    }

    @Test (expected = IOException.class)
    public void testGoToInvalid() {
        view.goTo("invalid");
    }
}