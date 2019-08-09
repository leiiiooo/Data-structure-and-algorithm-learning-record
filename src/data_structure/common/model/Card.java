package data_structure.common.model;

/**
 * Created by 48608 on 2017/11/27.
 */

public class Card implements Comparable {
    public int pokerColors;//花色
    public int cardPoints;//点数

    public Card(int pokerColors, int cardPoints) {
        this.pokerColors = pokerColors;
        this.cardPoints = cardPoints;
    }

    //提供一个方法，用来比较对象的大小
    @Override
    public int compareTo(Object o) {
        Card c = (Card) o;
        if (this.cardPoints > c.cardPoints) {
            return 1;
        } else if (this.cardPoints < c.cardPoints) {
            return -1;
        }
        if (this.pokerColors > c.pokerColors) {
            return 1;
        } else if (this.pokerColors < c.pokerColors) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Card{" +
                "pokerColors=" + pokerColors +
                ", cardPoints=" + cardPoints +
                '}';
    }

}








