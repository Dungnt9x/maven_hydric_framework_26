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

public class Level_18_Patten_Object extends BaseTest {
	private WebDriver driver;
	  private String emailAddress, invalidEmail, firstName, lastName, correctPassword, gender;
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
	    gender ="Female";
	    
  }
  @Test
  public void User_01_Register() {

	  	log.info("Register - step_01: Navigator to 'Register' page");
	    registerPage = homePage.clickToRegisterLink();
	    
	    log.info("Register - step_02: Click To radio button with value is '" + gender + "'");
	    registerPage.clickToRadioButtonByLabel(driver,gender);
	    
	    log.info("Register - step_03: Enter to firstname textbox with value is '" + firstName + "'");
		//registerPage.inputToFirstnameTextbox(firstName);
	    registerPage.inputToTextboxByID(driver,"FirstName",firstName);
		
		log.info("Register - step_04: Enter to lastname textbox with value is '" + lastName + "'");
		//registerPage.inputTolastnameTextbox(lastName);
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		
		log.info("Register - step_05: Select to DateOfBirthDay dropdown with value is '" + "22" + "'");
		registerPage.inputToDropdownByName(driver,"DateOfBirthDay", "22");
		
		log.info("Register - step_06: Select to DateOfBirthMonth dropdown with value is '" + "October" + "'");
		registerPage.inputToDropdownByName(driver,"DateOfBirthMonth", "October");
		
		log.info("Register - step_07: Select to DateOfBirthYear dropdown with value is '" + "10" + "'");
		registerPage.inputToDropdownByName(driver,"DateOfBirthYear", "1992");
		
		
		log.info("Register - step_08: Enter to email textbox with value is '" + emailAddress + "'");
		//registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);
		
		log.info("Register - step_09: Click To checkbox button with value is '" + "Newsletter:" + "'");
		registerPage.clickToCheckboxButtonByLabel(driver,"Newsletter");
		
		log.info("Register - step_10: Enter to correctpass textbox with value is '" + correctPassword + "'");
		//registerPage.inputToPasswordTextbox(correctPassword);
		registerPage.inputToTextboxByID(driver, "Password", correctPassword);
		
		log.info("Register - step_11: Enter to confirmpass textbox with value is '" + correctPassword + "'");
		//registerPage.inputToConfirmPasswordTextbox(correctPassword); 
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", correctPassword);
		
		log.info("Register - step_12: click to register button");
		//registerPage.clickToRegisterButton(); 
		registerPage.clickToButtonByText(driver,"Register");
		
		log.info("Register - step_13: verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	  
  }
  @Test
  public void User_02_Login() {

	  
	  log.info("Login - step_01: Navigator to 'login' page");
	  loginPage = homePage.openLoginPage();
	  
	  log.info("Login - step_02: Enter to email textbox with value is '" + emailAddress + "'");
	  //loginPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToTextboxByID(driver, "Email", emailAddress);
	  
	  log.info("Login - step_03: Enter to pass textbox with value is '" + correctPassword + "'");
	  //loginPage.inputToPasswordTextbox(correctPassword);
	  registerPage.inputToTextboxByID(driver, "Password", correctPassword);
	  
	  log.info("Login - step_04: click to login button");
	  //homePage = loginPage.clickToLoginButton();
	  registerPage.clickToButtonByText(driver,"Log in");
	  homePage=PageGeneratorManager.getUserHomePage(driver);
	  
	  log.info("Login - step_05: verify 'my account' link is displayed");
	  Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
  
	  
  }
  
  @Test
  public void User_03_Verify_My_Account() {
	  log.info("Login - step_01: navigative 'my account' page");
	  customerInfoPage = homePage.openMyAccountPage();
	  
	  log.info("Login - step_02: verify 'Customer infor' page is displayed");
	  Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplay());
	  
	  log.info("Login - step_03: verify 'Firstname' value is correctly");
	  Assert.assertEquals(customerInfoPage.getTextValueByID(driver, "FirstName"), firstName);
	  
	  log.info("Login - step_04: verify 'Lastname' value is correctly");
	  Assert.assertEquals(customerInfoPage.getTextValueByID(driver, "LastName"), lastName);
	  
  }
  
  

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  
}
