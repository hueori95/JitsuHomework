package com.openweathermap.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HomePage extends AbstractPage{
    private static final Logger log = LoggerFactory.getLogger(HomePage.class);
    String namepageLocator = "//span[@class='orange-text']";
    String cityinputLocator = "//input[@placeholder='Search city']";
    String searchButtonLocator = "//button[text()='Search']";
    String dropdownPopupLocator = "//ul[@class='search-dropdown-menu']";
    String listcityLocator = "//ul[@class='search-dropdown-menu']//li//span[1]";
    String currentDateLocator = "//div[@class='section-content']//span[@class='orange-text']";
    String citynameLocator = "//div[@class='section-content']//h2";
    String tempratureLocator = "//div[@class='section-content']//div[@class='current-temp']//span";

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public void goTo(String url){
        this.driver.get(url);
    }

    public  void searchCity(String expectedCity){
        log.info("--------------------");
        log.info(expectedCity);

        elementutils.sendkeys(cityinputLocator, expectedCity, 5);
        elementutils.waitForClickable(searchButtonLocator, 5);
        elementutils.clickOnLocator(searchButtonLocator);
        elementutils.waitVisibilityOfElementLocated(dropdownPopupLocator,5);
    }

    public void verifyResutls(String expectedCity) {
        boolean cityFound = false;
         if (elementutils.isDisplay(dropdownPopupLocator, 5)){
             List<WebElement> listLocal = elementutils.getElements(listcityLocator);
             for (WebElement element : listLocal){
                if(elementutils.getText(element).trim().equalsIgnoreCase(expectedCity)){
                    elementutils.click(element);
                    elementutils.waitInVisibilityOfElementLocated(dropdownPopupLocator, 5);
                    cityFound = true;
                    break;
                }
            }
            if(!cityFound){
                log.info("City not found: " + expectedCity);
            }
        }
    }

    public String getcityName(){
        return elementutils.getTextInElement(citynameLocator, 5);
    }

    public String getTemprature(){
        String temp = elementutils.getTextInElement(tempratureLocator, 5);
        if(!temp.isEmpty()){
            temp = temp.split("°")[0];
        }
        return temp;
    }

    public String getDateMonth(){
        String datemonth = elementutils.getTextInElement(currentDateLocator, 5);
       if (!datemonth.isEmpty()){
           datemonth = datemonth.split(",")[0];
       }
        return datemonth;
    }

    @Override
    public boolean isAt() {
        return elementutils.isDisplay(namepageLocator, 5);
    }
}
