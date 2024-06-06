package piatto.object.objets;

import piatto.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;


public class Chest extends SuperObject {

    public boolean isOpen = false;

    public Chest(){
        name = "Chest";

        setImage();

        collision = true;
    }

    public void toggleChest(){
        if(!isOpen){
            isOpen = !isOpen;

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
