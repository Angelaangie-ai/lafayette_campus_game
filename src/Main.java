import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private final int distance = 30;

    public Main() {
        setDesign();
    }


    private void setDesign()
    {
        Campus design = new Campus();
        add(design);

        setTitle("Lafayette Recycling Campus");

        setSize(design.getBoardWidth() + distance,design.getBoardHeight()+2*distance);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
        Main game = new Main();
        game.setVisible(true);


    });

}}
