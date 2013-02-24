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
        playerOne.setName("Ben");
        playerOne.setPlayerDeck(db.deckReader("C://Users/Owner/Desktop/PlayerDeck.xml"));
        board.setCenterDeck(db.deckReader("C://Users/Owner/Desktop/CenterDeck.xml"));
        board.setMystic(db.deckReader("C://Users/Owner/Desktop/Mystic.xml"));
        board.setHeavy(db.deckReader("C://Users/Owner/Desktop/Heavy.xml"));
        board.setCultist(db.deckReader("C://Users/Owner/Desktop/Cultist.xml"));

        playerOne.getDeck().shuffle();
        playerOne.draw(5);

        board.getCenterDeck().shuffle();
        board.draw(6);

        while (board.getHonorLeft() > 0) {
            if (playerOneTurn) {
                
                String playerOption = "";
                while (!playerOption.equals("3")) {
                	System.out.println("Cards in hand: " + playerOne.cardsByName(playerOne.getHand()));

                    System.out.println("1: Play a card.");
                    System.out.println("2: Buy a card.");
                    System.out.println("3: End turn.");
                    playerOption = scanner.nextLine();

                    if (playerOption.equals("1")) {
                        if (playerOne.getHand().size() > 0) {
                            System.out.println("Which card would you like to play?");
                            for (int j = 0; j < playerOne.getHand().size(); j++) {
                                System.out.println(j + 1 + ":" + playerOne.getHand().getCard(j).getName());
                            }
                            String cardChoice = scanner.nextLine();
                            int choice = Integer.parseInt(cardChoice) - 1;
                            playerOne.play(playerOne.getHand().getCard(choice));
                        } else {
                            System.out.println("No cards left in hand.");
                        }
                    } else if (playerOption.equals("2")) {
                        System.out.println("Which card would you like to buy?");
                        System.out.println("Rune: " + playerOne.getRuneTotal());
                        System.out.println("Battle: " + playerOne.getBattleTotal());
                        System.out.println("0: Back");
                        for (int j = 0; j < board.getCenterRow().size(); j++) {
                            System.out.println(j + 1 + ": " + board.getCenterRow().getCard(j).getName()
                                    + " Rune Cost: " + board.getCenterRow().getCard(j).getRuneCost()
                                    + " Battle Cost: " + board.getCenterRow().getCard(j).getBattleCost());
                        }
                        System.out.println("7" + ": " + board.getMystic().topCard().getName()
                                + " Rune Cost: " + board.getMystic().topCard().getRuneCost()
                                + " Battle Cost: " + board.getMystic().topCard().getBattleCost());
                        System.out.println("8" + ": " + board.getHeavy().topCard().getName()
                                + " Rune Cost: " + board.getHeavy().topCard().getRuneCost()
                                + " Battle Cost: " + board.getHeavy().topCard().getBattleCost());
                        System.out.println("9" + ": " + board.getCultist().topCard().getName()
                                + " Rune Cost: " + board.getCultist().topCard().getRuneCost()
                                + " Battle Cost: " + board.getCultist().topCard().getBattleCost());
                        String card = scanner.nextLine();
                        int choice = Integer.parseInt(card) - 1;
                        if (choice == -1 ) {}
                        else if (choice <= 5) {
                            playerOne.buy(playerOne, board.getCenterRow().getCard(choice), board);
                        } else if (choice == 6) {
                            playerOne.buy(playerOne, board.getMystic());
                        } else if (choice == 7) {
                            playerOne.buy(playerOne, board.getHeavy());
                        } else if (choice == 8) {
                            playerOne.buy(playerOne, board.getCultist());
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
