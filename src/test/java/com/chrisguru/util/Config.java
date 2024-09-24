package com.chrisguru.util;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Logger log= LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PATH="config/default.properties";
    private static Properties properties;
    public static void initialize(){
        //// Load the properties file
        properties=loadProperties();

        ///override the properties file values in case we receive a system properties through command line
        for(String key:properties.stringPropertyNames()){
            if(System.getProperties().stringPropertyNames().contains(key)){
                properties.setProperty(key,System.getProperty(key));
            }
        }

        //// Print all the properties file values
        for(String key:properties.stringPropertyNames()){
            log.info("The key: {}, has value: {} ", key,properties.getProperty(key));
        }
    }

    public static String getKeyValue(String key){
        return properties.getProperty(key);
    }

    private static Properties loadProperties(){
        Properties properties=new Properties();
        try (InputStream stream=ResourceLoader.getResource(DEFAULT_PATH)){
            properties.load(stream);
        }catch (Exception e){
            log.error("Unable to read the properties file, {}",DEFAULT_PATH,e);
        }
        return properties;
    }
}
