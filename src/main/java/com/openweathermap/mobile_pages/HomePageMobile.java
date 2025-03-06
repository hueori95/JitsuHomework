package com.openweathermap.mobile_pages;
import io.appium.java_client.AppiumDriver;

public class HomePageMobile extends AbstractPageMobile {
    String activeAssignmentLocator = "//android.view.View[@content-desc='Active Assignment']";
    String quittutorial = "//android.widget.Button[@content-desc='Quit tutorial'";
    String yesprompt = "//android.widget.Button[@content-desc='Yes, quit tutorial']";

    public HomePageMobile(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void quit_tutorial(){
//        elementUtilsMob.waitForClickable(quittutorial, 5);
//        elementUtilsMob.clickOnLocator(quittutorial);
//        elementUtilsMob.waitForClickable(yesprompt, 5);
//        elementUtilsMob.clickOnLocator(yesprompt);
        elementUtilsMob.longPress(333, 2327, 1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        elementUtilsMob.longPress(720, 1906, 1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void start_tutorial(){
        elementUtilsMob.longPress(720, 2255, 1);
    }
    @Override
    public boolean isAt() {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return elementUtilsMob.getElement(activeAssignmentLocator).isDisplayed();
    }
}
