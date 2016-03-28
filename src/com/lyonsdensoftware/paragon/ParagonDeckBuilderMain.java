
package com.lyonsdensoftware.paragon;

import java.nio.file.Paths;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.Iterator;
import javax.swing.SwingUtilities;

/**
 *
 * @author Joshua Lyons
 */
public class ParagonDeckBuilderMain extends javax.swing.JFrame {
    
    private ParagonDeck masterDeck;                 // Holds a reference to the master deck
    private ParagonDeck displayedDeck;              // Holds a reference to the displayed deck
    private ParagonDeck deckBeingCreated;           // Holds a reference to the deck being created
    private ParagonHero myHero;                     // Holds a reference to the selected hero
    private ParagonCardSlot[] cardSlots;            // Holds a reference to each of the available slots
    private final int CARDHEIGHT = 250, CARDWIDTH = 188, 
            PADDINGBETWEENCARDS = 5, CARDSPERROW = 5;
    private boolean showActive = true, showPassive = true, showUpgrade = true, showPrime = true;

    /**
     * Creates new form ParagonDeckBuilderMain
     */
    public ParagonDeckBuilderMain() {
        initComponents();
        
        centreWindow(this);
        
        // Create the default Hero
        this.myHero = new ParagonHero("Default");
        // Set levles combo box
        this.cb_Levels.setModel(new javax.swing.DefaultComboBoxModel(this.getLevels()));
        
        // Create the master deck
        this.masterDeck = new ParagonDeck(true);
        
        // New deck
        this.deckBeingCreated = ParagonDeck.buildStarterDeck(this.masterDeck);
        // Now iter through starter deck to add to slots
        Iterator<ParagonCard> myIter = this.deckBeingCreated.getCards().listIterator();
        
        // Now loop through the deck and add the card to the stack if it fits the filter
        while (myIter.hasNext()) {
            ParagonCard testCard = myIter.next();
            
            ParagonLayeredPane tmpPane = new ParagonLayeredPane(testCard);
            java.awt.Component[] tmpComp;
            boolean cardDuplicated = false;
            
            switch (testCard.getType()) {
                case "Prime":
                    // Clear the prime panel and add new one
                    this.panel_PrimeSlot.removeAll();
                    this.panel_PrimeSlot.add(tmpPane);
                    tmpPane.setBounds(0, 0, this.panel_PrimeSlot.getWidth(), this.panel_PrimeSlot.getHeight());
                    break;
                case "Upgrade":
                    tmpComp = this.panel_UpgradeCards.getComponents();
                
                    if (tmpComp.length > 0) {
                        for (java.awt.Component comp : tmpComp) {
                            ParagonLayeredPane testPane = (ParagonLayeredPane) comp;

                            if (tmpPane.getMyCard().equals(testPane.getMyCard())) { // Card already added so no need to add again
                                testPane.setCardCount(testPane.getCardCount() + 1);
                                cardDuplicated = true;
                            }
                        }      
                    }
                    else {
                        // Add to upgrade pane
                        this.panel_UpgradeCards.add(tmpPane);
                    }

                    if (!cardDuplicated) {
                        // Add to upgrade pane
                        this.panel_UpgradeCards.add(tmpPane);
                    }
                    break;
                case "Passive":
                case "Active":
                    tmpComp = this.panel_EquipmentCards.getComponents();
                
                    if (tmpComp.length > 0) {
                        for (java.awt.Component comp : tmpComp) {
                            ParagonLayeredPane testPane = (ParagonLayeredPane) comp;

                            if (tmpPane.getMyCard().equals(testPane.getMyCard())) { // Card already added so no need to add again
                                testPane.setCardCount(testPane.getCardCount() + 1);
                                cardDuplicated = true;
                            }
                        }      
                    }
                    else {
                        // Add to upgrade pane
                        this.panel_EquipmentCards.add(tmpPane);
                    }

                    if (!cardDuplicated) {
                        // Add to equipment pane
                        this.panel_EquipmentCards.add(tmpPane);
                    }
                    break;                    
            }
        }
        
        // Create the card slots
        cardSlots = new ParagonCardSlot[6];
        for (int i = 0; i < 6; i ++) {
            if (i < 5)
                cardSlots[i] = new ParagonCardSlot(true);
            else
                cardSlots[i] = new ParagonCardSlot(false);
        }
        
        // Refresh the gui
        this.refreshGui();
    }
    
