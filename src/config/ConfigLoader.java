package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static ConfigLoader instance;
    private Properties properties;

    private ConfigLoader() {
        properties = new Properties();
        try(InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if(in == null) {
                System.out.println("No config file found");
                return;
            }
            properties.load(in);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ConfigLoader getInstance() {
        if(instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }
}
