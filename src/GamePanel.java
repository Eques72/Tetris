import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{

    static final int width = 360;//320; //px
    static final int height = 660;//650;

    Thread main;
    ActionListener al;
    MyKeyListener kl;

    Board b;
    BoardDrafter dB;
    BoardEditor eB;

    GamePanel() {
        this.setPreferredSize(new Dimension(width,height));

        b= new Board();
        dB = new BoardDrafter(b);
        eB = new BoardEditor(b);
        eB.make_new_piece();

        kl = new MyKeyListener();
        this.addKeyListener(kl);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

        main = new Thread(this);
        main.start();
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(48,18,38));
        g.fillRect(0,0,width,height);

        Graphics2D g2d = (Graphics2D) g;
        dB.drawBoard(g2d);

    }

    @Override
    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1;//60.0;
        double nanoSec = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true)
        {
            long now = System.nanoTime();
            delta += (now - lastTime)/nanoSec;
            lastTime = now;
            if(delta >= 1)
            {
                eB.move(0,1);
                repaint();
                delta--;
            }
        }
    }

    class MyKeyListener implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case 65: case 37:
                        {
                            eB.move(-1, 0);
                            repaint();
                            break;
                        }
                case 68: case 39:
                        {
                            eB.move(1, 0);
                            repaint();
                            break;
                        }
                case 87: case 38 :
                        {
                            eB.rotate();
                            repaint();
                            break;
                        }
                case 40: case 83 :
                        {
                            eB.fall();
                            //TO DO
                           // block.speed_up(true);
                            break;
                        }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }









}
