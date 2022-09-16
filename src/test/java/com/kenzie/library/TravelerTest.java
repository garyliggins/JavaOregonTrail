package com.kenzie.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TravelerTest {
	public static final int TEST_INITIAL_FOOD = 1;
	public static final int TEST_HUNT_FOOD = 3;
	public static final int TEST_NO_FOOD = 0;
	public static final boolean TEST_ISHEALTHY = true;
	public static final boolean TEST_ISNOTHEALTHY = false;

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

	@DisplayName("Traveler class can be instantiated with no arguments")
	@Test
	void canCreateTraveler() {
		try {
			Traveler person = getTestTraveler();

			@SuppressWarnings("unchecked")
			Method getFood = Traveler.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method getIsHealthy = Traveler.class.getMethod("getIsHealthy");

			assertEquals(TEST_INITIAL_FOOD, (int)getFood.invoke(person), "Traveler.food starts at 1" );
			assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(person), "Traveler.isHealthy starts at true");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Traveler class must be defined with getFood() and getIsHealthy() method");
		}
	}

	@DisplayName("Traveler class can hunt")
	@Test
	void canHunt() {
		try {
			Traveler person = getTestTraveler();

			//define methods for invocation
			@SuppressWarnings("unchecked")
			Method getFood = Traveler.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method hunt = Traveler.class.getMethod("hunt");

			hunt.invoke(person);
			assertEquals(TEST_HUNT_FOOD, (int)getFood.invoke(person), "Traveler.food after hunting is 3" );
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Traveler class must be defined with hunt() and getFood() methods");
		}
	}

	@DisplayName("Traveler class can hunt")
	@Test
	void canEat() {
		try {
			Traveler person = getTestTraveler();

			//define methods for invocation
			@SuppressWarnings("unchecked")
			Method getFood = Traveler.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method eat = Traveler.class.getMethod("eat");

			eat.invoke(person);
			assertEquals(TEST_NO_FOOD, (int)getFood.invoke(person), "Traveler.food after eating is 0" );
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Traveler class must be defined with eat() and getFood() methods");
		}
	}

	@DisplayName("Traveler class can set getIsHealthy")
	@Test
	void canUpdateHealth() {
		try {
			Traveler person = getTestTraveler();

			//define methods for invocation
			@SuppressWarnings("unchecked")
			Method getFood = Traveler.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method eat = Traveler.class.getMethod("eat");
			@SuppressWarnings("unchecked")
			Method getIsHealthy = Traveler.class.getMethod("getIsHealthy");

			eat.invoke(person);
			assertEquals(TEST_NO_FOOD, (int)getFood.invoke(person), "Traveler.food after eating is 0" );
			assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(person), "Traveler.isHealthy after eating once is true" );
			eat.invoke(person);
			assertEquals(TEST_ISNOTHEALTHY, (boolean)getIsHealthy.invoke(person), "Traveler.isHealthy after eating once is false" );
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Traveler class must be defined with eat(), getFood(), and getIsHealthy() methods");
		}
	}
}
