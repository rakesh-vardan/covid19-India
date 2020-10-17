package com.jaguars.covid19.common;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserFactory {
    static final Logger logger=LogManager.getLogger(BrowserFactory.class);

    public static WebDriver createInstance(String browserName, String gridHubURL) {
        BrowserType browserType = BrowserType.valueOf(browserName);
        WebDriver driver = null;
        switch(browserType) {
            case LOCAL_CHROME :
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case LOCAL_FIREFOX :
                System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case GRID_CHROME :
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("build", "your build name");
                capabilities.setCapability("name", "your test name");
                capabilities.setCapability("platform", "Windows 10");
                capabilities.setCapability("browserName", "Chrome");
                capabilities.setCapability("version","85.0");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                try {
                    driver = new RemoteWebDriver(new URL(gridHubURL), capabilities);
                } catch (MalformedURLException e) {
                    logger.error("Grid Hub URL is malformed: ", e.getMessage());
                }
                break;
            case GRID_FIREFOX :
                capabilities = new DesiredCapabilities();
                capabilities.setCapability("build", "your build name");
                capabilities.setCapability("name", "your test name");
                capabilities.setCapability("platform", "MacOS Catalina");
                capabilities.setCapability("browserName", "Firefox");
                capabilities.setCapability("version","78.0");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                try {
                    driver = new RemoteWebDriver(new URL(gridHubURL), firefoxOptions);
                } catch (MalformedURLException e) {
                    logger.error("Grid Hub URL is malformed: ", e.getMessage());
                }
            default:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
                break;

        }
        driver.manage().window().maximize();
        return driver;

    }

}
