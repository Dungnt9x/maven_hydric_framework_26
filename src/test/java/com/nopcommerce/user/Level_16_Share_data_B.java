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

public class Level_16_Share_data_B extends BaseTest {
	private WebDriver driver;
	  private String emailAddress, firstName, lastName, correctPassword;
	  private UserHomePageObject homePage;
	  private UserRegisterPageObject registerPage;
	  private UserLoginPageObject loginPage;
  
	  @Parameters("browser")
	  @BeforeClass
	  public void beforeClass(String browserName) {
		  driver = getBrowserDriver(browserName);
		  homePage = PageGeneratorManager.getUserHomePage(driver);
	    
	    firstName = "automation";
	    lastName = "1"; 
	    correctPassword = "123456";
	    emailAddress = "afc" + genarateFakeNumber() + "@mail.vn";

	  	log.info("Pre-Condition - step_01: Navigator to 'Register' page");
	    registerPage = homePage.clickToRegisterLink();
	    
	    log.info("Pre-Condition - step_02: Enter to firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Pre-Condition - step_03: Enter to lastname textbox with value is '" + lastName + "'");
		registerPage.inputTolastnameTextbox(lastName);
		
		log.info("Pre-Condition - step_04: Enter to email textbox with value is '" + lastName + "'");
		registerPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-Condition - step_05: Enter to correctpass textbox with value is '" + correctPassword + "'");
		registerPage.inputToPasswordTextbox(correctPassword);
		
		log.info("Pre-Condition - step_06: Enter to confirmpass textbox with value is '" + correctPassword + "'");
		registerPage.inputToConfirmPasswordTextbox(correctPassword); 
		
		log.info("Pre-Condition - step_07: click to register button");
		registerPage.clickToRegisterButton(); 
		
		log.info("Pre-Condition - step_08: verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");

	  
	    log.info("Pre-Condition - step_09: Navigator to 'login' page");
	    loginPage = homePage.openLoginPage();
	  
	    log.info("Pre-Condition - step_10: Enter to email textbox with value is '" + emailAddress + "'");
	    loginPage.inputToEmailTextbox(emailAddress);
	  
	    log.info("Pre-Condition - step_11: Enter to pass textbox with value is '" + correctPassword + "'");
	    loginPage.inputToPasswordTextbox(correctPassword);
	  
	    log.info("Pre-Condition - step_12: click to login button");
	    homePage = loginPage.clickToLoginButton();
  }
  
  

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  
}
