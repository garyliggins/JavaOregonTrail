package com.kenzie.library;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MainTest {
	public static final int TEST_INITIAL_FOOD = 1;
	public static final int TEST_HUNT_FOOD = 3;
	public static final int TEST_NO_FOOD = 0;
	public static final boolean TEST_ISHEALTHY = true;
	public static final boolean TEST_ISNOTHEALTHY = false;

	@DisplayName("addedConstantVariables: Required Constants added")
	@Test
	void addedConstantVariables() {
		try {
			//load all static and constant variables if they exist. Otherise throw exception
			@SuppressWarnings("unchecked")
			int wagon_size = (int) OregonTrail.getStaticFieldValue("WAGON_SIZE");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("WAGON_SIZE static final variable needs to be defined");
		}

		try {
			//load all static and constant variables if they exist. Otherise throw exception
			@SuppressWarnings("unchecked")
			int max_days = (int) OregonTrail.getStaticFieldValue("MAX_DAYS");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("MAX_DAYS static final variable needs to be defined");
		}

		try {
			//load all static and constant variables if they exist. Otherise throw exception
			@SuppressWarnings("unchecked")
			int hunt_days = (int) OregonTrail.getStaticFieldValue("HUNT_DAYS");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("HUNT_DAYS static final variable needs to be defined");
		}

		try {
			//load all static and constant variables if they exist. Otherise throw exception
			@SuppressWarnings("unchecked")
			int food_exchange = (int) OregonTrail.getStaticFieldValue("FOOD_EXCHANGE");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("FOOD_EXCHANGE static final variable needs to be defined");
		}

		try {
			//load all static and constant variables if they exist. Otherise throw exception
			@SuppressWarnings("unchecked")
			int miles_per_day = (int) OregonTrail.getStaticFieldValue("MILES_PER_DAY");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("MILES_PER_DAY static final variable needs to be defined");
		}

		try {
			//load all static and constant variables if they exist. Otherise throw exception
			@SuppressWarnings("unchecked")
			int total_miles = (int) OregonTrail.getStaticFieldValue("TOTAL_MILES");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("TOTAL_MILES static final variable needs to be defined");
		}

		try {
			//load all static and constant variables if they exist. Otherise throw exception
			@SuppressWarnings("unchecked")
			int num_travelers = (int) OregonTrail.getStaticFieldValue("NUM_TRAVELERS");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("NUM_TRAVELERS static final variable needs to be defined");
		}

		try {
			//load all static and constant variables if they exist. Otherise throw exception
			@SuppressWarnings("unchecked")
			int num_doctors = (int) OregonTrail.getStaticFieldValue("NUM_DOCTORS");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("NUM_DOCTORS static final variable needs to be defined");
		}

		try {
			//load all static and constant variables if they exist. Otherise throw exception
			@SuppressWarnings("unchecked")
			int num_hunters = (int) OregonTrail.getStaticFieldValue("NUM_HUNTERS");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("NUM_HUNTERS static final variable needs to be defined");
		}

		try {
			//load all static and constant variables if they exist. Otherise throw exception
			@SuppressWarnings("unchecked")
			int days_travelled = (int) OregonTrail.getStaticFieldValue("daysTravelled");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("daysTravelled static variable needs to be defined");
		}
		try {
			//load all static and constant variables if they exist. Otherise throw exception
			@SuppressWarnings("unchecked")
			int miles_travelled = (int) OregonTrail.getStaticFieldValue("milesTravelled");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			fail("milesTravelled static variable needs to be defined");
		}
	}
}
