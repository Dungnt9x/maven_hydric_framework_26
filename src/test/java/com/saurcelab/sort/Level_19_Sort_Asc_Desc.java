package com.saurcelab.sort;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.saurceLab.LoginPageObject;
import pageObject.saurceLab.PageGeneratorManagerSL;
import pageObject.saurceLab.ProductPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_19_Sort_Asc_Desc extends BaseTest {
	private WebDriver driver;
	LoginPageObject loginPage;
	ProductPageObject productPage;
	
  
	  @Parameters({"browser", "appUrl"})
	  @BeforeClass
	  public void beforeClass(String browserName, String saurcelabUrl) {
		  driver = getBrowserDriver(browserName,saurcelabUrl);
		  loginPage = PageGeneratorManagerSL.getLoginPage(driver);
		  
		  loginPage.enterToUserNameTextbox("standard_user");
		  loginPage.enterToPasswordTextbox("secret_sauce");
		  productPage = loginPage.clickToLoginButton();
	    
	    
  }
  @Test
  public void Sort_01_Name() {
	  //Ascending
	  productPage.selectItemInProductSortDropdown("Name (A to Z)");
	  productPage.sleepInSecond(3);
	  
	  Assert.assertTrue(productPage.isProductNameSortByAscending());
	  
	//Descending
	  productPage.selectItemInProductSortDropdown("Name (Z to A)");
	  productPage.sleepInSecond(3);
	  
	  Assert.assertTrue(productPage.isProductNameSortByDescending());
	  
  }
  
  @Test
  public void Sort_02_Price() {
	//Ascending
	  productPage.selectItemInProductSortDropdown("Price (low to high)");
	  
	  Assert.assertTrue(productPage.isProductPriceSortByAscending());
	  
	//Descending
	  productPage.selectItemInProductSortDropdown("Price (high to low)");
	  
	  Assert.assertTrue(productPage.isProductPriceSortByDescending());
	  
  }

	  	
  
  

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  
}
