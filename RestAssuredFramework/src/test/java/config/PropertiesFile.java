package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.test.DataDriven_Lib;

public class PropertiesFile {
	static Properties prop = new Properties();
	static String projectPath= System.getProperty("user.dir");
	
	public static void main(String[] args) {
		getProperties();
		setProperties();

	}
	
	public static void getProperties() {
		
		try {			
			InputStream input = new FileInputStream(projectPath+"/src/test/java/config/config.properties");
			prop.load(input);
			String baseUrl=prop.getProperty("baseUrl");
			String env = prop.getProperty("env");
			
			System.out.println(baseUrl);
			System.out.println(env);
			DataDriven_Lib.env=env;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}
	
	public static void setProperties() {
		try {
		OutputStream output = new FileOutputStream(projectPath+"/src/test/java/config/config.properties");
		prop.setProperty("env", "DLAB");
		prop.store(output, "Setting up the environment");
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		
	}

}
