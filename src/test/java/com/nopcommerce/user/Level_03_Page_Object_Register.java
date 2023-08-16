package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object_Register {
	private WebDriver driver;
	  private String emailAddress, firstName, lastName, password;
	  private String projectPath = System.getProperty("user.dir");
	  private UserHomePageObject homePage;
	  private UserRegisterPageObject registerPage;
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    driver.get("https://demo.nopcommerce.com/");
	    
	    firstName = "automation";
	    lastName = "1"; 
	    password = "123456";
	    emailAddress = "afc" + genarateFakeNumber() + "@mail.vn";
	    
	    homePage = new UserHomePageObject(driver);
	    registerPage = new UserRegisterPageObject(driver);
  }
  @Test
  public void Register_01_Empty_Data() {
	  //waitForElementClickTable(driver, "//a[@class='ico-register']");
	  //clickToElement(driver, "//a[@class='ico-register']");
	  
	  //Hàm giả
	  System.out.println("Register_01 - Step 01: Click to Register link");
	  homePage.clickToRegisterLink();
	  //waitForElementClickTable(driver, "//button[@id='register-button']");
	  //clickToElement(driver, "//button[@id='register-button']");
	  System.out.println("Register_01 - Step 02: Click to Register button");
	  registerPage.clickToRegisterButton();
	  
	  
//	  Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
//	  Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
//	  Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
//	  Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
//	  Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	  
	  System.out.println("Register_01 - Step 03: Verify error mess Display");
	  Assert.assertEquals(registerPage.getErrorMessageAtFirstTextbox(), "First name is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtLastTextbox(), "Last name is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtConffirmPasswordTextbox(), "Password is required.");
  }
  @Test
  public void Register_02_Invalid_Email() {
//	  waitForElementClickTable(driver, "//a[@class='ico-register']");
//	  clickToElement(driver, "//a[@class='ico-register']");
	  System.out.println("Register_02 - Step 01: Click to Register link");
	  homePage.clickToRegisterLink();
	  
	  System.out.println("Register_02 - Step 02: Input To Required Field");
	  registerPage.inputToFirstnameTextbox(firstName);
	  registerPage.inputTolastnameTextbox(lastName);
	  registerPage.inputToEmailTextbox("fg#12");
	  registerPage.inputToPasswordTextbox(password);
	  registerPage.inputToConfirmPasswordTextbox(password);
	  
//	  senkeyToElement(driver,"//input[@id='FirstName']", "automation");
//	  senkeyToElement(driver,"//input[@id='LastName']", "1");
//	  senkeyToElement(driver,"//input[@id='Email']", "fg#12");
//	  senkeyToElement(driver,"//input[@id='Password']", "123456");
//	  senkeyToElement(driver,"//input[@id='ConfirmPassword']", "123456");
	  
	  System.out.println("Register_02 - Step 03: Click to Register button");
	  registerPage.clickToRegisterButton();
//	  waitForElementClickTable(driver, "//button[@id='register-button']");
//	  clickToElement(driver, "//button[@id='register-button']");
	  
	  System.out.println("Register_02 - Step 04: Verify Error Mess Display ");
	  Assert.assertEquals(registerPage.getErrorMessAtEmailTextbox(), "Wrong email");
	  //Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
  }
  @Test
  public void Register_03_Success() {
	  System.out.println("Register_03 - Step 01: Click to Register link");
	  homePage.clickToRegisterLink();
//	  waitForElementClickTable(driver, "//a[@class='ico-register']");
//	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  
	  System.out.println("Register_03 - Step 02: Input To Required Field");
	  registerPage.inputToFirstnameTextbox(firstName);
	  registerPage.inputTolastnameTextbox(lastName);
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox(password);
	  registerPage.inputToConfirmPasswordTextbox(password);
	  
//	  senkeyToElement(driver,"//input[@id='FirstName']", "automation");
//	  senkeyToElement(driver,"//input[@id='LastName']", "1");
//	  senkeyToElement(driver,"//input[@id='Email']", emailAddress);
//	  senkeyToElement(driver,"//input[@id='Password']", "123456");
//	  senkeyToElement(driver,"//input[@id='ConfirmPassword']", "123456");
	  
	  System.out.println("Register_03 - Step 03: Click to Register button");
	  registerPage.clickToRegisterButton();
//	  waitForElementClickTable(driver, "//button[@id='register-button']");
//	  clickToElement(driver, "//button[@id='register-button']");
	  
	  System.out.println("Register_03 - Step 04: Verify Success Mess Display ");
	  Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	  //Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
	  //waitForElementClickTable(driver, "//a[@class='ico-logout']");
	  //clickToElement(driver, "//a[@class='ico-logout']");
  }
  @Test
  public void Register_04_Existing_Email() {
	  System.out.println("Register_04 - Step 01: Click to Register link");
	  homePage.clickToRegisterLink();
//	  waitForElementClickTable(driver, "//a[@class='ico-register']");
//	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  System.out.println("Register_04 - Step 02: Input To Required Field");
	  registerPage.inputToFirstnameTextbox(firstName);
	  registerPage.inputTolastnameTextbox(lastName);
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox(password);
	  registerPage.inputToConfirmPasswordTextbox(password);
//	  senkeyToElement(driver,"//input[@id='FirstName']", "automation");
//	  senkeyToElement(driver,"//input[@id='LastName']", "1");
//	  senkeyToElement(driver,"//input[@id='Email']", emailAddress);
//	  senkeyToElement(driver,"//input[@id='Password']", "123456");
//	  senkeyToElement(driver,"//input[@id='ConfirmPassword']", "123456");
	  
	  System.out.println("Register_04 - Step 03: Click to Register button");
	  registerPage.clickToRegisterButton();
//	  waitForElementClickTable(driver, "//button[@id='register-button']");
//	  clickToElement(driver, "//button[@id='register-button']");
	  
	  System.out.println("Register_04 - Step 04: Verify Success Mess Display ");
	  Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
	  //Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");

  }
  @Test
  public void Register_05_Password_Less_Than_6_Char() {
	  System.out.println("Register_05 - Step 01: Click to Register link");
	  homePage.clickToRegisterLink();
//	  waitForElementClickTable(driver, "//a[@class='ico-register']");
//	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  System.out.println("Register_05 - Step 02: Input To Required Field");
	  registerPage.inputToFirstnameTextbox(firstName);
	  registerPage.inputTolastnameTextbox(lastName);
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox("123");
	  registerPage.inputToConfirmPasswordTextbox(password);
//	  senkeyToElement(driver,"//input[@id='FirstName']", "automation");
//	  senkeyToElement(driver,"//input[@id='LastName']", "1");
//	  senkeyToElement(driver,"//input[@id='Email']", emailAddress);
//	  senkeyToElement(driver,"//input[@id='Password']", "1234");
//	  senkeyToElement(driver,"//input[@id='ConfirmPassword']", "123456");
	  
	  System.out.println("Register_05 - Step 03: Click to Register button");
	  registerPage.clickToRegisterButton();
//	  waitForElementClickTable(driver, "//button[@id='register-button']");
//	  clickToElement(driver, "//button[@id='register-button']");
	  System.out.println("Register_05 - Step 04: Verify Error Mess Display ");
	  Assert.assertEquals(registerPage.getErrorMessAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 character");
	  //Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
  }
  @Test
  public void Register_06_Invalid_Conffirm_Password() {
	  System.out.println("Home Page - Step 01: Click to Register link");
	  homePage.clickToRegisterLink();
//	  waitForElementClickTable(driver, "//a[@class='ico-register']");
//	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  System.out.println("Register Page - Step 02: Input To Required Field");
	  registerPage.inputToFirstnameTextbox(firstName);
	  registerPage.inputTolastnameTextbox(lastName);
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox(password);
	  registerPage.inputToConfirmPasswordTextbox("123478");
//	  senkeyToElement(driver,"//input[@id='FirstName']", "automation");
//	  senkeyToElement(driver,"//input[@id='LastName']", "1");
//	  senkeyToElement(driver,"//input[@id='Email']", emailAddress);
//	  senkeyToElement(driver,"//input[@id='Password']", "123456");
//	  senkeyToElement(driver,"//input[@id='ConfirmPassword']", "123467");
	  
	  System.out.println("Register Page - Step 03: Click to Register button");
	  registerPage.clickToRegisterButton();
//	  waitForElementClickTable(driver, "//button[@id='register-button']");
//	  clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(registerPage.getErrorMessageAtConffirmPasswordTextbox(), "The password and confirmation password do not match.");
	  //Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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
