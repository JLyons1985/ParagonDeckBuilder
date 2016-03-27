
package com.lyonsdensoftware.paragon;

/**
 *
 * @author Joshua Lyons
 */
public class ParagonHero {
    
    private String heroName;                // Holds the heroes name
    
    /**
     * Constructor
     * @param heroName is the string name of the hero
     */
    public ParagonHero(String heroName) {
        this.heroName = heroName;
    }
    
    /**
     * Sets the heros name
     * @param heroName string of the name
     */
    public void setName(String heroName) {
        this.heroName = heroName;
    }
    
    /**
     * Returns the hero name
     * @return string
     */
    public String getName() {
        return this.heroName;
    }
    
    /**
     * Grabs the max level
     * @return int
     */
    public int getMaxLevel() {
        return XmlParser.getSingleStatAsInt(this.heroName, "MaxLevel");
    }
    
    /**
     * Grabs an array of affinities in string format
     * @return string array
     */
    public String[] getAffinities() {
        return XmlParser.getAffinities(this.heroName);
    }
    
    /**
     * Grabs an array of roles in string format
     * @return string array
     */
    public String[] getRoles() {
        return XmlParser.getRoles(this.heroName);
    }
    
    /**
     * Grabs the basic attack
     * @return int
     */
    public int getBasicAttack() {
        return XmlParser.getHeroBaseStat(this.heroName, "BasicAttack");
    }
    
    /**
     * Grabs the ability attack
     * @return int
     */
    public int getAbilityAttack() {
        return XmlParser.getHeroBaseStat(this.heroName, "AbilityAttack");
    }
    
    /**
     * Grabs the durability
     * @return int
     */
    public int getDurability() {
        return XmlParser.getHeroBaseStat(this.heroName, "Durability");
    }
    
    /**
     * Grabs the mobility
     * @return int
     */
    public int getMobility() {
        return XmlParser.getHeroBaseStat(this.heroName, "Mobility");
    }
    
    /**
     * Grabs the difficulty
     * @return int
     */
    public int getDifficulty() {
        return XmlParser.getHeroBaseStat(this.heroName, "Difficulty");
    }
    
    /**
     * Grabs the passive name
     * @return string
     */
    public String getPassiveName() {
        return XmlParser.getPassiveInfo(this.heroName, "Name");
    }
    
    /**
     * Grabs the passive description
     * @return string
     */
    public String getPassiveDescription() {
        return XmlParser.getPassiveInfo(this.heroName, "Description");
    }
    
