package piatto.map.tileManager.tileBuilder;

import piatto.map.tileManager.tileBuilder.Tile.Tile;

import java.util.HashMap;
import java.util.Map;

public class TileBuilder {
    public static Tile[] tilesLibrary = {
            TileBuilder.createTile(0,  "late_simple_large",  "/tiles/plate_simple_large.png"),
            TileBuilder.createTile(1,  "late_brick",  "/tiles/plate_brick.png"),
            TileBuilder.createTile(3,  "late_wall_left",  "/tiles/plate_wall_left.png"),
            TileBuilder.createTile(2,  "all_left",  "/tiles/wall_left.png", true),
            TileBuilder.createTile(4,  "all_right",  "/tiles/wall_right.png", true),
            TileBuilder.createTile(5,  "all_bottom_left_corner",  "/tiles/wall_bottom_left_corner.png", true),
            TileBuilder.createTile(6,  "all_bottom",  "/tiles/wall_bottom.png", true),
            TileBuilder.createTile(7,  "all_bottom_right_corner",  "/tiles/wall_bottom_right_corner.png", true),
            TileBuilder.createTile(8,  "all_top_right_corner",  "/tiles/wall_top_right_corner.png", true),
            TileBuilder.createTile(9,  "all_top_left_corner",  "/tiles/wall_top_left_corner.png", true),
            TileBuilder.createTile(10, "wall_top",   "/tiles/wall_top.png", true),
            TileBuilder.createTile(11, "wall_top_topper",   "/tiles/wall_top_topper.png", true),
            TileBuilder.createTile(40, "bush2",   "/tiles/bush2.png", true),
            TileBuilder.createTile(41, "grass",   "/tiles/grass.png"),
            TileBuilder.createTile(42, "plate_red",   "/tiles/plate_red.png"),
            TileBuilder.createTile(43, "grass_wall_left",   "/tiles/grass_wall_left.png"),
            TileBuilder.createTile(44, "dirt",   "/tiles/dirt.png"),
            TileBuilder.createTile(45, "water",   "/tiles/water.png", true),
            TileBuilder.createTile(46, "fence",   "/tiles/fence.png", true),
            TileBuilder.createTile(47, "grass",   "/tiles/grass-flowers.png"),
            TileBuilder.createTile(48, "grass",   "/tiles/grass-rock.png", true),
            TileBuilder.createTile(-1, "void",   "/tiles/void.png"),
    };

    private static Map<Long, Tile> tileMap = new HashMap<>();

    public TileBuilder(){
        this.InitializeTileLibrary();
    }

    public static Tile findTileByCode(int code) {
        return tileMap.get((long) code);
    }

    private void InitializeTileLibrary(){
        for (Tile tile: tilesLibrary){
            tile.Initialize();
            tileMap.put(tile.Code, tile);
        }
    }

    private static Tile createTile(long code, String name, String src) {
        return new Tile(code, name, src, false);
    }

    private static Tile createTile(long code, String name, String src, boolean enableCollision) {
        return new Tile(code, name, src, enableCollision);
    }
}
