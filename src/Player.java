import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Iterator;

public class Player extends Leopard {

    public Player(int x,int y) {
        super (x,y);

        inputPlayer();
    }
    //Define a picture for the player.
    public void inputPlayer(){
        ImageIcon imagePlayer = new ImageIcon("src/resources/maskLeopard.png");
        Image player = imagePlayer.getImage();
        setImage(player);
    }

    public void goAroundCampus (int x, int y) {
        int xChange = x() +x;
        int yChange = y() + y;

        getXCoordinate(xChange);
        getYCoordinate(yChange);
    }

}
