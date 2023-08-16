package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object_Login {
	private WebDriver driver;
	  private String existingEmail, invalidEmail, notFoundEmail, firstName, lastName, correctPassword, incorrectPassword;
	  private String projectPath = System.getProperty("user.dir");
	  private UserHomePageObject homePage;
	  private UserRegisterPageObject registerPage;
	  private UserLoginPageObject loginPage;
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    driver.get("https://demo.nopcommerce.com/");
	    
	    firstName = "automation";
	    lastName = "1"; 
	    correctPassword = "123456";
	    incorrectPassword = "654321";
	    invalidEmail = "afc";
	    existingEmail = "afc" + genarateFakeNumber() + "@mail.vn";
	    notFoundEmail = "afc" + genarateFakeNumber() + "@gmail.com";
	    
	    homePage = new UserHomePageObject(driver);
	    
	    System.out.println("Pre_Condition - Step 01: Click to Register link");
		  homePage.clickToRegisterLink();
		  registerPage = new UserRegisterPageObject(driver);
		  
		  
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
	  homePage.openLoginPage();
	  
	  loginPage = new UserLoginPageObject(driver);
	  loginPage.clickToLoginButton();
	  Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
  }
  @Test
  public void Register_02_Invalid_Email() {
	  homePage.openLoginPage();
	  loginPage = new UserLoginPageObject(driver);
	  loginPage.inputToEmailTextbox(invalidEmail);
	  loginPage.clickToLoginButton();
	  Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
  }
  @Test
  public void Register_03_Email_Not_Register() {
	  homePage.openLoginPage();
	  loginPage = new UserLoginPageObject(driver);
	  loginPage.inputToEmailTextbox(notFoundEmail);
	  loginPage.clickToLoginButton();
	  Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
  }
  @Test
  public void Register_04_Existing_Email_Empty_Password() {
	  homePage.openLoginPage();
	  loginPage = new UserLoginPageObject(driver);
	  loginPage.inputToEmailTextbox(existingEmail);
	  loginPage.inputToPasswordTextbox("");
	  loginPage.clickToLoginButton();
	  Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
  }
  @Test
  public void Register_05_Existing_Email_Invalid_Password() {
	  homePage.openLoginPage();
	  loginPage = new UserLoginPageObject(driver);
	  loginPage.inputToEmailTextbox(existingEmail);
	  loginPage.inputToPasswordTextbox(incorrectPassword);
	  loginPage.clickToLoginButton();
	  Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
  }
  @Test
  public void Register_06_Valid_Email_Password() {
	  homePage.openLoginPage();
	  loginPage = new UserLoginPageObject(driver);
	  loginPage.inputToEmailTextbox(existingEmail);
	  loginPage.inputToPasswordTextbox(correctPassword);
	  loginPage.clickToLoginButton();
	  
	  homePage = new UserHomePageObject(driver);
	  Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
  }

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  public int genarateFakeNumber() {
	  Random rand = new Random();
	  return rand.nextInt(9999);
  }
  
}
