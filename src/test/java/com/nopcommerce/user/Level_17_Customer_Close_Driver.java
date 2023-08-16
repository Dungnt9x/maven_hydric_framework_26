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

public class Level_17_Customer_Close_Driver extends BaseTest {
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

	  	log.info("Register - step_01: Navigator to 'Register' page");
	    registerPage = homePage.clickToRegisterLink();
	    
	    log.info("Register - step_02: Enter to firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Register - step_03: Enter to lastname textbox with value is '" + lastName + "'");
		registerPage.inputTolastnameTextbox(lastName);
		
		log.info("Register - step_04: Enter to email textbox with value is '" + lastName + "'");
		registerPage.inputToEmailTextbox(emailAddress);
		
		log.info("Register - step_05: Enter to correctpass textbox with value is '" + correctPassword + "'");
		registerPage.inputToPasswordTextbox(correctPassword);
		
		log.info("Register - step_06: Enter to confirmpass textbox with value is '" + correctPassword + "'");
		registerPage.inputToConfirmPasswordTextbox(correctPassword); 
		
		log.info("Register - step_07: click to register button");
		registerPage.clickToRegisterButton(); 
		
		driver=null;
		
		log.info("Register - step_08: verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");
	  

	  
	  log.info("Login - step_09: Navigator to 'login' page");
	  loginPage = homePage.openLoginPage();
	  
	  log.info("Login - step_10: Enter to email textbox with value is '" + emailAddress + "'");
	  loginPage.inputToEmailTextbox(emailAddress);
	  
	  log.info("Login - 11: Enter to pass textbox with value is '" + correctPassword + "'");
	  loginPage.inputToPasswordTextbox(correctPassword);
	  
	  log.info("Login - 12: click to login button");
	  homePage = loginPage.clickToLoginButton();
  }
  
  

  @AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
  
}
