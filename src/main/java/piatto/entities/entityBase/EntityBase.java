package piatto.entities.entityBase;

import java.awt.image.BufferedImage;

public class EntityBase {
    public int x;
    public int y;

    public int speed;

    public BufferedImage go_down_1, go_left_1, go_down_3, go_down_2, go_left_2, go_left_3, go_right_1, go_right_2, go_right_3, go_up_1, go_up_2, go_up_3;
    public String direction;

    public int spriteCounter = 1;
    public int spriteNumber = 1;
}
