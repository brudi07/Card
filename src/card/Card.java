package card;

import java.util.List;


/** Card
 *
 * @author Ben Rudi, Matt Bates
 * @version 0.1 02/23/13
 */
public final class Card {
    
    private String name;
    private int rune;
    private int battle;
    private int honor;
    private int runeCost;
    private int battleCost;
    private int honorWorth;
    private boolean banishable;
    private Faction faction;
    private Type type;
    private List<Ability> abilities;

    public enum Faction {
    	VOID,
    	LIFEBLOOD,
    	MECHANA,
    	ENLIGHTENED
    }
    public enum Type {
        HERO,
        MONSTER,
        CONSTRUCT
    }

//    public Card(String name, Type type, 
//            int rune, int battle, int honor, 
//            int runeCost, int battleCost, int honorWorth,
//            Ability... abilities) {
//        setName(name);
//        setType(type);
//        setRune(rune);
//        setBattle(battle);
//        setHonor(honor);
//        setRuneCost(runeCost);
//        setBattleCost(battleCost);
//        setHonorWorth(honorWorth);
//        setAbilities(abilities);
//    }
    
    /**
     * Get the card name
     *
     * @param none
     * @return Name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the card
     *
     * @param Card name
     * @return void
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the rune worth
     *
     * @param none
     * @return Rune worth
     */
    public int getRune() {
        return rune;
    }

    /**
     * Set the rune worth
     *
     * @param Rune worth
     * @return void
     */
    public void setRune(int rune) {
        this.rune = rune;
    }

    /**
     * Get the battle worth
     *
     * @param none
     * @return Battle worth
     */
    public int getBattle() {
        return battle;
    }

    /**
     * Set the battle worth
     *
     * @param Battle worth
     * @return void
     */
    public void setBattle(int battle) {
        this.battle = battle;
    }

    /**
     * Get the honor worth
     *
     * @param none
     * @return Honor worth
     */
    public int getHonor() {
        return honor;
    }

    /**
     * Set the honor worth
     *
     * @param Honor worth
     * @return void
     */
    public void setHonor(int honor) {
        this.honor = honor;
    }

    /**
     * Get the rune cost
     *
     * @param none
     * @return Rune cost
     */
    public int getRuneCost() {
        return runeCost;
    }

    /**
     * Set the rune cost
     *
     * @param Rune cost
     * @return void
     */
    public void setRuneCost(int runeCost) {
        this.runeCost = runeCost;
    }

    /**
     * Get the battle cost
     *
     * @param none
     * @return Battle cost
     */
    public int getBattleCost() {
        return battleCost;
    }

    /**
     * Set the battle cost
     *
     * @param Battle cost
     * @return void
     */
    public void setBattleCost(int battleCost) {
        this.battleCost = battleCost;
    }

    /**
     * Get the honor worth
     *
     * @param none
     * @return Honor worth
     */
    public int getHonorWorth() {
        return honorWorth;
    }

    /**
     * Set the honor worth
     *
     * @param Honor worth
     * @return void
     */
    public void setHonorWorth(int honorWorth) {
        this.honorWorth = honorWorth;
    }

    /**
     * Get the card type
     *
     * @param none
     * @return Card type (Hero, Construct, Monster)
     */
    public Type getCardType() {
        return type;
    }

    /**
     * Set the card type
     *
     * @param Card type (Hero, Construct, Monster)
     * @return void
     */
    public void setType(Type type) {
        this.type = type;
    }

    public void setFaction(Faction faction){
    	this.faction = faction;
    }
    
    public Faction getFaction(){
    	return faction;
    }
    
    public void setBanishable(boolean banishable){
    	this.banishable = banishable;
    }
    
    public boolean getBanishable(){
    	return banishable;
    }
    
    /**
     * Get ability
     *
     * @param none
     * @return Ability
     */
    public List<Ability> getAbilities() {
        return abilities;
    }

    /**
     * Set ability
     *
     * @param Ability
     * @return void
     */
    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    /**
     * Card to String
     *
     * @param none
     * @return String of all the card elements
     */
    @Override
    public String toString() {
        return name + "\n" + faction + " " + type + "\n" + 
                "Rune Cost: " + runeCost + "\n" +
                "Battle Cost: " + battleCost + "\n" +
                "Rune: " + rune + "\n" +
                "Battle: " + battle + "\n" +
                "Honor: " + honor + "\n" +
                "Honor Worth: " + honorWorth + "\n" +
                "Abilities: " + abilities;
    }

}
