package card;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import player.Player;
import board.Board;

/** Ability
 *
 * @author Ben Rudi, Matt Bates
 * @version 0.1 02/23/13
 */
@SuppressWarnings("unused")
public enum Ability {
	
	DRAW_ONE("transfer one card from player deck to hand"),
	DRAW_TWO("transfer two cards from player deck to hand"),
	BANISH_CENTER("transfer one card from center deck to banish deck"),
	BANISH_DISCARD("transfer one card from player discard to banish deck");
	
	private Ability(String description){
		this.description = description;
	}
    
    public final String description;

    /**
     * Get the description
     *
     * @param none
     * @return Card description
     */
    public String getDescription() {
        return description;
    }
    
    public void perform(Player source) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
    	Method method = Ability.class.getMethod(this.toString(),Player.class);
    	method.invoke(this, source);
    }
    
    private void DRAW_ONE(Player player){
    	
    }
    
    private void DRAW_TWO(Player player){
    	
    }
    
    private void BANISH_CENTER(Player player){
    	
    }
    
    private void BANISH_DISCARD(Player player){
    	
    }

    
    /**
     * Draw cards from players deck
     *
     * @param Player drawing cards
     * @param Number of cards being drawn
     * @return Draw ability
     */
    public void draw(Player player, int num) {
        player.draw(player.getHand(), player.getPlayerDeck(), num);
        System.out.println(player + " drew " + num + " card(s).");
    }
    
    /**
     * Discards cards from players deck
     *
     * @param Player discarding cards
     * @param Number of cards being discarded
     * @return Discard ability
     */
    public void discard(Player player, int num) {
        player.discard(player.getHand(), player.getPlayerDiscard(), num);
        System.out.println(player + " discarded " + num + " card(s).");
    }
    
    /**
     * Banish cards from the board
     *
     * @param Player banishing cards
     * @param Board you are banishing from
     * @param Number of cards being banishing
     * @return Banish From Board ability
     */
    public void banishFromBoard(Player player, Board board, int num) {
        player.discard(board.getCenterDeck(), board.getCenterVoid(), num);
        // Need to add a selection method to determine what card to banish
        System.out.println(player + " banished a card from the center row");
    }
    
    
}
