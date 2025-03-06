package com.openweathermap.mobile_pages;
import io.appium.java_client.AppiumDriver;
public class LoginPagesMobile extends AbstractPageMobile{
    String usernameLocator = "(//android.widget.EditText)[1]";
    String passwordLocator = "(//android.widget.EditText)[2]";
    String loginButtonLocator = "//android.widget.Button[@content-desc='Log In']";


    public LoginPagesMobile(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void enterUserName(String username){
        elementUtilsMob.clickOnLocator(usernameLocator);
        elementUtilsMob.sendkeys(usernameLocator, username, 5);
    }
    public void enterPassword(String password){
        elementUtilsMob.clickOnLocator(passwordLocator);
        elementUtilsMob.sendkeys(passwordLocator, password, 5);
    }

    public void clickOnLoginButton(){
        elementUtilsMob.clickOnLocator(loginButtonLocator);
    }


    @Override
    public boolean isAt() {
        return elementUtilsMob.isDisplay(loginButtonLocator,5);
    }
}
