package piatto.core.gamePanel;

import piatto.core.collisionManager.CollisionManager;
import piatto.entities.player.Player;
import piatto.core.keyHandler.KeyHandler;
import piatto.tile.tileManager.TileManager;

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
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    // WORLD SETTINGS
    public final int maxWorldCol =  50;
    public final int maxWorldRow =  50;
    public final int maxWorldWidth =  maxWorldCol * tileSize;
    public final int maxWorldHeight =  maxWorldRow * tileSize;

    /*
    screen size  768x576
     */
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    /*
    FPS Game thread master
     */
    Thread gameThread;
    int FPS = 60;
    /*
    KeyBoard Event Chatcher
     */
    KeyHandler keyHandler = new KeyHandler();

    /*
    Entities
     */
    public Player player = new Player(this, keyHandler);

    /*
    Tile Manager
     */
    public TileManager tileManager = new TileManager(this);

    /*
    Collision Manager
     */
    public CollisionManager collisionManager = new CollisionManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        this.setBackground(Color.decode("#2a1e34"));
        /* (improve rendering)
        true -> all drowing from this component will
        be done in an offscreen painting buffer
         */
        this.setDoubleBuffered(true);

        this.addKeyListener(keyHandler);
        this.setFocusable(true);
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

            if (delta >= 1) {
                // Update
                update();
                // Draw
                repaint();

                delta--;
                drawCount++;
            }

            if (timer > 1000000000) {
                System.out.println("FPS-" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);

        player.draw(g2);


        g2.dispose();
    }
}
