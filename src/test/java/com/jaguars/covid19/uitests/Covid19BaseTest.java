package com.jaguars.covid19.uitests;

import com.jaguars.covid19.common.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class Covid19BaseTest {
    protected WebDriver driver;
    static final Logger logger = LogManager.getLogger(Covid19BaseTest.class);

    @BeforeSuite
    public void beforeSuite(){
        //Implement when needed
    }

    @AfterSuite
    public void afterSuite(){
        //Implement when needed
    }

    @BeforeTest
    @Parameters({"browser", "gridHubURL"})
    public void beforeTest(String browser, String gridHubURL, ITestContext testContext){
        logger.info("Browser: %s, Grid Hub URL: %s", browser, gridHubURL);
        driver = BrowserFactory.createInstance(browser, gridHubURL);
        testContext.setAttribute("driver", driver);
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
