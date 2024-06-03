package faccioli.giorgio.piatto.GamePanel;

import faccioli.giorgio.piatto.KeyHandle.KeyHandle;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    /*
    16 x 16 standard item size
     */
    final int originalTileSize = 16; //16x16 px
    final int scale = 3;

    /*
    real screen dimension 16 * 3 = 48x48
     */
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    /*
    screen size  768x576
     */
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    /*
    FPS Game thread master
     */
    Thread gameThread;
    int FPS = 60;

    /*
    KeyBoard Event Chatcher
     */
    KeyHandle keyHandle = new KeyHandle();

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 1;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        this.setBackground(Color.BLACK);
        /* (improve rendering)
        true -> all drowing from this component will
        be done in an offscreen painting buffer
         */
        this.setDoubleBuffered(true);

        this.addKeyListener(keyHandle);
        this.setFocusable(true);
    }


    public void update() {
        if (keyHandle.upPressed) {
            playerY -= playerSpeed;
        }
        if (keyHandle.downPressed) {
            playerY += playerSpeed;
        }
        if (keyHandle.rightPressed) {
            playerX += playerSpeed;
        }
        if (keyHandle.leftPressed) {
            playerX -= playerSpeed;
        }
    }

    /*
    Game Loop Management
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                // Update
                update();
                // Draw
                repaint();

                delta--;
                drawCount++;
            }

            if(timer > 1000000000){
                System.out.println("FPS-" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }
}