    /**
     * Centers the frame on the screen
     * @param frame 
     */
    public static void centreWindow(javax.swing.JFrame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    
    /**
     * Returns combobox objects for hero level
     * @return Objects[] 
     */
    public Object[] getLevels(){
        Object[] myObjects;
        
        // get the max level
        int heroMaxLevel = this.myHero.getMaxLevel();
        
        //Now create the objects
        if (heroMaxLevel == 0) {
            myObjects = new Object[1];
            myObjects[0] = "";
            return myObjects;
        }
        myObjects = new Object[heroMaxLevel];
        
        for (int i = 0; i < heroMaxLevel; i++) {
            myObjects[i] = "Level " + (i + 1);
        }
        
        // Return
        return myObjects;
    }
    
    /**
     * Refreshes the gui based off the variables
     */
    public void refreshGui() {
        
        // Refresh hero icon
        this.refreshHeroIcon();        
        
        // Refresh affinities
        this.refreshAffinities();
        
        // Refresh stats
        this.refreshStats();
        
        // Refresh Deck builder displayed card
        this.refreshDeckBuilderCards();
        
        // Refresh deck builder numbers
        this.refreshDeckBuilderNumbers();
    }
    
    /**
     * Refresh hero icon
     */
    public void refreshHeroIcon() {
        // Set the hero button image to the correct image
        String imgPath = Paths.get("./Art/Heroes/" + 
                this.myHero.getName() + ".png").toString();
        
        this.btn_HeroSelect.setIcon(new StretchIcon(imgPath));
    }
    
    /**
     * Refreshes the affinities icons
     */
    public void refreshAffinities() {
        // Set the affinities images
        String[] affinities = this.myHero.getAffinities();
        String imgPath;
        
        if (affinities.length > 0){
            this.Affinity1.setVisible(true);
            this.Affinity2.setVisible(true);
                
            if (affinities.length > 1){
                imgPath = Paths.get("./Art/Affinities/" + affinities[0] + ".png").toString();
                this.Affinity1.setIcon(new StretchIcon(imgPath));
                imgPath = Paths.get("./Art/Affinities/" + affinities[1] + ".png").toString();
                this.Affinity2.setIcon(new StretchIcon(imgPath));
            }
            else{
                imgPath = Paths.get("./Art/Affinities/" + affinities[0] + ".png").toString();
                this.Affinity1.setIcon(new StretchIcon(imgPath));
                this.Affinity2.setIcon(new StretchIcon(imgPath));
            }
        }
        else {
            this.Affinity1.setVisible(false);
            this.Affinity2.setVisible(false);
        }
    }
    
    /**
     * Refreshes the cards in the deck build panel
     */
    public void refreshDeckBuilderCards() {
        
        // clear the panel
        this.panel_DeckBuilderPanel.removeAll();
        
        this.buildDisplayedCardsDeck();
        
        // Now that the deck is built need to display it        
        Iterator<ParagonCard> myIter = displayedDeck.getCards().listIterator();
        
        int cardPosX = this.PADDINGBETWEENCARDS, cardPosY = this.PADDINGBETWEENCARDS;
        int cardCount = 0;
        
        // Now loop through the deck and add the card to the stack if it fits the filter
        while (myIter.hasNext()) {
            ParagonCard testCard = myIter.next();
            
            // Create a button
            ParagonCardButton tmpButton = new ParagonCardButton(testCard);
            tmpButton.setSize(this.CARDWIDTH, this.CARDHEIGHT);
            
            // Add event listener
            tmpButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn_DeckBuilderCardActionPerformed(evt);
                }
            });
            
