package player;

import java.lang.reflect.InvocationTargetException;

import main.Main;
import board.Board;
import card.Ability;
import card.Card;
import card.Card.Type;
import deck.Deck;

/** Player
 *
 * @author Ben Rudi
 * @version 0.1 02/23/13
 */
public class Player {

	private String name;
    private Deck deck = new Deck();
    private Deck discard = new Deck();
    private Deck hand = new Deck();
    private Deck constructs = new Deck();
    private Deck played = new Deck();
    private Deck purchased = new Deck();
    private int honorTotal;
    private int runeTotal;
    private int battleTotal;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
     * Get the player's deck
     *
     * @param none
     * @return Player deck
     */
    public Deck getDeck() {
        return deck;
    }
    public Deck getConstructs() {
        return constructs;
    }
    public Deck getPlayed() {
        return played;
    }
    public Deck getPurchased() {
        return purchased;
    }

    /**
     * Set the player's deck
     *
     * @param Player deck
     * @return void
     */
    public void setPlayerDeck(Deck playerDeck) {
        this.deck = playerDeck;
    }

    /**
     * Get the player's discard deck
     *
     * @param none
     * @return Player discard deck
     */
    public Deck getDiscard() {
        return discard;
    }

    /**
     * Set the player's discard deck
     *
     * @param Player discard deck
     * @return void
     */
    public void setPlayerDiscard(Deck playerDiscard) {
        this.discard = playerDiscard;
    }

    /**
     * Get the player's hand
     *
     * @param none
     * @return Player hand
     */
    public Deck getHand() {
    	return hand;
    }

    /**
     * Set the player's hand
     *
     * @param Player hand
     * @return void
     */
    public void setHand(Deck hand) {
        this.hand = hand;
    }

    /**
     * Get the player's honor total
     *
     * @param none
     * @return Honor total
     */
    public int getHonorTotal() {
        return honorTotal;
    }

    /**
     * Set the player's honor total
     *
     * @param Honor total
     * @return void
     */
    public void setHonorTotal(int honorTotal) {
        this.honorTotal = honorTotal;
    }
    public int addHonor(int honor){
    	return honorTotal+=honor;
    }

    /**
     * Get the player's rune total
     *
     * @param none
     * @return Rune total
     */
    public int getRuneTotal() {
        return runeTotal;
    }

    /**
     * Set the player's rune total
     *
     * @param Rune total
     * @return void
     */
    public void setRuneTotal(int runeTotal) {
        this.runeTotal = runeTotal;
    }
    
    public int addRune(int runes){
    	return runeTotal+= runes;
    }

    /**
     * Get the player's battle total
     *
     * @param none
     * @return Battle total
     */
    public int getBattleTotal() {
        return battleTotal;
    }
    public int addBattle(int battle){
    	return battleTotal += battle;
    }

    /**
     * Set the player's battle total
     *
     * @param Battle total
     * @return void
     */
    public void setBattleTotal(int battleTotal) {
        this.battleTotal = battleTotal;
    }

    public void draw(int number){
    	Main.draw(getDeck(), getDiscard(), getHand(), number);
    }

    /**
     * Discard card(s) from deck
     *
     * @param Number of cards to discard
     * @return void
     */
    public void discard(int number) {
        for (int i = 0; i < number && getHand().size() > i; i++) {
            Card card = getHand().remove(i);
            getDiscard().add(card);
        }
    }
    public void discard(Deck deck){
    	getDiscard().addAll(deck);
    	deck.clear();
    }
    
    public void banish(Deck deck, Card card){
    	deck.remove(card);
    	Main.board.getCenterVoid().add(card);
    }

    /**
     * Cleanup at the end of a turn
     *
     * @param none
     * @return void
     */
    public void endOfTurn() {
        // Reset all of the players totals accrued this turn
        setRuneTotal(0);
        setBattleTotal(0);
        
        // Discard any cards remaining in the player's hand,purchased cards, and played cards
        discard( getHand() );
        discard(getPurchased());
        discard(getPlayed());
        
        // Draw five new cards
        draw(5);
    }

