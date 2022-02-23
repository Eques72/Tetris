import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    GamePanel gP;

    Screen()
    {
        gP = new GamePanel(this);

        this.setSize(new Dimension(gP.getWidth(), gP.getHeight()));
        this.setResizable(false);
        this.setVisible(true);
        this.setEnabled(true);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setTitle("Tetris");

        this.add(gP);
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
