package card;

import board.Board;
import player.Player;

/**
 *
 * @author Ben Rudi
 */
public final class Ability {
    
    private String name;
    private String description;
    
    public Ability() {
        
    }
    
    public Ability(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void abilityCheck(Player player, Card card) {
        if (card.getAbility().getName().equals("DrawOne")) {
            draw(player, 1);
        }
    }
    
    public Ability draw(Player player, int num) {
        Ability draw = null;
        player.draw(player.getHand(), player.getPlayerDeck(), num);
        System.out.println("You drew " + num + " card(s).");
        return draw;
    }
    
    public void discard(Player player, int num) {
        player.discard(player.getHand(), player.getPlayerDiscard(), num);
    }
    
    public void banishFromBoard(Player player, Board board, int num) {
        player.discard(board.getCenterDeck(), board.getCenterVoid(), num);
    }
    
    public void banishFromHand(Player player, Board board, int num) {
        player.discard(player.getHand(), board.getCenterVoid(), num);
    }

}
