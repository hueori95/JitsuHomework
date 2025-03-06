package com.openweathermap.test;

import com.google.common.util.concurrent.Uninterruptibles;
import com.openweathermap.page.HomePage;
import com.openweathermap.util.Constants;
import com.openweathermap.util.ResourceLoader;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestCity extends AbstractTest{

    private HomePage homePage;

    @BeforeTest
    public void setPageObject() {
        homePage = new HomePage(driver);
    }
    @Test(testName = "openpage")
    public void openpage(){
        homePage.goTo(Constants.envUrl);
        Assert.assertTrue(homePage.isAt());
    }
    @DataProvider(name = "loginData")
    public Object[][] getData() throws Exception {
        String filePath = "src/test/resources/test-data/data-test.csv";
        return ResourceLoader.readCSV(filePath);
    }


    @Test(dataProvider = "loginData", dependsOnMethods = "openpage")
    public void searchCity(String input) {
        homePage.searchCity(input);
        homePage.verifyResutls(input);

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d");
        String expectedDate = currentDate.format(formatter);

        String actualCity = homePage.getcityName();
        String actualTemprature = homePage.getTemprature();
        String actualDate = homePage.getDateMonth();

        Assert.assertEquals(actualCity, input, "Name city is incorrect");
        Assert.assertEquals(actualDate, expectedDate, "Date is incorrect");
        Assert.assertTrue(actualTemprature.matches("\\d+"), "Value is not a number");

    }

    @BeforeTest
    @Override
    public void setDriver() {
        super.setDriver();
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
