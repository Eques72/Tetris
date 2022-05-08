import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {

    static final int width = 360;//320; //px
    static final int height = 660;//650;
    static final String iconFile = "resources/TetrisLogo.png";

    Thread main;
    ActionListener al;
    MyKeyListener kl;

    Board b;
    BoardDrafter dB;
    BoardEditor eB;
    RetryPanel rP;
    Score score;

    boolean is_game_running;

    GamePanel(Screen screen) {
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(null);

        b = new Board();
        dB = new BoardDrafter(b);
        score = new Score();
        eB = new BoardEditor(b, score);
        eB.make_new_piece();


        kl = new MyKeyListener();
        this.addKeyListener(kl);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        al = new MyActionListener();


        setIcon(screen);

        //       rP = new RetryPanel();
        //     rP.addButton(al);
        is_game_running = true;
        main = new Thread(this);
        main.start();
    }


    private void setIcon(Screen screen) {
        Image icon;
        icon = Toolkit.getDefaultToolkit().createImage(iconFile);
        screen.setIconImage(icon);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(48, 18, 38));
        g.fillRect(0, 0, width, height);

        Graphics2D g2d = (Graphics2D) g;
        dB.drawBoard(g2d);
        if (eB.gameOver) {
//            defeatScreen(g2d);
//            //rP.drawRetry(g2d);
            rP.drawRetry(g2d, al);
            rP.displayScore(g2d, score);
//            this.add(rP);
        }
    }

    private void resetGame() {

        this.remove(rP);
        b = new Board();
        dB.setNewBoard(b);
        eB = new BoardEditor(b, score);
        eB.make_new_piece();
    }

    private void defeatScreen(Graphics2D g) {
        rP = new RetryPanel(this);
        //rP = new RetryPanel();
        rP.drawRetry(g, al);

    }

    private void defeat() {
        rP = new RetryPanel(this);
        this.add(rP);

        JButton b = new JButton("RETRY");

        b.setBounds(GamePanel.width / 2 - 50, GamePanel.height / 2 + 100, 100, 100);
        b.addActionListener(al);
        //this.add(b);
        this.add(b);

        repaint();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1;//60.0;
        double nanoSec = 1000000000 / amountOfTicks;
        double delta = 0;
        while (is_game_running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSec;
            lastTime = now;
            if (delta >= 1) {
                if (!eB.gameOver)
                    eB.move(0, 1);
                else
                    is_game_running = false;
                defeat();

                repaint();
                delta--;
            }
        }
    }

    class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case 65, 37 -> {
                    eB.move(-1, 0);
                    repaint();
                }
                case 68, 39 -> {
                    eB.move(1, 0);
                    repaint();
                }
                case 87, 38 -> {
                    eB.rotate();
                    repaint();
                }
                case 40, 83 -> eB.fall();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }


    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Objects.equals(e.getActionCommand(), "RETRY")) {
                resetGame();
                System.out.print("YWT");
            }
        }
    }


}
