package card;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import main.Main;
import player.Player;
import deck.Deck;

/** Ability
 *
 * @author Ben Rudi, Matt Bates
 * @version 0.1 02/23/13
 */
@SuppressWarnings("unused")
public enum Ability {
	
	DRAW("transfer cards from player deck to hand"),
	BANISH_CENTER("transfer card from center deck to banish deck"),
	BANISH_DISCARD("transfer cards from player discard to banish deck"),
	DEFEAT_MONSTER("defeat any monster costing X or less"),
	AQUIRE_HERO("transfer any hero from center row costing X or less to purchased"),
	CHOICE("choose from multiple abilities");
	
	private Ability(String description){
		this.description = description;
	}
    
    private final String description;
    private boolean optional = false;
    private int value;
    private List<Ability> choices;

    /**
     * Get the description
     *
     * @param none
     * @return Card description
     */
    public String getDescription() {
        return description;
    }
    
    public void setOptoinal(boolean optional) {
        this.optional = optional;
    }
    public boolean getOptional(){
    	return optional;
    }
    
    public void setValue(int value) {
    	this.value = value;
    }
    public int getValue(){
    	return value;
    }
    
    public void setChoices(List<Ability> choices){
    	this.choices = choices;
    }
    public List<Ability> getChoices(){
    	return choices;
    }
    
    
    public void perform(Player source) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
    	Method method = Ability.class.getMethod(this.toString(),Player.class);
    	method.invoke(this, source);
    }
    
    public void DRAW_ONE(Player player){
    	draw(player,1);
    }
    
    public void DRAW_TWO(Player player){
    	draw(player,2);
    }
    
    public void BANISH_CENTER(Player player){
    	banish(player,Main.board.getCenterRow());
    }
    
    public void BANISH_DISCARD(Player player){
    	banish(player,player.getDiscard());
    }

    
    /**
     * Draw cards from players deck
     *
     * @param Player drawing cards
     * @param Number of cards being drawn
     * @return Draw ability
     */
    public void draw(Player player, int num) {
        player.draw(num);
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
        player.discard(num);
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
    public void banish(Player player, Deck deck) {
        
        // Need to add a selection method to determine what card to banish
    	//TODO
        System.out.println(player + " banished a card from the center row");
    }
    
    public String toString(){
    	String toString = super.toString();
    	if (this.equals(CHOICE)){
    		toString += "(";
    		String appender = "";
    		for (Ability ability: getChoices()){
    			toString += appender + ability;
				appender = ",";
    		}
    		toString += ")";
    	}
    	else {
    		toString += getValue();
    	}
    	toString += (getOptional()?"*":"");
    	return toString;
    }
    
    
}
