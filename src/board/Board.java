package board;

import deck.Deck;

/** Board
 *
 * @author Ben Rudi
 * @version 0.1 02/23/13
 */
public class Board {
    
    private int honorLeft = 60;
    private Deck centerDeck = new Deck();
    private Deck centerVoid = new Deck();
    private Deck centerRow = new Deck();
    private Deck mystic = new Deck();
    private Deck heavy = new Deck();
    private Deck cultist = new Deck();

    /**
     * Get honor left to win the game
     *
     * @param none
     * @return Number of honor left to win the game
     */
    public int getHonorLeft() {
        return honorLeft;
    }

    /**
     * Set honor left to win the game
     *
     * @param Number of honor left to win the game
     * @return void
     */
    public void setHonorLeft(int honorLeft) {
        this.honorLeft = honorLeft;
    }

    /**
     * Get the center deck
     *
     * @param none
     * @return Center deck
     */
    public Deck getCenterDeck() {
        return centerDeck;
    }

    /**
     * Set the center deck
     *
     * @param Center deck
     * @return void
     */
    public void setCenterDeck(Deck centerDeck) {
        this.centerDeck = centerDeck;
    }

    /**
     * Get the center void
     *
     * @param none
     * @return Center void
     */
    public Deck getCenterVoid() {
        return centerVoid;
    }

    /**
     * Set the center void
     *
     * @param none
     * @return Center void
     */
    public void setCenterVoid(Deck centerVoid) {
        this.centerVoid = centerVoid;
    }

    /**
     * Get the center row
     *
     * @param none
     * @return Center row
     */
    public Deck getCenterRow() {
        return centerRow;
    }

    /**
     * Set the center row
     *
     * @param Center row
     * @return void
     */
    public void setCenterRow(Deck centerRow) {
        this.centerRow = centerRow;
    }

    /**
     * Get the mysitc deck
     *
     * @param none
     * @return Mystic deck
     */
    public Deck getMystic() {
        return mystic;
    }

    /**
     * Set the mystic deck
     *
     * @param Mystic deck
     * @return void
     */
    public void setMystic(Deck mystic) {
        this.mystic = mystic;
    }

    /**
     * Get the heavy militia deck
     *
     * @param none
     * @return Heavy militia deck
     */
    public Deck getHeavy() {
        return heavy;
    }

    /**
     * Set the heavy milita deck
     *
     * @param Heavy militia deck
     * @return void
     */
    public void setHeavy(Deck heavy) {
        this.heavy = heavy;
    }

    /**
     * Get the cultist deck
     *
     * @param none
     * @return Cultist deck
     */
    public Deck getCultist() {
        return cultist;
    }

    /**
     * Set the cultist deck
     *
     * @param Cultist deck
     * @return void
     */
    public void setCultist(Deck cultist) {
        this.cultist = cultist;
    }
}
