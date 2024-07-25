package assignment2;

public class Skill {
    //A pokemon relies on a skill during battles. A skill has a name, attack power (AP),
    // and energy cost (EC). All these values are specified when creating the skill. Both AP and energy cost are integer values.
    // Once created, the state of a skill cannot be changed.
    //Two skills are equal if they have the same names, APs and energy costs.
    // When printed, a skill should return: “<skill name> - AP: <ap> EC: <ec>”.

    private String skillName;
    final int attackPower;
    final int energyCost;

    Skill(String skillName, int attackPower, int energyCost) {
        this.skillName = skillName;
        this.attackPower = attackPower;
        this.energyCost = energyCost;

    }

    public String getSkillName(){
        return this.skillName;
    }

    public int getAttackPower(){
        return this.attackPower;
    }

    public int getEnergyCost(){
        return this.energyCost;
    }

    public String toString(){
        return ". Knows " + this.skillName + " - AP: " + this.attackPower + " EC: " + this.energyCost;
    }

    public boolean equals(Object otherObject) {
        boolean isEquals = false;
        if (this == otherObject){
            return true;
        } else if (otherObject == null) {
            return false;
        } else if ( otherObject instanceof Skill) {
            Skill otherSkill = (Skill)otherObject;
            boolean sameSkillName = this.skillName.equals(otherSkill.skillName);
            boolean sameAP = this.attackPower == otherSkill.attackPower;
            boolean sameEC = this.energyCost == otherSkill.energyCost;

            isEquals = sameAP && sameEC && sameSkillName;
        } else {
            isEquals = false;
        }
        return isEquals;
    }










}
