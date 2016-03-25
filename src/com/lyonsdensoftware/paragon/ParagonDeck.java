
package com.lyonsdensoftware.paragon;

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
    
}
