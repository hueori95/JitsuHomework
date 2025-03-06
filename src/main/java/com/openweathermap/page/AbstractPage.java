package com.openweathermap.page;

import com.openweathermap.util.ElementUtils;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
    protected final WebDriver driver;
    protected ElementUtils elementutils;

    public AbstractPage(WebDriver driver){
        this.driver = driver;
        this.elementutils = new ElementUtils(this.driver);

    }

    public abstract boolean isAt();

}
