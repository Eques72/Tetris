import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RetryPanel extends JPanel {

//    displays defeat screen
    //shows score, defeat information and
    // has a RETRY button, which after pressed clears the map and starts new game
    private String message = "You Loose";
   // Button b;

    //public void addButton( ActionListener a) {}


    public void drawRetry(Graphics2D g, ActionListener a)
    {
        g.setColor(new Color(36,0,74));
        g.fillRect(Board.tileSize,Board.tileSize,GamePanel.width-Board.tileSize*2,
                GamePanel.height-Board.tileSize*2);

        g.setColor(Color.WHITE);
        g.setFont(new Font("DIALOG",Font.ITALIC,35));
        g.drawString(message,(GamePanel.width/2)-80,(GamePanel.height/3));

        Button b = new Button("RETRY");

        b.setBounds(GamePanel.width/2-50,GamePanel.height/2+100,100,100);
        b.addActionListener(a);
        this.add(b);
    }

}
