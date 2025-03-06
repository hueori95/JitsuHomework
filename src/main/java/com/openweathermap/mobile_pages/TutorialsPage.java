package com.openweathermap.mobile_pages;

import com.openweathermap.util.WebDriverUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TutorialsPage extends AbstractPageMobile{
    private static final Logger log = LoggerFactory.getLogger(WebDriverUtils.class);
    String titleTurorials = "//android.view.View[@content-desc='Tutorials']";
    String xpath3Sections = "//android.view.View[contains(@content-desc, 'Learn about')]";
    String childtitleSections = "/android.view.View/android.view.View[@content-desc]";
    String assignedRoute = "//android.view.View[@content-desc='Assigned Route']";

    public TutorialsPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void verify3sections(){
        List<WebElement> listsections = elementUtilsMob.getElements(xpath3Sections);
        log.info(String.valueOf(listsections.size()));
        String[] expectedSection = new String[]{"Assigned Route", "Direct Booking", "Ticket Booking"};
        List<String> actualSections = new ArrayList<>();
        for(WebElement element:listsections){
            log.info(element.toString());
            WebElement titleElement = element.findElement(By.xpath(childtitleSections));
            String title = titleElement.getAttribute("content-desc");
            actualSections.add(title);
        }
        Assert.assertEquals(Arrays.asList(expectedSection), actualSections, "3 sections are incorrectly");
    }

    public void tabOnAssignedRoute(){
        elementUtilsMob.waitForClickable(assignedRoute, 5);
        elementUtilsMob.clickOnLocator(assignedRoute);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isAt() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return elementUtilsMob.getElement(titleTurorials).isDisplayed();
    }
}
