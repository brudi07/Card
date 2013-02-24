package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import player.Player;
import board.Board;
import card.Card;
import deck.Deck;
import deck.DeckBuilder;

/**
 *
 * @author Ben Rudi, Matt Bates
 */
public class Main {
	
	public static Board board;

	public static void draw(Deck source, Deck backup, Deck destination, int number){
		for (int i = 0; i < number; i++) {

            if (source.size() == 0) {
                System.out.println("Shuffling backup into deck.");
                backup.shuffle();
                source.addAll(backup);
                backup.clear();
            }
            
            Card card = source.draw();
            destination.add(card);
        }
	}
	
    public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException {
        DeckBuilder db = new DeckBuilder();
        Boolean playerOneTurn = true;
        Scanner scanner = new Scanner(System.in);

        board = new Board();
        Player playerOne = new Player();
        playerOne.setName("Ben");
        playerOne.setPlayerDeck(db.deckReader("resources/PlayerDeck.xml"));
        board.setCenterDeck(db.deckReader("resources/CenterDeck.xml"));
        board.setMystic(db.deckReader("resources/Mystic.xml"));
        board.setHeavy(db.deckReader("resources/Heavy.xml"));
        board.setCultist(db.deckReader("resources/Cultist.xml"));

        playerOne.getDeck().shuffle();
        playerOne.draw(5);

        board.getCenterDeck().shuffle();
        board.draw(6);
        
        List<String> options;
    	int choice;

        while (board.getHonorLeft() > 0) {
            if (playerOneTurn) {
                
                String playerOption = "";
                while (!playerOption.equals("3")) {
                	System.out.println("Cards in hand: " + playerOne.cardsByName(playerOne.getHand()));

                	options = new ArrayList<String>();
                	options.add(0,"Play a card");
                	options.add(1,"Buy/Kill a card");
                	options.add(2,"End turn");
                    choice = pick(options);

                    switch (choice){
	                    case 1:
	                    	if (playerOne.getHand().size() > 0) {
	                            System.out.println("Which card would you like to play?");
	                            choice = pick(playerOne.getHand());
	                            if (choice > 0) {
	                            	playerOne.play(playerOne.getHand().getCard(choice-1));
	                            }
	                        } else {
	                            System.out.println("No cards left in hand.");
	                        }
	                    	break;
	                    case 2:
	                    	System.out.println("Which card would you like to buy/kill?");
	                        System.out.println("Rune: " + playerOne.getRuneTotal());
	                        System.out.println("Battle: " + playerOne.getBattleTotal());
	                        
	                        options = deckToOptions(board.getCenterRow());
	                    	options.add(6,board.getMystic().topCard().getName());
	                    	options.add(7,board.getHeavy().topCard().getName());
	                    	options.add(8,board.getCultist().topCard().getName());
	                        
	                    	choice = pick(options);
	                        
	                        switch (choice) {
                        		case 0: break;
                        		case 7: playerOne.buy(playerOne, board.getMystic()); break;
                        		case 8: playerOne.buy(playerOne, board.getHeavy()); break;
                        		case 9: playerOne.buy(playerOne, board.getCultist()); break;
                        		default: playerOne.buy(playerOne, board.getCenterRow().getCard(choice-1), board); break;
                        	}
	                    	break;
	                    case 3:
	                    	System.out.println("End of turn.");
	                        playerOne.endOfTurn();
	                        playerOneTurn = false;
	                    	break;
                    	default: 
                    		System.out.println("Please enter a valid option.");
                    }
                    
                } // player out of cards

            } else { // end of player one turn
                playerOneTurn = true;
            } // start on player one turn again!
        } // end of game - no honor left
    }
    
    public static int pick(List<String> options){
    	int result = -1;
    	Scanner scanner = new Scanner(System.in);
    	int idx = 1;
    	System.out.println("0: Back");
    	for (String option : options){
    		System.out.println(idx + ": " + option);
    		idx++;
    	}
    	while (result == -1){
    		try{
    			result = Integer.parseInt(scanner.nextLine());
    			if (result > options.size()) {
    				result = -1;
    				throw new Exception();
    			}
    		}
    		catch (Exception e){
    			System.out.println("invalid input");
    		}
    	}
    	return result;
    }
    
    public static List<String> deckToOptions(Deck deck){
    	List<String> options = new ArrayList<String>();
    	for (Card card : deck.getDeck()){
    		options.add(card.getName());
    	}
    	return options;
    }
    
    public static int pick(Deck deck){
    	return pick(deckToOptions(deck));
    }
}
