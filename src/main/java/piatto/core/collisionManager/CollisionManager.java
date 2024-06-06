package piatto.core.collisionManager;

import piatto.core.gamePanel.GamePanel;
import piatto.entities.entityBase.EntityBase;

public class CollisionManager {
    GamePanel gamePanel;

    public CollisionManager(GamePanel gp) {
        this.gamePanel = gp;
    }

    public void checkTile(EntityBase entity) {

        int entityLeftWorldX = entity.worldX + entity.collisionArea.x;
        int entityRightWorldX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
        int entityTopWorldY = entity.worldY + entity.collisionArea.y;
        int entityBottomWorldY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        boolean tileNum1Collision = false, tileNum2Collision = false;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                int tile1 = gamePanel.tileManager.mapTile[entityLeftCol][entityTopRow];
                int tile2 = gamePanel.tileManager.mapTile[entityRightCol][entityTopRow];

                break;
            case "down":
                entityBottomRow = (entityTopWorldY + entity.speed) / gamePanel.tileSize;
                break;
            case "left":
                break;
            case "right":
                break;
        }

        tileNum1Collision = gamePanel.tileManager.mapTile

        entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
        tileNum1 = gamePanel.tileManager.mapTile[entityLeftCol][entityTopRow];
        tileNum2 = gamePanel.tileManager.mapTile[entityRightCol][entityTopRow];

        if (tileNum1Collision || tileNum2Collision) {
            entity.collisionOn = true;
        }


    }
}
