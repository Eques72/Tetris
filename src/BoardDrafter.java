import java.awt.*;
import java.awt.image.ImageObserver;

public class BoardDrafter {

    static final String textureFile = "resources/TetrisTex.png";
    Board board;
    Image sprites;
    ImageObserver iO;

    public BoardDrafter(Board b) {
        board = b;
        sprites = Toolkit.getDefaultToolkit().createImage(textureFile);

    }

    public void setNewBoard(Board new_board) {
        board = new_board;
    }

    public void drawBoard(Graphics2D g) {
        for (int q = 0; q < Board.boardHeight; q++) //pieces
            for (int w = 0; w < Board.boardWidth; w++)
                if (board.map[(q * Board.boardWidth) + w] == '%') {
                    g.drawImage(
                            sprites, w * Board.tileSize, q * Board.tileSize,
                            w * Board.tileSize + Board.tileSize, q * Board.tileSize + Board.tileSize,
                            6 * 20, 0, 20 * 7, 20, iO);
                }


        g.drawImage(    //draws left border
                sprites, 0, Board.tileSize,
                Board.tileSize, (Board.boardHeight - 1) * Board.tileSize,
                20 * 5, 0, 20 * 6, 20, iO);
        g.drawImage(    //draws right border
                sprites, (Board.boardWidth - 1) * Board.tileSize, Board.tileSize,
                Board.boardWidth * Board.tileSize, (Board.boardHeight - 1) * Board.tileSize,
                20 * 5, 0, 20 * 6, 20, iO);

        g.drawImage(    //draws top border
                sprites, Board.tileSize, 0,
                (Board.boardWidth - 1) * Board.tileSize, Board.tileSize,
                20 * 4, 0, 20 * 5, 20, iO);
        g.drawImage(    //draws bottom border
                sprites, Board.tileSize, Board.tileSize * (Board.boardHeight - 1),
                (Board.boardWidth - 1) * Board.tileSize, Board.tileSize * Board.boardHeight,
                20 * 4, 0, 20 * 5, 20, iO);


        g.drawImage(    //draws top left corner
                sprites, 0, 0,
                Board.tileSize, Board.tileSize,
                0, 0, 20, 20, iO);
        g.drawImage(    //draws top right corner
                sprites, (Board.boardWidth - 1) * Board.tileSize, 0,
                Board.boardWidth * Board.tileSize, Board.tileSize,
                20, 0, 20 * 2, 20, iO);
        g.drawImage(    //draws bottom right corner
                sprites, (Board.boardWidth - 1) * Board.tileSize, (Board.boardHeight - 1) * Board.tileSize,
                Board.boardWidth * Board.tileSize, Board.boardHeight * Board.tileSize,
                3 * 20, 0, 20 * 4, 20, iO);
        g.drawImage(    //draws bottom left corner
                sprites, 0, (Board.boardHeight - 1) * Board.tileSize,
                Board.tileSize, Board.boardHeight * Board.tileSize,
                2 * 20, 0, 20 * 3, 20, iO);


    }

}
