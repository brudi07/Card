package deck;

import card.Card;
import java.util.ArrayList;
import java.util.Collections;

/** Deck
 *
 * @author Ben Rudi
 * @version 0.1 02/23/13
 */
public class Deck {

    private ArrayList<Card> deck = new ArrayList();

    /**
     * Return the deck
     *
     * @param none
     * @return Deck
     */
    public ArrayList getDeck() {
        return deck;
    }

    /**
     * Set the deck
     *
     * @param Deck
     * @return void
     */
    public void setDeck(ArrayList deck) {
        this.deck = deck;
    }

    /**
     * Add a card to the deck
     *
     * @param Card
     * @return void
     */
    public void add(Card card) {
        this.deck.add(card);
    }

    /**
     * Remove a card from the deck
     *
     * @param Card
     * @return void
     */
    public void remove(Card card) {
        this.deck.remove(card);
    }

    /**
     * Remove a card from the deck
     *
     * @param Index of the card
     * @return void
     */
    public void remove(int num) {
        this.deck.remove(num);
    }

    /**
     * Return the size of the deck
     *
     * @param none
     * @return Deck size
     */
    public int size() {
        return this.deck.size();
    }

    /**
     * Get the top card of the deck
     *
     * @param none
     * @return Card on top of the deck
     */
    public Card topCard() {
        return this.deck.get(0);
    }

    /**
     * Get card at index
     *
     * @param Index of the card
     * @return Card at index
     */
    public Card getCard(int num) {
        return this.deck.get(num);
    }

    /**
     * Add all to the deck
     *
     * @param Deck
     * @return void
     */
    public void addAll(Deck deck) {
        this.deck.addAll(deck.getDeck());
    }

    /**
     * Clear the deck
     *
     * @param none
     * @return void
     */
    public void clear() {
        this.deck.clear();
    }

    /**
     * Shuffle the deck
     *
     * @param none
     * @return void
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Deck to String
     *
     * @param none
     * @return List of all the deck elements
     */
    @Override
    public String toString() {
        return "Deck" + "\n" + deck;
    }
}
