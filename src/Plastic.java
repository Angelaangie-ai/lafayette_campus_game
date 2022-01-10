import javax.swing.*;
import java.awt.*;

public class Plastic extends Leopard{
    public Plastic (int x, int y) {
        super(x,y);

        inputPlastic();
    }
    //Define a picture for plastic bags.
    private void inputPlastic() {
        ImageIcon imagePlastic = new ImageIcon("src/resources/plasticBag.png");
        Image plastic = imagePlastic.getImage();
        setImage(plastic);
    }

    public void goAroundCampus (int x, int y) {
        int xChange = x() +x;
        int yChange = y() +y;

        getXCoordinate(xChange);
        getYCoordinate(yChange);
    }


}
