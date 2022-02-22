import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    GamePanel gP;
//    Image icon;
//    static final String iconFile = "resources/TetrisLogo.png";

    Screen()
    {
        gP = new GamePanel(this);

        this.setSize(new Dimension(gP.getWidth(), gP.getHeight()));
        this.setResizable(false);
        this.setVisible(true);
        this.setEnabled(true);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


   //     icon = Toolkit.getDefaultToolkit().createImage(iconFile);
   //     this.setIconImage(icon); // stops keyListener from working for some reason
        this.setTitle("Tetris");

        this.add(gP);
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
