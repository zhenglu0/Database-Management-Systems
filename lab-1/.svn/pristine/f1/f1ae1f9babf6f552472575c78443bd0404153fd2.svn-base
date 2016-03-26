package cse530a;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Helper class for handling properties files.
 */
public class PropertiesLoader {
    private static final Logger LOGGER = Logger.getLogger(PropertiesLoader.class.getName());

    /**
     * Loads properties from a property file.
     * 
     * @return the loaded Properties
     */
    public static Properties loadProperties(String filename) {
        Properties properties = new Properties();
        
        InputStream inputStream = PropertiesLoader.class.getResourceAsStream(filename);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "could not read properties file", e);
            properties = null;
        }
        Utility.closeQuietly(inputStream);
        
        return properties;
    }
}
