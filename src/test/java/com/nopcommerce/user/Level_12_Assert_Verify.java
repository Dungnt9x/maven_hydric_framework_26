package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_12_Assert_Verify extends BaseTest {
	private WebDriver driver;
	  private String emailAddress, invalidEmail, firstName, lastName, correctPassword;
	  private String projectPath = System.getProperty("user.dir");
	  private UserHomePageObject homePage;
	  private UserRegisterPageObject registerPage;
	  private UserLoginPageObject loginPage;
	  private UserCustomerInfoPageObject customerInfoPage;
	  private UserAddressPageObject addressPage;
	  private UserMyProductReviewPageObject myProductReviewPage;
	  private UserRewardPointPageObject rewardPointPage;
  
	  @Parameters("browser")
	  @BeforeClass
	  public void beforeClass(String browserName) {
		  driver = getBrowserDriver(browserName);
		  homePage = PageGeneratorManager.getUserHomePage(driver);
	    
	    firstName = "automation";
	    lastName = "1"; 
	    correctPassword = "123456";
	    invalidEmail = "afc";
	    emailAddress = "afc" + genarateFakeNumber() + "@mail.vn";
	    
  }
  @Test
  public void Register_01_Register_Login() {

	    registerPage = homePage.clickToRegisterLink();
	    
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputTolastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(correctPassword);
		registerPage.inputToConfirmPasswordTextbox(correctPassword); 
		registerPage.clickToRegisterButton(); 
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");
	  
  
	  loginPage = homePage.openLoginPage();
	  
	  loginPage.inputToEmailTextbox(emailAddress);
	  loginPage.inputToPasswordTextbox(correctPassword);
	  
	  homePage = loginPage.clickToLoginButton();
	  verifyFalse(homePage.isMyAccountLinkDisplayed());
  
      
	  customerInfoPage = homePage.openMyAccountPage();
	  verifyFalse(customerInfoPage.isCustomerInfoPageDisplay());
  }
  
  

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  
}
