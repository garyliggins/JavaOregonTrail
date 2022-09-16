package com.kenzie.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

public class HunterTest {
	public static final int TEST_INITIAL_FOOD = 2;
	public static final int TEST_HUNT_FOOD = 7;
	public static final int TEST_NO_FOOD = 0;
	public static final int TEST_INITIAL_TRAVELER_FOOD = 1;
	public static final int TEST_GIVE_FOOD = 2;
	public static final int TEST_GIVE_FOOD_TOTAL = 3;
	public static final boolean TEST_ISHEALTHY = true;
	public static final boolean TEST_ISNOTHEALTHY = false;

	public static Hunter getTestHunter() throws NoSuchElementException {
		try {
			@SuppressWarnings("unchecked")
			Constructor hunterConstructor = Hunter.class.getConstructor();
			@SuppressWarnings("unchecked")
			Hunter hunter = (Hunter) hunterConstructor.newInstance();
			return hunter;
		}
		catch(Exception e){
			throw new NoSuchElementException(e.getMessage());
		}
	}

	public static Traveler getTestTraveler() throws NoSuchElementException {
		try {
			@SuppressWarnings("unchecked")
			Constructor travelerConstructor = Traveler.class.getConstructor();
			@SuppressWarnings("unchecked")
			Traveler person = (Traveler) travelerConstructor.newInstance();
			return person;
		}
		catch(Exception e){
			throw new NoSuchElementException(e.getMessage());
		}
	}

	@DisplayName("Hunter class can be instantiated with no arguments")
	@Test
	void canCreateHunter() {
		try {
			Hunter hunter = getTestHunter();
			@SuppressWarnings("unchecked")
			Method getFood = Hunter.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method getIsHealthy = Hunter.class.getMethod("getIsHealthy");

			assertTrue(hunter instanceof Traveler, "Hunter is instanceof Traveler");
			assertEquals(TEST_INITIAL_FOOD, (int)getFood.invoke(hunter), "Hunter.food starts at 2" );
			assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(hunter), "Hunter.isHealthy starts at true");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Hunter class must be defined with getFood() and getIsHealthy() method");
		}
	}

	@DisplayName("Hunter class has implemented hunt")
	@Test
	void canHunt() {
		try {
			Hunter hunter = getTestHunter();

			//define methods for invocation
			@SuppressWarnings("unchecked")
			Method getFood = Hunter.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method hunt = Hunter.class.getMethod("hunt");

			hunt.invoke(hunter);
			assertEquals(TEST_HUNT_FOOD, (int)getFood.invoke(hunter), "Hunter.food after hunting is 7" );
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Hunter class must be defined with hunt() and getFood() methods");
		}
	}

	@DisplayName("Hunter class has implemented eat")
	@Test
	void canEat() {
		try {
			Hunter hunter = getTestHunter();

			//define methods for invocation
			@SuppressWarnings("unchecked")
			Method getFood = Hunter.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method setFood = Hunter.class.getMethod("setFood",int.class);
			@SuppressWarnings("unchecked")
			Method eat = Hunter.class.getMethod("eat");

			eat.invoke(hunter);
			assertEquals(TEST_NO_FOOD, (int)getFood.invoke(hunter), "Hunter.food after eating is 0" );
			setFood.invoke(hunter,1);
			eat.invoke(hunter);
			assertEquals(TEST_NO_FOOD, (int)getFood.invoke(hunter), "Hunter.food after overeating is still 0" );
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Hunter class must be defined with eat() and getFood() methods");
		}
	}

	@DisplayName("Hunter class has implemented giveFood")
	@Test
	void canGiveFood() {

		try {
			Hunter hunter = getTestHunter();
			Traveler traveler = getTestTraveler();

			//define methods for invocation
			@SuppressWarnings("unchecked")
			Method getFood = Hunter.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method giveFood = Hunter.class.getMethod("giveFood",Traveler.class,int.class);

			assertEquals(TEST_INITIAL_TRAVELER_FOOD, (int)getFood.invoke(traveler), "Traveler.food after creation is 1" );
			giveFood.invoke(hunter,traveler,TEST_GIVE_FOOD);
			assertEquals(TEST_GIVE_FOOD_TOTAL, (int)getFood.invoke(traveler), "Traveler.food after giveFood is 3" );
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Hunter class must be defined with giveFood() and getFood() methods");
		}
	}

	@DisplayName("Hunter class can update isGetHealthy")
	@Test
	void canUpdateHealth() {
		try {
			Hunter hunter = getTestHunter();

			//define methods for invocation
			@SuppressWarnings("unchecked")
			Method getFood = Hunter.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method eat = Hunter.class.getMethod("eat");
			@SuppressWarnings("unchecked")
			Method getIsHealthy = Hunter.class.getMethod("getIsHealthy");

			eat.invoke(hunter);
			assertEquals(TEST_NO_FOOD, (int)getFood.invoke(hunter), "Hunter.food after eating is 0" );
			assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(hunter), "Hunter.isHealthy after eating once is true" );

			eat.invoke(hunter);
			assertEquals(TEST_NO_FOOD, (int)getFood.invoke(hunter), "Hunter.food after overeating is still 0" );
			assertEquals(TEST_ISNOTHEALTHY, (boolean)getIsHealthy.invoke(hunter), "Hunter.isHealthy after eating once is false" );
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Hunter class must be defined with eat(), getFood(), and getIsHealthy() methods");
		}
	}

}
