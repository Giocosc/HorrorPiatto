package piatto.tile.tileManager;

import piatto.core.gamePanel.GamePanel;
import piatto.tile.tiltBase.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;

    public int mapTile[][];


    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[20];

        mapTile = new int[gp.maxWorldRow][gp.maxWorldCol];
        getTileImage();
        //loadMap("/map/map_layout.txt");
        loadMap("/map/map_larger_layout.txt");
    }

    public void getTileImage() {
        try {
            for (int index = 0; index < tile.length; index++) {
                tile[index] = new Tile();
            }

            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/plate_simple_large.png"));
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/plate_brick.png"));
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_left.png"));
            tile[2].collision = true;
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_left_plate.png"));
            tile[3].collision = true;
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_right.png"));
            tile[4].collision = true;
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_bottom_left_corner.png"));
            tile[5].collision = true;
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_bottom.png"));
            tile[6].collision = true;
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_bottom_right_corner.png"));
            tile[7].collision = true;
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_top_right_corner.png"));
            tile[8].collision = true;
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_top_left_corner.png"));
            tile[9].collision = true;
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_top.png"));
            tile[10].collision = true;
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_top_topper.png"));
            tile[11].collision = true;

            tile[tile.length - 2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/plate_simple.png"));
            tile[tile.length - 1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/void.png"));
            tile[tile.length - 1].collision = true;
        } catch (
                Exception e) {
            e.printStackTrace();
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
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
