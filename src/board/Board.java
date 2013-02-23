package board;

import deck.Deck;

/**
 *
 * @author Ben Rudi
 */
public class Board {
    
    private int honorLeft = 60;
    private Deck centerDeck = new Deck();
    private Deck centerVoid = new Deck();
    private Deck centerRow = new Deck();
    private Deck mystic = new Deck();
    private Deck heavy = new Deck();
    private Deck cultist = new Deck();
    
    public Board() {
        
    }

    public int getHonorLeft() {
        return honorLeft;
    }

    public void setHonorLeft(int honorLeft) {
        this.honorLeft = honorLeft;
    }

    public Deck getCenterDeck() {
        return centerDeck;
    }

    public void setCenterDeck(Deck centerDeck) {
        this.centerDeck = centerDeck;
    }

    public Deck getCenterVoid() {
        return centerVoid;
    }

    public void setCenterVoid(Deck centerVoid) {
        this.centerVoid = centerVoid;
    }

    public Deck getCenterRow() {
        return centerRow;
    }

    public void setCenterRow(Deck centerRow) {
        this.centerRow = centerRow;
    }

    public Deck getMystic() {
        return mystic;
    }

    public void setMystic(Deck mystic) {
        this.mystic = mystic;
    }

    public Deck getHeavy() {
        return heavy;
    }

    public void setHeavy(Deck heavy) {
        this.heavy = heavy;
    }

    public Deck getCultist() {
        return cultist;
    }

    public void setCultist(Deck cultist) {
        this.cultist = cultist;
    }

}
