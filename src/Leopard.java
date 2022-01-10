import java.awt.Image;

public class Leopard {

    final int distance = 20;

    int x;
    int y;
    Image image;

    public Leopard(int x,int y) {
        this.x=x;
        this.y=y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image img){
        image=img;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void getXCoordinate (int x) {
        this.x=x;
    }

    public void getYCoordinate (int y) {
        this.y=y;
    }

    //Defining collisions between items.
    public boolean lTouch (Leopard leopard) {
        return x() - distance == leopard.x() && y() == leopard.y();
    }

    public boolean rTouch (Leopard leopard) {
        return x() + distance == leopard.x() && y() == leopard.y();
    }

    public boolean tTouch (Leopard leopard) {
        return y() - distance == leopard.y() && x() == leopard.x();
    }

    public boolean bTouch (Leopard leopard) {
        return y() + distance == leopard.y() && x() == leopard.x();
    }






}
