package dataProviders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.yaml.snakeyaml.Yaml;

public class ConfigFileReader {
    private static Properties properties;
	private final String propertyFilePath = "src/test/resources/configuration.properties";
	private String applicationConfigPath;
	private String country;
	private String browser;
	private String environment;
	private String testDataResourcePath;

	private BufferedReader reader = null;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public ConfigFileReader() {
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			properties.load(reader);
			testDataResourcePath = properties.getProperty("testDataResourcePath");
			country = System.getProperty("country");
			browser = System.getProperty("browser");
			environment = System.getProperty("environment");
		} catch (IOException e) {
			throw new RuntimeException("Properties file not found at path : " + propertyFilePath);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException ignore) {
			}
		}
	}

	public String getTestDataResourcePath(){
		return testDataResourcePath;
	}

    public Map<String, Object> getApplicationConfig() {
        applicationConfigPath = "src/test/resources/" + country + "/" + environment + ".yaml";
		File file = new File(applicationConfigPath);
		Map<String, Object> applicationConfigs = null;
        try (InputStream inputStream = new FileInputStream(file))
		{
			Yaml yaml = new Yaml();
			applicationConfigs = yaml.load(inputStream);
			if(applicationConfigs == null) throw new RuntimeException("Application config file for " + country + " and " + environment + " is empty.");
		} catch (Exception e) {
            e.printStackTrace();
        }
		return applicationConfigs;
	}
}
