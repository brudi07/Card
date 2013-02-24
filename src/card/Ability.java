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
public class Ability {
	
	public enum Type {
		DRAW("transfer cards from player deck to hand"),
		BANISH_CENTER("transfer card from center deck to banish deck"),
		BANISH_DISCARD("transfer cards from player discard to banish deck"),
		DEFEAT_MONSTER("defeat any monster costing X or less"),
		AQUIRE_HERO("transfer any hero from center row costing X or less to purchased"),
		CHOICE("choose from multiple abilities");
		
		public final String description;
		private Type(String description){
			this.description = description;
		}
	}
	
	private final Type type;
	private boolean optional = false;
	private int value;
	private List<Ability> choices;
    
    public Ability(Type type){
    	this.type = type;
    }
    
    public Type getType(){
    	return type;
    }
    
    public String getDescription() {
        return type.description;
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
    	Method method = Ability.class.getMethod(getType().toString(),Player.class,Integer.TYPE);
    	method.invoke(this, source, getValue());
    }
    
    public void DRAW(Player player, int number){
    	draw(player,number);
    }
    
    public void BANISH_CENTER(Player player, int number){
    	banish(player,Main.board.getCenterRow(), number);
    }
    
    public void BANISH_DISCARD(Player player, int number){
    	banish(player,player.getDiscard(), number);
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
    public void banish(Player player, Deck deck, int number) {
        
        // Need to add a selection method to determine what card to banish
    	//TODO
        System.out.println(player + " banished a card from the center row");
    }
    
    public String toString(){
    	String toString = type.toString();
    	if (getType() == Type.CHOICE){
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
