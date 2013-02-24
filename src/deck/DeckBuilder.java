package deck;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import card.Ability;
import card.Card;
import card.Card.Type;

/**
 * Deck Reader
 *
 * @author Ben Rudi
 * @version 0.1 02/23/13
 */
public class DeckBuilder {

    public Deck deckReader(String xmlFile) {

        Deck deck = new Deck();

        try {

            File deckXML = new File(xmlFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(deckXML);

            //optional, but recommended
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("card");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    // Create a card
                    Card card = new Card();
                    // Number of cards to create
                    int countNum = 0;

                    Element eElement = (Element) nNode;

                    // Get all of the xml values
                    String cardName = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String cardType = eElement.getElementsByTagName("type").item(0).getTextContent().toUpperCase();
                    String count = eElement.getElementsByTagName("count").item(0).getTextContent();
                    String rune = eElement.getElementsByTagName("rune").item(0).getTextContent();
                    String battle = eElement.getElementsByTagName("battle").item(0).getTextContent();
                    String honor = eElement.getElementsByTagName("honor").item(0).getTextContent();
                    String runeCost = eElement.getElementsByTagName("runeCost").item(0).getTextContent();
                    String battleCost = eElement.getElementsByTagName("battleCost").item(0).getTextContent();
                    String honorWorth = eElement.getElementsByTagName("honorWorth").item(0).getTextContent();
 
                    // Set all of the xml values to the card
                    card.setName(cardName);
                    card.setType(Type.valueOf(cardType));
                    card.setRune(Integer.parseInt(rune));
                    card.setBattle(Integer.parseInt(battle));
                    card.setHonor(Integer.parseInt(honor));
                    card.setRuneCost(Integer.parseInt(runeCost));
                    card.setBattleCost(Integer.parseInt(battleCost));
                    card.setHonorWorth(Integer.parseInt(honorWorth));

                    // Add all of the abilities to an arraylist from the xml
                    ArrayList<Ability> abilities = new ArrayList();
                    for (int i = 0; i < eElement.getElementsByTagName("ability").getLength(); i++) {
                        String ability = eElement.getElementsByTagName("ability").item(i).getTextContent().toUpperCase();
                        try {
                            abilities.add(Ability.valueOf(ability));
                        } catch (Exception ex) {
                            System.out.println("Exception with ability: " + ex);
                        }
                    }
                    // Set the abilities by casting the arraylist as an array
                    Ability[] abilityArray = new Ability[abilities.size()];
                    card.setAbilities(abilities.toArray(abilityArray));
                    
                    // Get the number of cards to add to the deck
                    countNum = Integer.parseInt(count);
                    // Add the cards to the deck
                    for (int i = 0; i < countNum; i++) {
                        deck.add(card);
                    }

                }
            }
        } catch (Exception ex) {
            System.out.println("Exception with creating the card: " + ex);
        }
        return deck;
    }
}
