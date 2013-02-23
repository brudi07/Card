package card;

import board.Board;
import player.Player;

/** Ability
 *
 * @author Ben Rudi
 * @version 0.1 02/23/13
 */
public final class Ability {
    
    private String name;
    private String description;
    
    /**
     * Ability constructor
     *
     */
    public Ability(String name, String description) {
        setName(name);
        setDescription(description);
    }

    /**
     * Get the ability name
     *
     * @param none
     * @return Name of the ability
     */
    public String getName() {
        return name;
    }

    /**
     * Set the ability name
     *
     * @param Name of the ability
     * @return void
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the description
     *
     * @param none
     * @return Card description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description
     *
     * @param Card description
     * @return void
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Check all of the abilities when card is played
     *
     * @param Player playing the card
     * @param Card being played
     * @return void
     */
    public void abilityCheck(Player player, Card card) {
        if (card.getAbility().getName().equals("DrawOne")) {
            draw(player, 1);
        }
    }
    
    /**
     * Draw cards from players deck
     *
     * @param Player drawing cards
     * @param Number of cards being drawn
     * @return Draw ability
     */
    public Ability draw(Player player, int num) {
        Ability draw = new Ability("Draw", "Draw card(s).");
        player.draw(player.getHand(), player.getPlayerDeck(), num);
        System.out.println(player + " drew " + num + " card(s).");
        return draw;
    }
    
    /**
     * Discards cards from players deck
     *
     * @param Player discarding cards
     * @param Number of cards being discarded
     * @return Discard ability
     */
    public Ability discard(Player player, int num) {
        Ability discard = new Ability("Discard", "Discard card(s).");
        player.discard(player.getHand(), player.getPlayerDiscard(), num);
        System.out.println(player + " discarded " + num + " card(s).");
        return discard;
    }
    
    /**
     * Banish cards from the board
     *
     * @param Player banishing cards
     * @param Board you are banishing from
     * @param Number of cards being banishing
     * @return Banish From Board ability
     */
    public Ability banishFromBoard(Player player, Board board, int num) {
        Ability banishFromBoard = new Ability("BanishFromBoard", "Banish a card from the center row.");
        player.discard(board.getCenterDeck(), board.getCenterVoid(), num);
        // Need to add a selection method to determine what card to banish
        System.out.println(player + " banished a card from the center row");
        return banishFromBoard;
    }
}
