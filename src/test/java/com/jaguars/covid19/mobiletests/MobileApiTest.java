package com.jaguars.covid19.mobiletests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.get;
import static org.testng.Assert.assertEquals;

public class MobileApiTest {

    private AppiumDriver<MobileElement> driver = null;
    private DesiredCapabilities caps;

    static final Logger logger = LogManager.getLogger(MobileApiTest.class);

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/rakesh-vardan/softwares/chromedriver_win32/chromedriver.exe");

        caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "My Phone");
        caps.setCapability("udid", "ZY2243DZXC");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "8.1.0");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("noReset", "true");
    }

    @Test
    public void testMobileAPIFlow1() throws InterruptedException {
        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps);

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }

        driver.get("https://www.covid19india.org/");

        //TODO:: update this to dynamic wait
        Thread.sleep(10000);

        driver.findElementByXPath("//div[contains(text(), 'Deceased')]").click();

        for (int i = 1; i <= 3; i++) {
            MobileElement stateName = driver.findElementByXPath("(//div[@class='state-name fadeInUp'])[" + i + "]");
            MobileElement confirmedCountMobile = driver.findElementByXPath("(//div[@class='state-name fadeInUp'])[" + i + "]/parent::div/following-sibling::div/div[2]");
            MobileElement ActiveCountMobile = driver.findElementByXPath("(//div[@class='state-name fadeInUp'])[" + i + "]/parent::div/following-sibling::div[2]/div");
            MobileElement RecoveredCountMobile = driver.findElementByXPath("(//div[@class='state-name fadeInUp'])[" + i + "]/parent::div/following-sibling::div[3]/div[2]");

            RestAssured.baseURI = "https://api.covid19india.org";

            Response response = get("/data.json");

            List<Map<String, ?>> teamsData = response.path("statewise");

            List<Map<String, ?>> collect = teamsData.stream().filter(m -> m.get("state").equals(stateName.getText())).collect(Collectors.toList());
            Map<String, ?> stringMap = collect.get(0);
            String activeAPI = (String) stringMap.get("active");
            String confirmedAPI = (String) stringMap.get("confirmed");
            String recoveredAPI = (String) stringMap.get("recovered");

            logger.info("active" + activeAPI + "confirmed" + confirmedAPI + "recovered" + recoveredAPI);

            assertEquals(confirmedCountMobile.getText(), confirmedAPI);
            assertEquals(ActiveCountMobile.getText(), activeAPI);
            assertEquals(RecoveredCountMobile.getText(), recoveredAPI);
        }
    }

    @Test
    public void testMobileFlow2() throws InterruptedException {
        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps);

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }

        driver.get("https://www.covid19india.org/");

        //TODO:: Update the wait to be dynamic
        Thread.sleep(10000);

        driver.findElementByXPath("//div[contains(text(), 'Deceased')]").click();
        driver.findElementByXPath("(//div[@class='state-name fadeInUp'])[1]").click();
        driver.findElementByXPath("//div[contains(@class, 'state-page')]").click();
        driver.findElementByXPath("//span[text()='View all']/parent::button").click();

        List<MobileElement> states = driver.findElementsByXPath("//div[@class='district']");

        List<String> countList = new ArrayList<>();
        List<String> cityNameList = new ArrayList<>();
        for (MobileElement me : states) {
            String count = me.findElementByXPath(".//h2").getText();
            String name = me.findElementByXPath(".//h5").getText();
            countList.add(count);
        }

        logger.info("all cities count: " + countList);
        logger.info("all cities: " + cityNameList);
    }

}
