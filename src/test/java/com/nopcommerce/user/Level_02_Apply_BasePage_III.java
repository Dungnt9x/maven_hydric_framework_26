package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_Apply_BasePage_III extends BasePage {
  WebDriver driver;
  String emailAddress;
  String projectPath = System.getProperty("user.dir");
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
	    driver = new FirefoxDriver();
	    emailAddress = "afc" + genarateFakeNumber() + "@mail.vn";
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    driver.get("https://demo.nopcommerce.com/");
  }
  @Test
  public void TC_01_Register_Empty_Data() {
	  waitForElementClickTable(driver, "//a[@class='ico-register']");
	  clickToElement(driver, "//a[@class='ico-register']");
	  waitForElementClickTable(driver, "//button[@id='register-button']");
	  clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
	  Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
	  Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
	  Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
	  Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
  }
  @Test
  public void TC_02_Register_Invalid_Email() {
	  waitForElementClickTable(driver, "//a[@class='ico-register']");
	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  senkeyToElement(driver,"//input[@id='FirstName']", "automation");
	  senkeyToElement(driver,"//input[@id='LastName']", "1");
	  senkeyToElement(driver,"//input[@id='Email']", "fg#12");
	  senkeyToElement(driver,"//input[@id='Password']", "123456");
	  senkeyToElement(driver,"//input[@id='ConfirmPassword']", "123456");
	  
	  waitForElementClickTable(driver, "//button[@id='register-button']");
	  clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
  }
  @Test
  public void TC_03_Register_Success() {
	  waitForElementClickTable(driver, "//a[@class='ico-register']");
	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  senkeyToElement(driver,"//input[@id='FirstName']", "automation");
	  senkeyToElement(driver,"//input[@id='LastName']", "1");
	  senkeyToElement(driver,"//input[@id='Email']", emailAddress);
	  senkeyToElement(driver,"//input[@id='Password']", "123456");
	  senkeyToElement(driver,"//input[@id='ConfirmPassword']", "123456");
	  
	  waitForElementClickTable(driver, "//button[@id='register-button']");
	  clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
	  //waitForElementClickTable(driver, "//a[@class='ico-logout']");
	  //clickToElement(driver, "//a[@class='ico-logout']");
  }
  @Test
  public void TC_04_Register_Existing_Email() {
	  waitForElementClickTable(driver, "//a[@class='ico-register']");
	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  senkeyToElement(driver,"//input[@id='FirstName']", "automation");
	  senkeyToElement(driver,"//input[@id='LastName']", "1");
	  senkeyToElement(driver,"//input[@id='Email']", emailAddress);
	  senkeyToElement(driver,"//input[@id='Password']", "123456");
	  senkeyToElement(driver,"//input[@id='ConfirmPassword']", "123456");
	  
	  waitForElementClickTable(driver, "//button[@id='register-button']");
	  clickToElement(driver, "//button[@id='register-button']");
	  Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");

  }
  @Test
  public void TC_05_Register_Password_Less_Than_6_Char() {
	  waitForElementClickTable(driver, "//a[@class='ico-register']");
	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  senkeyToElement(driver,"//input[@id='FirstName']", "automation");
	  senkeyToElement(driver,"//input[@id='LastName']", "1");
	  senkeyToElement(driver,"//input[@id='Email']", emailAddress);
	  senkeyToElement(driver,"//input[@id='Password']", "1234");
	  senkeyToElement(driver,"//input[@id='ConfirmPassword']", "1234");
	  
	  waitForElementClickTable(driver, "//button[@id='register-button']");
	  clickToElement(driver, "//button[@id='register-button']");
	  Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
  }
  @Test
  public void TC_06_Register_Invalid_Conffirm_Password() {
	  waitForElementClickTable(driver, "//a[@class='ico-register']");
	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  senkeyToElement(driver,"//input[@id='FirstName']", "automation");
	  senkeyToElement(driver,"//input[@id='LastName']", "1");
	  senkeyToElement(driver,"//input[@id='Email']", emailAddress);
	  senkeyToElement(driver,"//input[@id='Password']", "123456");
	  senkeyToElement(driver,"//input[@id='ConfirmPassword']", "123467");
	  
	  waitForElementClickTable(driver, "//button[@id='register-button']");
	  clickToElement(driver, "//button[@id='register-button']");
	  Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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
