package player;

import java.lang.reflect.InvocationTargetException;

import board.Board;
import card.Ability;
import card.Card;
import deck.Deck;

/** Player
 *
 * @author Ben Rudi
 * @version 0.1 02/23/13
 */
public class Player {

	private String name;
    private Deck playerDeck = new Deck();
    private Deck playerDiscard = new Deck();
    private Deck hand = new Deck();
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
    public Deck getPlayerDeck() {
        return playerDeck;
    }

    /**
     * Set the player's deck
     *
     * @param Player deck
     * @return void
     */
    public void setPlayerDeck(Deck playerDeck) {
        this.playerDeck = playerDeck;
    }

    /**
     * Get the player's discard deck
     *
     * @param none
     * @return Player discard deck
     */
    public Deck getPlayerDiscard() {
        return playerDiscard;
    }

    /**
     * Set the player's discard deck
     *
     * @param Player discard deck
     * @return void
     */
    public void setPlayerDiscard(Deck playerDiscard) {
        this.playerDiscard = playerDiscard;
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

    /**
     * Draw card(s) from deck
     *
     * @param Deck you are drawing to
     * @param Deck you are drawing from
     * @param Number of cards to draw
     * @return void
     */
    public void draw(Deck to, Deck drawDeck, int number) {
        for (int i = 0; i < number; i++) {
            
            // Check if drawing from playerDeck and it has no cards
            // If so, shuffle the discardDeck and add it to playerDeck
            if (getPlayerDeck().size() == 0) {
                System.out.println("Shuffling discard into deck.");
                getPlayerDiscard().shuffle();
                getPlayerDeck().addAll(getPlayerDiscard());
                getPlayerDiscard().clear();
            }
            
            Card topCard = drawDeck.topCard();
            to.add(topCard);
            drawDeck.remove(topCard);
        }
    }

    /**
     * Discard card(s) from deck
     *
     * @param Deck you are discarding from
     * @param Deck you are discarding to
     * @param Number of cards to discard
     * @return void
     */
    public void discard(Deck from, Deck discardDeck, int number) {
        for (int i = 0; i < number; i++) {
            Card topCard = from.topCard();
            discardDeck.add(topCard);
            from.remove(topCard);
        }
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
        // Discard any cards remaining in the player's hand
        discard(getHand(), getPlayerDiscard(), getHand().size());
        // Draw five new cards
        draw(getHand(), getPlayerDeck(), 5);
    }

    /**
     * Play a card from player's hand
     *
     * @param Player playing a card
     * @param Card being played
     * @return void
     */
    public void play(Player player, Card card) {
        System.out.println(player.getName() + " played " + card.getName() + ".");
        
        // Remove the card from the player's hand
        player.getHand().remove(card);

        // Add the values from the card to the player's total
        player.addRune(card.getRune());
        player.addBattle(card.getBattle());
        
        // Check the ability list and perform each ability
        for (Ability ability : card.getAbilities()){
        	try {
				ability.perform(player);
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
        
        // Add the card to the discard pile
        player.getPlayerDiscard().add(card);
        
        System.out.println("Rune: " + player.getRuneTotal());
        System.out.println("Battle: " + player.getBattleTotal());
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
        if (card.getRuneCost() <= player.getRuneTotal()) {
            // Add the card to the player's discard
            player.getPlayerDiscard().add(card);
            // Remove the card from the center row
            board.getCenterRow().remove(card);
            // Replace the card in the center row
            board.getCenterRow().add(board.getCenterDeck().topCard());
            // Remove the card value from the player's totals
            player.setRuneTotal(player.getRuneTotal() - card.getRuneCost());
            player.setBattleTotal(player.getBattleTotal() - card.getBattleCost());
            System.out.println("You bought " + card.getName());
        } else {
            System.out.println("You cannot buy that.");
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
