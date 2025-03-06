package com.openweathermap.test;

import com.google.common.util.concurrent.Uninterruptibles;
import com.openweathermap.listener.TestListener;
import com.openweathermap.util.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners({TestListener.class})
public abstract class AbstractTest {
    protected WebDriver driver;
    @BeforeTest
    public void setDriver() {
        this.driver = WebDriverUtils.getDriver();
    }

    @AfterTest
    public void quiteDriver(){
        this.driver.quit();
    }

    @AfterMethod
    public void sleep(){
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }
}
