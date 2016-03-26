package cse530a.util;


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
        InputStream inputStream = PropertiesLoader.class.getResourceAsStream(filename);
        if (inputStream != null) {
            try {
                Properties properties = new Properties();
                properties.load(inputStream);
                return properties;
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "could not read properties file", e);
            }
            
            Utility.closeQuietly(inputStream);
        }
        
        return null;
    }
}