            // Add button to panel
            this.panel_DeckBuilderPanel.add(tmpButton);
            tmpButton.setBounds(cardPosX, cardPosY, this.CARDWIDTH, this.CARDHEIGHT);
            tmpButton.setIcon(new StretchIcon(tmpButton.getIconPath()));
            tmpButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));;
            
            //this.panel_DeckBuilderPanel.revalidate();
            
            // set pos for next card
            // Set x
            cardPosX = ((cardCount + 1) % this.CARDSPERROW) * (this.CARDWIDTH) + this.PADDINGBETWEENCARDS;
            cardPosY = ((cardCount + 1) / this.CARDSPERROW) * (this.CARDHEIGHT) + this.PADDINGBETWEENCARDS;  
            
            cardCount++;
            
        }
        
        this.panel_DeckBuilderPanel.setPreferredSize(new Dimension(this.panel_DeckBuilderPanel.getWidth(), cardPosY));
        this.panel_DeckBuilderPanel.revalidate();
        //this.scrollPane_DeckBuilder.revalidate();
    }
    
    /**
     * Deck builder card clicked, add card to deck.
     * @param evt 
     */
    private void btn_DeckBuilderCardActionPerformed(java.awt.event.ActionEvent evt) {                                               
        
        ParagonCard tmpCard = ((ParagonCardButton)evt.getSource()).getMyCard();
        ParagonLayeredPane tmpPane = new ParagonLayeredPane(tmpCard);
        java.awt.Component[] tmpComp;
        boolean cardDuplicated = false;
        
        // Switch on card type
        switch (tmpCard.getType()) {
            case "Prime":
                // Clear the prime panel and add new one
                this.panel_PrimeSlot.removeAll();
                this.deckBeingCreated.removePrimeCard();
                
                this.panel_PrimeSlot.add(tmpPane);
                tmpPane.setBounds(0, 0, this.panel_PrimeSlot.getWidth(), this.panel_PrimeSlot.getHeight());
                
                //Add card to deck being created
                this.deckBeingCreated.addCard(tmpCard);
                break;
            case "Upgrade":
                tmpComp = this.panel_UpgradeCards.getComponents();
                
                if (tmpComp.length > 0) {
                    for (java.awt.Component comp : tmpComp) {
                        ParagonLayeredPane testPane = (ParagonLayeredPane) comp;

                        if (tmpPane.getMyCard().equals(testPane.getMyCard())) { // Card already added so no need to add again
                            testPane.setCardCount(testPane.getCardCount() + 1);
                            cardDuplicated = true;
                        }
                    }      
                }
                else {
                    // Add to upgrade pane
                    this.panel_UpgradeCards.add(tmpPane);
                }
                
                if (!cardDuplicated) {
                    // Add to upgrade pane
                    this.panel_UpgradeCards.add(tmpPane);
                }
                
                //Add card to deck being created
                this.deckBeingCreated.addCard(tmpCard);
                break;
            case "Passive":
            case "Active":
                tmpComp = this.panel_EquipmentCards.getComponents();
                
                if (tmpComp.length > 0) {
                    for (java.awt.Component comp : tmpComp) {
                        ParagonLayeredPane testPane = (ParagonLayeredPane) comp;

                        if (tmpPane.getMyCard().equals(testPane.getMyCard())) { // Card already added so no need to add again
                            testPane.setCardCount(testPane.getCardCount() + 1);
                            cardDuplicated = true;
                        }
                    }      
                }
                else {
                    // Add to upgrade pane
                    this.panel_EquipmentCards.add(tmpPane);
                }
                
                if (!cardDuplicated) {
                    // Add to equipment pane
                    this.panel_EquipmentCards.add(tmpPane);
                }
                
                //Add card to deck being created
                this.deckBeingCreated.addCard(tmpCard);
                break;
        }
               
        
        // Update deck numbers
        this.refreshDeckBuilderNumbers();
        
    }              
    
    /**
     * Refresh deck builder numbers
     */
    public void refreshDeckBuilderNumbers() {
        
        String tmpString;
        
        // Card count
        tmpString = this.deckBeingCreated.getCardCount() + "/" + this.deckBeingCreated.getMaxCards();
        this.tb_CardCount.setText(tmpString);
        
        // Card count slider
        this.progress_CardCount.setMinimum(0);
        this.progress_CardCount.setMaximum(this.deckBeingCreated.getMaxCards());
        this.progress_CardCount.setValue(this.deckBeingCreated.getCardCount());
                
    }
    
    /**
     * Build the displayed cards array based off of filters
     */
    public void buildDisplayedCardsDeck() {
        // Filter the deck
        displayedDeck = ParagonDeck.getDeckFromFilters(masterDeck, this.showUpgrade, this.showPassive, 
                this.showActive, this.showPrime, this.myHero.getAffinities(), "", "");
        
        //System.out.println(displayedDeck);        
    }
    
    /**
     * Loads the stat images and stat numbers
     */
    public void refreshStats() {
        
        if (!this.myHero.getName().equals("Default")) {
            // get the hero level
            int heroLevel = Integer.parseInt((String.valueOf(this.cb_Levels.getSelectedItem()).split("\\s+"))[1]);

            // Basic Attack LMB
            // Icon
            String imgPath = Paths.get("./Art/Abilities/" + 
                    this.myHero.getName() + "_LMB.png").toString();
            this.BasicAttack_Icon.setIcon(new StretchIcon(imgPath));

            // Attack Speed
            this.BasicAttack_AS.setText(String.valueOf(ParagonMath.getAttackSpeed(this.myHero.getName(), 
                    "BasicAttack", heroLevel)));
            
            // Damage
            this.BasicAttack_AD.setText(String.valueOf(ParagonMath.getAttackDamage(this.myHero.getName(), 
                    "BasicAttack", heroLevel)));
            
            // DPS
            this.BasicAttack_DPS.setText(String.valueOf(ParagonMath.getAttackDamage(this.myHero.getName(), 
                    "BasicAttack", heroLevel) / ParagonMath.getAttackSpeed(this.myHero.getName(), 
                    "BasicAttack", heroLevel)));
            
            // Alternate Ability RMB
            // Icon
            imgPath = Paths.get("./Art/Abilities/" + 
                    this.myHero.getName() + "_RMB.png").toString();
            this.AlternateAbility_Icon.setIcon(new StretchIcon(imgPath));

            // Attack Speed
            this.AlternateAbility_AS.setText(String.valueOf(ParagonMath.getAttackSpeed(this.myHero.getName(), 
                    "AlternateAbility", heroLevel)));
            
            // Damage
            this.AlternateAbility_AD.setText(String.valueOf(ParagonMath.getAttackDamage(this.myHero.getName(), 
                    "AlternateAbility", heroLevel)));
            
            // DPS
            this.AlternateAbility_DPS.setText(String.valueOf(ParagonMath.getAttackDamage(this.myHero.getName(), 
                    "AlternateAbility", heroLevel) / ParagonMath.getAttackSpeed(this.myHero.getName(), 
                    "AlternateAbility", heroLevel)));
            
            // Primary Ability Q
            // Icon
            imgPath = Paths.get("./Art/Abilities/" + 
                    this.myHero.getName() + "_Q.png").toString();
            this.PrimaryAbility_Icon.setIcon(new StretchIcon(imgPath));

            // Attack Speed
            this.PrimaryAbility_AS.setText(String.valueOf(ParagonMath.getAttackSpeed(this.myHero.getName(), 
                    "PrimaryAbility", heroLevel)));
            
            // Damage
            this.PrimaryAbility_AD.setText(String.valueOf(ParagonMath.getAttackDamage(this.myHero.getName(), 
                    "AlternateAbility", heroLevel)));
            
            // DPS
            this.PrimaryAbility_DPS.setText(String.valueOf(ParagonMath.getAttackDamage(this.myHero.getName(), 
                    "PrimaryAbility", heroLevel) / ParagonMath.getAttackSpeed(this.myHero.getName(), 
                    "PrimaryAbility", heroLevel)));
            
            // Secondary Ability R
            // Icon
            imgPath = Paths.get("./Art/Abilities/" + 
                    this.myHero.getName() + "_E.png").toString();
            this.SecondaryAbility_Icon.setIcon(new StretchIcon(imgPath));

            // Attack Speed
            this.SecondaryAbility_AS.setText(String.valueOf(ParagonMath.getAttackSpeed(this.myHero.getName(), 
                    "SecondaryAbility", heroLevel)));
            
            // Damage
            this.SecondaryAbility_AD.setText(String.valueOf(ParagonMath.getAttackDamage(this.myHero.getName(), 
                    "SecondaryAbility", heroLevel)));
            
            // DPS
            this.SecondaryAbility_DPS.setText(String.valueOf(ParagonMath.getAttackDamage(this.myHero.getName(), 
                    "SecondaryAbility", heroLevel) / ParagonMath.getAttackSpeed(this.myHero.getName(), 
                    "SecondaryAbility", heroLevel)));
            
            // Ultimate Ability R
            // Icon
            imgPath = Paths.get("./Art/Abilities/" + 
                    this.myHero.getName() + "_R.png").toString();
            this.UltimateAbility_Icon.setIcon(new StretchIcon(imgPath));

            // Attack Speed
            this.UltimateAbility_AS.setText(String.valueOf(ParagonMath.getAttackSpeed(this.myHero.getName(), 
                    "UltimateAbility", heroLevel)));
            
            // Damage
            this.UltimateAbility_AD.setText(String.valueOf(ParagonMath.getAttackDamage(this.myHero.getName(), 
                    "UltimateAbility", heroLevel)));
            
            // DPS
            this.UltimateAbility_DPS.setText(String.valueOf(ParagonMath.getAttackDamage(this.myHero.getName(), 
                    "UltimateAbility", heroLevel) / ParagonMath.getAttackSpeed(this.myHero.getName(), 
                    "UltimateAbility", heroLevel)));
        }
        
    }
    
    /**
     * Sets the hero to the one clicked by the user in the hero select dialog
     * @param heroName string of hero
     */
    public void setHero(String heroName) {
        
        this.myHero.setName(heroName);
        
        // Set levles combo box
        this.cb_Levels.setModel(new javax.swing.DefaultComboBoxModel(this.getLevels()));
        
        // Refresh gui
        this.refreshGui();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        btn_HeroSelect = new javax.swing.JButton();
        panel_HeroStats = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panel_BasicAttack = new javax.swing.JPanel();
        BasicAttack_Icon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BasicAttack_AS = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        BasicAttack_AD = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        BasicAttack_DPS = new javax.swing.JLabel();
        panel_AlternateAbility = new javax.swing.JPanel();
        AlternateAbility_Icon = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        AlternateAbility_AS = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        AlternateAbility_AD = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        AlternateAbility_DPS = new javax.swing.JLabel();
        panel_PrimaryAbility = new javax.swing.JPanel();
        PrimaryAbility_Icon = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        PrimaryAbility_AS = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        PrimaryAbility_AD = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        PrimaryAbility_DPS = new javax.swing.JLabel();
        panel_SecondaryAbility = new javax.swing.JPanel();
        SecondaryAbility_Icon = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        SecondaryAbility_AS = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        SecondaryAbility_AD = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        SecondaryAbility_DPS = new javax.swing.JLabel();
        panel_UltimateAbility = new javax.swing.JPanel();
        UltimateAbility_Icon = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        UltimateAbility_AS = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        UltimateAbility_AD = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        UltimateAbility_DPS = new javax.swing.JLabel();
        panel_Affinities = new javax.swing.JPanel();
        Affinity1 = new javax.swing.JLabel();
        Affinity2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cb_Levels = new javax.swing.JComboBox<>();
        tabbedPane_MainTabbed = new javax.swing.JTabbedPane();
        panel_DeckBuilder = new javax.swing.JPanel();
        scrollPane_DeckBuilder = new javax.swing.JScrollPane();
        panel_DeckBuilderPanel = new javax.swing.JPanel();
        panel_DeckListMain = new javax.swing.JPanel();
        panel_CardCountMain = new javax.swing.JLayeredPane();
        tb_CardCount = new javax.swing.JLabel();
        progress_CardCount = new javax.swing.JProgressBar();
        z_PrimeTitle = new javax.swing.JLabel();
        panel_PrimeSlot = new javax.swing.JPanel();
        z_EquipmentCardsTitle = new javax.swing.JLabel();
        z_EquipmentCardsTitle1 = new javax.swing.JLabel();
        scrollpanel_EquipmentCards = new javax.swing.JScrollPane();
        panel_EquipmentCards = new javax.swing.JPanel();
        scrollpanel_UpgradeCards = new javax.swing.JScrollPane();
        panel_UpgradeCards = new javax.swing.JPanel();
        panel_SlotDeck = new javax.swing.JPanel();
        panel_Slots = new javax.swing.JPanel();
        btn_Slot1 = new javax.swing.JButton();
        btn_Slot2 = new javax.swing.JButton();
        btn_Slot3 = new javax.swing.JButton();
        btn_Slot4 = new javax.swing.JButton();
        btn_Slot5 = new javax.swing.JButton();
        btn_Slot6 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        panel_radios = new javax.swing.JPanel();
        radio_showActive = new javax.swing.JRadioButton();
        radio_showPassive = new javax.swing.JRadioButton();
        radio_showUpgrade = new javax.swing.JRadioButton();
        radio_showPrime = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_HeroSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroSelectActionPerformed(evt);
            }
        });

        panel_HeroStats.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Statistics");

        BasicAttack_Icon.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel5.setText("Basic Attack");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel2.setText("Attack Speed:");

        BasicAttack_AS.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BasicAttack_AS.setText("0.000");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel6.setText("Damage:");

        BasicAttack_AD.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BasicAttack_AD.setText("0.000");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LMB");

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel7.setText("DPS:");

        BasicAttack_DPS.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        BasicAttack_DPS.setText("0.000");

        javax.swing.GroupLayout panel_BasicAttackLayout = new javax.swing.GroupLayout(panel_BasicAttack);
        panel_BasicAttack.setLayout(panel_BasicAttackLayout);
        panel_BasicAttackLayout.setHorizontalGroup(
            panel_BasicAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_BasicAttackLayout.createSequentialGroup()
                .addGroup(panel_BasicAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_BasicAttackLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_BasicAttackLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(BasicAttack_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panel_BasicAttackLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_BasicAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_BasicAttackLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_BasicAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BasicAttack_AD, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BasicAttack_AS, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BasicAttack_DPS, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_BasicAttackLayout.createSequentialGroup()
                        .addGroup(panel_BasicAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panel_BasicAttackLayout.setVerticalGroup(
            panel_BasicAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_BasicAttackLayout.createSequentialGroup()
                .addGroup(panel_BasicAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_BasicAttackLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(BasicAttack_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_BasicAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(BasicAttack_AS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_BasicAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(BasicAttack_AD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_BasicAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(BasicAttack_DPS))
                .addContainerGap())
        );

        AlternateAbility_Icon.setBackground(new java.awt.Color(0, 0, 0));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel8.setText("Alternate Ability");

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel9.setText("Attack Speed:");

        AlternateAbility_AS.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        AlternateAbility_AS.setText("0.000");

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel10.setText("Damage:");

        AlternateAbility_AD.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        AlternateAbility_AD.setText("0.000");

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("RMB");

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel12.setText("DPS:");

        AlternateAbility_DPS.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        AlternateAbility_DPS.setText("0.000");

        javax.swing.GroupLayout panel_AlternateAbilityLayout = new javax.swing.GroupLayout(panel_AlternateAbility);
        panel_AlternateAbility.setLayout(panel_AlternateAbilityLayout);
        panel_AlternateAbilityLayout.setHorizontalGroup(
            panel_AlternateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_AlternateAbilityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_AlternateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_AlternateAbilityLayout.createSequentialGroup()
                        .addGroup(panel_AlternateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_AlternateAbilityLayout.createSequentialGroup()
                        .addGroup(panel_AlternateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_AlternateAbilityLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_AlternateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AlternateAbility_AD, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AlternateAbility_AS, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AlternateAbility_DPS, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panel_AlternateAbilityLayout.createSequentialGroup()
                                .addGroup(panel_AlternateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_AlternateAbilityLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18))
                                    .addGroup(panel_AlternateAbilityLayout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(AlternateAbility_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        panel_AlternateAbilityLayout.setVerticalGroup(
            panel_AlternateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_AlternateAbilityLayout.createSequentialGroup()
                .addGroup(panel_AlternateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_AlternateAbilityLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addComponent(AlternateAbility_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_AlternateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(AlternateAbility_AS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_AlternateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(AlternateAbility_AD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_AlternateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(AlternateAbility_DPS))
                .addContainerGap())
        );

        PrimaryAbility_Icon.setBackground(new java.awt.Color(0, 0, 0));

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel13.setText("Primary Ability");

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel14.setText("Attack Speed:");

        PrimaryAbility_AS.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        PrimaryAbility_AS.setText("0.000");

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel15.setText("Damage:");

        PrimaryAbility_AD.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        PrimaryAbility_AD.setText("0.000");

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Q");

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel17.setText("DPS:");

        PrimaryAbility_DPS.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        PrimaryAbility_DPS.setText("0.000");

        javax.swing.GroupLayout panel_PrimaryAbilityLayout = new javax.swing.GroupLayout(panel_PrimaryAbility);
        panel_PrimaryAbility.setLayout(panel_PrimaryAbilityLayout);
        panel_PrimaryAbilityLayout.setHorizontalGroup(
            panel_PrimaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_PrimaryAbilityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_PrimaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_PrimaryAbilityLayout.createSequentialGroup()
                        .addGroup(panel_PrimaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_PrimaryAbilityLayout.createSequentialGroup()
                        .addGroup(panel_PrimaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_PrimaryAbilityLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panel_PrimaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_PrimaryAbilityLayout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22)))
                                .addGap(18, 18, 18)
                                .addComponent(PrimaryAbility_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_PrimaryAbilityLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_PrimaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PrimaryAbility_AD, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PrimaryAbility_AS, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PrimaryAbility_DPS, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        panel_PrimaryAbilityLayout.setVerticalGroup(
            panel_PrimaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_PrimaryAbilityLayout.createSequentialGroup()
                .addGroup(panel_PrimaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_PrimaryAbilityLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addComponent(PrimaryAbility_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_PrimaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(PrimaryAbility_AS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_PrimaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(PrimaryAbility_AD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_PrimaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(PrimaryAbility_DPS))
                .addContainerGap())
        );

        SecondaryAbility_Icon.setBackground(new java.awt.Color(0, 0, 0));

        jLabel18.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel18.setText("Secondary Ability");

        jLabel19.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel19.setText("Attack Speed:");

        SecondaryAbility_AS.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        SecondaryAbility_AS.setText("0.000");

        jLabel20.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel20.setText("Damage:");

        SecondaryAbility_AD.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        SecondaryAbility_AD.setText("0.000");

        jLabel21.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("E");

        jLabel22.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel22.setText("DPS:");

        SecondaryAbility_DPS.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        SecondaryAbility_DPS.setText("0.000");

        javax.swing.GroupLayout panel_SecondaryAbilityLayout = new javax.swing.GroupLayout(panel_SecondaryAbility);
        panel_SecondaryAbility.setLayout(panel_SecondaryAbilityLayout);
        panel_SecondaryAbilityLayout.setHorizontalGroup(
            panel_SecondaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_SecondaryAbilityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_SecondaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_SecondaryAbilityLayout.createSequentialGroup()
                        .addGroup(panel_SecondaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_SecondaryAbilityLayout.createSequentialGroup()
                        .addGroup(panel_SecondaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_SecondaryAbilityLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panel_SecondaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_SecondaryAbilityLayout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SecondaryAbility_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_SecondaryAbilityLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_SecondaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SecondaryAbility_AD, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SecondaryAbility_AS, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SecondaryAbility_DPS, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        panel_SecondaryAbilityLayout.setVerticalGroup(
            panel_SecondaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_SecondaryAbilityLayout.createSequentialGroup()
                .addGroup(panel_SecondaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_SecondaryAbilityLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21))
                    .addComponent(SecondaryAbility_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_SecondaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(SecondaryAbility_AS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_SecondaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(SecondaryAbility_AD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_SecondaryAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(SecondaryAbility_DPS))
                .addContainerGap())
        );

        UltimateAbility_Icon.setBackground(new java.awt.Color(0, 0, 0));

        jLabel23.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel23.setText("Ultimate Ability");

        jLabel24.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel24.setText("Attack Speed:");

        UltimateAbility_AS.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        UltimateAbility_AS.setText("0.000");

        jLabel25.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel25.setText("Damage:");

        UltimateAbility_AD.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        UltimateAbility_AD.setText("0.000");

        jLabel26.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("R");

        jLabel27.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel27.setText("DPS:");

        UltimateAbility_DPS.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        UltimateAbility_DPS.setText("0.000");

        javax.swing.GroupLayout panel_UltimateAbilityLayout = new javax.swing.GroupLayout(panel_UltimateAbility);
        panel_UltimateAbility.setLayout(panel_UltimateAbilityLayout);
        panel_UltimateAbilityLayout.setHorizontalGroup(
            panel_UltimateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_UltimateAbilityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_UltimateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_UltimateAbilityLayout.createSequentialGroup()
                        .addGroup(panel_UltimateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel27))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_UltimateAbilityLayout.createSequentialGroup()
                        .addGroup(panel_UltimateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_UltimateAbilityLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panel_UltimateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_UltimateAbilityLayout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_UltimateAbilityLayout.createSequentialGroup()
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)))
                                .addComponent(UltimateAbility_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_UltimateAbilityLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_UltimateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(UltimateAbility_AD, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(UltimateAbility_AS, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(UltimateAbility_DPS, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        panel_UltimateAbilityLayout.setVerticalGroup(
            panel_UltimateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_UltimateAbilityLayout.createSequentialGroup()
                .addGroup(panel_UltimateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_UltimateAbilityLayout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26))
                    .addComponent(UltimateAbility_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_UltimateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(UltimateAbility_AS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_UltimateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(UltimateAbility_AD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_UltimateAbilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(UltimateAbility_DPS))
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_HeroStatsLayout = new javax.swing.GroupLayout(panel_HeroStats);
        panel_HeroStats.setLayout(panel_HeroStatsLayout);
        panel_HeroStatsLayout.setHorizontalGroup(
            panel_HeroStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_BasicAttack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_AlternateAbility, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_PrimaryAbility, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_SecondaryAbility, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_UltimateAbility, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_HeroStatsLayout.setVerticalGroup(
            panel_HeroStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_HeroStatsLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_BasicAttack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_AlternateAbility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_PrimaryAbility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_SecondaryAbility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_UltimateAbility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_Affinities.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_Affinities.setOpaque(false);

        Affinity1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Affinities");

        javax.swing.GroupLayout panel_AffinitiesLayout = new javax.swing.GroupLayout(panel_Affinities);
        panel_Affinities.setLayout(panel_AffinitiesLayout);
        panel_AffinitiesLayout.setHorizontalGroup(
            panel_AffinitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_AffinitiesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Affinity1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Affinity2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_AffinitiesLayout.setVerticalGroup(
            panel_AffinitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_AffinitiesLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_AffinitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Affinity1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Affinity2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cb_Levels.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        cb_Levels.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_Levels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_LevelsActionPerformed(evt);
            }
        });

        tabbedPane_MainTabbed.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        scrollPane_DeckBuilder.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scrollPane_DeckBuilder.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_DeckBuilder.setHorizontalScrollBar(null);

        panel_DeckBuilderPanel.setAutoscrolls(true);

        javax.swing.GroupLayout panel_DeckBuilderPanelLayout = new javax.swing.GroupLayout(panel_DeckBuilderPanel);
        panel_DeckBuilderPanel.setLayout(panel_DeckBuilderPanelLayout);
        panel_DeckBuilderPanelLayout.setHorizontalGroup(
            panel_DeckBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 986, Short.MAX_VALUE)
        );
        panel_DeckBuilderPanelLayout.setVerticalGroup(
            panel_DeckBuilderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 655, Short.MAX_VALUE)
        );

        scrollPane_DeckBuilder.setViewportView(panel_DeckBuilderPanel);

        panel_DeckListMain.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        panel_CardCountMain.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tb_CardCount.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        tb_CardCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tb_CardCount.setText("0 / 0");
        tb_CardCount.setToolTipText("");

        progress_CardCount.setBorderPainted(false);
        progress_CardCount.setOpaque(true);

        panel_CardCountMain.setLayer(tb_CardCount, javax.swing.JLayeredPane.PALETTE_LAYER);
        panel_CardCountMain.setLayer(progress_CardCount, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout panel_CardCountMainLayout = new javax.swing.GroupLayout(panel_CardCountMain);
        panel_CardCountMain.setLayout(panel_CardCountMainLayout);
        panel_CardCountMainLayout.setHorizontalGroup(
            panel_CardCountMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(panel_CardCountMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tb_CardCount, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
            .addGroup(panel_CardCountMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(progress_CardCount, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
        );
        panel_CardCountMainLayout.setVerticalGroup(
            panel_CardCountMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(panel_CardCountMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tb_CardCount, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
            .addGroup(panel_CardCountMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(progress_CardCount, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
        );

        z_PrimeTitle.setBackground(new java.awt.Color(255, 255, 51));
        z_PrimeTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        z_PrimeTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        z_PrimeTitle.setText("Prime Helix:");
        z_PrimeTitle.setOpaque(true);
        z_PrimeTitle.setPreferredSize(new java.awt.Dimension(293, 29));

        panel_PrimeSlot.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel_PrimeSlot.setPreferredSize(new java.awt.Dimension(293, 29));

        javax.swing.GroupLayout panel_PrimeSlotLayout = new javax.swing.GroupLayout(panel_PrimeSlot);
        panel_PrimeSlot.setLayout(panel_PrimeSlotLayout);
        panel_PrimeSlotLayout.setHorizontalGroup(
            panel_PrimeSlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_PrimeSlotLayout.setVerticalGroup(
            panel_PrimeSlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        z_EquipmentCardsTitle.setBackground(new java.awt.Color(51, 204, 255));
        z_EquipmentCardsTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        z_EquipmentCardsTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        z_EquipmentCardsTitle.setText("Equipment Cards:");
        z_EquipmentCardsTitle.setOpaque(true);
        z_EquipmentCardsTitle.setPreferredSize(new java.awt.Dimension(293, 29));

        z_EquipmentCardsTitle1.setBackground(new java.awt.Color(153, 255, 153));
        z_EquipmentCardsTitle1.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        z_EquipmentCardsTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        z_EquipmentCardsTitle1.setText("Upgrade Cards:");
        z_EquipmentCardsTitle1.setOpaque(true);
        z_EquipmentCardsTitle1.setPreferredSize(new java.awt.Dimension(293, 29));

        scrollpanel_EquipmentCards.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpanel_EquipmentCards.setPreferredSize(new java.awt.Dimension(293, 209));

        panel_EquipmentCards.setLayout(new java.awt.GridLayout(0, 1));
        scrollpanel_EquipmentCards.setViewportView(panel_EquipmentCards);

        scrollpanel_UpgradeCards.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpanel_UpgradeCards.setPreferredSize(new java.awt.Dimension(293, 260));

        panel_UpgradeCards.setLayout(new java.awt.GridLayout(0, 1));
        scrollpanel_UpgradeCards.setViewportView(panel_UpgradeCards);

        javax.swing.GroupLayout panel_DeckListMainLayout = new javax.swing.GroupLayout(panel_DeckListMain);
        panel_DeckListMain.setLayout(panel_DeckListMainLayout);
        panel_DeckListMainLayout.setHorizontalGroup(
            panel_DeckListMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_CardCountMain)
            .addComponent(z_PrimeTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_PrimeSlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(z_EquipmentCardsTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(z_EquipmentCardsTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollpanel_EquipmentCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollpanel_UpgradeCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_DeckListMainLayout.setVerticalGroup(
            panel_DeckListMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_DeckListMainLayout.createSequentialGroup()
                .addComponent(panel_CardCountMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(z_PrimeTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_PrimeSlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(z_EquipmentCardsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpanel_EquipmentCards, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(z_EquipmentCardsTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpanel_UpgradeCards, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panel_DeckBuilderLayout = new javax.swing.GroupLayout(panel_DeckBuilder);
        panel_DeckBuilder.setLayout(panel_DeckBuilderLayout);
        panel_DeckBuilderLayout.setHorizontalGroup(
            panel_DeckBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_DeckBuilderLayout.createSequentialGroup()
                .addComponent(scrollPane_DeckBuilder, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_DeckListMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_DeckBuilderLayout.setVerticalGroup(
            panel_DeckBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_DeckBuilderLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panel_DeckBuilderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_DeckListMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane_DeckBuilder, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPane_MainTabbed.addTab("Build Deck", panel_DeckBuilder);

        panel_Slots.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_Slot1.setBackground(new java.awt.Color(102, 102, 102));
        btn_Slot1.setText("Empty");

        btn_Slot2.setBackground(new java.awt.Color(102, 102, 102));
        btn_Slot2.setText("Empty");

        btn_Slot3.setBackground(new java.awt.Color(102, 102, 102));
        btn_Slot3.setText("Empty");

        btn_Slot4.setBackground(new java.awt.Color(102, 102, 102));
        btn_Slot4.setText("Empty");

        btn_Slot5.setBackground(new java.awt.Color(102, 102, 102));
        btn_Slot5.setText("Empty");

        btn_Slot6.setBackground(new java.awt.Color(102, 102, 102));
        btn_Slot6.setText("Empty");

        javax.swing.GroupLayout panel_SlotsLayout = new javax.swing.GroupLayout(panel_Slots);
        panel_Slots.setLayout(panel_SlotsLayout);
        panel_SlotsLayout.setHorizontalGroup(
            panel_SlotsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_SlotsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Slot1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Slot2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Slot3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Slot4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Slot5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Slot6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_SlotsLayout.setVerticalGroup(
            panel_SlotsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_SlotsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_SlotsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_SlotsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Slot2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addComponent(btn_Slot3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addComponent(btn_Slot4, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addComponent(btn_Slot5, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addComponent(btn_Slot6, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                    .addComponent(btn_Slot1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(293, 29));

        jPanel1.setPreferredSize(new java.awt.Dimension(293, 29));

        jButton1.setPreferredSize(new java.awt.Dimension(293, 29));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel29.setText("jLabel29");

        jLabel28.setText("jLabel28");
        jLabel28.setPreferredSize(new java.awt.Dimension(40, 29));

        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel29, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel28, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(131, 131, 131)
                    .addComponent(jLabel29)
                    .addContainerGap(132, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(138, 138, 138)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(138, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel29)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panel_SlotDeckLayout = new javax.swing.GroupLayout(panel_SlotDeck);
        panel_SlotDeck.setLayout(panel_SlotDeckLayout);
        panel_SlotDeckLayout.setHorizontalGroup(
            panel_SlotDeckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_SlotDeckLayout.createSequentialGroup()
                .addGroup(panel_SlotDeckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_SlotDeckLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_Slots, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_SlotDeckLayout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(461, Short.MAX_VALUE))
        );
        panel_SlotDeckLayout.setVerticalGroup(
            panel_SlotDeckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_SlotDeckLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 391, Short.MAX_VALUE)
                .addComponent(panel_Slots, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPane_MainTabbed.addTab("Slot Cards", panel_SlotDeck);

        radio_showActive.setSelected(true);
        radio_showActive.setText("Show Active");
        radio_showActive.setActionCommand("");
        radio_showActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_showActiveActionPerformed(evt);
            }
        });

        radio_showPassive.setSelected(true);
        radio_showPassive.setText("Show Passive");
        radio_showPassive.setActionCommand("");
        radio_showPassive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_showPassiveActionPerformed(evt);
            }
        });

        radio_showUpgrade.setSelected(true);
        radio_showUpgrade.setText("Show Upgrade");
        radio_showUpgrade.setActionCommand("");
        radio_showUpgrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_showUpgradeActionPerformed(evt);
            }
        });

        radio_showPrime.setSelected(true);
        radio_showPrime.setText("Show Prime");
        radio_showPrime.setActionCommand("");
        radio_showPrime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_showPrimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_radiosLayout = new javax.swing.GroupLayout(panel_radios);
        panel_radios.setLayout(panel_radiosLayout);
        panel_radiosLayout.setHorizontalGroup(
            panel_radiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_radiosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radio_showActive)
                .addGap(10, 10, 10)
                .addComponent(radio_showPassive)
                .addGap(18, 18, 18)
                .addComponent(radio_showUpgrade)
                .addGap(18, 18, 18)
                .addComponent(radio_showPrime)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_radiosLayout.setVerticalGroup(
            panel_radiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_radiosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_radiosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radio_showActive)
                    .addComponent(radio_showPassive)
                    .addComponent(radio_showUpgrade)
                    .addComponent(radio_showPrime))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_HeroSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panel_Affinities, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cb_Levels, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(panel_HeroStats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPane_MainTabbed)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_radios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_Affinities, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_Levels, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_HeroSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panel_radios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_HeroStats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabbedPane_MainTabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tabbedPane_MainTabbed.getAccessibleContext().setAccessibleName("Deck Builder");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_HeroSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroSelectActionPerformed

        // Open the hero select form
        ParagonHeroSelector heroSelector = new ParagonHeroSelector(this, true);
        heroSelector.setVisible(true);

    }//GEN-LAST:event_btn_HeroSelectActionPerformed

    private void cb_LevelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_LevelsActionPerformed
        // Refresh stats
        this.refreshStats();
    }//GEN-LAST:event_cb_LevelsActionPerformed

    private void radio_showActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_showActiveActionPerformed
        this.showActive = this.radio_showActive.isSelected();
        this.refreshDeckBuilderCards();
    }//GEN-LAST:event_radio_showActiveActionPerformed

    private void radio_showPassiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_showPassiveActionPerformed
        this.showPassive = this.radio_showPassive.isSelected();
        this.refreshDeckBuilderCards();
    }//GEN-LAST:event_radio_showPassiveActionPerformed

    private void radio_showUpgradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_showUpgradeActionPerformed
        this.showUpgrade = this.radio_showUpgrade.isSelected();
        this.refreshDeckBuilderCards();
    }//GEN-LAST:event_radio_showUpgradeActionPerformed

    private void radio_showPrimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_showPrimeActionPerformed
        this.showPrime = this.radio_showPrime.isSelected();
        this.refreshDeckBuilderCards();
    }//GEN-LAST:event_radio_showPrimeActionPerformed

    /**
     * Returns the point of the hero selector button
     * @return Point string of hero
     */
    public Point getBtnHeroSelectLocation() {
        Point p = this.btn_HeroSelect.getLocation();
        SwingUtilities.convertPointToScreen(p, this.btn_HeroSelect);
        return p;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParagonDeckBuilderMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParagonDeckBuilderMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParagonDeckBuilderMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParagonDeckBuilderMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParagonDeckBuilderMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Affinity1;
    private javax.swing.JLabel Affinity2;
    private javax.swing.JLabel AlternateAbility_AD;
    private javax.swing.JLabel AlternateAbility_AS;
    private javax.swing.JLabel AlternateAbility_DPS;
    private javax.swing.JLabel AlternateAbility_Icon;
    private javax.swing.JLabel BasicAttack_AD;
    private javax.swing.JLabel BasicAttack_AS;
    private javax.swing.JLabel BasicAttack_DPS;
    private javax.swing.JLabel BasicAttack_Icon;
    private javax.swing.JLabel PrimaryAbility_AD;
    private javax.swing.JLabel PrimaryAbility_AS;
    private javax.swing.JLabel PrimaryAbility_DPS;
    private javax.swing.JLabel PrimaryAbility_Icon;
    private javax.swing.JLabel SecondaryAbility_AD;
    private javax.swing.JLabel SecondaryAbility_AS;
    private javax.swing.JLabel SecondaryAbility_DPS;
    private javax.swing.JLabel SecondaryAbility_Icon;
    private javax.swing.JLabel UltimateAbility_AD;
    private javax.swing.JLabel UltimateAbility_AS;
    private javax.swing.JLabel UltimateAbility_DPS;
    private javax.swing.JLabel UltimateAbility_Icon;
    private javax.swing.JButton btn_HeroSelect;
    private javax.swing.JButton btn_Slot1;
    private javax.swing.JButton btn_Slot2;
    private javax.swing.JButton btn_Slot3;
    private javax.swing.JButton btn_Slot4;
    private javax.swing.JButton btn_Slot5;
    private javax.swing.JButton btn_Slot6;
    private javax.swing.JComboBox<String> cb_Levels;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPanel panel_Affinities;
    private javax.swing.JPanel panel_AlternateAbility;
    private javax.swing.JPanel panel_BasicAttack;
    private javax.swing.JLayeredPane panel_CardCountMain;
    private javax.swing.JPanel panel_DeckBuilder;
    private javax.swing.JPanel panel_DeckBuilderPanel;
    private javax.swing.JPanel panel_DeckListMain;
    private javax.swing.JPanel panel_EquipmentCards;
    private javax.swing.JPanel panel_HeroStats;
    private javax.swing.JPanel panel_PrimaryAbility;
    private javax.swing.JPanel panel_PrimeSlot;
    private javax.swing.JPanel panel_SecondaryAbility;
    private javax.swing.JPanel panel_SlotDeck;
    private javax.swing.JPanel panel_Slots;
    private javax.swing.JPanel panel_UltimateAbility;
    private javax.swing.JPanel panel_UpgradeCards;
    private javax.swing.JPanel panel_radios;
    private javax.swing.JProgressBar progress_CardCount;
    private javax.swing.JRadioButton radio_showActive;
    private javax.swing.JRadioButton radio_showPassive;
    private javax.swing.JRadioButton radio_showPrime;
    private javax.swing.JRadioButton radio_showUpgrade;
    private javax.swing.JScrollPane scrollPane_DeckBuilder;
    private javax.swing.JScrollPane scrollpanel_EquipmentCards;
    private javax.swing.JScrollPane scrollpanel_UpgradeCards;
    private javax.swing.JTabbedPane tabbedPane_MainTabbed;
    private javax.swing.JLabel tb_CardCount;
    private javax.swing.JLabel z_EquipmentCardsTitle;
    private javax.swing.JLabel z_EquipmentCardsTitle1;
    private javax.swing.JLabel z_PrimeTitle;
    // End of variables declaration//GEN-END:variables

    
}
