package com.jaguars.covid19.uitests;

import com.jaguars.covid19.common.BrowserFactory;
import com.jaguars.covid19.common.CommonTask;
import com.jaguars.covid19.pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeceasedCountTest extends Covid19BaseTest {
    static final Logger logger= LogManager.getLogger(DeceasedCountTest.class);
    HomePage homePage;

    @Test(priority = 1)
    public void openCovid19HomePage() {
        CommonTask.launchApplication(driver);
        homePage = new HomePage(driver);
    }

    @Test(priority = 2, dependsOnMethods = {"openCovid19HomePage"})
    public void sortByDeceasedCount() {
        homePage.sortByDeceasedCount();
    }

    @Test(priority = 3, dependsOnMethods = {"openCovid19HomePage"})
    public void validateDetailsOfFirstState() {
        String firstState = homePage.getStateNameByPosition(1);
        Assert.assertEquals(homePage.getConfirmedCountForState(firstState), "15,76,062", "Verify active count");
        Assert.assertEquals(homePage.getConfirmedCountForState(firstState), "1,89,715", "Verify active count");
    }

    @Test(priority = 4, dependsOnMethods = {"openCovid19HomePage"})
    public void validateDetailsOfSecondState() {
        String firstState = homePage.getStateNameByPosition(2);
        Assert.assertEquals(homePage.getConfirmedCountForState(firstState), "3,21,858", "Verify active count");
        Assert.assertEquals(homePage.getConfirmedCountForState(firstState), "1,89,715", "Verify active count");
    }



}



