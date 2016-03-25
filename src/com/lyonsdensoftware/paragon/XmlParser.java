
package com.lyonsdensoftware.paragon;

// Imports
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author Joshua Lyons
 */
public class XmlParser {
    
    /**
     * Constructor
     */
    public XmlParser() {
    
    }
    
    /**
     * Returns an array of cards with the basic info pulled from the card xml file
     * @return array of PAragonCard
     */
    public static ParagonCard[] getAllCards(){
        
        ParagonCard[] allCards;
        
        try {
            
            // Open xml file
            File xmlFile = Paths.get("./Data/Cards.xml").toFile();
            DocumentBuilder dBuilder =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            doc.getDocumentElement().normalize();
            
                        
            // Create a list of all the elements with Card
            NodeList nList = doc.getElementsByTagName("Card");
            
            // Create a card array with the amount of cards
            int numOfCards = nList.getLength();
            allCards = new ParagonCard[numOfCards];
            
            // Now loop through the list and add the cards to the array
            for (int i = 0; i < numOfCards; i++) {
                
                Node nNode = nList.item(i);
                Element elem = (Element) nNode;
                
                // Create the card
                String cardName = elem.getElementsByTagName("CardName").item(0).getTextContent();
                int cost = Integer.parseInt(elem.getElementsByTagName("Cost").item(0).getTextContent());
                String type = elem.getElementsByTagName("Type").item(0).getTextContent();
                String affinity = elem.getElementsByTagName("Affinity").item(0).getTextContent();
                String rarity = elem.getElementsByTagName("Rarity").item(0).getTextContent();
                
                ParagonCard tmpCard = new ParagonCard(cardName, cost, type, affinity, rarity);
                
                // Add that card to the array
                allCards[i] = tmpCard;
                
                //System.out.print(allCards);
                
            }
            
            // Return cards
            return allCards;
            
        }
        catch (Exception e) {
            e.printStackTrace();
            
            return new ParagonCard[0];
        }
        
    }
    
     /**
     * Returns an the string value of the requested card data
     * @return string data of requested value
     * @param cardName is the name of the card to get the data for
     */
    public static String getSpecificCardDataFromXml(String cardName, String data) {
        
        try {
            
            // Open xml file
            File xmlFile = Paths.get("./Data/Cards.xml").toFile();
            DocumentBuilder dBuilder =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            doc.getDocumentElement().normalize();
            
                        
            // Create a list of all the elements with Card
            NodeList nList = doc.getElementsByTagName("Card");
            
            // Create a card array with the amount of cards
            int numOfCards = nList.getLength();
            
            // Now loop through the list and add the cards to the array
            for (int i = 0; i < numOfCards; i++) {
                
                Node nNode = nList.item(i);
                Element elem = (Element) nNode;
                
                // Check if this is the card we want
                if (elem.getElementsByTagName("CardName").item(0).getTextContent().equals(cardName)) 
                    return elem.getElementsByTagName(data).item(0).getTextContent();
                                
            }
            
            // No card found
            return "";
            
        }
        catch (Exception e) {
            e.printStackTrace();
            
            return "";
        }
    }
    
    /**
     * Returns an the string array of affinities for the hero
     * @return string array of requested value
     * @param heroName is the name of the hero to get the data for
     */
    public static String[] getAffinities(String heroName) {
        
        String[] returnArray;
        
         try {
            
            // Open xml file
            File xmlFile = Paths.get("./Data/" + heroName + ".xml").toFile();
            DocumentBuilder dBuilder =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            doc.getDocumentElement().normalize();
            
                        
            // Create a list of all the elements with Card
            NodeList nList = doc.getElementsByTagName("Affinity");
            
            // Create a card array with the amount of cards
            int numOfNodes = nList.getLength();
            returnArray = new String[numOfNodes];
            
            // Now loop through the list and add the cards to the array
            for (int i = 0; i < numOfNodes; i++) {
                
                Node nNode = nList.item(i);
                Element elem = (Element) nNode;
                
                // Add the affinity
                returnArray[i] = elem.getTextContent();
                                
            }
            
            // No card found
            return returnArray;
            
        }
        catch (Exception e) {
            e.printStackTrace();
            
            return new String[0];
        }
    }
    
    /**
     * Returns an the string array of roles for the hero
     * @return string array of requested value
     * @param heroName is the name of the hero to get the data for
     */
    public static String[] getRoles(String heroName) {
        
        String[] returnArray;
        
         try {
            
            // Open xml file
            File xmlFile = Paths.get("./Data/" + heroName + ".xml").toFile();
            DocumentBuilder dBuilder =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            doc.getDocumentElement().normalize();
            
                        
            // Create a list of all the elements with Card
            NodeList nList = doc.getElementsByTagName("Role");
            
            // Create a card array with the amount of cards
            int numOfNodes = nList.getLength();
            returnArray = new String[numOfNodes];
            
            // Now loop through the list and add the cards to the array
            for (int i = 0; i < numOfNodes; i++) {
                
                Node nNode = nList.item(i);
                Element elem = (Element) nNode;
                
                // Add the affinity
                returnArray[i] = elem.getTextContent();
                                
            }
            
            // No card found
            return returnArray;
            
        }
        catch (Exception e) {
            e.printStackTrace();
            
            return new String[0];
        }
    }
    
    /**
     * Returns an the int value of the requested hero data
     * @return int data of requested value
     * @param heroName is the name of the card to get the data for
     * @param stat is the name of the stat to get the data for
     */
    public static int getHeroBaseStat(String heroName, String stat) {
        
        try {
            
            // Open xml file
            File xmlFile = Paths.get("./Data/" + heroName + ".xml").toFile();
            DocumentBuilder dBuilder =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            doc.getDocumentElement().normalize();
            
                        
            // Create a list of all the elements with Card
            Element elem = (Element) doc.getElementsByTagName("Stats." + stat).item(0);
            
            return Integer.parseInt(elem.getTextContent());
            
        }
        catch (Exception e) {
            e.printStackTrace();
            
            return 0;
        }
    }
    
     /**
     * Returns an the string value of the requested hero data
     * @return string data of requested value
     * @param heroName is the name of the card to get the data for
     * @param stat is the name of the stat to get the data for
     */
    public static String getPassiveInfo(String heroName, String stat) {
        
        try {
            
            // Open xml file
            File xmlFile = Paths.get("./Data/" + heroName + ".xml").toFile();
            DocumentBuilder dBuilder =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            doc.getDocumentElement().normalize();
            
                        
            // Create a list of all the elements with Card
            Element elem = (Element) doc.getElementsByTagName(stat + ".Passive").item(0);
            
            return elem.getTextContent();
            
        }
        catch (Exception e) {
            e.printStackTrace();
            
            return "";
        }
    }
 
    /**
     * Returns an the double value of the requested hero data
     * @return double data of requested value
     * @param heroName is the name of the card to get the data for
     * @param stat is the name of the stat to get the data for
     * @param level is the level to look at
     */
    public static double getHeroStatAtLevel(String heroName, String stat, int level) {
        
        try {
            
            // Open xml file
            File xmlFile = Paths.get("./Data/" + heroName + ".xml").toFile();
            DocumentBuilder dBuilder =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            doc.getDocumentElement().normalize();
            
            // Create a list of all the elements with Card
            Element elem = (Element) doc.getElementsByTagName(stat).item(0);
            
            NodeList nList = elem.getChildNodes();
                         
            Element elemChild = (Element) nList.item(level - 1);
                
            return Double.parseDouble(elemChild.getTextContent());
                                
            
            
        }
        catch (Exception e) {
            e.printStackTrace();
            
            return 0;
        }
    }
}
