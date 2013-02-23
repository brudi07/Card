package deck;

import card.Card;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Ben Rudi
 */
public class Deck {
    
    private ArrayList<Card> deck = new ArrayList();

    public ArrayList getDeck() {
        return deck;
    }

    public void setDeck(ArrayList deck) {
        this.deck = deck;
    } 
    
    public void add(Card card) {
        this.deck.add(card);
    }
    
    public void remove(Card card) {
        this.deck.remove(card);
    }
    
    public void remove(int num) {
        this.deck.remove(num);
    }
    
    public int size() {
        return this.deck.size();
    }
    
    public Card topCard() {
        return this.deck.get(0);
    }
    
    public Card getCard(int num) {
        return this.deck.get(num);
    }
    
    public void addAll(Deck deck) {
        this.deck.addAll(deck.getDeck());
    }
    
    public void clear() {
        this.deck.clear();
    }
    
    public void removeAll(Deck deck) {
        this.deck.removeAll(deck.getDeck());
    }
    
    public void shuffle() {
        Collections.shuffle(deck);
    }

    @Override
    public String toString() {
        return "Deck" + "\n" + deck;
    }
}
