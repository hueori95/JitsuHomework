package com.openweathermap.mobile_pages;

import io.appium.java_client.AppiumDriver;

public class ProfilePage extends AbstractPageMobile{
    String titleProfilePage = "//android.view.View[@content-desc='Firstcibg Middlegta Lastegox']";

    public ProfilePage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void openProfileScreen(){
        elementUtilsMob.longPress(1296,2670, 1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void tabsOnTutorias(){
        elementUtilsMob.scrollWithTouchAction(60, 40);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        elementUtilsMob.longPress(720, 1570, 1);

    }


    @Override
    public boolean isAt() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return elementUtilsMob.getElement(titleProfilePage).isDisplayed();
    }
}
