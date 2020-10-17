package com.jaguars.covid19.common;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.get;

public class CommonTask {
    private static final Logger logger = LogManager.getLogger(CommonTask.class);


    public static Properties getProperties(String propertyFilename) {
        String propertyFile = "config/" + System.getProperty("env").toLowerCase() + "/" + propertyFilename;
        logger.info("File name: " + propertyFile);
        Properties props = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFile);
        try {
            logger.info("File name: " + propertyFile);
            props.load(inputStream);
        } catch (IOException ioe) {
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
        Random r = new Random(System.currentTimeMillis());
        int randomNumber = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        return String.format(firstName + lastName + "%s@abc.com", randomNumber);
    }

    public static String getCount(String stateName, String type) {
        List<Map<String, ?>> teamsData = getDataApiResponse().path("statewise");
        List<Map<String, ?>> collect = teamsData.stream().filter(m -> m.get("state").equals(stateName)).collect(Collectors.toList());
        Map<String, ?> stateData = collect.get(0);
        String count = null;

        if (StringUtils.equals(type, "active")) {
            count = (String) stateData.get("active");
        } else if (StringUtils.equals(type, "confirmed")) {
            count = (String) stateData.get("confirmed");
        } else if (StringUtils.equals(type, "recovered")) {
            count = (String) stateData.get("recovered");
        } else if (StringUtils.equals(type, "deaths")) {
            count = (String) stateData.get("deaths");
        }
        return count;
    }

    private static Response getDataApiResponse() {
        RestAssured.baseURI = "https://api.covid19india.org";
        return get("/data.json");
    }

}
