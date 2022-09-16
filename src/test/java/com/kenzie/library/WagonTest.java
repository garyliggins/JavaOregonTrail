package com.kenzie.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class WagonTest {
	public static final int TEST_CAPACITY = 4;
	public static final int TEST_CAPACITY_AFTER_JOIN = 3;
	public static final int TEST_NO_CAPACITY = 0;
	public static final int TEST_INITIAL_FOOD = 1;
	public static final int TEST_ADD_FOOD_1 = 2;
	public static final int TEST_ADD_FOOD_2 = 3;
	public static final int TEST_ADD_FOOD_3 = 4;
	public static final int TEST_NO_FOOD = 0;
	public static final boolean TEST_ISHEALTHY = true;
	public static final boolean TEST_ISNOTHEALTHY = false;
	public static final boolean TEST_SHOULD_QUARANTINE = true;
	public static final boolean TEST_SHOULDNOT_QUARANTINE = false;
	public static final int TEST_CAPACITY_LOAD = 8;
	public static final int TEST_CAPACITY_LOW = 2;
	public static final int TEST_NUM_TRAVELERS = 2;
	public static final int TEST_NUM_HUNTERS = 3;
	public static final int TEST_NUM_DOCTORS = 3;
	public static final int TEST_NUM_TRAVELERS_OVERFLOW = 4;

	public static Wagon getTestWagon() throws NoSuchElementException {
		try {
			@SuppressWarnings("unchecked")
			Constructor wagonConstructor = Wagon.class.getConstructor(int.class);
			@SuppressWarnings("unchecked")
			Wagon wagon = (Wagon) wagonConstructor.newInstance(TEST_CAPACITY);
			return wagon;
		}
		catch(Exception e){
			throw new NoSuchElementException(e.getMessage());
		}
	}

	public static Wagon getTestWagonForLoad() throws NoSuchElementException {
		try {
			@SuppressWarnings("unchecked")
			Constructor wagonConstructor = Wagon.class.getConstructor(int.class);
			@SuppressWarnings("unchecked")
			Wagon wagon = (Wagon) wagonConstructor.newInstance(TEST_CAPACITY_LOAD);
			return wagon;
		} catch (Exception e) {
			throw new NoSuchElementException(e.getMessage());
		}
	}

	public static Traveler getTestTraveler() throws NoSuchElementException{
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

	@DisplayName("Wagon class can be instantiated with the test capacity")
	@Test
	void canCreateWagon() throws NoSuchMethodException {
		try {
			Wagon wagon = getTestWagon();

			@SuppressWarnings("unchecked")
			Method getAvailableSeatCount = Wagon.class.getMethod("getAvailableSeatCount");
			int seatValue = (int) getAvailableSeatCount.invoke(wagon);
			assertEquals(TEST_CAPACITY, seatValue, "getAvailableSeatCount() returns the available seat count");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Wagon class must be defined with getAvailableSeatCount() method");

		}
	}

	@DisplayName("Wagon class can use join to add travelers")
	@Test
	void canTravelerJoin() {
		try {
			Wagon wagon = getTestWagon();
			Traveler traveler = getTestTraveler();

			@SuppressWarnings("unchecked")
			Method getFood = Traveler.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method getIsHealthy = Traveler.class.getMethod("getIsHealthy");

			assertEquals(TEST_INITIAL_FOOD, (int)getFood.invoke(traveler), "Traveler.food starts at 1" );
			assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(traveler), "Traveler.isHealthy starts at true");

			//test Wagon join method
			@SuppressWarnings("unchecked")
			Method join = Wagon.class.getMethod("join", Traveler.class);
			join.invoke(wagon,traveler);

			@SuppressWarnings("unchecked")
			Method getAvailableSeatCount = Wagon.class.getMethod("getAvailableSeatCount");
			assertEquals(TEST_CAPACITY_AFTER_JOIN, (int) getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() after join returns 1 less");

			//add three travelers to test a full wagon
			join.invoke(wagon,traveler);
			join.invoke(wagon,traveler);
			join.invoke(wagon,traveler);
			assertEquals(TEST_NO_CAPACITY, (int) getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() after 4 joins is 0");

			//add one more after no capacity
			join.invoke(wagon,traveler);
			assertEquals(TEST_NO_CAPACITY, (int) getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() after 5 joins is 0");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Wagon class must be defined with getAvailableSeatCount() and join() method");
		}
	}

	@DisplayName("checkTravelerFood: Wagon class can check total food in wagon")
	@Test
	void checkTravelerFood() {
		try {
			Wagon wagon = getTestWagon();
			@SuppressWarnings("unchecked")
			Method totalFood = Wagon.class.getMethod("totalFood");
			assertEquals(TEST_NO_FOOD,(int) totalFood.invoke(wagon), "totalFood() on empty wagon should return 0");

			Traveler traveler = getTestTraveler();
			@SuppressWarnings("unchecked")
			Method join = Wagon.class.getMethod("join",Traveler.class);

			//add travelers and test totalFood
			join.invoke(wagon,traveler);
			assertEquals(TEST_INITIAL_FOOD,(int) totalFood.invoke(wagon), "totalFood() after 1 join should return 1");
			join.invoke(wagon,traveler);
			assertEquals(TEST_ADD_FOOD_1,(int) totalFood.invoke(wagon), "totalFood() after 2 joins should return 2");
			join.invoke(wagon,traveler);
			assertEquals(TEST_ADD_FOOD_2,(int) totalFood.invoke(wagon), "totalFood() after 3 joins should return 3");
			join.invoke(wagon,traveler);
			assertEquals(TEST_ADD_FOOD_3,(int) totalFood.invoke(wagon), "totalFood() after 4 join should return 4");

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Wagon class must be defined with totalFood() method");

		}
	}

	@DisplayName("checkShouldQuarantine: Wagon class can check health of all passengers to set quarantine")
	@Test
	void checkShouldQuarantine() {
		try {
			Wagon wagon = getTestWagon();
			@SuppressWarnings("unchecked")
			Method shouldQuarantine = Wagon.class.getMethod("shouldQuarantine");

			assertEquals(TEST_SHOULDNOT_QUARANTINE,(boolean)shouldQuarantine.invoke(wagon), "shouldQuarantine() on empty wagon should return false");

			Traveler traveler = getTestTraveler();
			@SuppressWarnings("unchecked")
			Method join = Wagon.class.getMethod("join", Traveler.class);
			@SuppressWarnings("unchecked")
			Method eat = Traveler.class.getMethod("eat");
			@SuppressWarnings("unchecked")
			Method getIsHealthy = Traveler.class.getMethod("getIsHealthy");

			join.invoke(wagon,traveler);
			assertEquals(TEST_SHOULDNOT_QUARANTINE,(boolean) shouldQuarantine.invoke(wagon), "shouldQuarantine() on one healthy traveler should return false");

			Traveler sickTraveler = getTestTraveler();
			eat.invoke(sickTraveler);
			eat.invoke(sickTraveler);
			assertEquals(TEST_ISNOTHEALTHY,(boolean)getIsHealthy.invoke(sickTraveler),"sickTraveler is unhealthy after eating with no food");

			join.invoke(wagon,sickTraveler);
			assertEquals(TEST_SHOULD_QUARANTINE,shouldQuarantine.invoke(wagon), "shouldQuarantine() with one unhealthy travelers should return true");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Wagon class must be defined with shouldQuarantine and join methods.");
		}
	}

	@DisplayName("canLoadWagon: Wagon class can load travelers into wagon")
	@Test
	void canLoadWagon() {
		try {
			Wagon wagon = getTestWagonForLoad();

			//define methods for invocation
			@SuppressWarnings("unchecked")
			Method getAvailableSeatCount = Wagon.class.getMethod("getAvailableSeatCount");
			@SuppressWarnings("unchecked")
			Method loadWagon = Wagon.class.getMethod("loadWagon", int.class, int.class, int.class);

			assertEquals(TEST_CAPACITY_LOAD, (int)getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() should be 8");
			loadWagon.invoke(wagon,TEST_NUM_TRAVELERS,TEST_NUM_DOCTORS,TEST_NUM_HUNTERS);
			assertEquals(TEST_NO_CAPACITY, (int)getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() should be 0 after load");

			//test small wagon
			Wagon wagonLowCapacity = getTestWagonForLoad();
			assertEquals(TEST_CAPACITY_LOAD, (int)getAvailableSeatCount.invoke(wagonLowCapacity), "getAvailableSeatCount() should initially be 8");
			loadWagon.invoke(wagonLowCapacity,TEST_NUM_TRAVELERS,TEST_NUM_TRAVELERS,TEST_NUM_TRAVELERS);
			assertEquals(TEST_CAPACITY_LOW, (int)getAvailableSeatCount.invoke(wagonLowCapacity), "getAvailableSeatCount() should be 2");

			//test loading over capacity in wagon
			Wagon wagonOverflowCapacity = getTestWagonForLoad();
			assertEquals(TEST_CAPACITY_LOAD, (int)getAvailableSeatCount.invoke(wagonOverflowCapacity), "getAvailableSeatCount() should initially be 8");
			loadWagon.invoke(wagonOverflowCapacity,TEST_NUM_TRAVELERS_OVERFLOW,TEST_NUM_TRAVELERS,TEST_NUM_TRAVELERS);
			assertEquals(TEST_NO_CAPACITY, (int)getAvailableSeatCount.invoke(wagonOverflowCapacity), "getAvailableSeatCount() should be 0");
			loadWagon.invoke(wagonOverflowCapacity,TEST_NUM_TRAVELERS_OVERFLOW,TEST_NUM_TRAVELERS,TEST_NUM_TRAVELERS);
			assertEquals(TEST_NO_CAPACITY, (int)getAvailableSeatCount.invoke(wagonOverflowCapacity), "getAvailableSeatCount() should still be 0 after overflow load");

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Wagon class must be defined with loadWagon() and getAvailableSeatCount method");
		}
	}

	@DisplayName("checkPassengers: Check that passengers are unique")
	@Test
	void checkPassengers() {
		try {
			Wagon wagon = getTestWagonForLoad();

			//define methods for invocation
			@SuppressWarnings("unchecked")
			Method getAvailableSeatCount = Wagon.class.getMethod("getAvailableSeatCount");
			@SuppressWarnings("unchecked")
			Method loadWagon = Wagon.class.getMethod("loadWagon", int.class, int.class, int.class);
			@SuppressWarnings("unchecked")
			Method getPassengers = Wagon.class.getMethod("getPassengers");

			assertEquals(TEST_CAPACITY_LOAD, (int)getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() should be 8");
			loadWagon.invoke(wagon,TEST_NUM_TRAVELERS,TEST_NUM_DOCTORS,TEST_NUM_HUNTERS);
			assertEquals(TEST_NO_CAPACITY, (int)getAvailableSeatCount.invoke(wagon), "getAvailableSeatCount() should be 0 after load");

			Traveler[] passengerArray = (Traveler[]) getPassengers.invoke(wagon);
			//check that passenger 1 and 2 are not same reference
			assertNotEquals(passengerArray[0],passengerArray[1], "loadWagon needs to add unique Traveler objects");

			//check that passenger 3 and 4 are not same reference
			assertNotEquals(passengerArray[2],passengerArray[3], "loadWagon needs to add unique Doctor objects");

			//check that passenger 6 and 7 are not same reference
			assertNotEquals(passengerArray[5],passengerArray[6], "loadWagon needs to add unique Hunter objects");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Wagon class must be defined with checkPassengers(), loadWagon(), and getAvailableSeatCount method");
		}
	}

}
