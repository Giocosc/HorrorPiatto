package piatto.core.collisionManager;

import piatto.core.gamePanel.GamePanel;
import piatto.entities.EntityBase;

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

        int tileNum1 = -1, tileNum2 = -1;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTile[entityTopRow][entityRightCol];

                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityBottomRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTile[entityBottomRow][entityRightCol];

                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityTopRow][entityLeftCol];
                tileNum2 = gamePanel.tileManager.mapTile[entityBottomRow][entityLeftCol];
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTile[entityTopRow][entityRightCol];
                tileNum2 = gamePanel.tileManager.mapTile[entityBottomRow][entityRightCol];
                break;
        }

        if (tileNum1 != -1 && tileNum2 != -1) {
            boolean tileNum1Collision = gamePanel.tileManager.GetTail(tileNum1).collision;
            boolean tileNum2Collision = gamePanel.tileManager.GetTail(tileNum2).collision;
            if (tileNum1Collision || tileNum2Collision) {
                entity.collisionOn = true;
            }
        }


    }

    public int checkObject(EntityBase entity, boolean player) {
        int index = -1;

        for (int i = 0; i < gamePanel.obj.length; i++) {
            if (gamePanel.obj[i] != null && gamePanel.obj[i].collision) {
                // Get entity collision area
                entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
                entity.collisionArea.y = entity.worldY + entity.collisionArea.y;

                // Get obj collision area
                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].worldX + gamePanel.obj[i].solidArea.x;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].worldY + gamePanel.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.collisionArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.collisionArea.y += entity.speed;
                        break;
                    case "left":
                        entity.collisionArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.collisionArea.x += entity.speed;
                        break;
                }

                if (entity.collisionArea.intersects(gamePanel.obj[i].solidArea)) {
                    entity.collisionOn = true;
                    if(player){
                        index = i;
                    }
                }

                entity.collisionArea.x = entity.solidAreaDefaultX;
                entity.collisionArea.y = entity.solidAreaDefaultY;
                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].solidAreaDefaultX;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }

}
