package piatto.map.tileManager.tileBuilder.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Tile extends TileBluePrint implements TileBluePrintBuildRule {
    public BufferedImage image;

    public Tile(long code, String name, String src, boolean enableCollision) {
        this.Name = name;
        this.Code = code;
        this.Src = src;
        this.Collision = enableCollision;
    }

    @Override
    public void Initialize() {
        try {
            if (this.Src != null) {
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(this.Src)));
            } else {
                throw new RuntimeException("Cannot build " + this.Name + " Tile: src is null");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
