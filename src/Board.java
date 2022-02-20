public class Board
{
//    private String map_str =   "############" +
private String str_map =     "############" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "#          #" +
                            "############";
    public static int boardWidth = 12;
    public static int boardHeight = 22;
    public static int tileSize = 30; //px DEFAULT = 20
    public char[] map;
    public Board() { map = str_map.toCharArray(); }

    public int checkLines() {
        int len = 0;
        int pos = -1;
        for (int q = 0; q < map.length; q++)
        {       if (map[q] == '%' && pos == -1 && (q-1)%boardWidth == 0) {
                len++;
                pos = q;
            }
            else if (map[q] == '%' && pos != -1)
                len++;
            else {
                len = 0;
                pos = -1;
            }
            if (len == boardWidth-2)
            break;
        }
            return pos;
    }


}
