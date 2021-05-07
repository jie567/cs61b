package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    public static TETile randomTile() {
        Random fill = new Random();
        int tileNum = fill.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.MOUNTAIN;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.LOCKED_DOOR;
            case 4: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }

    public static int hexRowWidth(int s,int i){ // s代表大小，i是第几行
        int effectiveI=i;                  //有多少个
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;  //数学逻辑
        }
        return s + 2 * effectiveI; // s=3 return 3
    }

    public static int hexRowOffset(int s,int i){  // s=3 开始画的位置 -2，-1，0，1，2
        int effectiveN = i;
        if (i >= s) {
            effectiveN = 2 * s - 1 - effectiveN;
        }
        return -effectiveN;
    }

    public static void addRow(TETile[][] world,int s,int i,int xPosition,int yPosition, TETile t){
        int number=hexRowWidth(s,i);            // s=4 i=0 hexRowWidth return 4
        int start=hexRowOffset(s,i)+xPosition;        // s=4 i=0 hexRowOffset return -4 + xPosition =
        for(int j=0;j<number;j++,start++){
            if(start<0){
                world[start][i+yPosition]=Tileset.NOTHING;   // s-1 加回来 y是列数
            }
            world[start][i+yPosition]=t;
        }
    }
    public static void addHexagon(TETile[][] world, int s,int xPosition,int yPosition, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        for(int i=0;i<2*s;i++){
            addRow(world, s, i,xPosition, yPosition, t);
        }
    }

    public static void addTesselationHexagon(TETile[][] world, int s,int xPosition,int yPosition, TETile t){
        addHexagon(world,s,xPosition,yPosition,HexWorld.randomTile());
        for(int i=1;i<8;i++){
            if(i%2!=0){
                addHexagon(world,s,xPosition-7,yPosition+4*i,HexWorld.randomTile());
                addHexagon(world,s,xPosition+7,yPosition+4*i,HexWorld.randomTile());
            }
            else{
                addHexagon(world,s,xPosition,yPosition+4*i,HexWorld.randomTile());
                addHexagon(world,s,xPosition-14,yPosition+4*i,HexWorld.randomTile());
                addHexagon(world,s,xPosition+14,yPosition+4*i,HexWorld.randomTile());
            }
        }
        addHexagon(world,s,xPosition,yPosition+32,HexWorld.randomTile());
    }

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(50, 50);
        TETile[][] world = new TETile[50][50];

        for (int x = 0; x < 50; x += 1) {
            for (int y = 0; y < 50; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

//        HexWorld.addHexagon(world,4,21,2,HexWorld.randomTile());
        HexWorld.addTesselationHexagon(world,4,21,2,HexWorld.randomTile());
        ter.renderFrame(world);
    }
}
