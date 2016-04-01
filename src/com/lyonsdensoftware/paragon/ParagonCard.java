package com.lyonsdensoftware.paragon;

import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Joshua Lyons
 */
public class ParagonCard implements java.io.Serializable {

    // Variables
    private String cardName;
    private int cost, id;
    private String type;
    private String affinity;
    private String rarity;
    private boolean physicalDamage;
    private boolean energyDamage;
    private LinkedList<ParagonCard> upgrades;     // Reference to the upgrade cards
    private transient StretchIcon cardImage;            // Reference to the card image

    /**
     * Constructor
     *
     * @param cardName is the name of the card
     * @param cost is the cost of the card
     * @param type is the type of the card
     * @param affinity is the name of the card
     * @param rarity is the rarity of the card
     * @param physicalDamage
     * @param energyDamage
     * @param id
     */
    public ParagonCard(String cardName, int cost, String type, String affinity, 
            String rarity, int id, boolean physicalDamage, boolean energyDamage) {
        this.cardName = cardName;
        this.cost = cost;
        this.type = type;
        this.affinity = affinity;
        this.rarity = rarity;
        this.id = id;
        this.physicalDamage = physicalDamage;
        this.energyDamage = energyDamage;
        this.upgrades = new LinkedList<ParagonCard>();
    }

    /**
     *
     * @return upgrade cards
     */
    public LinkedList<ParagonCard> getUpgradeCards() {
        return this.upgrades;
    }

    /**
     * Removes the provided card from the upgrades
     *
     * @param card to remove
     */
    public void removeCardFromUpgrades(ParagonCard card) {
        this.upgrades.remove(card);
    }

