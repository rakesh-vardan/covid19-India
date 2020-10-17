package com.jaguars.covid19.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class CommonTask {
    static final Logger logger=LogManager.getLogger(CommonTask.class);

    public static Properties getProperties(String propertyFilename) {
        String propertyFile = "config/"+System.getProperty("env").toLowerCase()+"/"+propertyFilename;
        logger.info("File name: "+propertyFile );
        Properties props = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFile);
        try {
            logger.info("File name: "+propertyFile );
            props.load(inputStream);
        }catch(IOException ioe) {
            logger.error("Error loading properties file");
        }
        return props;
    }

    public static String getTestProperty(String key) {
        Properties props = getProperties("application.properties");
        return props.getProperty(key);
    }

    public static void launchApplication(WebDriver driver) {
        String appURL = getTestProperty("appURL");
        driver.get(appURL);
    }

    public static String getEmailAddress(String firstName, String lastName) {
        Random r = new Random( System.currentTimeMillis() );
        int randomNumber = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        return String.format(firstName+lastName+"%s@abc.com", randomNumber);
    }

}
