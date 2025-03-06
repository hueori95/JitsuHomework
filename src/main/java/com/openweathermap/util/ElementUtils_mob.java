package com.openweathermap.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ElementUtils_mob {
    private AppiumDriver appiumDriver;

    public ElementUtils_mob(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public WebElement getElement(String element_locator){
        return appiumDriver.findElement(By.xpath(element_locator));
    }

    public List<WebElement> getElements(String element_locator){
        return appiumDriver.findElements(By.xpath(element_locator));
    }
    public void waitForClickable(String element_locator, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(timeoutInSeconds));
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
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element_locator)));
    }

    public void waitInVisibilityOfElementLocated(String element_locator, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element_locator)));
    }

    public void waitTextToBePresentInElementValue(WebElement element, String text, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    public void clearText(String element_locator){
        getElement(element_locator).clear();
    }

    public void sendkeys(String element_locator, String text, int timeoutInSeconds){
        WebElement element = getElement(element_locator);
        element.sendKeys(text);
    }

    public boolean isDisplay(String element_locator, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element_locator))).isDisplayed();
    }

    public String getTextInElement(String element_locator,  int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element_locator))).getText();
    }
    public String getText(WebElement element){
        return element.getText();
    }

    public void tabAtPoint(int x, int y){
        TouchAction action = new TouchAction((PerformsTouchActions) this.appiumDriver);

        action.tap(PointOption.point(x, y)).perform();
    }
    public void longPress(int x, int y, int second){
        TouchAction action = new TouchAction((PerformsTouchActions) this.appiumDriver);

        action.longPress(PointOption.point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(second)))
                .release()
                .perform();
    }

    public void scrollWithTouchAction(int ratioStartY, int ratioEndY) {
        Dimension size = appiumDriver.manage().window().getSize();
        int screenWidth = size.getWidth();  // e.g., 1080
        int screenHeight = size.getHeight(); // e.g., 2400
        int startX = screenWidth/2;
        int endX = startX;
        int startY = (int) (screenHeight * ratioStartY/100);
        int endY = (int) (screenHeight * ratioEndY/100); // 20% from top (upper area)

        TouchAction action = new TouchAction((PerformsTouchActions) appiumDriver);
        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();

    }

}
