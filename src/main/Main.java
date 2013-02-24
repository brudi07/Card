package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import player.Player;
import board.Board;
import card.Ability;
import card.Card;
import card.Card.Type;
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
	
    public static void main(String args[]) {
        DeckBuilder db = new DeckBuilder();
        Boolean playerOneTurn = true;
        Scanner scanner = new Scanner(System.in);
        
        board = new Board();
        Player playerOne = new Player();      

        Deck playerDeck = playerOne.getDeck();
        Deck playerHand = playerOne.getHand(); 
        Deck playerDiscard = playerOne.getDiscard();
        
        Deck centerDeck = db.deckReader("resources/deck.xml");
        Deck voidDeck = board.getCenterVoid();
        Deck centerRow = board.getCenterRow();
        
        Card apprentice = new Card("Apprentice", Type.HERO,
                1, 0, 0,
                0, 0, 0,
                null);

        Card militia = new Card("Militia", Type.HERO,
                0, 1, 0,
                0, 0, 0,
                null);

        Card mystic = new Card("Mystic", Type.HERO,
                3, 0, 0,
                3, 0, 1,
                null);
        
        Card heavy = new Card("Heavy", Type.HERO,
                0, 2, 0,
                2, 0, 1,
                null);
        
        Card initiate = new Card("Initiate", Type.HERO,
                0, 0, 0,
                1, 0, 1,
                Ability.DRAW_ONE);

        for (int i = 0; i < 8; i++) {
            playerOne.getDeck().add(apprentice);
        }
        for (int i = 0; i < 2; i++) {
            playerOne.getDeck().add(militia);
        }
        for (int i = 0; i < 5; i++) {
            board.getCenterDeck().add(mystic);
            board.getCenterDeck().add(initiate);
            board.getCenterDeck().add(heavy);
        }

        playerDeck.shuffle();
        playerOne.draw(5);

        centerDeck.shuffle();
        board.draw(6);

        while (board.getHonorLeft() > 0) {
            if (playerOneTurn) {
                
                String playerOption = "";
                while (!playerOption.equals("3")) {
                	System.out.println("Cards in hand: " + playerOne.cardsByName(playerHand));

                    System.out.println("1: Play a card.");
                    System.out.println("2: Buy a card.");
                    System.out.println("3: End turn.");
                    playerOption = scanner.nextLine();

                    if (playerOption.equals("1")) {
                        if (playerHand.size() > 0) {
                            System.out.println("Which card would you like to play?");
                            for (int j = 0; j < playerHand.size(); j++) {
                                System.out.println(j + 1 + ":" + playerHand.getCard(j).getName());
                            }
                            String cardChoice = scanner.nextLine();
                            int choice = Integer.parseInt(cardChoice) - 1;
                            playerOne.play(playerHand.getCard(choice));
                        } else {
                            System.out.println("No cards left in hand.");
                        }
                    } else if (playerOption.equals("2")) {
                        System.out.println("Which card would you like to buy?");
                        System.out.println("Rune: " + playerOne.getRuneTotal());
                        System.out.println("Battle: " + playerOne.getBattleTotal());
                        System.out.println("0: Back");
                        for (int j = 0; j < centerRow.size(); j++) {
                            System.out.println(j + 1 + ": " + centerRow.getCard(j).getName()
                                    + " Rune Cost: " + centerRow.getCard(j).getRuneCost()
                                    + " Battle Cost: " + centerRow.getCard(j).getBattleCost());
                        }
                        String card = scanner.nextLine();
                        int choice = Integer.parseInt(card);
                        if (choice != 0) {
                        	playerOne.buy(playerOne,centerRow.getCard(choice-1), board);
                        }
                    } else if (playerOption.equals("3")) {
                        System.out.println("End of turn.");
                        playerOne.endOfTurn();
                        playerOneTurn = false;
                    } else {
                        System.out.println("Please enter a valid option.");
                    }
                } // player out of cards

            } else { // end of player one turn
                playerOneTurn = true;
            } // start on player one turn again!
        } // end of game - no honor left
    }
}
