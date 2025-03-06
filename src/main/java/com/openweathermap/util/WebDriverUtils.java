package com.openweathermap.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverUtils {

    private static final Logger log = LoggerFactory.getLogger(WebDriverUtils.class);
    private static WebDriver driver;
    private static AppiumDriver appiumDriver;
    public static WebDriver getDriver() {
        if(Boolean.getBoolean("selenium.grid.enable")){
            driver = getRemoteDriver();
        }else {
            driver = getLocalDriver();
        }
        return driver;
    }

    public static AppiumDriver getAppiumDriver() throws MalformedURLException {
        appiumDriver = getAndroidDriver();
        return appiumDriver;
    }

    private static WebDriver getRemoteDriver()  {
        // http://localhost:4444
        Capabilities capabilities;
        if (System.getProperty("browser").equalsIgnoreCase("chrome"))
            capabilities = new ChromeOptions();
        else capabilities = new FirefoxOptions();

        try {
            return new RemoteWebDriver(new URL("http://localhost:4444"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static WebDriver getLocalDriver(){
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver");
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");

        driver = new ChromeDriver(chromeOptions);
        return driver;
    }

    private static AppiumDriver getAndroidDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appium:automationName","UIAutomator2");
        capabilities.setCapability("appium:deviceName","emulator-5554");
        capabilities.setCapability("appium:appPackage","com.axlehire.drive.staging");
        capabilities.setCapability("appium:appActivity","com.axlehire.drive.MainActivity");
        capabilities.setCapability("noReset", "false");
        capabilities.setCapability("fullReset", "false");
        capabilities.setCapability("autoGrantPermissions", "true");

        appiumDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        return appiumDriver;
    }

}
