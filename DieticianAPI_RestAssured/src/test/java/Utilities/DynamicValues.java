package Utilities;

import com.github.javafaker.Faker;

public class DynamicValues {
	
	
	 public static String FirstName()
		{
		 Faker faker = new Faker();

	     String randomFirstName = faker.name().firstName();

			return randomFirstName;
		}

	public static int PhoneNumber()
	{
		return (int) (Math.floor(Math.random() * 1000000000));
	}

}