    /**
     * Play a card from player's hand
     *
     * @param Player playing a card
     * @param Card being played
     * @return void
     */
    public void play( Card card) {
        System.out.println(getName() + " played " + card.getName() + ".");
        
        // Remove the card from the player's hand
        getHand().remove(card);

        // Add the values from the card to the player's total
        addRune(card.getRune());
        addBattle(card.getBattle());
        
        // Check the ability list and perform each ability
        for (Ability ability : card.getAbilities()){
        	try {
				ability.perform(this);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        if (card.getCardType().equals(Type.CONSTRUCT)) {
            // Add card to the construct deck
            getConstructs().add(card);
        } else {
            // Add the card to the played deck
            getPlayed().add(card);
        }
        
        System.out.println("Rune: " + getRuneTotal());
        System.out.println("Battle: " + getBattleTotal());
    }
    
    
    /**
     * Buy a card from the board
     *
     * @param Player buying a card
     * @param Card being played
     * @param Board
     * @return void
     */
    public void buy(Player player, Card card, Board board) {
        // Check to see if the player can afford the card
        if (card.getRuneCost() <= player.getRuneTotal() && card.getBattleCost() <= player.getBattleTotal()) {
            if (card.getCardType().equals(Type.HERO) || card.getCardType().equals(Type.CONSTRUCT)) {
                // Add the card to the player's discard
                player.getPurchased().add(card);
                // Remove the card from the center row
                board.getCenterRow().remove(card);
                // Replace the card in the center row
                board.getCenterRow().add(board.getCenterDeck().topCard());
                System.out.println("You acquired " + card.getName());
            } else {
                // Remove the card from the center row
                board.getCenterRow().remove(card);
                // Replace the card in the center row
                board.getCenterRow().add(board.getCenterDeck().topCard());
                System.out.println("You defeated " + card.getName());
            }
            // Remove the card value from the player's totals
            player.setRuneTotal(player.getRuneTotal() - card.getRuneCost());
            player.setBattleTotal(player.getBattleTotal() - card.getBattleCost());
            player.setHonorTotal(player.getHonorTotal() + card.getHonor());
        } else {
            System.out.println("You cannot buy that.");
        }
    }

    /**
     * Buy a card from the side decks
     *
     * @param Player buying a card
     * @param Deck being drawn from
     * @return void
     */
    public void buy(Player player, Deck deck) {
        
        if (deck.size() > 0) {
            Card card = deck.topCard();
            // Check to see if the player can afford the card
            if (card.getRuneCost() <= player.getRuneTotal() && card.getBattleCost() <= player.getBattleTotal()) {
                if (card.getCardType().equals(Type.HERO) || card.getCardType().equals(Type.CONSTRUCT)) {
                    // Add the card to the player's discard
                    player.getPurchased().add(card);
                    // Remove the card from the deck
                    deck.remove(card);
                    System.out.println("You acquired " + card.getName());
                } else {               
                    // Remove the card from the deck
                    deck.remove(card);
                    System.out.println("You defeated " + card.getName());
                }
                // Remove the card value from the player's totals
                player.setRuneTotal(player.getRuneTotal() - card.getRuneCost());
                player.setBattleTotal(player.getBattleTotal() - card.getBattleCost());
                player.setHonorTotal(player.getHonorTotal() + card.getHonor());
            } else {
                System.out.println("You cannot buy that.");
            }
        } else {
            System.out.println("Deck is out of cards.");
        }
    }

    /**
     * Get just the cards name in a deck
     *
     * @param Deck being read
     * @return String of all the cards names from the deck
     */
    public StringBuilder cardsByName(Deck deck) {
        StringBuilder cards = new StringBuilder();
        for (int i = 0; i < deck.size(); i++) {
            cards.append(deck.getCard(i).getName());
            if (i != deck.size() - 1) {
                cards.append(", ");
            }
        }
        return cards;
    }
}
