package data_structure.common.model;

/**
 * Created by 48608 on 2017/12/6.
 */

public class Mahjong {
    public int suit;//筒，万，索
    public int rank;//点数 一  二  三

    public Mahjong(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "(" + this.suit + " " + this.rank + ")";
    }
}
