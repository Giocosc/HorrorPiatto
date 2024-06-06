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
        gamePanel.obj[2] = new Ingot();
        gamePanel.obj[2].worldX = random_int(2, gamePanel.maxWorldCol) * gamePanel.tileSize;
        gamePanel.obj[2].worldY = random_int(2, gamePanel.maxWorldRow) * gamePanel.tileSize;
        gamePanel.obj[3] = new Ingot();
        gamePanel.obj[3].worldX = random_int(2, gamePanel.maxWorldCol) * gamePanel.tileSize;
        gamePanel.obj[3].worldY = random_int(2, gamePanel.maxWorldRow) * gamePanel.tileSize;
        gamePanel.obj[4] = new Ingot();
        gamePanel.obj[4].worldX = random_int(2, gamePanel.maxWorldCol) * gamePanel.tileSize;
        gamePanel.obj[4].worldY = random_int(2, gamePanel.maxWorldRow) * gamePanel.tileSize;
    }
}
