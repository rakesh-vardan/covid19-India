package com.jaguars;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MobileTests {

    AppiumDriver<MobileElement> driver = null;
    DesiredCapabilities caps;

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
    public void testMobile() throws InterruptedException {
        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps);

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }

        driver.get("https://www.covid19india.org/");

        Thread.sleep(10000);
        driver.findElementByXPath("//div[contains(text(), 'Deceased')]").click();
        String confirmed = driver.findElementByXPath("//div[@class='row']/div/div[contains(text(), 'Maharashtra')]/parent::div/following-sibling::div/div[2]").getText();
        String active = driver.findElementByXPath("//div[@class='row']/div/div[contains(text(), 'Maharashtra')]/parent::div/following-sibling::div[2]/div").getText();
        String recovered = driver.findElementByXPath("//div[@class='row']/div/div[contains(text(), 'Maharashtra')]/parent::div/following-sibling::div[3]/div[2]").getText();
        System.out.println( "Maharashtra: confirmed - " + confirmed + ", active: " + active + ", recovered: " + recovered);

        String confirmed2 = driver.findElementByXPath("//div[@class='row']/div/div[contains(text(), 'Tamil Nadu')]/parent::div/following-sibling::div/div[2]").getText();
        String active2 = driver.findElementByXPath("//div[@class='row']/div/div[contains(text(), 'Tamil Nadu')]/parent::div/following-sibling::div[2]/div").getText();
        String recovered2 = driver.findElementByXPath("//div[@class='row']/div/div[contains(text(), 'Tamil Nadu')]/parent::div/following-sibling::div[3]/div[2]").getText();
        System.out.println( "Tamil Nadu: confirmed - " + confirmed2 + ", active: " + active2 + ", recovered: " + recovered2);

        String confirmed3 = driver.findElementByXPath("//div[@class='row']/div/div[contains(text(), 'Karnataka')]/parent::div/following-sibling::div/div[2]").getText();
        String active3 = driver.findElementByXPath("//div[@class='row']/div/div[contains(text(), 'Karnataka')]/parent::div/following-sibling::div[2]/div").getText();
        String recovered3 = driver.findElementByXPath("//div[@class='row']/div/div[contains(text(), 'Karnataka')]/parent::div/following-sibling::div[3]/div[2]").getText();
        System.out.println( "Karnataka: confirmed - " + confirmed3 + ", active: " + active3 + ", recovered: " + recovered3);
        Thread.sleep(20000L);


        driver.findElementByXPath("//div[contains(@class, 'state-name') and text()='Maharashtra']").click();
        driver.findElementByXPath("//div[contains(@class, 'state-page')]").click();
        driver.findElementByXPath("//span[text()='View all']/parent::button").click();

        List<MobileElement> states = driver.findElementsByXPath("//div[@class='district']");

        List<String> data = new ArrayList<>();
        List<String> cityname = new ArrayList<>();
        for(MobileElement me : states) {
            String count = me.findElementByXPath(".//h2").getText();
            String name = me.findElementByXPath(".//h5").getText();
            data.add(count);
        }

        System.out.println("all states data: " + data);

        //div[@class='district']/h2   total shoudl be 36






    }
}
