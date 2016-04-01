
package com.lyonsdensoftware.paragon;

/**
 *
 * @author Joshua Lyons
 */
public class ParagonMath {
    
    /**
     * Calculates the attack speed of the ability
     * @param heroName is the name of the hero to search for
     * @param ability string of the ability to calculate for
     * @param level int level of hero
     * @return double
     */
    public static double getAttackSpeed(String heroName, String ability, int level) {
        
        double tmpDouble = (100 / XmlParser.getHeroStatAtLevel(heroName, "AttackSpeedRating", level)) * 
                       XmlParser.getHeroStatAtLevel(heroName, "BaseAttackTime", level);
        
        //System.out.println(XmlParser.getHeroStatAtLevel(heroName, "AttackSpeedRating", level));
        //System.out.println(XmlParser.getHeroStatAtLevel(heroName, "BaseAttackTime", level));
        
        switch (ability) {
            case "BasicAttack":
                tmpDouble += XmlParser.getHeroAbilityStat(heroName, ability, "Cooldown.Basic.Primary", level);
                break;
            case "AlternateAbility":
                tmpDouble += XmlParser.getHeroAbilityStat(heroName, ability, "Cooldown.Ability.Alternate", level);
                break;
            case "PrimaryAbility":
                tmpDouble += XmlParser.getHeroAbilityStat(heroName, ability, "Cooldown.Ability.Primary", level);
                break;
            case "SecondaryAbility":
                tmpDouble += XmlParser.getHeroAbilityStat(heroName, ability, "Cooldown.Ability.Secondary", level);
                break;
            case "UltimateAbility":
                tmpDouble += XmlParser.getHeroAbilityStat(heroName, ability, "Cooldown.Ability.Ultimate", level);
                break;
        }
        
        return tmpDouble;
    }
    
    /**
     * Calculates the attack damage of the ability
     * @param heroName is the name of the hero to search for
     * @param ability string of the ability to calculate for
     * @param level int level of hero
     * @param cardBonuses
     * @return double
     */
    public static double getAttackDamage(String heroName, String ability, int level, double[] cardBonuses ) {
                
        String abilityString = "", baseStat = "", damageType = "";

        //System.out.println(XmlParser.getHeroStatAtLevel(heroName, "AttackSpeedRating", level));
        //System.out.println(XmlParser.getHeroStatAtLevel(heroName, "BaseAttackTime", level));
        
        switch (ability) {
            case "BasicAttack":
                abilityString = "Basic.Primary";
                baseStat = "BasicAttack";
                break;
            case "AlternateAbility":
                abilityString = "Ability.Alternate";
                baseStat = "AbilityAttack";
                break;
            case "PrimaryAbility":
                abilityString = "Ability.Primary";
                baseStat = "AbilityAttack";
                break;
            case "SecondaryAbility":
                abilityString = "Ability.Secondary";
                baseStat = "AbilityAttack";
                break;
            case "UltimateAbility":
                abilityString = "Ability.Ultimate";
                baseStat = "AbilityAttack";
                break;
        }
        
        switch (heroName) {
            case "Howitzer":
            case "Murdock":
            case "Gideon":
            case "Gadget":
            case "Dekker":
            case "Muriel":
                damageType = "Energy";
                break;
            case "Kallari":
            case "Rampage":
            case "Steel":
            case "FengMao":
            case "TwinBlast":
            case "Grux":
            case "Sevarog":
            case "Sparrow":
                damageType = "Physical";
                break;
        }
        
        double aoeRating, basePower, attackRatingCoeff, 
                attackRating, damageBonusSource, damageBonusTarget, 
                damageResistance, energyResistRating, energyPenetrationRating, 
                physicalResistRating, physicalPenetrationRating;
        
        aoeRating = 1;                  // Not sure about this so set at 1
        basePower = XmlParser.getHeroAbilityStat(heroName, ability, "BasePower." + abilityString, level);
        attackRatingCoeff = XmlParser.getHeroAbilityStat(heroName, ability, "ARC." + abilityString, level);
        attackRating = XmlParser.getHeroStatAtLevel(heroName, "AttackRating", level);
        if (damageType.equals("Energy")) {
            if (cardBonuses[5] > 0)
                basePower += cardBonuses[5];
        }
        else {
            if (cardBonuses[13] > 0)
                basePower += cardBonuses[13];
        }
        
        damageBonusSource = 1;
        damageBonusTarget = 1;          // Not sure about this so set at 1
        damageResistance = 0;           // Not sure about this so set at 0
        energyResistRating = XmlParser.getHeroStatAtLevel(heroName, "EnergyResistanceRating", level);
        energyPenetrationRating = 0;    // Not sure about this so set at 0
        physicalResistRating = XmlParser.getHeroStatAtLevel(heroName, "PhysicalResistanceRating", level);
        physicalPenetrationRating = 0;  // Not sure about this so set at 0
        
        double damage = aoeRating * (basePower + (attackRatingCoeff * attackRating)) 
                * damageBonusSource * damageBonusTarget * (1 - damageResistance); 
                //* getResistsReduction((energyResistRating - energyPenetrationRating), level)
                //* getResistsReduction((physicalResistRating - physicalPenetrationRating), level);
        
        return damage;
    }
    
    /**
     * Calculates the resists
     * @param finalResistsRating double of resist minus pen
     * @param level int level of defending hero
     * @return double
     */
    public static double getResistsReduction(double finalResistsRating, int level) {
        return (finalResistsRating / ( 100 + finalResistsRating + ( level - 1 ) * 10 ) );
    }
    
}
