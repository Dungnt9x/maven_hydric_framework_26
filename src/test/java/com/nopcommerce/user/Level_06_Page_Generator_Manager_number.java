package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_06_Page_Generator_Manager_number extends BaseTest {
	private WebDriver driver;
	  private String existingEmail, invalidEmail, notFoundEmail, firstName, lastName, correctPassword, incorrectPassword;
	  private String projectPath = System.getProperty("user.dir");
	  private UserHomePageObject homePage;
	  private UserRegisterPageObject registerPage;
	  private UserLoginPageObject loginPage;
	  private UserCustomerInfoPageObject myAccountPage;
	  @Parameters("browser")
	  @BeforeClass
	  public void beforeClass(String browserName) {
		  driver = getBrowserDriver(browserName);
		  homePage = PageGeneratorManager.getUserHomePage(driver);
	    
	    firstName = "automation";
	    lastName = "1"; 
	    correctPassword = "123456";
	    incorrectPassword = "654321";
	    invalidEmail = "afc";
	    existingEmail = "afc" + genarateFakeNumber() + "@mail.vn";
	    notFoundEmail = "afc" + genarateFakeNumber() + "@gmail.com";
	    
	    
	    System.out.println("Pre_Condition - Step 01: Click to Register link");
	    registerPage = homePage.clickToRegisterLink();
		  
		  
		  System.out.println("Pre_Condition - Step 02: Input To Required Field");
		  registerPage.inputToFirstnameTextbox(firstName);
		  registerPage.inputTolastnameTextbox(lastName);
		  registerPage.inputToEmailTextbox(existingEmail);
		  registerPage.inputToPasswordTextbox(correctPassword);
		  registerPage.inputToConfirmPasswordTextbox(correctPassword);
		  
		  System.out.println("Pre_Condition - Step 03: Click to Register button");
		  registerPage.clickToRegisterButton();
		  
		  System.out.println("Pre_Condition - Step 04: Verify Success Mess Display ");
		  Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
  }
  @Test
  public void Register_01_Empty_Data() {
	  loginPage = homePage.openLoginPage();
	  
	  loginPage.clickToLoginButton();
	  Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
  }
  @Test
  public void Register_02_Invalid_Email() {
	  loginPage = homePage.openLoginPage();
	  
	  loginPage.inputToEmailTextbox(invalidEmail);
	  loginPage.clickToLoginButton();
	  Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
  }
  @Test
  public void Register_03_Email_Not_Register() {
	  loginPage = homePage.openLoginPage();
	  
	  loginPage.inputToEmailTextbox(notFoundEmail);
	  loginPage.clickToLoginButton();
	  Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
  }
  @Test
  public void Register_04_Existing_Email_Empty_Password() {
	  loginPage = homePage.openLoginPage();
	  
	  loginPage.inputToEmailTextbox(existingEmail);
	  loginPage.inputToPasswordTextbox("");
	  loginPage.clickToLoginButton();
	  
	  Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
  }
  @Test
  public void Register_05_Existing_Email_Invalid_Password() {
	  loginPage = homePage.openLoginPage();
	  loginPage.inputToEmailTextbox(existingEmail);
	  loginPage.inputToPasswordTextbox(incorrectPassword);
	  loginPage.clickToLoginButton();
	  Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
  }
  @Test
  public void Register_06_Valid_Email_Password() {
	  loginPage = homePage.openLoginPage();
	  
	  loginPage.inputToEmailTextbox(existingEmail);
	  loginPage.inputToPasswordTextbox(correctPassword);
	  
	  homePage = loginPage.clickToLoginButton();
	  Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	  
	  myAccountPage = homePage.openMyAccountPage();
  }

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  
}
