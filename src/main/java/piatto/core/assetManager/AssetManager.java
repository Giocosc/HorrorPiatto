package piatto.core.assetManager;

import piatto.core.gamePanel.GamePanel;
import piatto.object.objets.Chest;
import piatto.object.objets.Ingot;

import static piatto.common.Random.random_int;

public class AssetManager {
    GamePanel gamePanel;

    public AssetManager(GamePanel gp) {
        gamePanel = gp;
    }

    public void setObject() {
        gamePanel.obj[0] = new Chest();
        gamePanel.obj[0].worldX = 20 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 13 * gamePanel.tileSize;
        gamePanel.obj[1] = new Chest();
        gamePanel.obj[1].worldX = 21 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 13 * gamePanel.tileSize;

        for(int index = 2; index < 5; index ++){
            int x, y;

            boolean busySlot = true;
            do{
                x = random_int(2, gamePanel.maxWorldCol);
                y = random_int(2, gamePanel.maxWorldRow);

                int tile = gamePanel.tileManager.mapTile[y][x];

                busySlot = (tile != 41);
            }while (busySlot);

            gamePanel.obj[index] = new Ingot();
            gamePanel.obj[index].worldX = x* gamePanel.tileSize;
            gamePanel.obj[index].worldY = y* gamePanel.tileSize;
        }
    }
}
