import java.awt.*;

public class Square extends Rectangle {
    int x,y;
    static int id;
    boolean is_active = false;
    public static final int len = 30;

    Square(int _x, int _y, boolean activate) { id++; x = _x; y = _y; is_active = activate;}

    public void set_active() {is_active = true;}

    void draw(Graphics g)
    {
        if (is_active) {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, len, len);
            g.setColor(Color.YELLOW);
            g.fillRect(x+3, y+3, len-3, len-3);
        }
    }
}
