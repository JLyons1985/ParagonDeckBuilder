
package com.lyonsdensoftware.paragon;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Joshua Lyons
 */
public class ParagonDeck implements Iterable<ParagonCard>, java.io.Serializable {
    
    private LinkedList<ParagonCard> cards;        // Holds all the cards in this deck
    private boolean isMasterDeck;                 // Is this the master deck of all the cards
    private final int MAXCARDS = 40;              // Max amoutn of cards a normal deck can have
    private int cardCount;                        // Holds the amount of cards in this deck
    private String heroName;                      // Name of the hero this deck is for
    private ParagonCard[] slottedCards;           // Holds the slotted cards
    
    /**
     * Default Deck Constructor
     * @param heroName
     */
    public ParagonDeck(String heroName) {
        this.cards = new LinkedList<ParagonCard>();
        this.isMasterDeck = false;
        this.cardCount = 0;
        this.heroName = heroName;
        this.slottedCards = new ParagonCard[6];
    }
    
    /**
     * Deck Constructor
     * @param cards
     * @param heroName
     */
    public ParagonDeck(LinkedList<ParagonCard> cards, String heroName) {
        this.cards = cards;
        this.isMasterDeck = false;
        this.cardCount = this.cards.size();
        this.slottedCards = new ParagonCard[6];
    }
    
    /**
     * Deck constructor to construct the master deck
     * @param isMasterDeck tells the object it holds all the cards
     */
    public ParagonDeck(boolean isMasterDeck) {
        this.isMasterDeck = isMasterDeck;
        if (isMasterDeck) {
            this.cards = XmlParser.getAllCards();
            this.cardCount = this.cards.size();
        }
        else
            this.cardCount = 0;
        
        this.heroName = "Default";
        this.slottedCards = new ParagonCard[6];
    }
    
    public static ParagonDeck buildStarterDeck(ParagonDeck masterDeck, String heroName) {
        ParagonDeck tmpDeck = new ParagonDeck(heroName);
        
        // Now loop through the deck and add the card to the stack if it fits the filter
        for (ParagonCard testCard : masterDeck.getCards()) {
            if (testCard.getRarity().equals("Starter")) {
                if (testCard.getType().equals("Prime")) {
                    if (testCard.getCardName().equals("The Centurion")) {
                        tmpDeck.addCard(testCard);
                    }
                }
                else {
                    tmpDeck.addCard(testCard);     
                }                   
                
            }
        }
        
        return tmpDeck;
    }
    
    /**
     * Adds the card to the deck
     * @param card 
     */
    public void addCard(ParagonCard card) {
        this.cards.add(card);
        this.setCardCount(this.cards.size());
    }
    
    /**
     * Removes the specified card from the deck
     * @param card 
     */
    public void removeCard(ParagonCard card) {
        this.cards.remove(card);
        this.setCardCount(this.cards.size());
    }
    
    /**
     * Removes the prime card to add a new one
     */
    public void removePrimeCard() {
        // Now loop through the deck and add the card to the stack if it fits the filter
        for (ParagonCard tmpCard : this.cards) {
            if (tmpCard.getType().equals("Prime"))
                this.removeCard(tmpCard);
        }
    }
    
    /**
     * @return the cards
     */
    public LinkedList<ParagonCard> getCards() {
        return cards;
    }
    
    /**
     * 
     * @return max cards for this deck
     */
    public int getMaxCards() {
        return this.MAXCARDS;
    }

    /**
     * @param cards the cards to set
     */
    public void setCards(LinkedList<ParagonCard> cards) {
        this.cards = cards;
    }

    /**
     * @return the isMasterDeck
     */
    public boolean isIsMasterDeck() {
        return isMasterDeck;
    }

    /**
     * @param isMasterDeck the isMasterDeck to set
     */
    public void setIsMasterDeck(boolean isMasterDeck) {
        this.isMasterDeck = isMasterDeck;
    }
    
    /**
     * 
     * @return the cards in this deck
     */
    public int getCardCount() {
        if (this.cards.isEmpty())
            return 0;
        else
            return this.cards.size();
    }
    
