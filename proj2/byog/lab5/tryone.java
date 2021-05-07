package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class tryone {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 594088;
    private static final Random RANDOM = new Random(SEED);

    private static Position p;
    private static TETile[][] world;

    public static class Position{
        int x;
        int y;

        public Position(){
            x=10;
            y=10;
        }

        public Position(int r,int l){
            x=r;
            y=l;
        }
    }

    private static TETile randomTile() {
        Random dice = new Random();
        int tileNum = dice.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.FLOOR;
            case 4: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }

    public int hexRowWidth(int s,int i){
        int effectiveI=i;                  //有多少个
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;  //数学逻辑
        }
        return s + 2 * effectiveI;
    }

    public int hexRowOffset(int s,int i){  // 开始画的位置 -2，-1，0，1，2
        int effectiveN = i;
        if (i >= s) {
            effectiveN = 2 * s - 1 - effectiveN;
        }
        return -effectiveN;
    }

    public void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    public void addHexagon(TETile[][] world, Position p, int s, TETile t) {

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        // hexagons have 2*s rows. this code iterates up from the bottom row,
        // which we call row 0.
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;

            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);

            int rowWidth = hexRowWidth(s, yi);

            addRow(world, rowStartP, rowWidth, t);

        }
    }

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

//        TETile[][] world=new TETile[WIDTH][HEIGHT];
        int s=3;

        tryone w=new tryone();
        w.world=new TETile[WIDTH][HEIGHT];
        w.p=new Position();
        w.addHexagon(w.world,w.p, s, Tileset.FLOWER);

        ter.renderFrame(w.world);
    }
}
