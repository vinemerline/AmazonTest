package in.amazon.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class ConfigReader {
	
	public static Properties readPropertiesFile(String fileName) throws Exception{
		
		Properties property = new Properties();
		InputStream fileReader;
		fileReader = new FileInputStream(fileName);
		property.load(fileReader);
		return property;
	}

}
