//package backend.map;
//
///**
// * Mayurbhai Maisuria
// * CS 2340
// * NameNotFoundException 39
// * 11/09/2015
// * Class : Tile Constructor: Tile(int type)
// * JUnit test for constructor name public Tile(int type)
// * All the tests check if all the tile types have corresponding the same
// * number of resources
// */
//
//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleIntegerProperty;
//import org.junit.Test;
//import org.junit.Before;
//
//import java.lang.Integer;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertArrayEquals;
//
//public class mickeyTest {
//
//    public IntegerProperty round = new SimpleIntegerProperty(0);
//    public static final int Timeout = 200;
//    Tile mountain1 = new Tile(TileType.MOUNTAIN1);
//    Tile mountain2 = new Tile(TileType.MOUNTAIN2);
//    Tile mountain3 = new Tile(TileType.MOUNTAIN3);
//    Tile river = new Tile(TileType.RIVER);
//    Tile plain = new Tile(TileType.PLAIN);
//    Tile town = new Tile(TileType.TOWN);
//
//    @Test(timeout = Timeout)
//    public void testMountain1() {
//        assertEquals(TileType.MOUNTAIN1, mountain1.getType());
//        assertEquals(1, mountain1.getFood());
//        assertEquals(1, mountain1.getEnergy());
//        assertEquals(2, mountain1.getOre());
//        assertEquals(0, mountain1.getCrystite());
//    }
//
//    @Test(timeout = Timeout)
//    public void testMountain2() {
//        assertEquals(1, mountain2.getFood());
//        assertEquals(1, mountain2.getEnergy());
//        assertEquals(3, mountain2.getOre());
//        assertEquals(0, mountain2.getCrystite());
//        assertEquals(TileType.MOUNTAIN2, mountain2.getType());
//    }
//
//    @Test(timeout = Timeout)
//    public void testMountain3() {
//        assertEquals(1, mountain3.getFood());
//        assertEquals(1, mountain3.getEnergy());
//        assertEquals(4, mountain3.getOre());
//        assertEquals(0, mountain3.getCrystite());
//        assertEquals(TileType.MOUNTAIN3, mountain3.getType());
//    }
//
//    @Test(timeout = Timeout)
//    public void testRiver() {
//        assertEquals(4, river.getFood());
//        assertEquals(2, river.getEnergy());
//        assertEquals(0, river.getOre());
//        assertEquals(0, river.getCrystite());
//        assertEquals(TileType.RIVER, river.getType());
//    }
//
//    @Test(timeout = Timeout)
//    public void testPlain() {
//        assertEquals(2, plain.getFood());
//        assertEquals(3, plain.getEnergy());
//        assertEquals(1, plain.getOre());
//        assertEquals(0, plain.getCrystite());
//        assertEquals(TileType.PLAIN, plain.getType());
//    }
//
//    @Test(timeout = Timeout)
//    public void testTown() {
//        assertEquals(0, town.getFood());
//        assertEquals(0, town.getEnergy());
//        assertEquals(0, town.getOre());
//        assertEquals(0, town.getCrystite());
//        assertEquals(TileType.TOWN, town.getType());
//    }
//}