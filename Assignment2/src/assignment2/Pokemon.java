package assignment2;

public class Pokemon {

   // ATTRIBUTES
   private String name;
   private Types type;
   private int MAX_HP;
   private int MAX_EP;
   private int current_HP;
   private int current_EP;
   private Skill skill;


   // CONSTRUCTOR
   // Overload?

   public Pokemon(String name, int MAX_HP, String type) {
      this.name = name;
      this.type = Types.FIRE;
      this.type = Types.NORMAL;
      this.type = Types.WATER;
      this.type = Types.GRASS;
      this.MAX_HP = MAX_HP;
      this.current_HP = MAX_HP;
      this.MAX_EP = 100;
      this.current_EP = MAX_EP;
      this.skill = null;


   }

   // GETTERS

   public String getName() {
      return this.name;
   }

   public Types getType() {
      return this.type;
   }

   public int getMAX_HP() {
      return this.MAX_HP;
   }

   public int getEnergy() {
      return this.MAX_EP;
   }

   public int getCurrentHP() {
      return this.current_HP;
   }

   public int getCurrentEnergy() {
      return this.current_EP;
   }

   public Skill getSkill() {
      return this.skill;
   }


   // SETTERS

   public void setName(String name) {
      this.name = name;
   }

   public void setType(Types type) {
      this.type = type;
   }

   public void setMAX_HP(int MAX_HP) {
      this.MAX_HP = MAX_HP;
   }

   public void setMAX_EP(int MAX_EP) {
      this.MAX_EP = MAX_EP;
   }

   public void setCurrent_HP(int current_HP) {
      this.current_HP = current_HP;
   }

   public void setCurrent_EP(int current_EP) {
      this.current_EP = current_EP;
   }

   public void setSkill(Skill skill) {
      this.skill = skill;
   }

   // toString()

   public String toString() {
      return knowsSkill() ? this.name + " " + "(" + this.type + ")" + this.skill.toString()
              :
              this.name + " " + "(" + this.type + ")";
   }


   // METHODS

   public boolean equals(Object otherObject) {
      boolean isEquals = false;
      if (this == otherObject) {
         return true;
      } else if (otherObject == null) {
         return false;
      } else if (otherObject instanceof Pokemon) {
         Pokemon otherPokemon = (Pokemon) otherObject;
         boolean sameType = this.type.equals(otherPokemon.type);
         boolean sameName = this.name.equals(otherPokemon.name);
         boolean sameMAX_HP = this.MAX_HP == otherPokemon.MAX_HP;
         boolean sameCurrent_HP = this.current_HP == otherPokemon.current_HP;
         boolean sameEnergy = this.MAX_EP == otherPokemon.MAX_EP;
         boolean sameSkill = this.skill == otherPokemon.skill;

         isEquals = sameName && sameType && sameMAX_HP && sameCurrent_HP && sameEnergy && sameSkill;

      } else {
         isEquals = false;
      }
      return isEquals;
   }

   public void learnSkill(String skillName, int attackPower, int energyCost) {
      this.skill = new Skill(skillName, attackPower, energyCost);
   }

   public void forgetSkill() {
      this.skill = null;
   }

   public boolean knowsSkill() {
      return this.skill != null;
   }


   // 3.2  - Receive Damage and Rest:
   // A pokemon can receive a specific damage value that is deducted from its current HP.
   // When resting, the pokemon always heals 20 HP.
   // When the HP reaches zero, the pokemon faints and resting has no effect; thus, the pokemon can only be healed with items.
   // A pokemon cannot heal more than its MAX HP.
   // 3.3 - Spend and Recover Energy Point:
   // A pokemon uses EP during battle (details in task below).
   // However, a pokemon can recover energy where a fixed amount of 25 EP is restored.
   // Restoring energy has no effect on a pokemon that has fainted.


   public void rest(){
      if (this.current_HP == 0) {
         this.current_HP = 0;
      } else if (this.current_HP == this.MAX_HP || this.MAX_HP - this.current_HP <= 20) {
         this.current_HP = this.MAX_HP;
      } else {
         this.current_HP = this.current_HP + 20;
      }
   }

   public int receiveDamage(int damageValue) {
      this.current_HP = this.current_HP - damageValue;
      if (this.current_HP <= 0) {
         this.current_HP = 0;
      }
      return this.current_HP;
   }

   public String attack(Pokemon target) {
      // If attacks fails
      String message = "";
      if (this.getCurrentHP() <= 0) {
         message = String.format("Attack failed. %s fainted.", this.name);
      } else if (target.getCurrentHP() <= 0){
         message = String.format("Attack failed. %s fainted.", target.name);
      } else if (!this.knowsSkill()) {
         message =String.format("Attack failed. %s does not know a skill", this.name);
      } else if (this.current_EP < this.skill.getEnergyCost()) {
         message = String.format("Attack failed. %s lacks energy: %d/%d",this.name, this.getCurrentEnergy(), this.skill.getEnergyCost());
      }
   }


   public void recoverEnergy() {
      if (this.current_HP == 0) {
         this.current_EP = 0;
      } else if (this.current_EP >= 75) {
         this.current_EP = this.MAX_EP;
      } else {
         this.current_EP = this.current_EP + 25;
      }

   }

   public void costOfEnergy(int cost) {
      current_EP -= cost;
      if (current_EP < 0) {
         current_EP = 0;
      }
   }

   /// ----------- TASK 4 ------------ ///
   public double EffectiveCalculation(Pokemon defender){
      double effectiveness = 1.0;

      if (type.equals("Grass")) {
         if(defender.type.equals("Fire") || defender.type.equals("Grass")) {
            effectiveness = 0.5;
         } else if (defender.type.equals("Water")) {
            effectiveness = 2.0;
         } else {
            effectiveness = 1;}
      }
      if (type.equals("Fire")) {
         if(defender.type.equals("Fire") || defender.type.equals("Water")) {
            effectiveness = 0.5;
         } else if (defender.type.equals("Grass")) {
            effectiveness = 2.0;
         } else {
            effectiveness = 1.0;}
         }
      if (type.equals("Water")) {
         if(defender.type.equals("Grass") || defender.type.equals("Water")) {
            effectiveness = 0.5;
         } else if (defender.type.equals("Fire")) {
            effectiveness = 2.0;
         } else {
            effectiveness = 1.0;}
      }
      if (type.equals("Normal")) {
         effectiveness = 1.0;
      }
      return effectiveness;
   }











}

