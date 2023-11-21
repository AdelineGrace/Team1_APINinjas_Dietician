package Utilities;


import com.github.javafaker.Faker;


public class DynamicValues {
	

	 

	

	    public static long PhoneNumber() {
	    	 Faker faker = new Faker();

	    	 long random10DigitInteger = faker.number().randomNumber(10, false);
	         return Math.abs(random10DigitInteger);
	    }

	}


