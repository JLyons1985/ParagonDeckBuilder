
package com.lyonsdensoftware.paragon;

/**
 *
 * @author Joshua Lyons
 */
public class ParagonCard {
    
    // Variables
    private String cardName;
    private int cost;
    private String type;
    private String affinity;
    private String rarity;
//    private double energyDamage;
//    private double manaRegen;
//    private double maxHealth;
//    private double physicalDamage;
//    private double healthRegen;
//    private double maxMana;
//    private double energyPen;
//    private double physicalArmor;
//    private double cooldownReduction;
//    private double physicalPen;
//    private double energyArmor;
//    private double critChance;
//    private double lifesteal;
//    private double critBonus;
//    private double attackSpeed;
//    private double harvesterPlacementTime;
//    private double maxMovementSpeed;
//    private double damageBonus;
//    private double maxedEnergyDamage;
//    private double maxedManaRegen;
//    private double maxedMaxHealth;
//    private double maxedPhysicalDamage;
//    private double maxedHealthRegen;
//    private double maxedMaxMana;
//    private double maxedEnergyPen;
//    private double maxedPhysicalArmor;
//    private double maxedCooldownReduction;
//    private double maxedPhysicalPen;
//    private double maxedEnergyArmor;
//    private double maxedCritChance;
//    private double maxedLifesteal;
//    private double maxedCritBonus;
//    private double maxedAttackSpeed;
//    private double maxedHarvesterPlacementTime;
//    private double maxedMaxMovementSpeed;
//    private double maxedDamageBonus;
//    private String special0;
//    private String special1;
//    private String special2;
//    private String maxedSpecial0;
//    private String maxedSpecial1;
//    private String maxedSpecial2;

    /**
     * Constructor
     * @param cardName is the name of the card
     * @param cost is the cost of the card
     * @param type is the type of the card
     * @param affinity is the name of the card
     * @param rarity is the rarity of the card
     */
    public ParagonCard(String cardName, int cost, String type, String affinity, String rarity){
        this.cardName = cardName;
        this.cost = cost;
        this.type = type;
        this.affinity = affinity;
        this.rarity = rarity;
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
        return affinity;
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
        return rarity;
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
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "EnergyDamage"));
    }

    /**
     * @return the manaRegen
     */
    public double getManaRegen() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "ManaRegen"));
    }

    /**
     * @return the maxHealth
     */
    public double getMaxHealth() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxHealth"));
    }

    /**
     * @return the physicalDamage
     */
    public double getPhysicalDamage() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "PhysicalDamage"));
    }

    /**
     * @return the healthRegen
     */
    public double getHealthRegen() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "HealthRegen"));
    }

    /**
     * @return the maxMana
     */
    public double getMaxMana() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxMana"));
    }

    /**
     * @return the energyPen
     */
    public double getEnergyPen() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "EnergyPen"));
    }

    /**
     * @return the physicalArmor
     */
    public double getPhysicalArmor() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "PhysicalArmor"));
    }

    /**
     * @return the cooldownReduction
     */
    public double getCooldownReduction() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "CooldownReduction"));
    }

    /**
     * @return the physicalPen
     */
    public double getPhysicalPen() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "PhysicalPen"));
    }

    /**
     * @return the energyArmor
     */
    public double getEnergyArmor() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "EnergyArmor"));
    }

    /**
     * @return the critChance
     */
    public double getCritChance() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "CritChance"));
    }

    /**
     * @return the lifesteal
     */
    public double getLifesteal() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "Lifesteal"));
    }

    /**
     * @return the critBonus
     */
    public double getCritBonus() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "CritBonus"));
    }

    /**
     * @return the attackSpeed
     */
    public double getAttackSpeed() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "AttackSpeed"));
    }

    /**
     * @return the harvesterPlacementTime
     */
    public double getHarvesterPlacementTime() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "HarvesterPlacementTime"));
    }

    /**
     * @return the maxMovementSpeed
     */
    public double getMaxMovementSpeed() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxMovementSpeed"));
    }

    /**
     * @return the damageBonus
     */
    public double getDamageBonus() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "DamageBonus"));
    }

    /**
     * @return the maxedEnergyDamage
     */
    public double getMaxedEnergyDamage() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedEnergyDamage"));
    }

    /**
     * @return the maxedManaRegen
     */
    public double getMaxedManaRegen() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedManaRegen"));
    }

    /**
     * @return the maxedMaxHealth
     */
    public double getMaxedMaxHealth() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedMaxHealth"));
    }

    /**
     * @return the maxedPhysicalDamage
     */
    public double getMaxedPhysicalDamage() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedPhysicalDamage"));
    }

    /**
     * @return the maxedHealthRegen
     */
    public double getMaxedHealthRegen() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedHealthRegen"));
    }

    /**
     * @return the maxedMaxMana
     */
    public double getMaxedMaxMana() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedMaxMana"));
    }

    /**
     * @return the maxedEnergyPen
     */
    public double getMaxedEnergyPen() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedEnergyPen"));
    }

    /**
     * @return the maxedPhysicalArmor
     */
    public double getMaxedPhysicalArmor() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedPhysicalArmor"));
    }

    /**
     * @return the maxedCooldownReduction
     */
    public double getMaxedCooldownReduction() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedCooldownRedction"));
    }

    /**
     * @return the maxedPhysicalPen
     */
    public double getMaxedPhysicalPen() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedPhysicalPen"));
    }

    /**
     * @return the maxedEnergyArmor
     */
    public double getMaxedEnergyArmor() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedEnergyArmor"));
    }

    /**
     * @return the maxedCritChance
     */
    public double getMaxedCritChance() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedCritChance"));
    }

    /**
     * @return the maxedLifesteal
     */
    public double getMaxedLifesteal() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedLifesteal"));
    }

    /**
     * @return the maxedCritBonus
     */
    public double getMaxedCritBonus() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedCritBonus"));
    }

    /**
     * @return the maxedAttackSpeed
     */
    public double getMaxedAttackSpeed() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedAttackSpeed"));
    }

    /**
     * @return the maxedHarvesterPlacementTime
     */
    public double getMaxedHarvesterPlacementTime() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedHarvesterPlacementTime"));
    }

    /**
     * @return the maxedMaxMovementSpeed
     */
    public double getMaxedMaxMovementSpeed() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedMaxMovementSpeed"));
    }

    /**
     * @return the maxedDamageBonus
     */
    public double getMaxedDamageBonus() {
        return Double.parseDouble(XmlParser.getSpecificCardDataFromXml(this.cardName, "MaxedDamageBonus"));
    }

    /**
     * @return the special0
     */
    public String getSpecial0() {
        return XmlParser.getSpecificCardDataFromXml(this.cardName, "Special0");
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
    
}
