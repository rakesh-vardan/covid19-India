package com.jaguars.covid19.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(HomePage.class);
    private static final String TITLE = "title";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    protected String getXpathForRecovered(String state) {
        return String.format("//div[contains(@class, 'row')]//div[text()='%s']/parent::div/following-sibling::div[3]/div[2]", state);
    }

    protected String getXpathForConfirmed(String state) {
        return String.format("(//div[contains(@class, 'row')]//div[text()='%s']/parent::div/following-sibling::div/div[2])[1]", state);
    }

    protected String getXpathForActive(String state) {
        return String.format("//div[contains(@class, 'row')]//div[text()='%s']/parent::div/following-sibling::div[2]/div", state);
    }

    protected String getXpathForDeceased(String state) {
        return String.format("//div[contains(@class, 'row')]//div[text()='%s']/parent::div/following-sibling::div[4]/div[2]", state);
    }

    public void sortByDeceasedCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text() = 'Deceased']")));
        WebElement deceasedColumnHeader = driver.findElement(By.xpath("//div[text() = 'Deceased']"));
        deceasedColumnHeader.click();
    }

    public String getStateNameByPosition(int order) {
        String xpath = String.format("(//div[@class='state-name fadeInUp'])[%s]", order);
        WebElement stateByPosition = driver.findElement(By.xpath(xpath));
        logger.info("State by position: {} ", stateByPosition);
        return stateByPosition.getText();
    }

    public String getConfirmedCountForState(String state) {
        WebElement confirmedCountElement = driver.findElement(By.xpath(this.getXpathForConfirmed(state)));
        logger.info("Confirmed Count: {} ", confirmedCountElement.getText());
        return confirmedCountElement.getAttribute(TITLE);
    }

    public String getActiveCountForState(String state) {
        WebElement activeCountElement = driver.findElement(By.xpath(this.getXpathForActive(state)));
        logger.info("Active Count: {} ", activeCountElement.getText());
        return driver.findElement(By.xpath(this.getXpathForActive(state))).getAttribute(TITLE);
    }

    public String getRecoveredCountForState(String state) {
        WebElement recoveredCountElement = driver.findElement(By.xpath(this.getXpathForRecovered(state)));
        logger.info("Recovered Count: {} ", recoveredCountElement.getText());
        return recoveredCountElement.getAttribute(TITLE);
    }

    public String getDeceasedCountForState(String state) {
        WebElement deceasedCountElement = driver.findElement(By.xpath(this.getXpathForDeceased(state)));
        logger.info("Deceased Count: {} ", deceasedCountElement.getText());
        return deceasedCountElement.getAttribute(TITLE);
    }

}
