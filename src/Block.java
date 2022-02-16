import java.awt.*;

public class Block {

    int shape; // L, S, Q, K, I
    static int id;
    public Square plane[];
    int x,y; //left down corner
    int speed;

    Block(int _shape, int _x)
    {
        speed = 1; id++;  shape = _shape;
        x = _x*Square.len+Tower.tower_offset;
        y = Tower.tower_offset-Square.len*2;//Tower.max_height-(Square.len);
        plane = new Square[4];

        create();
    }

    void rotate(char direction)
    {
        if (direction == 'L' && plane[0].x > 0)
            for(int q = 1; q < 4; q++)
            {
                int _x = plane[q].x - plane[0].x;
                plane[q].x = plane[q].y - plane[0].y;// * -1;
                plane[q].y = _x;
            }
        else if (direction == 'R' && plane[0].x < Tower.max_width-1)
            for(int q = 0; q < 3; q++)
                rotate('L');
    }

    void speed_up(boolean faster)
    {
        if(faster)
            speed = 2;
        else
            speed = 1;
    }

    public void move()
    {
        for (Square square : plane) square.y += Square.len*speed;
    }

    void create()
    {
        switch (shape) {
            case 0 -> {
                plane[0] = new Square(x, y, true);
                plane[1] = new Square(x + Square.len, y, true);
                plane[2] = new Square(x + Square.len*2, y, true);
                plane[3] = new Square(x + Square.len*2, y + Square.len, true);
            }
            case 1 -> {
                plane[0] = new Square(x, y, true);
                plane[1] = new Square(x, y + Square.len, true);
                plane[2] = new Square(x + Square.len, y + Square.len, true);
                plane[3] = new Square(x + Square.len, y + Square.len*2, true);
            }
            case 2 -> {
                plane[0] = new Square(x, y, true);
                plane[1] = new Square(x + Square.len, y, true);
                plane[2] = new Square(x, y + Square.len, true);
                plane[3] = new Square(x + Square.len, y + Square.len, true);
            }
            case 3 -> {
                plane[0] = new Square(x, y, true);
                plane[1] = new Square(x + Square.len, y, true);
                plane[2] = new Square(x + Square.len*2, y, true);
                plane[3] = new Square(x + Square.len, y + Square.len, true);
            }
            case 4 -> {
                plane[0] = new Square(x, y, true);
                plane[1] = new Square(x + Square.len, y, true);
                plane[2] = new Square(x + Square.len*2, y, true);
                plane[3] = new Square(x + Square.len*3, y, true);
            }
        }

    }

    public void draw(Graphics g)
    {
        for(int q = 0; q< plane.length; q++)
           plane[q].draw(g);
     //   for (Square square : plane) square.draw(g);
    }

}
