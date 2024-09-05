package piatto.map;

import piatto.core.gamePanel.GamePanel;
import piatto.map.tileManager.TileManager;
import piatto.map.tileManager.tileBuilder.Tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.util.Collections.min;
import static piatto.common.Random.random_int;

public class MapManager {
    GamePanel gp;

    public int mapTile[][];
    public TileManager tileManager = new TileManager();


    public MapManager(GamePanel gp) {
        this.gp = gp;

        mapTile = new int[gp.maxWorldRow][gp.maxWorldCol];
        //loadMap("/map/map_layout.txt");
        //loadMap("/map/map_larger_layout.txt");
        loadMap("/map/map_with_grass_larger_layout.txt");
        addRandomDecoration();
    }

    private void addRandomDecoration() {

        // Add brush
        for (int index = 0; index < 150; index++) {
            int x = random_int(2, 49);
            int y = random_int(2, 49);
            if (mapTile[y][x] == 41) {
                mapTile[y][x] = 40;
            }
        }

        // Add flower
        for (int index = 0; index < 200; index++) {
            int x = random_int(2, 49);
            int y = random_int(2, 49);
            if (mapTile[y][x] == 41 || mapTile[y][x] == 40) {
                mapTile[y][x] = 47;
            }
        }
        // Rock
        for (int index = 0; index < 50; index++) {
            int x = random_int(2, 49);
            int y = random_int(2, 49);
            if (mapTile[y][x] == 41 || mapTile[y][x] == 40) {
                mapTile[y][x] = 48;
            }
        }
    }

    public void loadMap(String fileName) {
        try {
            InputStream is = getClass().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTile[row][col] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTile[worldRow][worldCol];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            /*
            Drow tile only when i see
             */
            if (
                    worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
            ) {
                g2.drawImage( tileManager.getTileById(tileNum).image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
