package com.openweathermap.stepdefinitions;

import com.openweathermap.page.HomePage;
import com.openweathermap.util.Constants;
import com.openweathermap.util.ResourceLoader;
import com.openweathermap.util.WebDriverUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SearchCitySteps{
    private static final Logger log = LoggerFactory.getLogger(SearchCitySteps.class);
    private HomePage homePage;
    private String city;
    private WebDriver driver;

    public void before_setup() {
        this.driver = WebDriverUtils.getDriver();
        homePage = new HomePage(this.driver);
        String filePath = "src/test/resources/test-data/search_data.csv";
        this.city = ResourceLoader.readCSVToString(filePath);
    }

    @Given("I open OpenWeatherMap website")
    public void i_open_open_weather_map_website() {
        before_setup();
        homePage.goTo(Constants.envUrl);
        Assert.assertTrue(homePage.isAt());

    }

    @When("I search for city")
    public void i_search_for_city() {
        homePage.searchCity(this.city);
    }

    @When("I click the city on the result popup")
    public void i_click_the_city_on_the_result_popup(){
        homePage.verifyResutls(this.city);
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("It should be show the city name displayed as city")
    public void it_should_be_show_the_city_name_displayed_as_city(){
        String actualCity = homePage.getcityName();
        Assert.assertEquals(actualCity,this.city, "Name city is incorrect");
    }

    @Then("It should be display temperature displayed as a number")
    public void it_should_be_display_tempareture_displayed_as_a_number(){
        String actualTemprature = homePage.getTemprature();
        Assert.assertTrue(actualTemprature.matches("\\d+"), "Value is not a number");
    }

    @Then("It should be display current date correctly")
    public void it_should_be_display_current_date_correctly(){
        ZonedDateTime laTime = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d");
        String expectedDate = laTime.format(formatter);
        String actualDate = homePage.getDateMonth();
        Assert.assertEquals(actualDate, expectedDate, "Date is incorrect");
        quiteDriver();
    }

    public void quiteDriver() {
        this.driver.quit();
    }

}
