package deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import card.Card;

/** Deck
 *
 * @author Ben Rudi
 * @version 0.1 02/23/13
 */
public class Deck {

    private List<Card> deck = new ArrayList<Card>();

    /**
     * Return the deck
     *
     * @param none
     * @return Deck
     */
    public List<Card> getDeck() {
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
    public boolean add(Card card) {
       return  this.deck.add(card);
    }

    /**
     * Remove a card from the deck
     *
     * @param Card
     * @return void
     */
    public boolean remove(Card card) {
       return this.deck.remove(card);
    }

    /**
     * Remove a card from the deck
     *
     * @param Index of the card
     * @return void
     */
    public Card remove(int num) {
        return this.deck.remove(num);
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
    
    public Card draw(){
    	return getDeck().remove(0);
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
