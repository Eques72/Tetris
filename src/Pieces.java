import java.util.Arrays;

public class Pieces {

    public static char[] types = {'L', 'I', 'O', 'J', 'S', 'Z', 'T'};
    private final short[][] matrixI = {{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}};
    private final short[][] matrixL = {{0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
    private final short[][] matrixJ = {{1, 0, 0}, {1, 1, 1}, {0, 0, 0}};
    private final short[][] matrixS = {{0, 1, 1}, {1, 1, 0}, {0, 0, 0}};
    private final short[][] matrixZ = {{1, 1, 0}, {0, 1, 1}, {0, 0, 0}};
    private final short[][] matrixT = {{0, 1, 0}, {1, 1, 1}, {0, 0, 0}};
    private final short[][] matrixO = {{1, 1}, {1, 1}};
    public short[][] matrix;
    // X and Y coordinates should be in range <1,10> and <1,20> respectively
    public int x; //is a position on the board where 0 is left border
    public int y; //is a position on the board where 0.0 is top border
    public char type;

    public Pieces(int _x, int _y, char _type) {
        x = _x;
        y = _y;
        type = _type;
        switch (type) {
            case 'I' -> matrix = matrixI;

            case 'L' -> matrix = matrixL;

            case 'O' -> matrix = matrixO;

            case 'J' -> matrix = matrixJ;

            case 'S' -> matrix = matrixS;

            case 'Z' -> matrix = matrixZ;

            case 'T' -> matrix = matrixT;
        }
    }

    public void move(int _x, int _y) //distance is a number of squares on the Board
    {
        y += _y;
        x += _x;
    }

    public int[] getLowerProjection() {
        int[] collision_table = new int[matrix.length];
        Arrays.fill(collision_table, -1);

        for (int q = matrix.length - 1; q >= 0; q--)
            for (int w = 0; w < matrix[q].length; w++)
                if (matrix[q][w] == 1 && collision_table[w] == -1)
                    collision_table[w] = q;

        return collision_table;
    }

    public int[] getLeftSideProjection() {
        int[] collision_table = new int[matrix.length];
        Arrays.fill(collision_table, -1);

        for (int q = 0; q < matrix.length; q++) {
            int w = 0;
            while (w < matrix[q].length && matrix[q][w] == 0)
                w++;

            if (w < matrix[q].length)
                collision_table[q] = w;
        }
        return collision_table;
    }

    public int[] getRightSideProjection() {
        int[] collision_table = new int[matrix.length];
        Arrays.fill(collision_table, -1);

        for (int q = 0; q < matrix.length; q++) {
            int w = matrix.length - 1;
            while (w >= 0 && matrix[q][w] != 1)
                --w;

            if (w >= 0)
                collision_table[q] = w;
        }
        return collision_table;
    }

    public void rotate() {
        if (type == 'O')
            return;
        short[][] tmp_matrix;
        if (type == 'I') {
            tmp_matrix = new short[][]
                    {{0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}};
            for (short i = 0; i < matrix.length; i++)
                for (short j = 0; j < matrix[i].length; j++)
                    tmp_matrix[j][i] = matrix[i][j];
            short[][] diagonl_matrix =
                    {{0, 0, 0, 1},
                            {0, 0, 1, 0},
                            {0, 1, 0, 0},
                            {1, 0, 0, 0}};
            for (short i = 0; i < matrix.length; i++)
                for (short j = 0; j < matrix[i].length; j++)
                    matrix[i][j] = (short) (tmp_matrix[i][0] * diagonl_matrix[0][j] +
                            tmp_matrix[i][1] * diagonl_matrix[1][j] + tmp_matrix[i][2] * diagonl_matrix[2][j] +
                            tmp_matrix[i][3] * diagonl_matrix[3][j]);
        } else {
            tmp_matrix = new short[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

            for (short i = 0; i < matrix.length; i++)
                for (short j = 0; j < matrix[i].length; j++)
                    if (matrix[i][j] == 1) {
                        int indexY = i - 1;
                        int indexX = j - 1;

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
}
