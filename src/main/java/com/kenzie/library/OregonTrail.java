package com.kenzie.library;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.lang.reflect.*;

//helper methods for running Oregon Trail
public class OregonTrail {


    //helper method - allow runtime access of static variable names
    static int getStaticFieldValue(String path) throws NoSuchElementException {
        try {
            //Assume Main class
            Field f = Main.class.getDeclaredField(path);

            return f.getInt(null);

        }
        catch(Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    //Load wagon using reflection
    public static void loadWagon(Wagon wagon, int num_travelers, int num_doctors, int num_hunters) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // preparing loadWagon and getPassengers method for runtime invocation
        @SuppressWarnings("unchecked")
        Method loadWagon = Wagon.class.getMethod("loadWagon", int.class, int.class, int.class);

        // Loading wagon
        if (num_travelers + num_doctors + num_hunters > 0) {
            loadWagon.invoke(wagon,num_travelers, num_doctors, num_hunters);
        } else {
            System.out.println("Number of travelers = 0. No one to load in wagon.");
            System.out.println("The journey of a thousand miles starts with a single step. " +
                    "Better luck next time.");
            System.exit(0);
        }

    }

    //Eating routine for wagon using reflection
    public static void feedWagon(Traveler[] passengerArray) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        @SuppressWarnings("unchecked")
        Method hunterEat = Hunter.class.getMethod("eat");
        @SuppressWarnings("unchecked")
        Method doctorEat = Doctor.class.getMethod("eat");
        @SuppressWarnings("unchecked")
        Method travelerEat = Traveler.class.getMethod("eat");

        // For-each member in wagon: eat and update healthy status
        for (int j = 0; j < passengerArray.length; j++) {
            if (java.util.Objects.nonNull(passengerArray[j])) {
                Traveler person = passengerArray[j];

                if (person instanceof Hunter) {
                    // Cast to Hunter in order to call specific version of eat

                    hunterEat.invoke((Hunter)person);
                } else {
                    //call traveler version of eat
                    travelerEat.invoke(person);
                }
            }
        }
    }

    //Hunting routine for wagon using reflection
    public static void goHunting(Traveler[] passengerArray) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // preparing eat method in Hunter and Traveler for runtime invocation
        @SuppressWarnings("unchecked")
        Method hunterHunt = Hunter.class.getMethod("hunt");
        @SuppressWarnings("unchecked")
        Method travelerHunt = Traveler.class.getMethod("hunt");

        //passengerArray set above
        for (int j = 0; j < passengerArray.length; j++) {
            if (java.util.Objects.nonNull(passengerArray[j])) {
                Traveler person = passengerArray[j];
                if (person instanceof Hunter) {
                    // Cast to Hunter in order to call specific version of hunt
                    hunterHunt.invoke((Hunter)person);
                } else {
                    travelerHunt.invoke(person);
                }
            }
        }
    }

    //Quarantine care for wagon
    public static void quarantineCare(Traveler[] passengerArray, int food_exchange) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        @SuppressWarnings("unchecked")
        Method shouldQuarantine = Wagon.class.getMethod("shouldQuarantine");
        @SuppressWarnings("unchecked")
        Method getFood = Traveler.class.getMethod("getFood");
        @SuppressWarnings("unchecked")
        Method getIsHealthy = Traveler.class.getMethod("getIsHealthy");

        // preparing Hunter giveFood for runtime invocation
        @SuppressWarnings("unchecked")
        Method giveFood = Hunter.class.getMethod("giveFood",Traveler.class,int.class);

        // preparing Doctor heal for runtime invocation
        @SuppressWarnings("unchecked")
        Method heal = Doctor.class.getMethod("heal",Traveler.class);

        for (int j = 0; j < passengerArray.length; j++) {
            if (java.util.Objects.nonNull(passengerArray[j])) {
                Traveler person = passengerArray[j];

                // If quarantine, hunters give food to someone out of food
                if (person instanceof Hunter) {
                    for (Traveler fellowTraveler : passengerArray) {
                        // If encounter unhealthy fellow traveler, give food and exit
                        // Can only give food to one other Traveler per turn
                        if ((int)getFood.invoke(fellowTraveler) == 0)
                            giveFood.invoke((Hunter)person,fellowTraveler, food_exchange);
                        break;
                    }
                } else if (person instanceof Doctor) {
                    // If quarantined, doctors attempt to heal one person. That one person can be themselves
                    for (Traveler fellowTraveler : passengerArray) {
                        // If encounter unhealthy fellow traveler, heal and exit
                        // Can only heal one unhealthy Traveler per turn
                        if (!(boolean)getIsHealthy.invoke(fellowTraveler)) {
                            heal.invoke((Doctor)person,fellowTraveler);
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void displayStatus (Wagon wagon, int daysTravelled, int milesTravelled) throws Exception {
        @SuppressWarnings("unchecked")
        Method shouldQuarantine = Wagon.class.getMethod("shouldQuarantine");

        System.out.println("*************************************");
        System.out.println("Days travelled:"+ daysTravelled);
        System.out.println("Miles travelled:"+ milesTravelled);
        System.out.println("Quarantined?:" + ((boolean)shouldQuarantine.invoke(wagon)) );
        System.out.println("Remaining miles to Oregon:" + ((int)OregonTrail.getStaticFieldValue("TOTAL_MILES") - milesTravelled));
    }

}
