import java.awt.*;
import java.awt.image.ImageObserver;

public class BoardDrafter {

    Board board;
    Image sprites;
    ImageObserver iO;
    static final String textureFile = "resources/TetrisTex.png";

    public BoardDrafter(Board b) {
        board = b;
        sprites = Toolkit.getDefaultToolkit().createImage(textureFile); //"resources/icon_picture.png");

    }

    public void drawBoard(Graphics2D g)
    {
        for(int q = 0; q < board.boardHeight; q++)
            for(int w = 0; w < board.boardWidth; w++)
                if(board.map.charAt((q*board.boardWidth) + w) == '#')
                {
                    if ((w == 0 || w+1 == board.boardWidth) && (q != 0 && q+1 != board.boardHeight)) // ||
                        g.drawImage(
                                sprites, w*board.tileSize,q*board.tileSize,
                                w*board.tileSize+board.tileSize,q*board.tileSize+board.tileSize,
                                20*5, 0,20*6,20, iO);
                    else if ((w != 0 && w+1 != board.boardWidth) && (q == 0 || q+1 == board.boardHeight))  // --
                        g.drawImage(
                                sprites, w*board.tileSize,q*board.tileSize,
                                w*board.tileSize+board.tileSize,q*board.tileSize+board.tileSize,
                                20*4, 0,20*5,20, iO);
                    else if(q == 0 && w == 0) //top left
                        g.drawImage(
                                sprites, w*board.tileSize,q*board.tileSize,
                                w*board.tileSize+board.tileSize,q*board.tileSize+board.tileSize,
                                0, 0,20,20, iO);
                    else if (q == 0 && w + 1 == board.boardWidth) //top right
                        g.drawImage(
                                sprites, w*board.tileSize,q*board.tileSize,
                                w*board.tileSize+board.tileSize,q*board.tileSize+board.tileSize,
                                20, 0,20*2,20, iO);
                    else if (q +1 == board.boardHeight && w + 1 == board.boardWidth) //bottom right
                    g.drawImage(
                            sprites, w*board.tileSize,q*board.tileSize,
                            w*board.tileSize+board.tileSize,q*board.tileSize+board.tileSize,
                            3*20, 0,20*4,20, iO);
                    else //bottom left
                        g.drawImage(
                                sprites, w*board.tileSize,q*board.tileSize,
                                w*board.tileSize+board.tileSize,q*board.tileSize+board.tileSize,
                                2*20, 0,20*3,20, iO);
                }
                else if(board.map.charAt((q*board.boardWidth) + w) == '%')
                {
                    g.drawImage(
                            sprites, w*board.tileSize,q*board.tileSize,
                            w*board.tileSize+board.tileSize,q*board.tileSize+board.tileSize,
                            6*20, 0,20*7,20, iO);
                }
    }

}
