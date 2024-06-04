package piatto.entities.player;

import piatto.entities.entityBase.EntityBase;
import piatto.gamePanel.GamePanel;
import piatto.keyHandler.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends EntityBase {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = (gamePanel.screenWidth / 2 ) - (gamePanel.tileSize/ 2);
        screenY = (gamePanel.screenHeight / 2)- (gamePanel.tileSize/ 2) ;

        setDefaultValues();
        getPlayerImage();
    }

    /*
    Set Playes default value
     */
    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 12;
        worldY = gamePanel.tileSize * 9;
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
                worldY -= speed;
            }
            if (keyHandler.downPressed) {
                direction = "down";
                worldY += speed;
            }
            if (keyHandler.rightPressed) {
                direction = "right";
                worldX += speed;
            }
            if (keyHandler.leftPressed) {
                direction = "left";
                worldX -= speed;
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNumber == 3) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                } else {
                    spriteNumber = 3;
                }
                spriteCounter = 0;
            }
        }

    }
}


