package com.jaguars.covid19.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{
    static final Logger logger= LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void sortByDeceasedCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text() = 'Deceased']")));
        WebElement deceasedColumnHeader = driver.findElement(By.xpath("//div[text() = 'Deceased']"));
        deceasedColumnHeader.click();
    }

    public String getStateNameByPosition(int order){
        String xpath = String.format("//div[contains(@class, 'state-name')][%s]", order);
        WebElement stateByPosition = driver.findElement(By.xpath(xpath));
        logger.info("State by position: "+stateByPosition);
        return stateByPosition.getText();
    }

    public String getConfirmedCountForState(String state){
        String xpath = String.format("//div[contains(@class, 'row')]//div[text()='%s']/parent::div/following-sibling::div/div[2]", state);
        WebElement confirmedCountElement = driver.findElement(By.xpath(xpath));
        logger.info("Confirmed Count: "+confirmedCountElement.getText());
        return confirmedCountElement.getText();
    }

    public String getActiveCountForState(String state){
        String xpath = String.format("//div[contains(@class, 'row')]//div[text()='%s']/parent::div/following-sibling::div/div[3]", state);
        WebElement activeCountElement = driver.findElement(By.xpath(xpath));
        logger.info("Confirmed Count: "+activeCountElement.getText());
        return activeCountElement.getText();
    }

}
