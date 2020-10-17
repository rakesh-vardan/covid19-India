package com.jaguars.covid19.uitests;

import com.jaguars.covid19.common.CommonTask;
import com.jaguars.covid19.pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeceasedCountTest extends Covid19BaseTest {

    private HomePage homePage;

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
        Assert.assertEquals(homePage.getConfirmedCountForState(firstState), CommonTask.getCount(firstState, "confirmed"), "Verify confirmed count");
        Assert.assertEquals(homePage.getActiveCountForState(firstState), CommonTask.getCount(firstState, "active"), "Verify active count");
        Assert.assertEquals(homePage.getRecoveredCountForState(firstState), CommonTask.getCount(firstState, "recovered"), "Verify Recovered count");
        Assert.assertEquals(homePage.getDeceasedCountForState(firstState), CommonTask.getCount(firstState, "deaths"), "Verify Deceased count");
    }

    @Test(priority = 4, dependsOnMethods = {"openCovid19HomePage"})
    public void validateDetailsOfSecondState() {
        String secondState = homePage.getStateNameByPosition(2);
        Assert.assertEquals(homePage.getConfirmedCountForState(secondState), CommonTask.getCount(secondState, "confirmed"), "Verify confirmed count");
        Assert.assertEquals(homePage.getActiveCountForState(secondState), CommonTask.getCount(secondState, "active"), "Verify active count");
        Assert.assertEquals(homePage.getRecoveredCountForState(secondState), CommonTask.getCount(secondState, "recovered"), "Verify Recovered count");
        Assert.assertEquals(homePage.getDeceasedCountForState(secondState), CommonTask.getCount(secondState, "deaths"), "Verify Deceased count");
    }

    @Test(priority = 5, dependsOnMethods = {"openCovid19HomePage"})
    public void validateDetailsOfThirdState() {
        String thirdState = homePage.getStateNameByPosition(3);
        Assert.assertEquals(homePage.getConfirmedCountForState(thirdState), CommonTask.getCount(thirdState, "confirmed"), "Verify confirmed count");
        Assert.assertEquals(homePage.getActiveCountForState(thirdState), CommonTask.getCount(thirdState, "active"), "Verify active count");
        Assert.assertEquals(homePage.getRecoveredCountForState(thirdState), CommonTask.getCount(thirdState, "recovered"), "Verify Recovered count");
        Assert.assertEquals(homePage.getDeceasedCountForState(thirdState), CommonTask.getCount(thirdState, "deaths"), "Verify Deceased count");
    }

}



