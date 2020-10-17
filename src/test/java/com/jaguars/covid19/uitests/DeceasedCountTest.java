package com.jaguars.covid19.uitests;

import com.jaguars.covid19.common.CommonTask;
import com.jaguars.covid19.pages.HomePage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.get;

public class DeceasedCountTest extends Covid19BaseTest {
    static final Logger logger = LogManager.getLogger(DeceasedCountTest.class);
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
    public void validateDetailsOfFirstState(ITestContext context) {
        String firstState = homePage.getStateNameByPosition(1);
        Assert.assertEquals(homePage.getConfirmedCountForState(firstState), "15,76,062", "Verify confirmed active count");
        Assert.assertEquals(homePage.getConfirmedCountForState(firstState), "1,89,715", "Verify active count");

        RestAssured.baseURI = "https://api.covid19india.org";

        Response response = get("/data.json");

        List<Map<String, ?>> teamsData = response.path("statewise");

        List<Map<String, ?>> collect = teamsData.stream().filter(m -> m.get("state").equals(firstState)).collect(Collectors.toList());
        Map<String, ?> stringMap = collect.get(0);
        String activeAPI = (String) stringMap.get("active");
        String confirmedAPI = (String) stringMap.get("confirmed");
        String recoveredAPI = (String) stringMap.get("recovered");
    }

    @Test(priority = 4, dependsOnMethods = {"openCovid19HomePage"})
    public void validateDetailsOfSecondState(ITestContext context) {
        String secondState = homePage.getStateNameByPosition(2);
        Assert.assertEquals(homePage.getConfirmedCountForState(secondState), "3,21,858", "Verify active count");
        Assert.assertEquals(homePage.getConfirmedCountForState(secondState), "1,89,715", "Verify active count");

        context.setAttribute("secondState", secondState);
    }


}



