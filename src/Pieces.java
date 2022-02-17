import java.util.Arrays;

public class Pieces {

    private short[][] matrixI = { {0,0,0,0},{1,1,1,1},{0,0,0,0},{0,0,0,0}};
    private short[][] matrixL = { {0,0,1},{1,1,1},{0,0,0}};
    private short[][] matrixJ = { {1,0,0},{1,1,1},{0,0,0}};
    private short[][] matrixS = { {0,1,1},{1,1,0},{0,0,0}};
    private short[][] matrixZ = { {1,1,0},{0,1,1},{0,0,0}};
    private short[][] matrixT = { {0,1,0},{1,1,1},{0,0,0}};
    private short[][] matrixO = { {1,1},{1,1} };

    public static char[] types = {'L','I','O','J','S','Z','T'};
    public short[][] matrix;
    // X and Y coordinates should be in range <1,10> and <1,20> respectively
    public int x; //is a position on the board where 0 is left border
    public int y; //is a position on the board where 0.0 is top border
    public char type;
    public int width; //position of outmost '1' to the right in matrix
    public int height;//position of outmost '1' to the bottom in matrix

    public Pieces(int _x, int _y, char _type)
    { x = _x; y = _y; type = _type;
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
            case 'J' -> {
                matrix = matrixJ;
                width = 2;
                height = 1;
            }
            case 'S' -> {
                matrix = matrixS;
                width = 2;
                height = 1;
            }
            case 'Z' -> {
                matrix = matrixZ;
                width = 2;
                height = 1;
            }
            case 'T' -> {
                matrix = matrixT;
                width = 2;
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

    public void rotate()
    {
        if(type == 'O')
            return;
        short[][] tmp_matrix;
        if(type == 'I')
            tmp_matrix = new short[][]{ {0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        else
            tmp_matrix = new short[][]{ {0,0,0},{0,0,0},{0,0,0} };

        for(short i = 0; i < matrix.length; i++)
            for(short j = 0; j < matrix[i].length; j++)
                if(matrix[i][j] == 1)
                {
                   int indexY = i-1 ;
                    int indexX = j-1 ;

                    int tmp = indexY;
                    indexY = indexX;
                    indexX = tmp;

                    indexX *= -1;
                    indexX++;
                    indexY++;
                    tmp_matrix[indexY][indexX] = 1;
                }

        matrix = tmp_matrix;

    }
}
