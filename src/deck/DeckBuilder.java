package deck;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import card.Ability;
import card.Card;
import card.Card.Faction;
import card.Card.Type;

/**
 * Deck Reader
 *
 * @author Ben Rudi
 * @version 0.1 02/23/13
 */
public class DeckBuilder {

    public Deck deckReader(String xmlFile) throws ParserConfigurationException, SAXException, IOException {

        Deck deck = new Deck();
        File deckXML = new File(xmlFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(deckXML);

        //optional, but recommended
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("card");
        
        for (int temp = 0; temp < nList.getLength(); temp++) {
        	String name = "";
        	try {

	            Node nNode = nList.item(temp);
	
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
	                // Create a card
	                Card card = new Card();
	                
	                // Number of cards to create
	                int countNum = 0;
	
	                Element eElement = (Element) nNode;
	
	                // Get all of the xml values
	                name = getTextContent(eElement, "name");
	                String faction = optTextContent(eElement, "faction","").toUpperCase();
	                String type = getTextContent(eElement, "type").toUpperCase();
	                String banishable = optTextContent(eElement, "banishable", "true");
	                String count = optTextContent(eElement, "count", "1");
	                String rune = optTextContent(eElement, "rune", "0");
	                String battle = optTextContent(eElement, "battle","0");
	                String honor = optTextContent(eElement, "honor","0");
	                String runeCost = optTextContent(eElement, "runeCost","0");
	                String battleCost = optTextContent(eElement, "battleCost","0");
	                String honorWorth = optTextContent(eElement, "honorWorth","0");
	 
	                    // Set all of the xml values to the card
	                card.setName(name);
	                if (!faction.isEmpty())card.setFaction(Faction.valueOf(faction));
	                card.setBanishable(Boolean.valueOf(banishable));
	                card.setType(Type.valueOf(type));
	                card.setRune(Integer.parseInt(rune));
	                card.setBattle(Integer.parseInt(battle));
	                card.setHonor(Integer.parseInt(honor));
	                card.setRuneCost(Integer.parseInt(runeCost));
	                card.setBattleCost(Integer.parseInt(battleCost));
	                card.setHonorWorth(Integer.parseInt(honorWorth));
	
	                // Add all of the abilities to an arraylist from the xml
	                
	                List<Ability> abilities = new ArrayList<Ability>();
	                NodeList eAbilities = eElement.getElementsByTagName("ability");
					for (int i = 0; i < eAbilities.getLength(); i++) {
						Node eAbility = eAbilities.item(i);
						
	                    try {
	                    	Ability ability = createAbility(eAbility);
	                        abilities.add(ability);
	                    } catch (Exception ex) {
	                        System.out.println("Exception with " + name + " ability: " + ex);
	                    }
	                }
	                card.setAbilities(abilities);
	                
	                // Get the number of cards to add to the deck
	                countNum = Integer.parseInt(count);
	                // Add the cards to the deck
	                for (int i = 0; i < countNum; i++) {
	                    deck.add(card);
	                }
	
	            }
        	} catch (Exception ex) {
        		System.out.println("Exception with creating " + name + ": " + ex);
        	}
        }
        return deck;
    }
    
    //create an ability from a node, including optional status and creating choices
    private Ability createAbility(Node node) throws Exception{
    	Ability ability;
    	
    	String abilityString = node.getTextContent().toUpperCase();
    	List<Ability> abilities = new ArrayList<Ability>();
    	for (String abString : abilityString.split(",")){
    		abString = abString.trim().toUpperCase();
    		Ability ab = createAbility(abString);
    		//System.out.println(abilities + "<----"+ ab);
    		abilities.add(ab);
    		//System.out.println(abilities);
    	}
    	
    	if (abilities.size() > 1){
    		Ability.Type abilityType;
    		Node nType = node.getAttributes().getNamedItem("type");
        	if (nType != null){
        		String type = nType.getNodeValue().toUpperCase();
        		abilityType = Ability.Type.valueOf(type);
        	} else{
        		throw new Exception("no type set for compound ability");
        	}
    		ability = new Ability(abilityType);
    		ability.setAbilities(abilities);
    	}
    	else{
    		ability = abilities.get(0);
    	}
    	
    	//if optional exists, set it
    	Node nOptional = node.getAttributes().getNamedItem("optional");
    	if (nOptional != null) {
    		ability.setOptoinal(Boolean.valueOf(nOptional.getNodeValue()));
    	}
    	
    	//System.out.println("final ability " + ability);
    	return ability;
    }
    
    //create a single ability from a string, optional=false, no choices
    private Ability createAbility(String abilityString){
    	Pattern pattern = Pattern.compile("([a-zA-Z_]+)(\\d*)"); 
    	Matcher matcher = pattern.matcher(abilityString);
    	matcher.find();
    	
    	Ability.Type type = Ability.Type.valueOf(matcher.group(1));
    	Ability ability = new Ability(type);
    	ability.setValue(Integer.valueOf(matcher.group(2)));
    	
    	return ability;
    }
    
    private String optTextContent(Element element, String subElement, String defaultValue ){
    	String result = defaultValue;
    	NodeList nList = element.getElementsByTagName(subElement);
    	if (nList != null) {
    		Node node = nList.item(0);
    		if (node != null){
    			String context = nList.item(0).getTextContent();
    			if (context != null && !context.trim().isEmpty()){
    				result = nList.item(0).getTextContent();
    			}
    		}
    	}
    	return result;
    }
    
    private String getTextContent(Element element, String subElement) throws Exception{
    	String result = optTextContent(element,subElement,null);
    	if (result == null || result.trim().isEmpty()){
    		throw new Exception("no value specified for " + subElement);
    	}
    	return result;
    }
}