    /**
     * Grabs the XPToLevel stat at provided lvl
     * @return double
     */
    public double getXPToLevel(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "XPToLevel", level);
    }
    
    /**
     * Grabs the XPBounty stat at provided lvl
     * @return double
     */
    public double getXPBounty(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "XPBounty", level);
    }
    
    /**
     * Grabs the GainXPFromKills stat at provided lvl
     * @return double
     */
    public double getGainXPFromKills(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "GainXPFromKills", level);
    }
    
    /**     
     * Grabs the MaxCardLevel stat at provided lvl     
     * @return double     
     */    
    public double getMaxCardLevel(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "MaxCardLevel", level);
    }    
    
    /**     
     * Grabs the CardXPToLevel stat at provided lvl     
     * @return double     */    
    public double getCardXPToLevel(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "CardXPToLevel", level);
     }    
     
    /**     
     * Grabs the MaxHealth stat at provided lvl     
     * @return double     
     */    
     public double getMaxHealth(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "MaxHealth", level);
     }    
     
    /**     
     * Grabs the HealthRegenRate stat at provided lvl     
     * @return double     
     */    
     public double getHealthRegenRate(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "HealthRegenRate", level);
     }   
     
    /**     
     * Grabs the CriticallyWoundedThresholdPCT stat at provided lvl     
     * @return double     
     */    
     public double getCriticallyWoundedThresholdPCT(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "CriticallyWoundedThresholdPCT", level);
     }    
     
    /**     
     * Grabs the EnergyResistanceRatingstat at provided lvl     
     * @return double     
     */    
     public double getEnergyResistanceRating(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "EnergyResistanceRating", level);
     }    
     
    /**     
     * Grabs the PhysicalResistanceRating stat at provided lvl     
     * @return double     
     */    
     public double getPhysicalResistanceRating(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "PhysicalResistanceRating", level);
     }    
     
    /**     
     * Grabs the AttackRating stat at provided lvl     
     * @return double     
     */    
     public double getAttackRating(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "AttackRating", level);
     }  
     
    /**     
     * Grabs the BaseAttackTime stat at provided lvl     
     * @return double     
     */    
     public double getBaseAttackTime(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "BaseAttackTime", level);
     }    
     
    /**     
     * Grabs the AttackSpeedRating stat at provided lvl     
     * @return double     
     */    
     public double getAttackSpeedRating(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "AttackSpeedRating", level);
     }    
     
    /**     
     * Grabs the CleaveRating stat at provided lvl     
     * @return double     
     */    
     public double getCleaveRating(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "CleaveRating", level);
     }    
     
    /**     
     * Grabs the CriticalDamageBonus stat at provided lvl     
     * @return double     
     */    
     public double getCriticalDamageBonus(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "CriticalDamageBonus", level);
     }    
     
    /**     
     * Grabs the EnergyPenetrationRating stat at provided lvl     
     * @return double     
     */    
     public double getEnergyPenetrationRating(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "EnergyPenetrationRating", level);
     }    
     
    /**     
     * Grabs the PhysicalPenetrationRating stat at provided lvl     
     * @return double     
     */    
     public double getPhysicalPenetrationRating(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "PhysicalPenetrationRating", level);
     }
     
    /**     
     * Grabs the MaxMoveSpeed stat at provided lvl     
     * @return double     
     */    
     public double getMaxMoveSpeed(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "MaxMoveSpeed", level);
     }    
    /**    
     * Grabs the MaxEnergy stat at provided lvl     
     * @return double     
     */    
     public double getMaxEnergy(int level) {
         return XmlParser.getHeroStatAtLevel(this.heroName, "MaxEnergy", level);
     }    
     
    /**     
     * Grabs the EnergyRegenRate stat at provided lvl     
     * @return double     
     */    
     public double getEnergyRegenRate(int level) {
             return XmlParser.getHeroStatAtLevel(this.heroName, "EnergyRegenRate", level);
    }    
     
    /**     
     * Grabs the VisionRadius stat at provided lvl     
     * @return double     
     */    
     public double getVisionRadius(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "VisionRadius", level);
    }    
     
    /**     
     * Grabs the BasePowerBasicPrimary stat at provided lvl     
     * @return double     
     */    
     public double getBasePowerBasicPrimary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "BasePower.Basic.Primary", level);
    }  
     
    /**     
    * Grabs the ARCBasicPrimary stat at provided lvl     
    * @return double     
    */    
    public double getARCBasicPrimary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "ARC.Basic.Primary", level);
    }
    
    /**     
    * Grabs the CostEnergyBasicPrimary stat at provided lvl     
    * @return double     
    */    
    public double getCostEnergyBasicPrimary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Cost.Energy.Basic.Primary", level);
    }
    
    /**     
    * Grabs the CooldownBasicPrimary stat at provided lvl     
    * @return double     
    */    
    public double getCooldownBasicPrimary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Cooldown.Basic.Primary", level);
    }
    
    /**     
    * Grabs the BRangeBasicPrimary stat at provided lvl     
    * @return double     
    */    
    public double getRangeBasicPrimary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Range.Basic.Primary", level);
    }
    
    /**     
     * Grabs the BasePowerAbilityAlternate stat at provided lvl     
     * @return double     
     */    
     public double getBasePowerAbilityAlternate(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "BasePower.Ability.Alternate", level);
    }  
     
    /**     
    * Grabs the ARCAbilityAlternate stat at provided lvl     
    * @return double     
    */    
    public double getARCAbilityAlternate(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "ARC.Ability.Alternate", level);
    }
    
    /**     
    * Grabs the CostEnergyAbilityAlternate stat at provided lvl     
    * @return double     
    */    
    public double getCostEnergyAbilityAlternate(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Cost.Energy.Ability.Alternate", level);
    }
    
    /**     
    * Grabs the CooldownAbilityAlternate stat at provided lvl     
    * @return double     
    */    
    public double getCooldownAbilityAlternate(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Cooldown.Ability.Alternate", level);
    }
    
    /**     
    * Grabs the RangeAbilityAlternatestat at provided lvl     
    * @return double     
    */    
    public double getRangeAbilityAlternate(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Range.Ability.Alternate", level);
    }
    
    /**     
     * Grabs the BasePowerAbilityPrimary stat at provided lvl     
     * @return double     
     */    
     public double getBasePowerAbilityPrimary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "BasePower.Ability.Primary", level);
    }  
     
    /**     
    * Grabs the ARCAbilityPrimary stat at provided lvl     
    * @return double     
    */    
    public double getARCAbilityPrimary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "ARC.Ability.Primary", level);
    }
    
    /**     
    * Grabs the CostEnergyAbilityPrimary stat at provided lvl     
    * @return double     
    */    
    public double getCostEnergyAbilityPrimary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Cost.Energy.Ability.Primary", level);
    }
    
    /**     
    * Grabs the CooldownAbilityPrimary stat at provided lvl     
    * @return double     
    */    
    public double getCooldownAbilityPrimary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Cooldown.Ability.Primary", level);
    }
    
    /**     
    * Grabs the RangeAbilityPrimary stat at provided lvl     
    * @return double     
    */    
    public double getRangeAbilityPrimary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Range.Ability.Primary", level);
    }
    
    /**     
     * Grabs the BasePowerAbilitySecondary stat at provided lvl     
     * @return double     
     */    
     public double getBasePowerAbilitySecondary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "BasePower.Ability.Secondary", level);
    }  
     
    /**     
    * Grabs the ARCAbilitySecondary stat at provided lvl     
    * @return double     
    */    
    public double getARCAbilitySecondary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "ARC.Ability.Secondary", level);
    }
    
    /**     
    * Grabs the CostEnergyAbilitySecondary stat at provided lvl     
    * @return double     
    */    
    public double getCostEnergyAbilitySecondary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Cost.Energy.Ability.Secondary", level);
    }
    
    /**     
    * Grabs the CooldownAbilitySecondary stat at provided lvl     
    * @return double     
    */    
    public double getCooldownAbilitySecondary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Cooldown.Ability.Secondary", level);
    }
    
    /**     
    * Grabs the RangeAbilitySecondary stat at provided lvl     
    * @return double     
    */    
    public double getRangeAbilitySecondary(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Range.Ability.Secondary", level);
    }

    /**     
     * Grabs the BasePowerAbilityUltimate stat at provided lvl     
     * @return double     
     */    
     public double getBasePowerAbilityUltimate(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "BasePower.Ability.Ultimate", level);
    }  
     
    /**     
    * Grabs the ARCAbilityUltimate stat at provided lvl     
    * @return double     
    */    
    public double getARCAbilityUltimate(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "ARC.Ability.Ultimate", level);
    }
    
    /**     
    * Grabs the CostEnergyAbilityUltimate stat at provided lvl     
    * @return double     
    */    
    public double getCostEnergyAbilityUltimate(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Cost.Energy.Ability.Ultimate", level);
    }
    
    /**     
    * Grabs the CooldownAbilityUltimate stat at provided lvl     
    * @return double     
    */    
    public double getCooldownAbilityUltimate(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Cooldown.Ability.Ultimate", level);
    }
    
    /**     
    * Grabs the RangeAbilityUltimate stat at provided lvl     
    * @return double     
    */    
    public double getRangeAbilityUltimate(int level) {
        return XmlParser.getHeroStatAtLevel(this.heroName, "Range.Ability.Ultimate", level);
    }
    
}
