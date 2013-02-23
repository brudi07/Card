package player;

import board.Board;
import card.Card;
import deck.Deck;

/**
 *
 * @author Ben Rudi
 */
public class Player {

    private Deck playerDeck = new Deck();
    private Deck playerDiscard = new Deck();
    private Deck hand = new Deck();
    private int honorTotal;
    private int runeTotal;
    private int battleTotal;

    public Deck getPlayerDeck() {
        return playerDeck;
    }

    public void setPlayerDeck(Deck playerDeck) {
        this.playerDeck = playerDeck;
    }

    public Deck getPlayerDiscard() {
        return playerDiscard;
    }

    public void setPlayerDiscard(Deck playerDiscard) {
        this.playerDiscard = playerDiscard;
    }

    public Deck getHand() {
        return hand;
    }

    public void setHand(Deck hand) {
        this.hand = hand;
    }

    public int getHonorTotal() {
        return honorTotal;
    }

    public void setHonorTotal(int honorTotal) {
        this.honorTotal = honorTotal;
    }

    public int getRuneTotal() {
        return runeTotal;
    }

    public void setRuneTotal(int runeTotal) {
        this.runeTotal = runeTotal;
    }

    public int getBattleTotal() {
        return battleTotal;
    }

    public void setBattleTotal(int battleTotal) {
        this.battleTotal = battleTotal;
    }

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

    public void discard(Deck from, Deck discardDeck, int number) {
        for (int i = 0; i < number; i++) {
            Card topCard = from.topCard();
            discardDeck.add(topCard);
            from.remove(topCard);
        }
    }

    public void endOfTurn() {
        setRuneTotal(0);
        setBattleTotal(0);
        discard(getHand(), getPlayerDiscard(), getHand().size());
        draw(getHand(), getPlayerDeck(), 5);
    }

    public void play(Player player, Card card) {
        System.out.println("You played " + card.getName() + ".");
        int rune = player.getRuneTotal() + card.getRune();
        int battle = player.getBattleTotal() + card.getBattle();
        player.setRuneTotal(rune);
        player.setBattleTotal(battle);
        player.getHand().remove(card);
        player.getPlayerDiscard().add(card);
        card.getAbility().abilityCheck(player, card);
        System.out.println("Rune: " + player.getRuneTotal());
        System.out.println("Battle: " + player.getBattleTotal());
    }
    
    public void buy(Card card, Player player, Board board) {
        if (card.getRuneCost() <= player.getRuneTotal()) {
            player.getPlayerDiscard().add(card);
            board.getCenterRow().remove(card);
            board.getCenterRow().add(board.getCenterDeck().topCard());
            player.setRuneTotal(player.getRuneTotal() - card.getRuneCost());
            player.setBattleTotal(player.getBattleTotal() - card.getBattleCost());
            System.out.println("You bought " + card.getName());
        } else {
            System.out.println("You cannot buy that.");
        }
    }

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
