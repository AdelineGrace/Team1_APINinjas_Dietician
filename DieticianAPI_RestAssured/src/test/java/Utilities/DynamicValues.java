package Utilities;

import com.github.javafaker.Faker;

public class DynamicValues {
	
	  static Faker faker;

	 public static String FirstName()
		{

	     String randomFirstName = faker.name().firstName();

			return randomFirstName;
		}
	 public static String LastName()
		{
		  faker = new Faker();

	     String randomLastName = faker.name().firstName();

			return randomLastName;
		}

	public static int PhoneNumber()
	{
		return (int) (Math.floor(Math.random() * 1000000000));
	}

}
