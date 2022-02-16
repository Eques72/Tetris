import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    GamePanel gP;


    Screen()
    {
        gP = new GamePanel();

        this.setSize(new Dimension(gP.getWidth(), gP.getHeight()));
        this.setResizable(false);
  //      this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setEnabled(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(gP);
        this.pack();
    }

}