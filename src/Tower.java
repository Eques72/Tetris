import java.awt.*;

public class Tower {

    static final int max_width = 10;
    static final int max_height = 24; //20 + 4 extra
    static final int tower_offset = 10;

    Square rows[][];

    Tower() { rows = new Square[max_height][max_width]; fill_rows();}

    public void fill_rows() {
        for(int q = 0; q < rows.length; q++)
        for(int w = 0; w < rows[q].length; w++)
            rows[q][w] = new Square(tower_offset+Square.len*w,tower_offset+Square.len*(q-4),false); }


    public void draw(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(0,0,max_width*Square.len+tower_offset,(max_height-4)*Square.len+tower_offset);

        for(int q = 0; q < rows.length; q++)
            for(int w = 0; w < rows[q].length; w++)
                rows[q][w].draw(g);
    }


}
