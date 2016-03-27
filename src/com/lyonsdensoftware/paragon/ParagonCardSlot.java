
package com.lyonsdensoftware.paragon;

/**
 *
 * @author Joshua Lyons
 */
public class ParagonCardSlot {
    
    private ParagonCard card;               // Holds the reference to the card in this slot
    private ParagonCard[] upgardeCards;     // Holds the upgrade cards in this slot
    private boolean isActiveSlot;           // Is this slot an active slot

    /**
     * Constructor
     * @param isActiveSlot is the boolean for the active slot
     */
    public ParagonCardSlot (boolean isActiveSlot) {
        this.card = null;
        this.isActiveSlot = isActiveSlot;
    }
    
    /**
     * @return the card
     */
    public ParagonCard getCard() {
        return card;
    }

    /**
     * @param card the card to set
     */
    public void setCard(ParagonCard card) {
        this.card = card;
    }

    /**
     * @return the upgardeCards
     */
    public ParagonCard[] getUpgardeCards() {
        return upgardeCards;
    }

    /**
     * @param upgardeCards the upgardeCards to set
     */
    public void setUpgardeCards(ParagonCard[] upgardeCards) {
        this.upgardeCards = upgardeCards;
    }

    /**
     * @return the isActiveSlot
     */
    public boolean isIsActiveSlot() {
        return isActiveSlot;
    }

    /**
     * @param isActiveSlot the isActiveSlot to set
     */
    public void setIsActiveSlot(boolean isActiveSlot) {
        this.isActiveSlot = isActiveSlot;
    }
    
}
