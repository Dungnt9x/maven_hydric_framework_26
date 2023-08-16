package com.facebook.register;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObject.Facebook.LoginPageObject;
import pageObject.Facebook.PageGenaratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_13_Element_Undisplayed extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	
  
	  @Parameters({"browser", "url"})
	  @BeforeClass
	  public void beforeClass(String browserName, String appUrl) {
		  driver = getBrowserDriver(browserName, appUrl);
		  loginPage = PageGenaratorManager.getLoginPage(driver);
	    
  }
  
  
  @Test
  public void Table_01_Verify_Element_Display() {
	  loginPage.clickToCreateNewAccountButotn();
	  //verifyTrue(loginPage.isEmailAddressTextboxDisplayed());
	  
	//Verify true - cho hàm trả về là Displayed
	  loginPage.enterToEmailTextbox("dungnt@gmail.com");
	  verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
	  
  }
  
  @Test
  public void Table_02_Verify_Element_Undisplay_In_Dom() {
	  
	//Verify false - cho hàm trả về là Displayed
	  loginPage.enterToEmailTextbox("");
	  verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
	  
  }
  
  @Test
  public void Table_03_Verify_Element_Undisplay_Not_In_Dom() {
	  loginPage.clickCloseIconAtRegisterForm();
	  //Kho close cái form register đi thì confirm email không còn trong DOM nữa
	  //Nên hàm findElemend fail vì không tìm thấy Element
	  
	  verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplaed());
	  
  }
  

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  
}
