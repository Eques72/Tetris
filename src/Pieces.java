import java.util.Arrays;

public class Pieces {

    private short[][] matrixI = { {0,0,0,0},{1,1,1,1},{0,0,0,0},{0,0,0,0}};
    private short[][] matrixL = { {0,0,1},{1,1,1},{0,0,0}};
    private short[][] matrixO = { {1,1},{1,1} };

    public static char[] types = {'L','I','O'};
    public short[][] matrix;
    // X and Y coordinates should be in range <1,10> and <1,20> respectively
    public int x; //is a position on the board where 0 is left border
    public int y; //is a position on the board where 0.0 is top border
    public int width; //position of outmost '1' to the right in matrix
    public int height;//position of outmost '1' to the bottom in matrix
    public Pieces(int _x, int _y, char type)
    { x = _x; y = _y;
        switch (type) {
            case 'I' -> {
                matrix = matrixI;
                width = 3;
                height = 1;
            }
            case 'L' -> {
                matrix = matrixL;
                width = 2;
                height = 1;
            }
            case 'O' -> {
                matrix = matrixO;
                width = 1;
                height = 1;
            }
        }
    }

    public void move(int _x, int _y) //distance is a number of squares on the Board
    { y += _y; x+= _x; }

    public int[] getLowerBlocks()
    {
        int[] collision_table = new int[matrix.length];
        Arrays.fill(collision_table, -1);

        for(int q = matrix.length-1; q >= 0 ; q--)
            for(int w = 0; w < matrix[q].length; w++)
                if(matrix[q][w] == 1 && collision_table[w] == -1)
                    collision_table[w] = q;

        return collision_table;
    }

    public void rotate() {}
}
