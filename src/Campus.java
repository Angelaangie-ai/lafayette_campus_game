import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;


public class Campus<img> extends JPanel {

    final int yDistance = 30;
    final int distance = 20;
    final int lDistance = 1;
    final int rDistance = 2;
    final int tDistance = 3;
    final int bDistance = 4;

    private ArrayList<bin> bins;
    private ArrayList<Plastic> plastics;

    private Player Angela;
    private int w=0;
    private int h=0;


    ImageIcon lafCampus = new ImageIcon("src/resources/lafCampus.jpg");
    Image lafayetteCampus = lafCampus.getImage();


    private boolean cleanCampus = false;
    //Set up the map of recycling bins,plastic bags & the player along the campus background.
    private String map =
            "\n\n\n" +
                    "                                     $           #" +
                    "\n\n\n                      $            $       #" +
                   "\n\n                                   $                #" +
                    "\n\n                                   $                        # " +
                    " \n\n                           " +
                    "                  #   @           " +
                    "\n\n                                                     $    " +
                    "\n                                 $            $" +
                    "\n\n\n\n                                                   #" +
                    "\n                                                                   #" +
                    "\n\n\n\n\n\n\n                                                #";

    public Campus() {
        startCampus();
    }

    private void startCampus() {
        addKeyListener(new campusAdapter());
        setFocusable(true);
        launchCampus();
    }

