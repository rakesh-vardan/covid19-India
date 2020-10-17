package com.jaguars.covid19.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    static final Logger logger= LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void sortByDeceasedCount() {
        WebElement deceasedColumnHeader = driver.findElement(By.xpath("//div[text() = 'Deceased']"));
        deceasedColumnHeader.click();
    }

    public String getStateNameByPosition(int order){
        String xpath = String.format("//div[@class = 'state-name'][%s]", order);
        WebElement stateByPosition = driver.findElement(By.xpath(xpath));
        logger.info("State by potiion: "+stateByPosition);
        return stateByPosition.getText();

    }
}