    /**
     * 
     * @param deckToFilter
     * @param showUpgrades
     * @param showPassives
     * @param showActives
     * @param showPrime
     * @param affinities
     * @param rarity
     * @param searchText
     * @param heroName
     * @return new deck
     */
    public static ParagonDeck getDeckFromFilters(ParagonDeck deckToFilter, boolean showUpgrades, 
            boolean showPassives, boolean showActives, boolean showPrime, String[] affinities, String rarity, 
            String searchText, String heroName) {
        
        LinkedList<ParagonCard> tmpDeck = new LinkedList<ParagonCard>();
        
        // Now loop through the deck and add the card to the stack if it fits the filter
        for (ParagonCard testCard : deckToFilter.getCards()) {
            
            boolean addCard = false;
            
            String testString;
            
            testString = testCard.getType();
            if (showUpgrades){
                if (!testString.equals("Upgrade")) 
                    if (addCard)
                        addCard = true;
                    else
                        addCard = false;
                else
                    addCard = true;
            }
            else {
                if (testString.equals("Upgrade"))
                    continue;
                else
                    addCard = true;
            }
                
            
            if (showPassives) {
                if (!testCard.getType().equals("Passive"))
                    if (addCard)
                        addCard = true;
                    else
                        addCard = false;
                else
                    addCard = true;
            }
            else {
                if (testCard.getType().equals("Passive"))
                    continue;
                else
                    addCard = true;
            }
            
            if (showActives) {
                if (!testCard.getType().equals("Active"))
                    if (addCard)
                        addCard = true;
                    else
                        addCard = false;
                else
                    addCard = true;
            }
            else {
                if (testCard.getType().equals("Active"))
                    continue;
                else
                    addCard = true;
            }
            
            if (showPrime) {
                if (!testCard.getType().equals("Prime"))
                    if (addCard)
                        addCard = true;
                    else
                        addCard = false;
                else
                    addCard = true;
            }
            else {
                if (testCard.getType().equals("Prime"))
                    continue;
                else
                    addCard = true;
            }
            
            if (affinities.length == 1) {
                if (!(testCard.getAffinity().equals("Affinity." + affinities[0]) || testCard.getAffinity().equals("Affinity.Universal")))
                    continue;
                else
                    addCard = true;
            }
            else if (affinities.length == 2) {
                if (!(testCard.getAffinity().equals("Affinity." + affinities[0]) || testCard.getAffinity().equals("Affinity." + affinities[1]) 
                        || testCard.getAffinity().equals("Affinity.Universal")))
                    continue;
                else
                    addCard = true;
            }
            else {
                addCard = true;
            }
            
            if (!rarity.isEmpty()) {
                if (!testCard.getRarity().equals(rarity))
                    continue;
                else
                    addCard = true;
            }
            else {
                addCard = true;
            }
                            
            if (!searchText.isEmpty()) {
                if (!testCard.getCardName().contains(searchText))
                    continue;
                else
                    addCard = true;
            }
            else {
                addCard = true;
            }
            
            // Check for energy or physical based on hero
            if (!heroName.equals("Default")){
                switch (heroName) {
                    case "Howitzer":
                    case "Murdock":
                    case "Gideon":
                    case "Gadget":
                    case "Dekker":
                    case "Muriel":
                        if (testCard.isPhysicalDamage()) {
                            continue;
                        }
                        else addCard = true;
                        break;
                    case "Kallari":
                    case "Rampage":
                    case "Steel":
                    case "FengMao":
                    case "TwinBlast":
                    case "Grux":
                    case "Sevarog":
                    case "Sparrow":
                        if (testCard.isEnergyDamage()) {
                            continue;
                        }
                        else addCard = true;
                        break;
                }
            }
            
            // Card Passes
            tmpDeck.add(testCard);
            
        }
        
        return new ParagonDeck(tmpDeck, heroName);
        
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        String tmpString = "";
                
        for (ParagonCard card: this.cards) {
            tmpString += card.getCardName() + "\n";
        }
        
        return tmpString;
    }

    @Override
    public Iterator<ParagonCard> iterator() {
        return this.cards.iterator();
    }

    /**
     * @return the heroName
     */
    public String getHeroName() {
        return heroName;
    }

    /**
     * @param heroName the heroName to set
     */
    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    /**
     * @param cardCount the cardCount to set
     */
    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }

    /**
     * @return the slottedCards
     */
    public ParagonCard[] getSlottedCards() {
        return slottedCards;
    }

    /**
     * @param slottedCard the slottedCards to set
     * @param slot
     */
    public void setSlottedCard(ParagonCard slottedCard, int slot) {
        this.slottedCards[slot] = slottedCard;
    }
    
}
