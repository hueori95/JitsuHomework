package com.openweathermap.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class ElementUtils {
    private WebDriver driver;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(String element_locator){
        return driver.findElement(By.xpath(element_locator));
    }

    public List<WebElement> getElements(String element_locator){
        return driver.findElements(By.xpath(element_locator));
    }
    public void waitForClickable(String element_locator, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element_locator)));
    }

    public void clickOnLocator(String element_locator){
        WebElement element = getElement(element_locator);
        element.click();
    }
    public void click(WebElement element){
        element.click();
    }

    public void waitVisibilityOfElementLocated(String element_locator, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element_locator)));
    }

    public void waitInVisibilityOfElementLocated(String element_locator, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element_locator)));
    }

    public void waitTextToBePresentInElementValue(WebElement element, String text, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    public void clearText(String element_locator){
        getElement(element_locator).clear();
    }

    public void sendkeys(String element_locator, String text, int timeoutInSeconds){
        WebElement element = getElement(element_locator);
        getElement(element_locator).sendKeys(text);
        waitTextToBePresentInElementValue(element, text, timeoutInSeconds);

    }

    public boolean isDisplay(String element_locator, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element_locator))).isDisplayed();
    }

    public String getTextInElement(String element_locator,  int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element_locator))).getText();
    }
    public String getText(WebElement element){
        return element.getText();
    }

}
