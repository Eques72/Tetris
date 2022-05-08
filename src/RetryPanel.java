import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RetryPanel extends JPanel {

    Button b;
    GamePanel gP;
    //    displays defeat screen
    //shows score, defeat information and
    // has a RETRY button, which after pressed clears the map and starts new game
    private String message = "You Loose";

    //public void addButton( ActionListener a) {}
    public RetryPanel(GamePanel _gP) {
        gP = _gP;
    }

    public void drawRetry(Graphics2D g, ActionListener a) {
        g.setColor(new Color(36, 0, 74));
        g.fillRect(Board.tileSize, Board.tileSize, GamePanel.width - Board.tileSize * 2,
                GamePanel.height - Board.tileSize * 2);

        g.setColor(Color.WHITE);
        g.setFont(new Font("DIALOG", Font.ITALIC, 35));
        g.drawString(message, (GamePanel.width / 2) - 80, (GamePanel.height / 3));

//        JButton b = new JButton("RETRY");
//
//        b.setBounds(GamePanel.width/2-50,GamePanel.height/2+100,100,100);
//        b.addActionListener(a);
//        //this.add(b);
//        gP.add(b);
    }

    public void displayScore(Graphics2D g, Score s) {
        long points = s.getScore();
        String a = "" + points;

        g.setColor(Color.WHITE);
        g.setFont(new Font("DIALOG", Font.ITALIC, 30));
        g.drawString("Score:", (GamePanel.width / 2) - 60, (GamePanel.height / 2 - 40));
        g.drawString(a, (GamePanel.width / 2) - 60, (GamePanel.height / 2));
    }

}
