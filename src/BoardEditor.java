import java.util.Random;

public class BoardEditor {

    Board board;
    Pieces piece;
    Score score;
    final char piece_sym = '%';
    final char empty_sym = ' ';
    final char border_sym = '#';
    public boolean gameOver;

    public BoardEditor(Board b, Score s)
    {
        score = s;
        board = b;
        gameOver = false;
    }

    public void make_new_piece() //(int x, int y, char type)
    {
        Random rd = new Random();
        piece = new Pieces(rd.nextInt(8)+1,0, Pieces.types[rd.nextInt(7)]);
        insert_piece();
    }

    public void insert_piece()
    {
        for(int i = 0; i < piece.matrix.length; i++)
            for(int j = 0; j < piece.matrix[i].length; j++)
                if(piece.matrix[i][j] == 1)
                    board.map[j+i* Board.boardWidth + piece.x+piece.y* Board.boardWidth] = piece_sym;
    }

    public void clear_piece()
    {
        for(int i = 0; i < piece.matrix.length; i++)
            for(int j = 0; j < piece.matrix[i].length; j++)
                if(piece.matrix[i][j] == 1)
                    board.map[j+i* Board.boardWidth + piece.x+piece.y* Board.boardWidth] = empty_sym;
    }

    public void isGameOver()
    {
        for(int q = 0; q < Board.boardWidth; q++)
            if (board.map[q] == piece_sym) {
                gameOver = true;
                break;
            }
    }

    public boolean move(int x, int y)
    {
        if(collisions(x,y) || gameOver)
            return false;

            clear_piece();

            piece.move(x, y);
            insert_piece();
            return true;
    }

    public void rotate()
    {
        clear_piece();
        piece.rotate();
        insert_piece();
    }

    public void fall()
    {
        while(move(0,1));
    }

    public void removeLine()
    {
        int deleted_lines = 0;
        int index= board.checkLines();
        while(index != -1) {
            for (int q = index; q < Board.boardWidth - 2 + index; q++)
                board.map[q] = ' ';

            int rows = (index - 1) / Board.boardWidth - 1;
            for (int j = rows; j > 0; j--)
                for (int i = 10; i > 0; i--)
                    if (board.map[j* Board.boardWidth +i] == piece_sym)
                    {
                        board.map[j * Board.boardWidth + i + Board.boardWidth] = piece_sym;
                        board.map[j* Board.boardWidth +i] = ' ';
                    }
            index= board.checkLines();

            deleted_lines++;
        }

        if(deleted_lines == 0)
            score.resetCombo();
        else
            score.addPoints(deleted_lines);

    }

    public boolean collisions(int x, int y) //check collisions during movement, not rotation
    {
            if (y == 0)
            {
                int[] coll_tab;
                if(x < 0)
                    coll_tab = piece.getLeftSideProjection();
                else
                    coll_tab = piece.getRightSideProjection();

                for (int i = 0; i < coll_tab.length; i++) {
                        if (coll_tab[i] != -1 &&
                                (board.map[(piece.y+i) * Board.boardWidth + piece.x + coll_tab[i] + x] == piece_sym ||
                                        board.map[(piece.y+i) * Board.boardWidth + piece.x + coll_tab[i] + x] == border_sym))
                        {                 return true; }
                    }
            }
           else if(x == 0)
           {
                int[] coll_tab = piece.getLowerProjection();
                for(int q = 0; q < coll_tab.length; q++)
                    if(coll_tab[q] != -1 &&
                            ( board.map[(piece.y+coll_tab[q]+y)* Board.boardWidth +(piece.x+q)] == piece_sym
                                || board.map[(piece.y+coll_tab[q]+y)* Board.boardWidth +(piece.x+q)] == border_sym) ) {
                        insert_piece();
                        isGameOver();
                        if(!gameOver)
                        {
                            removeLine();
                            make_new_piece();
                        }
                        return true;
                    }
           }

                return false;

    }
}
