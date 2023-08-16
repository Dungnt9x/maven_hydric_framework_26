package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_End_User;

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

public class Level_16_Share_data extends BaseTest {
	private WebDriver driver;
	  private String emailAddress, correctPassword;
	  private UserHomePageObject homePage;
	  private UserLoginPageObject loginPage;
  
	  @Parameters("browser")
	  @BeforeClass
	  public void beforeClass(String browserName) {
		  driver = getBrowserDriver(browserName);
		  homePage = PageGeneratorManager.getUserHomePage(driver);
		  
		  emailAddress =Common_01_Register_End_User.emailAddress;
		  correctPassword =Common_01_Register_End_User.correctPassword;
	    
		  log.info("Login - step_01: Navigator to 'login' page");
		  loginPage = homePage.openLoginPage();
		  
		  log.info("Login - step_02: Enter to email textbox with value is '" + emailAddress + "'");
		  loginPage.inputToEmailTextbox(emailAddress);
		  
		  log.info("Login - step_03: Enter to pass textbox with value is '" + correctPassword + "'");
		  loginPage.inputToPasswordTextbox(correctPassword);
		  
		  log.info("Login - step_04: click to login button");
		  homePage = loginPage.clickToLoginButton();
	    
  }
  
  @Test
  public void Search_01_Empty_Data() {

	  
	  
	  
  }
  
  

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  
}
