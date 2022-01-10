import javax.swing.*;
import java.awt.Image;

public class bin extends Leopard {

    public Image recyclingBin;

    public bin (int x,int y) {
        super(x,y);

        inputBin();
    }

    //Store a picture for the recycling bin.
    public void inputBin() {
        ImageIcon imageBin = new ImageIcon("src/resources/greenBin.png");
        recyclingBin = imageBin.getImage();
        setImage(recyclingBin);
    }



}
