package com.nopcommerce.common;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class Common_01_Register_End_User extends BaseTest {
	private WebDriver driver;
	  private String invalidEmail, firstName, lastName;
	  public static String emailAddress, correctPassword;
	  private UserHomePageObject homePage;
	  private UserRegisterPageObject registerPage;
  
	  @Parameters("browser")
	  @BeforeTest(description= "Create new user for all class")
	  public void beforeClass(String browserName) {
		  driver = getBrowserDriver(browserName);
		  homePage = PageGeneratorManager.getUserHomePage(driver);
	    
	    firstName = "automation";
	    lastName = "1"; 
	    correctPassword = "123456";
	    invalidEmail = "afc";
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
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	  
  }
 
  
  

  @AfterTest
	public void afterClass() {
		driver.quit();
	}
  
}
