package card;

/**
 *
 * @author Ben Rudi
 */
public final class Card {
    
    private String name;
    private int rune;
    private int battle;
    private int honor;
    private int runeCost;
    private int battleCost;
    private int honorWorth;
    private CardType cardType;
    private Ability ability;

    public enum CardType {
        HERO,
        MONSTER,
        CONSTRUCT
    }
    
    public Card() {
        
    }
    
    public Card(String name, CardType cardType, 
            int rune, int battle, int honor, 
            int runeCost, int battleCost, int honorWorth) {
        setName(name);
        setCardType(cardType);
        setRune(rune);
        setBattle(battle);
        setHonor(honor);
        setRuneCost(runeCost);
        setBattleCost(battleCost);
        setHonorWorth(honorWorth);
    }
    
    public Card(String name, CardType cardType, 
            int rune, int battle, int honor, 
            int runeCost, int battleCost, int honorWorth,
            Ability ability) {
        setName(name);
        setCardType(cardType);
        setRune(rune);
        setBattle(battle);
        setHonor(honor);
        setRuneCost(runeCost);
        setBattleCost(battleCost);
        setHonorWorth(honorWorth);
        setAbility(ability);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRune() {
        return rune;
    }

    public void setRune(int rune) {
        this.rune = rune;
    }

    public int getBattle() {
        return battle;
    }

    public void setBattle(int battle) {
        this.battle = battle;
    }

    public int getHonor() {
        return honor;
    }

    public void setHonor(int honor) {
        this.honor = honor;
    }

    public int getRuneCost() {
        return runeCost;
    }

    public void setRuneCost(int runeCost) {
        this.runeCost = runeCost;
    }

    public int getBattleCost() {
        return battleCost;
    }

    public void setBattleCost(int battleCost) {
        this.battleCost = battleCost;
    }

    public int getHonorWorth() {
        return honorWorth;
    }

    public void setHonorWorth(int honorWorth) {
        this.honorWorth = honorWorth;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    @Override
    public String toString() {
        return name + "\n" + cardType + "\n" + 
                "Rune Cost: " + runeCost + "\n" +
                "Battle Cost: " + battleCost + "\n" +
                "Rune: " + rune + "\n" +
                "Battle: " + battle + "\n" +
                "Honor: " + honor + "\n" +
                "Honor Worth: " + honorWorth + "\n" +
                "Ability: " + ability;
    }

}
