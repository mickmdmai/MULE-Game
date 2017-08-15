package backend.random;

/**
 * Created by patrickcaruso on 12/2/15.
 */
public class RandomEventFactory {

    private static RandomEventFactory generator = new RandomEventFactory();
    private static RandomEvent[] events = {new CatBugRandomEvent(), new GypsyRandomEvent(), new MooseRatRandomEvent(), new MuseumRandomEvent(),
        new PackageRandomEvent(), new UGARandomEvent(), new WandererRandomEvent(), new BurriedTreasureEvent(), new PhoneRandomEvent()};

    private static RandomEvent[] roundEvents = {new AlienRaiders(), new GalacticBattle(), new UnusualRainStorm()};

    public static RandomEventFactory getInstance() {
        return generator;
    }

    public RandomEvent getNextRandomEvent() {
        return events[(int) (Math.random() * events.length)];
    }

    public RandomEvent getNextRoundEvent() {
        return roundEvents[(int) (Math.random() * roundEvents.length)];
    }
}
