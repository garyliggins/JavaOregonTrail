package com.kenzie.library;

public class Traveler {
//TODO: Code Traveler class
    protected String name;
    protected int food = 1;
    protected boolean isHealthy = true;

    Health.TravelerHealth travelerHealth;
    public Traveler() {
      this.name = name;
      this.food = food;
      this.isHealthy = isHealthy;
    }

    public Traveler(String name) {
        this.name = name;

    }

    //getters


    public String getName() {
        return name;
    }

    public int getFood() {
        return food;
    }

    public boolean getIsHealthy() {
        return isHealthy;
    }

    //setters

    public void setName(String name) {
        this.name = name;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setIsHealthy(boolean isHealthy) {
        isHealthy = isHealthy;
    }

    public void hunt() {
        food += 2;

    }

    public void eat() {
        if (food >= 1) {
            food -= 1;} else {
            isHealthy = false;
        }

    }
}

class Doctor extends Traveler {
//TODO: Code Doctor class

    public Doctor() {
        super();
    }
    public Doctor(String name) {
        super(name);

    }

    public void hunt() {
        food += 2;

    }

    public void eat() {
        if (food >= 1) {
            food -= 1;
        } else {
            isHealthy = false;
        }
    }

   public void heal(Traveler traveler) {
        traveler.isHealthy = true;
    }
  
}

class Hunter extends Traveler {
//TODO: Code Hunter class

    public Hunter() {
        super();
        setFood(2);
    }

    public Hunter(String name) {
        super(name);
        setFood(2);
    }

    public void hunt() {
        food += 5;

    }

    public void eat() {
            if (food >= 2) {
                food -= 2;
            }
            else {
               setFood(0);
                isHealthy = false;
            }
    }

    public void giveFood(Traveler traveler, int numOfFoodUnits) {
        if (food >= numOfFoodUnits) {
            food -= numOfFoodUnits;
            traveler.food += numOfFoodUnits;

        }
    }

}

