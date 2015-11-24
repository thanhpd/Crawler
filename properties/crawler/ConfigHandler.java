package crawler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigHandler {
	private static ConfigHandler instance = null;
	
	private ConfigHandler() {
		createConfigFile();
	}
	
	public static ConfigHandler getInstance() {
		if(instance == null) instance = new ConfigHandler();
		return instance;
	}
	
	private void createConfigFile() {
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			output = new FileOutputStream("config.properties");

			// set the properties value
			prop.setProperty("serverhost", "localhost");
			prop.setProperty("port", "8983");
			prop.setProperty("core", "new_core");
			prop.setProperty("solrPath", "solr");

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	  
	}
	
	public Property getProperty() {
		Properties prop = new Properties();
		InputStream input = null;
		Property customProp = new Property();
		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			String host = prop.getProperty("host");
			String port = prop.getProperty("port");
			String core = prop.getProperty("core");
			String solrPath = prop.getProperty("solrPath");		
			customProp.setProperty(host, port, core, solrPath);			

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return customProp;
	}	
}
