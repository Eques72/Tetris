import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{

    static final int width = 320; //px
    static final int height = 650;

    Tower tower;
    Block block;

    Thread main;
    ActionListener al;
    MyKeyListener kl;

    GamePanel() {
        this.setPreferredSize(new Dimension(width,height));

        tower = new Tower();
        tower.fill_rows();

        generate_block();

        kl = new MyKeyListener();
        this.addKeyListener(kl);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

        main = new Thread(this);
        main.start();
    }

    private void move()
    {
        block.move();
    }

    private void check_collisions()
    {
        boolean collision = false;
        int q = 0;
        while (!collision && q < 4)
        {
            if(block.plane[q].y >= (Tower.max_height-4)*Square.len+Tower.tower_offset)
                {
                    System.out.print("collololo");
                    collision = true;
                    for(int w = 0; w < block.plane.length; w++)
                        tower.rows[(((block.plane[w].y-Square.len-Tower.tower_offset))/Square.len)+4][(block.plane[w].x-Tower.tower_offset)/Square.len].set_active(); //[h or y][w or x]

                    generate_block();
                }


            int e = Tower.max_height-1;
            while(e > 2 && !collision)
            {
                if(block.plane[q].y == tower.rows[e][(block.plane[q].x-Tower.tower_offset)/Square.len].y && tower.rows[e][(block.plane[q].x-Tower.tower_offset)/Square.len].is_active)
                {
                    collision = true;
                    for(int w = 0; w < block.plane.length; w++)
                        tower.rows[(((block.plane[w].y-Square.len-Tower.tower_offset))/Square.len)+4][(block.plane[w].x-Tower.tower_offset)/Square.len].set_active(); //[h or y][w or x]

                    generate_block();
                }
                e--;
            }
            q++;
        }
        //after move operation?
        //if any cube from block is overlaping with any active tower's cube or is on the bottom
        //all cubes of the block (-Square.len) become active tower's Squares
        //Delete Block and generate new one.
    }

    private void generate_block()
    {
        Random rd;
        rd = new Random();

        block = new Block(rd.nextInt(4),rd.nextInt(Tower.max_width-2));
    }

    private void check_rows()
    {
        //if any row is full of active cubes, lower every row over it
        //increase score
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(48,18,38));
        g.fillRect(0,0,width,height);

        tower.draw(g);
        block.draw(g);
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
                move();
                check_collisions();
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
            switch (e.getKeyChar())
            {
                case 'q' ->
                        {
                            block.rotate('L');
                        }
                case 'e' ->
                        {
                            block.rotate('R');
                        }
                case 's' ->
                        {
                            block.speed_up(true);
                        }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyChar() == 's')
                block.speed_up(false);
        }
    }









}
