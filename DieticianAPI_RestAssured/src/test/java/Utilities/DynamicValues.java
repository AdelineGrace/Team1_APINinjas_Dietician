package Utilities;


import com.github.javafaker.Faker;


public class DynamicValues {
	

	 

	

	    public static int PhoneNumber() {
	    	 Faker faker = new Faker();

	         int random10DigitInteger = (int) faker.number().randomNumber(10, true);

	        return random10DigitInteger;
	    }

	}


