package com.jquery.datatable;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGenaratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_11_Upload_files extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	
	String oneFileName = "01A535E0-3EA0-4918-9376-68382FBEA1F2.jpeg";
	String twoFileName = "254162169_2107203636103098_6988641684673644280_n.jpeg";
	String threeFileName = "272747844_1895905023927614_2742019531332076772_n.jpeg";
	String[] multipleFileNames = {"oneFileName, twoFileName, threeFileName"};
  
	  @Parameters({"browser", "url"})
	  @BeforeClass
	  public void beforeClass(String browserName, String appUrl) {
		  driver = getBrowserDriver(browserName, appUrl);
		  homePage = PageGenaratorManager.getHomePage(driver);
	    
  }
  
  
  @Test
  public void Table_01_One_File_Per_Time() {
	  //Step 01: Load single file
	  homePage.uploadMutipleFiles(driver, oneFileName);
	  
	  //Step 02: Verify single file loaded success
	  Assert.assertTrue(homePage.isFileLoadByName("oneFileName"));
	  
	  //Step 03: Click to start button
	  homePage.clickForElement();
	  
	  //Step 04: Verify single file link upload success
	  Assert.assertTrue(homePage.isFileLinkUpLoadedByName("oneFileName"));
	  
	//Step 05: Verify single file image upload success
	  Assert.assertTrue(homePage.isFileImageUpLoadedByName("oneFileName"));
	  
  }
  
  @Test
  public void Table_02_Multiple_File_Per_Time() {
	  homePage.refreshCurentPage(driver);
	  //Step 01: Load Multiple file
	  homePage.uploadMutipleFiles(driver, multipleFileNames);
	  
	  //Step 02: Verify Multiple file loaded success
	  //oneFileName, twoFileName, threeFileName
	  Assert.assertTrue(homePage.isFileLoadByName("oneFileName"));
	  Assert.assertTrue(homePage.isFileLoadByName("twoFileName"));
	  Assert.assertTrue(homePage.isFileLoadByName("threeFileName"));
	  
	  //Step 03: Click to start button
	  homePage.clickForElement();
	  
	  //Step 04: Verify multiple file link upload success
	  Assert.assertTrue(homePage.isFileLinkUpLoadedByName("oneFileName"));
	  Assert.assertTrue(homePage.isFileLinkUpLoadedByName("twoFileName"));
	  Assert.assertTrue(homePage.isFileLinkUpLoadedByName("threeFileName"));
	  
	//Step 05: Verify Multiple file image upload success
	  Assert.assertTrue(homePage.isFileImageUpLoadedByName("oneFileName"));
	  Assert.assertTrue(homePage.isFileImageUpLoadedByName("twoFileName"));
	  Assert.assertTrue(homePage.isFileImageUpLoadedByName("twoFileName"));
	  
  }
  

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  
}
