package piatto.object.objets;

import piatto.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ingot extends SuperObject {
    public Ingot() {
        name = "Ingot";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/items/ingot.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
