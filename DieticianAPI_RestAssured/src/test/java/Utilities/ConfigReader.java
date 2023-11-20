package Utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import Utilities.LoggerLoad;

public class ConfigReader {
	private static Properties properties;
<<<<<<< HEAD
	private static final String propertyFilePath = "C:\\Users\\shaun\\git\\repository3\\DieticianAPI_RestAssured\\src\\test\\resources\\Config\\config.properties";
=======
	private static final String propertyFilePath = "C:\\Users\\shaun\\eclipse-workspace\\Tryyyyyyy_API\\src\\test\\resources\\Config\\config.properties";
>>>>>>> branch 'master' of https://github.com/AdelineGrace/Team1_APINinjas_Dietician.git
	static {
        loadProperty();
    }
	public static void loadProperty() 
	{
		BufferedReader reader;
		try 
		{
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try 
			{
				properties.load(reader);
				reader.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}
	
	public static String getProperty( String key) {
		return properties.getProperty(key);
	}
	
	public static void setProperty(String key, String value) throws IOException {
		
		FileOutputStream out;
		try {
			out = new FileOutputStream(propertyFilePath);
			properties.setProperty(key, value);;
			properties.store(out, null);
		} catch (FileNotFoundException e) {
			LoggerLoad.logInfo(e.getMessage());
			e.printStackTrace();
		}	 
	}
	
	public static String BaseURL()
	{
		String data = properties.getProperty("baseUrl");
		if (data != null)
			return data;
		else
			throw new RuntimeException("baseUrl is not specified in the config.properties file");
	}
	public static String PostURL()
	{
		String data = properties.getProperty("postUrl");
		if (data != null)
			return data;
		else
			throw new RuntimeException("postUrl is not specified in the config.properties file");
	}
	public static String LoginURL()
	{
		String data = properties.getProperty("loginUrl");
		if (data != null)
			return data;
		else
			throw new RuntimeException("loginUrl is not specified in the config.properties file");
	}
<<<<<<< HEAD
	public static String PostPatientSchema()
	{
		String data = properties.getProperty("postpatientschema");
		if (data != null)
			return data;
		else
			throw new RuntimeException("postpatientschema is not specified in the config.properties file");
	}
	public static String BadRequestSchema()
	{
		String data = properties.getProperty("400badrequestschema");
		if (data != null)
			return data;
		else
			throw new RuntimeException("400badrequestschema is not specified in the config.properties file");
	}
	public static String AloginCredentials()
	{
		String data = properties.getProperty("alogincredentials");
		if (data != null)
			return data;
		else
			throw new RuntimeException("alogincredentials is not specified in the config.properties file");
	}
	public static String UserLoginUrl()
	{
		String data = properties.getProperty("userloginUrl");
		if (data != null)
			return data;
		else
			throw new RuntimeException("userloginUrl is not specified in the config.properties file");
=======
	public static String GetPatientsURL()
	{
		String data = properties.getProperty("getPatientsUrl");
		if (data != null)
			return data;
		else
			throw new RuntimeException("getPatientsUrl is not specified in the config.properties file");
	}
	public static String DeletePatientURL()
	{
		String data = properties.getProperty("deletePatientUrl");
		if (data != null)
			return data;
		else
			throw new RuntimeException("deletePatientUrl is not specified in the config.properties file");
>>>>>>> branch 'master' of https://github.com/AdelineGrace/Team1_APINinjas_Dietician.git
	}
}
