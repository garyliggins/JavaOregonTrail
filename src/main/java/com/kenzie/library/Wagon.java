package com.kenzie.library;

public class Wagon {

    //instance properties
    private int capacity;
    Traveler[] passengers;


    //constructor
    public Wagon(int capacity) {
        this.capacity = capacity;
        this.passengers = new Traveler[capacity];
    }

    //getters and setters

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Traveler[] getPassengers() {
        return passengers;
    }

    public void setPassengers(Traveler[] passengers) {
        this.passengers = passengers;
    }



    //methods

    public int getAvailableSeatCount() {
        int count = 0;
        System.out.println("Cap: "+capacity);
        for (Traveler passengersCount : passengers) {
            if (passengersCount == null) {
                count++;
                System.out.println("Traveler");
            }
        }
        System.out.println("Count: "+count);
        return count;
    }

    public void join(Traveler traveler) {
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i] == null) {
                passengers[i] = traveler;
                break;
            }
        }

    }

    public boolean shouldQuarantine() {
        boolean quarantine = false;//Assume that we don't have to Quarantine
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i] != null) {
                if(passengers[i].getIsHealthy() == false)
                {
                    quarantine = true;
                }
                if(quarantine == true)
                {
                    passengers[i].travelerHealth = Health.TravelerHealth.LOW;
                }
            }
        }
        return quarantine;
    }

    public int totalFood() {
      int AmountFood = 0;
        for (int i = 0; i < passengers.length; i++) {
            if (passengers[i] != null) {
                AmountFood += passengers[i].getFood();
            }
        }
        return AmountFood;
        }

    public void loadWagon(int numTravelers, int numHunters, int numDoctors) {
        if (getAvailableSeatCount() != 0) {
            Traveler[] Travelers = new Traveler[numTravelers];
            Traveler[] Hunters = new Traveler[numHunters];
            Traveler[] Doctors = new Traveler[numDoctors];
            for(int i = 0; i < Travelers.length; i++)
            {

                Traveler t = new Traveler("T"+i);
                join(t);
            }
            for(int i = 0; i < Hunters.length; i++)
            {
                Hunter h = new Hunter("H"+i);
                join(h);
            }
            for(int i = 0; i < Doctors.length; i++)
            {
                Doctor doc = new Doctor("i"+i);
                join(doc);
            }
        }
        else
        {
            System.out.println("Seats: "+getAvailableSeatCount()+"\nCap: "+capacity);
        }
    }
}

