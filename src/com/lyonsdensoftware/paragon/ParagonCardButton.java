
package com.lyonsdensoftware.paragon;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import javax.swing.JButton;

/**
 *
 * @author Joshua Lyons
 */
public class ParagonCardButton extends JButton {
    
    private ParagonCard myCard;         // Holds a reference to the card in this button
    private boolean slotIsActiveSlot;   // Is this an active slot
    private int slotIndex;              // Ref to slot index
    
    /**
     * 
     * @param card is the card passed at construction
     */
    public ParagonCardButton (ParagonCard card) {
        this.myCard = card;
        
    }
    
    /**
     * 
     * @param card
     * @param isActiveSlot
     * @param slotIndex 
     */
    public ParagonCardButton (ParagonCard card, boolean isActiveSlot, int slotIndex) {
        this.myCard = card;
        this.slotIsActiveSlot = isActiveSlot;
        this.slotIndex = slotIndex;
        
    }
    
    /**
     * 
     * @return the card in this button
     */
    public ParagonCard getMyCard() {
        return this.myCard;
    }
    
    /**
     * 
     * @return icon path
     */
    public String getIconPath() {
        // Set icon
        String affinity = "";
        String rarity = "";
        String id = "";
        
        switch (this.myCard.getAffinity()) {
            case "Affinity.Corruption":
                affinity = "C";
                break;
            case "Affinity.Fury":
                affinity = "F";
                break;
            case "Affinity.Growth":
                affinity = "G";
                break;
            case "Affinity.Intellect":
                affinity = "I";
                break;
            case "Affinity.Order":
                affinity = "O";
                break;
            case "Affinity.Universal":
                affinity = "U";
                break;
        }
        
        switch (this.myCard.getRarity()) {
            case "Common":
                rarity = "C";
                break;
            case "Uncommon":
                rarity = "U";
                break;
            case "Starter":
                rarity = "S";
                break;
        }
        
        if (myCard.getId() < 10)
            id = "0" + this.myCard.getId();
        else
            id = String.valueOf(this.myCard.getId());
        
        String imgPath = Paths.get("./Art/Cards/Beta_" + 
                rarity + "_" + affinity + "_" + id + "_Full.png").toString();
        
        if (!Files.exists(Paths.get(imgPath)))
            System.out.println("Can't find: " + imgPath + " " + this.myCard.getCardName());
        
        return imgPath;
    }

    /**
     * @return the slotIsActiveSlot
     */
    public boolean getSlotIsActiveSlot() {
        return slotIsActiveSlot;
    }

    /**
     * @param slotIsActiveSlot the slotIsActiveSlot to set
     */
    public void setSlotIsActiveSlot(boolean slotIsActiveSlot) {
        this.slotIsActiveSlot = slotIsActiveSlot;
    }

    /**
     * @return the slotIndex
     */
    public int getSlotIndex() {
        return slotIndex;
    }

    /**
     * @param slotIndex the slotIndex to set
     */
    public void setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
    }

    /**
     * @param myCard the myCard to set
     */
    public void setMyCard(ParagonCard myCard) {
        this.myCard = myCard;
    }

    
    
}
