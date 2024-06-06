package piatto.object.objets;

import piatto.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Door extends SuperObject {

    public boolean isOpen = false;

    public Door(){
        name = "Door";

        setImage();

        collision = true;
    }

    public void toggleDoor(){
        if(!isOpen){
            isOpen = true;
            collision = false;

            setImage();
        }
    }

    private void setImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream(isOpen ? "/items/chest_simple_open.png" : "/items/chest_simple.png"));

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
