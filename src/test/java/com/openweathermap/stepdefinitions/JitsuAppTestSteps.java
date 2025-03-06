package com.openweathermap.stepdefinitions;

import com.openweathermap.mobile_pages.HomePageMobile;
import com.openweathermap.mobile_pages.LoginPagesMobile;
import com.openweathermap.mobile_pages.ProfilePage;
import com.openweathermap.mobile_pages.TutorialsPage;
import com.openweathermap.util.WebDriverUtils;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.net.MalformedURLException;

public class JitsuAppTestSteps {
    private static final Logger log = LoggerFactory.getLogger(JitsuAppTestSteps.class);
    private LoginPagesMobile loginPagesMobile;
    private HomePageMobile homePageMobile;
    private AppiumDriver appiumDriver;
    private ProfilePage profilePage;
    private TutorialsPage tutorialsPage;

    public JitsuAppTestSteps() throws MalformedURLException {
        this.appiumDriver = WebDriverUtils.getAppiumDriver();
        this.loginPagesMobile = new LoginPagesMobile(this.appiumDriver);
        this.homePageMobile = new HomePageMobile(this.appiumDriver);
        this.profilePage = new ProfilePage(this.appiumDriver);
        this.tutorialsPage = new TutorialsPage(this.appiumDriver);
    }

    @Given("I access to the Jitsu app")
    public void i_access_to_the_jitsu_app() {
        loginPagesMobile.isAt();
    }

    @When("Login to app with valid information username {string} and password {string}")
    public void login_to_app_with_valid_information_username_and_password(String username, String password){
        loginPagesMobile.enterUserName(username);
        loginPagesMobile.enterPassword(password);
        loginPagesMobile.clickOnLoginButton();
        Assert.assertTrue(homePageMobile.isAt(), "Login unsuccessfully");
    }

    @And("He opens the Profile screen and taps on the Tutorias")
    public void he_opens_the_profile_screen_and_taps_on_the_tutorias(){
        profilePage.openProfileScreen();
        Assert.assertTrue(profilePage.isAt(), "Cannot open Profile page");
        profilePage.tabsOnTutorias();
        Assert.assertTrue(tutorialsPage.isAt(), "Cannot open Tutorials page");

    }

    @Then("He sees 3 sections: Assigned Route, Direct Booking, Ticket Booking")
    public void he_sees_3_section() {
        tutorialsPage.verify3sections();
    }

    @And("He taps on Assigned Route")
    public void he_taps_on_assign_route(){
        tutorialsPage.tabOnAssignedRoute();

        Assert.assertTrue(homePageMobile.isAt(), "Go to Assigned Route successfully");
    }

    @Then("He is in Active Assignment screen and sees the starting of tutorial which is chosen in step 4")
    public void he_is_in_active_assigment_screen(){
        try{
            homePageMobile.start_tutorial();
        }catch (Exception ex){
        }
        homePageMobile.quit_tutorial();
        this.appiumDriver.quit();
    }


}
