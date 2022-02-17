import java.util.Random;

public class BoardEditor {

    Board board;
    Pieces piece;
    final char piece_sym = '%';
    final char empty_sym = ' ';
    final char border_sym = '#';

    public BoardEditor(Board b)
    {
        board = b;
    }

    public void make_new_piece() //(int x, int y, char type)
    {
        Random rd = new Random();
        piece = new Pieces(rd.nextInt(8)+1,0, piece.types[rd.nextInt(3)]);
        insert_piece();
    }

    public void insert_piece()
    {
        for(int i = 0; i < piece.matrix.length; i++)
            for(int j = 0; j < piece.matrix[i].length; j++)
                if(piece.matrix[i][j] == 1)
                    board.map[j+i*board.boardWidth+ piece.x+piece.y*board.boardWidth] = '%';
    }

    public boolean move(int x, int y)
    {



        if(collisions(x,y)){
            x = 0; y = 0;
            removeLine();
            return false;
        }    //TO DO, triggers creation of new el and blends old permanently into board

            for(int i = 0; i < piece.matrix.length; i++)
                for(int j = 0; j < piece.matrix[i].length; j++)
                    if(piece.matrix[i][j] == 1)
                        board.map[j+i*board.boardWidth+ piece.x+piece.y*board.boardWidth] = ' ';
            piece.move(x, y);
            insert_piece();
            return true;
    }

    public void rotate(){}

    public void fall()
    {
        while(move(0,1));
    }

    public void removeLine()
    {
        int index= board.checkLines();
        if(index != -1) {
            for (int q = index; q < board.boardWidth - 2 + index; q++)
                board.map[q] = ' ';

            int rows = (index - 1) / board.boardWidth - 1;
            for (int j = rows; j > 0; j--)
                for (int i = 10; i > 0; i--)
                    if (board.map[j* board.boardWidth+i] == '%')
                    {
                        board.map[j * board.boardWidth + i + board.boardWidth] = '%';
                        board.map[j* board.boardWidth+i] = ' ';
                    }
        }


    }

    public boolean collisions(int x, int y) //check collisions during movement, not rotation
    {
            if (y == 0 && (piece.x+piece.width + x >= board.boardWidth-1 || piece.x + x <= 0))
                return true;
           else if(x == 0)
           {
                int[] coll_tab = piece.getLowerBlocks();
                for(int q = 0; q < coll_tab.length; q++)
                    if(coll_tab[q] != -1 &&
                            ( board.map[(piece.y+coll_tab[q]+y)*board.boardWidth+(piece.x+q)] == '%'
                                || board.map[(piece.y+coll_tab[q]+y)*board.boardWidth+(piece.x+q)] == '#') )
                    {
                        insert_piece();
                        make_new_piece();
                        return true;
                    }
           }

                return false;

    } //TO DO

}
