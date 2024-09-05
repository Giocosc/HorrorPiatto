package piatto.map.tileManager;

import piatto.map.tileManager.tileBuilder.Tile.Tile;
import piatto.map.tileManager.tileBuilder.TileBuilder;

import java.util.Arrays;

public class TileManager {
    private TileBuilder tileBuilder = new TileBuilder();

    public Tile[] getTiles(){
        return this.tileBuilder.tilesLibrary;
    }
    public Tile getTileById(int code){
        return this.tileBuilder.findTileByCode(code);
    }
}

