public class Score {

    private long score;
    private int combo;

    public Score() { score = 0; }

    public void addPoints(int new_combo)
    {
        score += (long) combo *new_combo*10 + new_combo* 10L;
    }
    public void resetCombo() { combo = 0;}
    public long getScore() { return score; }
    public void resetScore() { score = 0; }

}
