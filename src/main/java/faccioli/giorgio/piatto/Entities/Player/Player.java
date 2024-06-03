package faccioli.giorgio.piatto.Entities.Player;

import faccioli.giorgio.piatto.Entities.Entity.Entity;
import faccioli.giorgio.piatto.GamePanel.GamePanel;
import faccioli.giorgio.piatto.KeyHandler.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    /*
    Set Playes default value
     */
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("player/front_1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("player/down_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("player/right_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("player/left_1.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }

        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public void update() {
        if (keyHandler.upPressed) {
            direction = "up";
            y -= speed;
        }
        if (keyHandler.downPressed) {
            direction = "down";
            y += speed;
        }
        if (keyHandler.rightPressed) {
            direction = "right";
            x += speed;
        }
        if (keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
        }
    }
}

