package com.kenzie.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.*;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorTest {
	public static final int TEST_INITIAL_FOOD = 1;
	public static final int TEST_HUNT_FOOD = 3;
	public static final int TEST_NO_FOOD = 0;
	public static final boolean TEST_ISHEALTHY = true;
	public static final boolean TEST_ISNOTHEALTHY = false;

	public static Doctor getTestDoctor() throws NoSuchElementException {
		try {
			@SuppressWarnings("unchecked")
			Constructor doctorConstructor = Doctor.class.getConstructor();
			@SuppressWarnings("unchecked")
			Doctor doctor = (Doctor) doctorConstructor.newInstance();
			return doctor;
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


	@DisplayName("Doctor class can be instantiated with no arguments")
	@Test
	void canCreateDoctor() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		try {
			Doctor doctor = getTestDoctor();
			@SuppressWarnings("unchecked")
			Method getFood = Doctor.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method getIsHealthy = Doctor.class.getMethod("getIsHealthy");

			assertTrue(doctor instanceof Traveler, "Doctor is instanceof Traveler");
			assertEquals(TEST_INITIAL_FOOD, (int)getFood.invoke(doctor), "Doctor.food starts at 1");
			assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(doctor), "Doctor.isHealthy starts at true");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Doctor class must be defined with getFood() and getIsHealthy() method");
		}
	}

	@DisplayName("Doctor class has implemented hunt")
	@Test
	void canHunt() {
		try {
			Doctor doctor = getTestDoctor();

			//define methods for invocation
			@SuppressWarnings("unchecked")
			Method getFood = Doctor.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method hunt = Doctor.class.getMethod("hunt");

			hunt.invoke(doctor);
			assertEquals(TEST_HUNT_FOOD,  (int)getFood.invoke(doctor), "Doctor.food after hunting is 3" );
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Doctor class must be defined with hunt() and getFood() methods");
		}

	}

	@DisplayName("Doctor class has implemented eat")
	@Test
	void canEat() {

		try {
			Doctor doctor = getTestDoctor();

			//define methods for invocation
			@SuppressWarnings("unchecked")
			Method getFood = Doctor.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method setFood = Doctor.class.getMethod("setFood",int.class);
			@SuppressWarnings("unchecked")
			Method eat = Doctor.class.getMethod("eat");

			eat.invoke(doctor);
			assertEquals(TEST_NO_FOOD, (int)getFood.invoke(doctor), "Doctor.food after eating is 0" );
			setFood.invoke(doctor,1);
			eat.invoke(doctor);
			assertEquals(TEST_NO_FOOD, (int)getFood.invoke(doctor), "Doctor.food after overeating is still 0" );
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Doctor class must be defined with eat(),setFood(), and getFood() methods");
		}
	}

	@DisplayName("Doctor class can update isGetHealthy")
	@Test
	void canUpdateHealth() {
		try {
			Doctor doctor = getTestDoctor();

			//define methods for invocation
			@SuppressWarnings("unchecked")
			Method getFood = Doctor.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method eat = Doctor.class.getMethod("eat");
			@SuppressWarnings("unchecked")
			Method getIsHealthy = Doctor.class.getMethod("getIsHealthy");

			eat.invoke(doctor);
			assertEquals(TEST_NO_FOOD, (int)getFood.invoke(doctor), "Doctor.food after eating is 0" );
			assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(doctor), "Doctor.isHealthy after eating once is true" );

			eat.invoke(doctor);
			assertEquals(TEST_NO_FOOD, (int)getFood.invoke(doctor), "Doctor.food after overeating is still 0" );
			assertEquals(TEST_ISNOTHEALTHY,  (boolean)getIsHealthy.invoke(doctor), "Doctor.isHealthy after eating once is false" );
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Doctor class must be defined with eat(), getFood(), and getIsHealthy() methods");
		}
	}

	@DisplayName("Doctor class has implemented heal")
	@Test
	void canHeal() {
		try {
			Doctor doctor = getTestDoctor();
			Traveler traveler = getTestTraveler();

			//define doctor methods for invocation
			@SuppressWarnings("unchecked")
			Method heal = Doctor.class.getMethod("heal", Traveler.class);

			//define traveler methods for invocation
			@SuppressWarnings("unchecked")
			Method eat = Traveler.class.getMethod("eat");
			@SuppressWarnings("unchecked")
			Method getFood = Traveler.class.getMethod("getFood");
			@SuppressWarnings("unchecked")
			Method getIsHealthy = Traveler.class.getMethod("getIsHealthy");

			eat.invoke(traveler);
			assertEquals(TEST_NO_FOOD, (int) getFood.invoke(traveler), "Traveler.food after eating is 0" );
			assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(traveler), "Traveler.isHealthy after eating once is true" );

			eat.invoke(traveler);
			assertEquals(TEST_ISNOTHEALTHY, (boolean)getIsHealthy.invoke(traveler), "Traveler.isHealthy after eating once is false" );

			heal.invoke(doctor,traveler);
			assertEquals(TEST_ISHEALTHY, (boolean)getIsHealthy.invoke(traveler), "Traveler.isHealthy after being healed" );
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("Doctor class must be defined with heal() method. Traveler must be defined with getIsHealthy and eat methods");
		}

	}
}