    public int getBoardWidth () {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    //In order to modify the set of plastic bags and recycling bins, we set them up in Array Lists.
        private void launchCampus() {
        plastics = new ArrayList<>();
        bins = new ArrayList<>();

        int x = yDistance;
        int y = yDistance;


        Plastic B;
        bin A;

        //We change each of the symbols in the map string,with a picture.
        for (int i = 0; i < map.length(); i++) {
            char item = map.charAt(i);
            switch (item) {

                case '\n':
                    y += distance;
                    if (this.w < x) {
                        this.w = x;
                    }
                    x = yDistance;
                    break;

                case '$':
                    B = new Plastic(x, y);
                    plastics.add(B);
                    x += distance;
                    break;

                case '#':
                    A = new bin(x,y);
                    bins.add(A);
                    x+= distance;
                    break;

                case '@':
                    Angela = new Player(x, y);
                    x += distance;
                    break;

                case ' ':
                    x += distance;
                    break;

                default:
                    break;
            }
            h = y;


        }
    }

//Set up the background picture and all other elements on the display.
 private void createCampus (Graphics g) {
        g.setColor(Color.green);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        ArrayList<Leopard> world = new ArrayList<>();
        g.drawImage(lafayetteCampus,0,-20,1920,1080,null);
        world.addAll(plastics);
        world.addAll(bins);
        world.add(Angela);

        for (int i=0; i<world.size(); i++) {
            Leopard item = world.get(i);
            g.drawImage(item.getImage(),item.x()+2,item.y()+2,this);
        }

        //Execute final message if game is finished.
        if(cleanCampus) {
            g.setColor(new Color(0,0,0));
            g.drawString("Hooray! Lafayette College is clean again!",50,20);
        }


 }

 //Ability for the player to move, when triggered by the arrow keys.
 @Override
public void paintComponent (Graphics g) {
     super.paintComponent(g);
        createCampus(g);
 }
  private class campusAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(cleanCampus) {
                return;
            }
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    if (playerBin(Angela,lDistance)) {
                        return;
                    }
                    if (bagBag(lDistance)) {
                        return;
                    }

                    Angela.goAroundCampus(-distance, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    if (playerBin(Angela,rDistance)) {
                        return;
                    }
                    if (bagBag(rDistance)) {
                        return;
                    }

                    Angela.goAroundCampus(distance, 0);
                    break;
                case KeyEvent.VK_UP:
                    if (playerBin(Angela,tDistance)) {
                        return;
                    }
                    if (bagBag(tDistance)) {
                        return;
                    }

                    Angela.goAroundCampus(0, -distance);
                    break;
                case KeyEvent.VK_DOWN:
                    if (playerBin(Angela,bDistance)) {
                        return;
                    }
                    if (bagBag(bDistance)) {
                        return;
                    }

                    Angela.goAroundCampus(0, distance);
                    break;

                case KeyEvent.VK_R:
                    cleanCampusAgain();
                    break;

                default:
                    break;
            }
            repaint();

        }

  }
  //Define collisions between a player and the recycle bins.
    private boolean playerBin (Leopard leopard, int type) {
        switch (type) {
            case lDistance:
                for (int i=0; i < bins.size(); i++) {
                    bin bin = bins.get(i);
                    if (leopard.lTouch(bin)) {
                        return true;
                    }
                }
                return false;
            case rDistance:
                for (int i=0; i < bins.size(); i++) {
                    bin bin = bins.get(i);
                    if (leopard.rTouch(bin)) {
                        return true;
                    }
                }
                return false;
            case tDistance:
                for (int i=0; i < bins.size(); i++) {
                    bin bin = bins.get(i);
                    if (leopard.tTouch(bin)) {
                        return true;
                    }
                }
                return false;
            case bDistance:
                for (int i=0; i < bins.size(); i++) {
                    bin bin = bins.get(i);
                    if (leopard.bTouch(bin)) {
                        return true;
                    }
                }
                return false;
            default:break;
        }
        return false;

    }

    //Ability for the player to move the bags around the campus.
    private boolean bagBag(int type) {
        switch (type) {
            case lDistance:
                for (int i=0; i<plastics.size(); i++) {
                    Plastic bag = plastics.get(i);
                    if (Angela.lTouch(bag)) {
                        for (int j=0; j<plastics.size(); j++) {
                            Plastic bag1 = plastics.get(j);
                            if(!bag.equals(bag1)) {
                                if (bag.lTouch(bag1)) {
                                    return true;
                                }
                            }
                            if (playerBin(bag,lDistance)) {
                                return true;
                            }
                        }
                        bag.goAroundCampus(-distance,0);
                        cleanCampus();
                    }
            }
                return false;

            case rDistance:
                for (int i=0; i<plastics.size(); i++) {
                    Plastic bag = plastics.get(i);
                    if (Angela.rTouch(bag)) {
                        for (int j=0; j<plastics.size(); j++) {
                            Plastic bag1 = plastics.get(j);
                            if(!bag.equals(bag1)) {
                                if (bag.rTouch(bag1)) {
                                    return true;
                                }
                            }
                            if (playerBin(bag,rDistance)) {
                                return true;
                            }
                        }
                        bag.goAroundCampus(distance,0);
                        cleanCampus();
                    }
                }
                return false;
            case tDistance:
                for (int i=0; i<plastics.size(); i++) {
                    Plastic bag = plastics.get(i);
                    if (Angela.tTouch(bag)) {
                        for (int j=0; j<plastics.size(); j++) {
                            Plastic bag1 = plastics.get(j);
                            if(!bag.equals(bag1)) {
                                if (bag.tTouch(bag1)) {
                                    return true;
                                }
                            }
                            if (playerBin(bag,tDistance)) {
                                return true;
                            }
                        }
                        bag.goAroundCampus(0,-distance);
                        cleanCampus();
                    }
                }
                return false;
            case bDistance:
                for (int i=0; i<plastics.size(); i++) {
                    Plastic bag = plastics.get(i);
                    if (Angela.bTouch(bag)) {
                        for (int j=0; j<plastics.size(); j++) {
                            Plastic bag1 = plastics.get(j);
                            if(!bag.equals(bag1)) {
                                if (bag.bTouch(bag1)) {
                                    return true;
                                }
                            }
                            if (playerBin(bag,bDistance)) {
                                return true;
                            }
                        }
                        bag.goAroundCampus(0,distance);
                        cleanCampus();
                    }
                }
                break;
            default:break;

        }
        return false;
    }

 //Define ending of the game: when there is a plastic bag next to each recycle bin.
 public void cleanCampus() {
        int nBag= bins.size();
        int fBag = 0;

        for (int i=0; i<nBag; i++) {
            Plastic bag = plastics.get(i);

            for (int j = 0; j<nBag; j++) {
                bin bin = bins.get(j);

                if (bag.x()==bin.x() && bag.y()==bin.y())
                {
                    fBag = fBag +1;
                }
                else if (bag.x()==bin.x()+20 && bag.y()==bin.y())
                {
                    fBag = fBag +1;
                }
                else if (bag.x()==bin.x()-20 && bag.y()==bin.y())
                {
                    fBag = fBag +1;
                }
                else if (bag.x()==bin.x()+1 && bag.y()==bin.y()-1)
                {
                    fBag = fBag +1;
                }
                else if (bag.x()==bin.x()+1 && bag.y()==bin.y()+1)
                {
                    fBag = fBag +1;
                }
                else if (bag.x()==bin.x()-1 && bag.y()==bin.y()+1)
                {
                    fBag = fBag +1;
                }
                else if (bag.x()==bin.x()-1 && bag.y()==bin.y()-1)
                {
                    fBag = fBag +1;
                }

            }
        }
        if(fBag == nBag) {
            cleanCampus = true;
            repaint();
        }
 }
 //Restarting the Game
private void cleanCampusAgain() {
        bins.clear();
        plastics.clear();

        launchCampus();

        if(cleanCampus) {
            cleanCampus = false;
        }

}


}
