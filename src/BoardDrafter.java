import java.awt.*;
import java.awt.image.ImageObserver;

public class BoardDrafter {

    Board board;
    Image sprites;
    ImageObserver iO;
    static final String textureFile = "resources/TetrisTex.png";

    public BoardDrafter(Board b) {
        board = b;
        sprites = Toolkit.getDefaultToolkit().createImage(textureFile);

    }

    public void setNewBoard(Board new_board) { board = new_board; }

    public void drawBoard(Graphics2D g)
    {
        for(int q = 0; q < board.boardHeight; q++) //pieces
            for(int w = 0; w < board.boardWidth; w++)
                if(board.map[(q*board.boardWidth) + w] == '%') {
                    g.drawImage(
                            sprites, w * board.tileSize, q * board.tileSize,
                            w * board.tileSize + board.tileSize, q * board.tileSize + board.tileSize,
                            6 * 20, 0, 20 * 7, 20, iO);
                }


            g.drawImage(    //draws left border
                    sprites, 0, board.tileSize,
                     board.tileSize, (board.boardHeight-1) * board.tileSize,
                    20 * 5, 0, 20 * 6, 20, iO);
            g.drawImage(    //draws right border
                    sprites, (board.boardWidth-1) * board.tileSize, board.tileSize,
                    board.boardWidth * board.tileSize, (board.boardHeight-1) * board.tileSize,
                    20 * 5, 0, 20 * 6, 20, iO);

            g.drawImage(    //draws top border
                    sprites, board.tileSize, 0,
                    (board.boardWidth-1) * board.tileSize, board.tileSize,
                    20*4, 0,20*5,20, iO);
            g.drawImage(    //draws bottom border
                    sprites, board.tileSize, board.tileSize * (board.boardHeight-1),
                    (board.boardWidth-1)* board.tileSize, board.tileSize * board.boardHeight,
                    20*4, 0,20*5,20, iO);


                        g.drawImage(    //draws top left corner
                                sprites, 0,0,
                                board.tileSize,board.tileSize,
                                0, 0,20,20, iO);
                        g.drawImage(    //draws top right corner
                                sprites, (board.boardWidth-1)*board.tileSize,0,
                                board.boardWidth*board.tileSize,board.tileSize,
                                20, 0,20*2,20, iO);
                        g.drawImage(    //draws bottom right corner
                                sprites, (board.boardWidth-1)*board.tileSize,(board.boardHeight-1)*board.tileSize,
                                board.boardWidth*board.tileSize,board.boardHeight*board.tileSize,
                            3*20, 0,20*4,20, iO);
                        g.drawImage(    //draws bottom left corner
                                sprites, 0,(board.boardHeight-1)*board.tileSize,
                                board.tileSize,board.boardHeight*board.tileSize,
                                2*20, 0,20*3,20, iO);



    }

}
