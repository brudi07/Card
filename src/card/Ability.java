package card;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
		DISCARD("transfer cards from hand to discard"),
		GAIN_RUNE("gain X runes"),
		GAIN_BATTLE("gain X battle points"),
		GAIN_HONOR("gain X honor"),
		SPEND_RUNE("spend X runes"),
		BANISH_CENTER("transfer card from center deck to banish deck"),
		BANISH_DISCARD("transfer cards from player discard to banish deck"),
		DEFEAT_MONSTER("defeat any monster costing X or less"),
		AQUIRE_HERO("transfer any hero from center row costing X or less to purchased"),
		ADDITIONAL_TURN("take an additional turn after this one"),
		COPY_HERO_EFFECT("copy the effect of a hero played"),
		PAY_LESS_CONSTRUCT("pay X less for a construct"),
		DESTROY_CONSTRUCT("force a player to banish a construct they control"),
		TAKE_PLAYER_CARD("transfer X cards from a player's hand to ?"),//TODO where?
		CHAIN("X must succeed in order for Y"),
		CHOICE("choose from multiple abilities");
		
		
		public final String description;
		private Type(String description){
			this.description = description;
		}
	}
	
	private final Type type;
	private boolean optional = false;
	private int value = 0;
	private List<Ability> abilities;
    
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
    
    public void setAbilities(List<Ability> abilities){
    	this.abilities = abilities;
    }
    public List<Ability> getAbilities(){
    	return abilities;
    }
    
    public boolean perform(Player source) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
    	boolean perform = true;
    	if (getOptional()){
    		List<String> options = new ArrayList<String>();
    		options.add("Yes");
    		options.add("No");
    		System.out.println(getType()+String.valueOf(getValue())+"?");
    		int choice = Main.pick(options, null);
    		perform = choice == 1;
    	}
    	if (perform) {
	    	Method method = Ability.class.getMethod(getType().toString(),Player.class,int.class);
	    	return (Boolean) method.invoke(this, source, getValue());
    	}
    	return false;
    }
    
    public boolean DRAW(Player player, int number){
    	return draw(player,number);
    }
    
    public boolean DISCARD(Player player, int number){
    	return discard(player,number);
    }
    
    public boolean GAIN_RUNE(Player player, int number){
    	player.addRune(number);
    	return true;
    }
    
    public boolean GAIN_BATTLE(Player player, int number){
    	player.addBattle(number);
    	return true;
    }
    
    public boolean GAIN_HONOR(Player player, int number){
    	player.addHonor(number);
    	return true;
    }
    
    public boolean SPEND_RUNE(Player player, int number){
    	return player.spendRunes(number);
    }
    
    public boolean AQUIRE_HERO(Player player, int number){
    	//TODO
    	return false;
    }
    
    public boolean BANISH_CENTER(Player player, int number){
    	return banish(player,Main.board.getCenterRow(), number);
    }
    
    public boolean BANISH_DISCARD(Player player, int number){
    	return banish(player,player.getDiscard(), number);
    }
    
    public boolean DEFEAT_MONSTER(Player player, int number){
    	//TODO transfer monster from center to banish pile and have player perform monster abilities
    	return false;
    }
    
    public boolean ADDITIONAL_TURN(Player player, int number){
    	//TODO add current player into list of turns at current index
    	return false;
    }

    public boolean COPY_HERO_EFFECT(Player player, int number){
    	//TODO choose a hero from played deck and copy their effects
    	return false;
    }
    
    public boolean PAY_LESS_CONSTRUCT(Player player, int number){
    	//TODO
    	return false;
    }
    
    public boolean DESTROY_CONSTRUCT(Player player, int number){
    	//TODO transfer card from opponent's construct deck to banish deck
    	return false;
    }

    public boolean TAKE_PLAYER_CARD(Player player, int number){
    	//TODO transfer card from opponent's hand to ?
    	return false;
    }
    
    public boolean CHOICE(Player player, int number) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
    	List<String> options = new ArrayList<String>();
    	List<String> descriptions = new ArrayList<String>();
    	for (Ability ability :getAbilities()){
    		options.add(ability.toString() + ability.getValue());
    		descriptions.add(ability.getDescription());
    	}
    	int choice = Main.pick(options,descriptions);
    	
    	return getAbilities().get(choice-1).perform(player);//TODO TEST
    }
    
    public boolean CHAIN(Player player, int number) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
    	
    	for (Ability ability : getAbilities()){
    		if (!ability.perform(player)) return false;
    	}
    	return true;//TODO TEST
    }
    
    
    
    /**
     * Draw cards from players deck
     *
     * @param Player drawing cards
     * @param Number of cards being drawn
     * @return Draw ability
     */
    public boolean draw(Player player, int num) {
        player.draw(num);
        return true;
    }
    
    /**
     * Discards cards from players deck
     *
     * @param Player discarding cards
     * @param Number of cards being discarded
     * @return Discard ability
     */
    public boolean discard(Player player, int num) {
        return player.discard(num);
    }
    
    /**
     * Banish cards from the board
     *
     * @param Player banishing cards
     * @param Board you are banishing from
     * @param Number of cards being banishing
     * @return Banish From Board ability
     */
    public boolean banish(Player player, Deck deck, int number) {
        
        // Need to add a selection method to determine what card to banish
    	//TODO
        System.out.println(player + " banished a card from the center row");
        return false;
    }
    
    public String toString(){
    	String toString = type.toString();
    	if (getType() == Type.CHOICE || getType() == Type.CHAIN){
    		toString += "(";
    		String appender = "";
    		for (Ability ability: getAbilities()){
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
