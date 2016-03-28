
package com.lyonsdensoftware.paragon;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;

/**
 * This extends jlayered pane to add customized layered pane look for the deck builder
 * @author Joshua Lyons
 */
public class ParagonLayeredPane extends javax.swing.JLayeredPane{
    
    private ParagonCard myCard;             // Holds a reference to the card associated with this pane
    JButton btn_myButton;           // Reference to the button
    JLabel cardCostNameLbl;         // Cost and Name lbl of the card
    JLabel cardCountLbl;            // Card count label
    private int cardCount;                  // How many cards are associated with this pane
    int width = 293, height = 29;   // Width height of components
    
    public ParagonLayeredPane(ParagonCard card) {
        this.myCard = card;
        this.cardCount = 1;
        this.btn_myButton = new JButton();
        this.cardCostNameLbl = new JLabel();
        this.cardCountLbl = new JLabel();
        
        // Setup dimensions
        Dimension tmpDimension = new Dimension(this.width, this.height);
        this.setPreferredSize(tmpDimension);
        
        // Set text
        this.btn_myButton.setText("");
        this.cardCostNameLbl.setText(this.myCard.getCost() + " " + this.myCard.getCardName());
        this.cardCountLbl.setText("x" + this.cardCount);
        
        // Add components to jpanel
        this.add(this.btn_myButton, 0);
        this.add(this.cardCostNameLbl, 1);
        this.add(this.cardCountLbl, 2);
        
        // Change depth
        this.setLayer(this.btn_myButton, 0);
        this.setLayer(this.cardCostNameLbl, 1);
        this.setLayer(this.cardCountLbl, 2);
        
        // Set justifications
        this.cardCostNameLbl.setHorizontalTextPosition(SwingConstants.LEADING);
        this.cardCostNameLbl.setVerticalTextPosition(SwingConstants.CENTER);
        this.cardCountLbl.setHorizontalTextPosition(SwingConstants.TRAILING);
        this.cardCountLbl.setVerticalTextPosition(SwingConstants.CENTER);
             
        this.btn_myButton.setPreferredSize(tmpDimension);
        this.cardCostNameLbl.setPreferredSize(tmpDimension);
        tmpDimension = new Dimension(50, this.height);
        this.cardCountLbl.setPreferredSize(tmpDimension);
        
        // Set locations
        this.btn_myButton.setBounds(0, 0, this.width, this.height);
        this.cardCostNameLbl.setBounds(10, 0, this.width, this.height);
        this.cardCountLbl.setBounds((this.width - 40), 0, this.width, this.height);
                
    }

    /**
     * @return the myCard
     */
    public ParagonCard getMyCard() {
        return myCard;
    }

    /**
     * @param myCard the myCard to set
     */
    public void setMyCard(ParagonCard myCard) {
        this.myCard = myCard;
    }

    /**
     * @return the cardCount
     */
    public int getCardCount() {
        return cardCount;
    }

    /**
     * @param cardCount the cardCount to set
     */
    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
        // Update text
        this.cardCountLbl.setText("x" + this.cardCount);
    }
    
}
