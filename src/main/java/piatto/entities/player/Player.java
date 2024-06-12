package piatto.entities.player;

import piatto.entities.EntityBase;
import piatto.core.gamePanel.GamePanel;
import piatto.core.keyHandler.KeyHandler;
import piatto.object.objets.Chest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends EntityBase {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public int totalIngots = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = (gamePanel.screenWidth / 2) - (gamePanel.tileSize / 2);
        screenY = (gamePanel.screenHeight / 2) - (gamePanel.tileSize / 2);

        collisionArea = new Rectangle();
        collisionArea.x = 8;
        collisionArea.y = 16;
        collisionArea.width = 24;
        collisionArea.height = 24;
        solidAreaDefaultX = collisionArea.x;
        solidAreaDefaultY = collisionArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    /*
    Set Playes default value
     */
    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 21;
        worldY = gamePanel.tileSize * 25;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            go_down_1 = ImageIO.read(getClass().getResourceAsStream("/player/go_down_1.png"));
            go_left_1 = ImageIO.read(getClass().getResourceAsStream("/player/go_left_1.png"));
            go_down_3 = ImageIO.read(getClass().getResourceAsStream("/player/go_down_3.png"));
            go_down_2 = ImageIO.read(getClass().getResourceAsStream("/player/go_down_2.png"));
            go_left_2 = ImageIO.read(getClass().getResourceAsStream("/player/go_left_2.png"));
            go_left_3 = ImageIO.read(getClass().getResourceAsStream("/player/go_left_3.png"));
            go_right_1 = ImageIO.read(getClass().getResourceAsStream("/player/go_right_1.png"));
            go_right_2 = ImageIO.read(getClass().getResourceAsStream("/player/go_right_2.png"));
            go_right_3 = ImageIO.read(getClass().getResourceAsStream("/player/go_right_3.png"));
            go_up_1 = ImageIO.read(getClass().getResourceAsStream("/player/go_up_1.png"));
            go_up_2 = ImageIO.read(getClass().getResourceAsStream("/player/go_up_2.png"));
            go_up_3 = ImageIO.read(getClass().getResourceAsStream("/player/go_up_3.png"));
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = go_up_1;
                } else if (spriteNumber == 2) {
                    image = go_up_2;
                } else {
                    image = go_up_3;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = go_down_1;
                } else if (spriteNumber == 2) {
                    image = go_down_2;
                } else {
                    image = go_down_3;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = go_left_1;
                } else if (spriteNumber == 2) {
                    image = go_left_2;
                } else {
                    image = go_left_3;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = go_right_1;
                } else if (spriteNumber == 2) {
                    image = go_right_2;
                } else {
                    image = go_right_3;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void update() {

        if (keyHandler.anyPressedKey()) {
            if (keyHandler.upPressed) {
                direction = "up";
            }
            if (keyHandler.downPressed) {
                direction = "down";
            }
            if (keyHandler.rightPressed) {
                direction = "right";
            }
            if (keyHandler.leftPressed) {
                direction = "left";
            }

            // Check tile collision
            collisionOn = false;
            gamePanel.collisionManager.checkTile(this);

            // Check Obj collision
            int objIndex = gamePanel.collisionManager.checkObject(this, true);
            if (objIndex > -1) {
                pickUpObject(objIndex);
            }

            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {

                if (spriteNumber == 3) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                } else {
                    spriteNumber = 3;
                    gamePanel.playSoundEffect(2);
                }
                spriteCounter = 0;
            }
        }

    }

    public void pickUpObject(int objIndex) {
        String objectName = gamePanel.obj[objIndex].name;
        switch (objectName) {
            case "Chest":
                ((Chest) (gamePanel.obj[objIndex])).toggleChest();
                break;
            case "Ingot":
                gamePanel.playSoundEffect(1);
                gamePanel.obj[objIndex] = null;
                totalIngots += 1;

                if (totalIngots == gamePanel.ingotToCatch) {
                    gamePanel.stopMusic();
                    gamePanel.uiManager.gameFinished = true;
                    gamePanel.playSoundEffect(3);
                }

                break;
        }

    }
}


