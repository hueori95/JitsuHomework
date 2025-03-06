package com.openweathermap.mobile_pages;

import com.openweathermap.util.ElementUtils_mob;
import io.appium.java_client.AppiumDriver;

public abstract class AbstractPageMobile {
    protected final AppiumDriver appiumDriver;
    protected final ElementUtils_mob elementUtilsMob;

    public AbstractPageMobile(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        this.elementUtilsMob = new ElementUtils_mob(this.appiumDriver);
    }

    public abstract boolean isAt();
}
