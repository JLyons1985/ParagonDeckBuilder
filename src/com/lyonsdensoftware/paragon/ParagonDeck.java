
package com.lyonsdensoftware.paragon;

import java.util.Stack;

/**
 *
 * @author Joshua Lyons
 */
public class ParagonDeck {
    
    private ParagonCard[] cards;        // Holds all the cards in this deck
    private boolean isMasterDeck;       // Is this the master deck of all the cards
    private final int MAXCARDS = 40;    // Max amoutn of cards a normal deck can have
    
    /**
     * Default Deck Constructor
     */
    public ParagonDeck() {
        this.cards = new ParagonCard[MAXCARDS];
        this.isMasterDeck = false;
    }
    
    /**
     * Deck Constructor
     * @param cards
     */
    public ParagonDeck(ParagonCard[] cards) {
        this.cards = cards;
        this.isMasterDeck = false;
    }
    
    /**
     * Deck constructor to construct the master deck
     * @param isMasterDeck tells the object it holds all the cards
     */
    public ParagonDeck(boolean isMasterDeck) {
        this.isMasterDeck = isMasterDeck;
        this.cards = XmlParser.getAllCards();
    }

    /**
     * @return the cards
     */
    public ParagonCard[] getCards() {
        return cards;
    }

    /**
     * @param cards the cards to set
     */
    public void setCards(ParagonCard[] cards) {
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
     * @param deckToFilter
     * @param showUpgrades
     * @param showPassives
     * @param showActives
     * @param showPrime
     * @param affinities
     * @param rarity
     * @param searchText
     * @return new deck
     */
    public static ParagonDeck getDeckFromFilters(ParagonDeck deckToFilter, boolean showUpgrades, 
            boolean showPassives, boolean showActives, boolean showPrime, String[] affinities, String rarity, 
            String searchText) {
        
        Stack<ParagonCard> filteredDeck = new Stack();
        ParagonCard[] tmpCards = deckToFilter.getCards();
        
        // Now loop through the deck and add the card to the stack if it fits the filter
        for (int i = 0; i < tmpCards.length; i ++) {
            
            boolean addCard = false;
            
            String testString;
            
            testString = tmpCards[i].getType();
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
                if (!tmpCards[i].getType().equals("Passive"))
                    if (addCard)
                        addCard = true;
                    else
                        addCard = false;
                else
                    addCard = true;
            }
            else {
                if (tmpCards[i].getType().equals("Passive"))
                    continue;
                else
                    addCard = true;
            }
            
            if (showActives) {
                if (!tmpCards[i].getType().equals("Active"))
                    if (addCard)
                        addCard = true;
                    else
                        addCard = false;
                else
                    addCard = true;
            }
            else {
                if (tmpCards[i].getType().equals("Active"))
                    continue;
                else
                    addCard = true;
            }
            
            if (showPrime) {
                if (!tmpCards[i].getType().equals("Prime"))
                    if (addCard)
                        addCard = true;
                    else
                        addCard = false;
                else
                    addCard = true;
            }
            else {
                if (tmpCards[i].getType().equals("Prime"))
                    continue;
                else
                    addCard = true;
            }
            
            if (affinities.length == 1) {
                if (!(tmpCards[i].getAffinity().equals("Affinity." + affinities[0]) || tmpCards[i].getAffinity().equals("Affinity.Universal")))
                    continue;
                else
                    addCard = true;
            }
            else if (affinities.length == 2) {
                if (!(tmpCards[i].getAffinity().equals("Affinity." + affinities[0]) || tmpCards[i].getAffinity().equals("Affinity." + affinities[1]) 
                        || tmpCards[i].getAffinity().equals("Affinity.Universal")))
                    continue;
                else
                    addCard = true;
            }
            else {
                addCard = true;
            }
            
            if (!rarity.isEmpty()) {
                if (!tmpCards[i].getRarity().equals(rarity))
                    continue;
                else
                    addCard = true;
            }
            else {
                addCard = true;
            }
                
            
            if (!searchText.isEmpty()) {
                if (!tmpCards[i].getCardName().contains(searchText))
                    continue;
                else
                    addCard = true;
            }
            else {
                addCard = true;
            }
            
            // Card Passes
            filteredDeck.push(tmpCards[i]);
            
        }
                
        // Convert stack to deck
        ParagonCard[] myCards = new ParagonCard[filteredDeck.size()];
        for (int i = 0; i < myCards.length; i++) {
            if (!filteredDeck.empty())
                myCards[i] = filteredDeck.pop();
        }
        
        return new ParagonDeck(myCards);
        
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        String tmpString = "";
        
        if (this.cards.length > 0) {
            for (int i = 0; i < this.cards.length; i++) {
                tmpString += this.cards[i].getCardName() + "\n";
            }
        }
        
        return tmpString;
    }
    
}
