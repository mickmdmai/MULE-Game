package backend.map.config;

import backend.map.tiles.*;

import java.awt.*;
import java.util.ArrayList;

public class MapGenerator {
    private static MapGenerator instance = new MapGenerator();

    private MapGenerator() {
        populate();
    }

    public static MapGenerator getInstance() {
        return instance;
    }

    private final Point[] points = new Point[] {new Point(-1, 0), new Point(1, 0), new Point(0, 1), new Point(0, -1)};
    private Tile[][] tiles = new Tile[5][9];
    private TileFactory factory = new TileFactory();
    /**
     * Randomly generates a map config
     * @return a randomly generated, two-dimensional array of TileType integers
     */
    public Tile[][] generateRandomMap() {
        populate();

        Point lastPoint = new Point(1 + (int) (Math.random()*7 + 1), 0);
        ArrayList<Point> possibleTiles = new ArrayList<Point>();
        Point holder = null;
        Point secHolder = null;
        int tilesAdded = 0;
        do {
            RiverTile river;
            river = new RiverTile();
            tiles[lastPoint.y][lastPoint.x] = river;
            tilesAdded++;
            possibleTiles.clear();
            for (Point surr: getSurroundings(lastPoint)) {
                if (isValid(tiles, surr)) {
                    possibleTiles.add(surr);
                }
            }
            if (possibleTiles.size() == 0) {
                break;
            }

            if (holder != null) {
                secHolder = new Point(holder.x, holder.y);
            }

            //last tile
            holder = new Point(lastPoint.x, lastPoint.y);

            //newer tile
            lastPoint = possibleTiles.get((int)(Math.random() * possibleTiles.size()));

            String image = getRiverImage(secHolder, holder, lastPoint);
            river.setRiverType(image);

            System.out.println(tiles[holder.y][holder.x].getImage());

        } while (lastPoint.getY() < 4 || tilesAdded <= 5 || tilesAdded >= 10);
        tiles[2][4] = new TownTile();
        return tiles;
    }

    private String getRiverImage(Point p, Point last, Point now) {
        if (p == null || last == null || now == null) {
            return "riverns";
        }
        int pX = last.x - p.x;
        int pY = last.y - p.y;

        int lX = last.x - now.x;
        int lY = last.y - now.y;

        String s1 = "";
        String s2 = "";

        if (pX == 0) {
            if (pY > 0) {
                s1 = "n";
            } else {
                s1 = "s";
            }
        } else if (pX > 0) {
            s1 = "w";
        } else {
            s1 = "e";
        }

        if (lX == 0) {
            if (lY > 0) {
                s2 = "n";
            } else {
                s2 = "s";
            }
        } else if (lX > 0) {
            s2 = "w";
        } else {
            s2 = "e";
        }

        return directionsToString(s1, s2);
    }

    private String directionsToString(String s1, String s2) {

        if (s1.equals("n"))
        System.out.println(s1 + " " + s2);

        if ((s1.equals("s") && s2.equals("e")) || (s1.equals("e") && s2.equals("s"))) {
            return "riverse";
        } else if ((s1.equals("s") && s2.equals("n")) || (s1.equals("n") && s2.equals("s"))) {
            return "riverns";
        } else if ((s1.equals("s") && s2.equals("w")) || (s1.equals("w") && s2.equals("s"))) {
            return "riversw";
        } else if ((s1.equals("e") && s2.equals("n")) || (s1.equals("n") && s2.equals("e"))) {
            return "riverne";
        } else if ((s1.equals("e") && s2.equals("w")) || (s1.equals("w") && s2.equals("e"))) {
            return "riverew";
        } else if ((s1.equals("n") && s2.equals("w")) || (s1.equals("w") && s2.equals("n"))) {
            return "rivernw";
        } else if ((s1.equals("w") && s2.equals("w")) || (s1.equals("e") && s2.equals("e"))) {
            return "riverew";
        }
        return "riverns";
    }
    /**
     * Populates tiles with random gaussian representations
     * of tiles
     */
    public void populate() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 5; y++) {
                tiles[y][x] = factory.nextTile();
            }
        }
    }

    /**
     * Gets the surrounding points for a single point
     * @param p the point in question
     * @return adjacent points to the point
     */
    private Point[] getSurroundings(Point p) {
        ArrayList<Point> valids = new ArrayList<Point>();
        for (int i = 0; i <= 3; i++) {
            Point point = new Point((int) (p.getX() + points[i].getX()), (int) (p.getY() + points[i].getY()));
            if (point.getX() >= 0 && point.getY() >= 0 && point.getX() <= 8 && point.getY() <= 4) {
                valids.add(point);
            }
        }
        return valids.toArray(new Point[valids.size()]);
    }

    /**
     * Returns whether a point in question is valid to be added
     * @param mapBuild
     * @param point
     * @return
     */
    private boolean isValid(Tile[][] mapBuild, Point point) {
        Tile tile = mapBuild[point.y][point.x];
        if (tile.isRiver() || tile.isTown()) {
            return false; //point in question is already a river or town
        }

        int invalids = 0;
        Point[] points = getSurroundings(point); //only valid surroundings of a point

        for (Point p: points) {
            if (mapBuild[p.y][p.x].isRiver()) {
                invalids++;
            }
        }
        return invalids < 2;
    }

    /**
     * @return the default map config, as a two-dimensional array of type Tile
     */
    public Tile[][] defaultMap() {
        return new Tile[][] {
                {new PlainTile(), new PlainTile(), new Mountain1Tile(), new PlainTile(), new RiverTile(), new PlainTile(), new Mountain3Tile(), new PlainTile(), new PlainTile()},
                {new PlainTile(), new Mountain1Tile(), new PlainTile(), new PlainTile(), new RiverTile(), new PlainTile(), new PlainTile(), new PlainTile(), new Mountain3Tile()},
                {new Mountain3Tile(), new PlainTile(), new PlainTile(), new PlainTile(), new TownTile(), new PlainTile(), new PlainTile(), new PlainTile(), new Mountain1Tile()},
                {new PlainTile(), new Mountain2Tile(), new PlainTile(), new PlainTile(), new RiverTile(), new PlainTile(), new Mountain2Tile(), new PlainTile(), new PlainTile()},
                {new PlainTile(), new PlainTile(), new Mountain2Tile(), new PlainTile(), new RiverTile(), new PlainTile(), new PlainTile(), new PlainTile(), new Mountain2Tile()}
        };
    }
}