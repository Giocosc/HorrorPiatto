package piatto.core.UIManager;

import piatto.core.gamePanel.GamePanel;
import piatto.object.objets.Ingot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UIManager {
    GamePanel gamePanel;

    static Font defaultFont = new Font("Arial", Font.PLAIN, 40);
    static Font fontArial55 = new Font("Arial", Font.BOLD, 55);

    BufferedImage ingotImage;

    public boolean gameFinished = false;

    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;

    double playingTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UIManager(GamePanel gp) {
        gamePanel = gp;

        Ingot ingot = new Ingot();
        ingotImage = ingot.image;

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;


    }

    public void draw(Graphics2D g2) {

        if (!gameFinished) {
            g2.setFont(defaultFont);
            g2.setColor(Color.WHITE);

            g2.drawImage(ingotImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.drawString(" - " + gamePanel.player.totalIngots +"\\" + gamePanel.ingotToCatch, 70, gamePanel.tileSize + gamePanel.tileSize / 2);

            // TIME
            playingTime += (double) 1/60;
            g2.drawString("Time:" + decimalFormat.format(playingTime), gamePanel.tileSize * 11  , gamePanel.tileSize + gamePanel.tileSize / 2);

            // MESSAGE
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gamePanel.tileSize * 2, gamePanel.tileSize * 5);

                messageCounter++;
                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }

        } else {
            g2.setFont(defaultFont);
            g2.setColor(Color.white);

            String text = "You took all the ingots.";
            int textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            int x = gamePanel.screenWidth / 2 - (textLenght / 2);
            int y = gamePanel.screenHeight / 2 - (gamePanel.tileSize*3);
            g2.drawString(text, x, y);


            g2.setFont(fontArial55);
            g2.setColor(Color.orange);

            text = "You won, congratulations!!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gamePanel.screenWidth / 2 - (textLenght / 2);
            y = gamePanel.screenHeight / 2 + (gamePanel.tileSize*2);
            g2.drawString(text, x, y);


            g2.setFont(defaultFont);
            g2.setColor(Color.white);

            text = "Your time is: " + decimalFormat.format(playingTime) +"!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gamePanel.screenWidth / 2 - (textLenght / 2);
            y = gamePanel.screenHeight / 2 + (gamePanel.tileSize*4);
            g2.drawString(text, x, y);

            gamePanel.stopThread();
        }
    }

}