    /**
     * Adds the upgrade card to the upgrades
     *
     * @param card to add
     * @return true if success
     */
    public boolean addCardToUpgrades(ParagonCard card) {
        if (this.upgrades.size() < 3 && card.getType().equals("Upgrade")) {
            this.upgrades.add(card);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return if this card can be upgraded
     */
    public boolean canCardBeUpgraded() {
        if (this.upgrades.size() < 3 && !this.rarity.equals("Starter")) {
            return true;
        } else {
            return false;
        }
    }

    public double[] getCardBonuses() {

        double[] cardBonuses = new double[15];

        // Each slot = a bonus so go through individually
        cardBonuses[0] = this.getAttackSpeed();
        cardBonuses[1] = this.getCooldownReduction();
        cardBonuses[2] = this.getCritBonus();
        cardBonuses[3] = this.getCritChance();
        cardBonuses[4] = this.getEnergyArmor();
        cardBonuses[5] = this.getEnergyDamage();
        cardBonuses[6] = this.getEnergyPen();
        cardBonuses[7] = this.getHealthRegen();
        cardBonuses[8] = this.getLifesteal();
        cardBonuses[9] = this.getManaRegen();
        cardBonuses[10] = this.getMaxHealth();
        cardBonuses[11] = this.getMaxMana();
        cardBonuses[12] = this.getPhysicalArmor();
        cardBonuses[13] = this.getPhysicalDamage();
        cardBonuses[14] = this.getPhysicalPen();

        // Now go through any upgrade cards
        Iterator<ParagonCard> myiter = this.upgrades.iterator();
        
        while (myiter.hasNext()) {
            ParagonCard tmpCard = myiter.next();
            double [] tmpBonuses = tmpCard.getCardBonuses();
            for (int i = 0; i < 15; i++)
            {
                cardBonuses[i] += tmpBonuses[i];
            }
        }
        
        // Now if the card is max upgraded get the bonus
        if (this.upgrades.size() == 3){
            cardBonuses[0] += this.getMaxedAttackSpeed();
            cardBonuses[1] += this.getMaxedCooldownReduction();
            cardBonuses[2] += this.getMaxedCritBonus();
            cardBonuses[3] += this.getMaxedCritChance();
            cardBonuses[4] += this.getMaxedEnergyArmor();
            cardBonuses[5] += this.getMaxedEnergyDamage();
            cardBonuses[6] += this.getMaxedEnergyPen();
            cardBonuses[7] += this.getMaxedHealthRegen();
            cardBonuses[8] += this.getMaxedLifesteal();
            cardBonuses[9] += this.getMaxedManaRegen();
            cardBonuses[10] += this.getMaxedMaxHealth();
            cardBonuses[11] += this.getMaxedMaxMana();
            cardBonuses[12] += this.getMaxedPhysicalArmor();
            cardBonuses[13] += this.getMaxedPhysicalDamage();
            cardBonuses[14] += this.getMaxedPhysicalPen();
        }

        return cardBonuses;
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
        
        switch (getAffinity()) {
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
        
        switch (getRarity()) {
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
        
        if (getId() < 10)
            id = "0" + getId();
        else
            id = String.valueOf(getId());
        
               
        if (!Paths.get("./Art/Cards/Beta_" + rarity + "_" + affinity + "_" + id + "_Full.png").toFile().exists()) {
            
            System.out.println("Can't find: " + "/Art/Cards/Beta" + 
                rarity + "_" + affinity + "_" + id + "_Full.png" + " " + getCardName());
            
            return "";
        }
        else
            return "Beta_" + rarity + "_" + affinity + "_" + id + "_Full.png";
        
        
    }

    /**
     * @return the cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @param cardName the cardName to set
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the affinity
     */
    public String getAffinity() {
        return this.affinity;
    }

    /**
     * @param affinity the affinity to set
     */
    public void setAffinity(String affinity) {
        this.affinity = affinity;
    }

    /**
     * @return the rarity
     */
    public String getRarity() {
        return this.rarity;
    }

    /**
     * @param rarity the rarity to set
     */
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    /**
     * @return the energyDamage
     */
    public double getEnergyDamage() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "EnergyDamage");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the manaRegen
     */
    public double getManaRegen() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "ManaRegen");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxHealth
     */
    public double getMaxHealth() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxHealth");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the physicalDamage
     */
    public double getPhysicalDamage() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "PhysicalDamage");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the healthRegen
     */
    public double getHealthRegen() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "HealthRegen");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxMana
     */
    public double getMaxMana() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxMana");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the energyPen
     */
    public double getEnergyPen() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "EnergyPen");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the physicalArmor
     */
    public double getPhysicalArmor() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "PhysicalArmor");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the cooldownReduction
     */
    public double getCooldownReduction() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "CooldownReduction");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the physicalPen
     */
    public double getPhysicalPen() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "PhysicalPen");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the energyArmor
     */
    public double getEnergyArmor() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "EnergyArmor");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the critChance
     */
    public double getCritChance() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "CritChance");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the lifesteal
     */
    public double getLifesteal() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "Lifesteal");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the critBonus
     */
    public double getCritBonus() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "CritBonus");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the attackSpeed
     */
    public double getAttackSpeed() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "AttackSpeed");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the harvesterPlacementTime
     */
    public double getHarvesterPlacementTime() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "HarvesterPlacementTime");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxMovementSpeed
     */
    public double getMaxMovementSpeed() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxMovementSpeed");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the damageBonus
     */
    public double getDamageBonus() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "DamageBonus");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedEnergyDamage
     */
    public double getMaxedEnergyDamage() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedEnergyDamage");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedManaRegen
     */
    public double getMaxedManaRegen() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedManaRegen");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedMaxHealth
     */
    public double getMaxedMaxHealth() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedMaxHealth");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedPhysicalDamage
     */
    public double getMaxedPhysicalDamage() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedPhysicalDamage");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedHealthRegen
     */
    public double getMaxedHealthRegen() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedHealthRegen");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedMaxMana
     */
    public double getMaxedMaxMana() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedMaxMana");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedEnergyPen
     */
    public double getMaxedEnergyPen() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedEnergyPen");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedPhysicalArmor
     */
    public double getMaxedPhysicalArmor() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedPhysicalArmor");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedCooldownReduction
     */
    public double getMaxedCooldownReduction() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedCooldownReduction");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedPhysicalPen
     */
    public double getMaxedPhysicalPen() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedPhysicalPen");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedEnergyArmor
     */
    public double getMaxedEnergyArmor() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedEnergyArmor");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedCritChance
     */
    public double getMaxedCritChance() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedCritChance");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedLifesteal
     */
    public double getMaxedLifesteal() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedLifesteal");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedCritBonus
     */
    public double getMaxedCritBonus() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedCritBonus");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedAttackSpeed
     */
    public double getMaxedAttackSpeed() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedAttackSpeed");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedHarvesterPlacementTime
     */
    public double getMaxedHarvesterPlacementTime() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedHArvesterPlacementTime");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedMaxMovementSpeed
     */
    public double getMaxedMaxMovementSpeed() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedMaxMovementSpeed");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the maxedDamageBonus
     */
    public double getMaxedDamageBonus() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedDamageBonus");
        if (!tmpString.equals(""))
            return Double.parseDouble(tmpString);
        else
            return 0.0;
    }

    /**
     * @return the special0
     */
    public String getSpecial0() {
        String tmpString = XmlParser.getSpecificCardDataFromXml(this.cardName, "Special0");
        return tmpString;
    }

    /**
     * @return the special1
     */
    public String getSpecial1() {
        return XmlParser.getSpecificCardDataFromXml(this.cardName, "Special1");
    }

    /**
     * @return the special2
     */
    public String getSpecial2() {
        return XmlParser.getSpecificCardDataFromXml(this.cardName, "Special2");
    }

    /**
     * @return the maxedSpecial0
     */
    public String getMaxedSpecial0() {
        return XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedSpecial0");
    }

    /**
     * @return the maxedSpecial1
     */
    public String getMaxedSpecial1() {
        return XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedSpecial1");
    }

    /**
     * @return the maxedSpecial2
     */
    public String getMaxedSpecial2() {
        return XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedSpecial2");
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the physicalDamage
     */
    public boolean isPhysicalDamage() {
        return physicalDamage;
    }

    /**
     * @return the energyDamage
     */
    public boolean isEnergyDamage() {
        return energyDamage;
    }
    
    /**
     * Returns the card points
     * @return 
     */
    public int getCardPoints() {
        int tmpInt = this.getCost();
        
        // Now go through any upgrade cards
        Iterator<ParagonCard> myiter = this.upgrades.iterator();
        
        while (myiter.hasNext()) {
            tmpInt += myiter.next().getCost();
        }
        
        return tmpInt;
    }

    /**
     * @return the cardImage
     */
    public StretchIcon getCardImage() {
        return cardImage;
    }

    /**
     * @param cardImage the cardImage to set
     */
    public void setCardImage(StretchIcon cardImage) {
        this.cardImage = cardImage;
    }

}
