package com.jquery.datatable;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGenaratorManager;
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

public class Level_10_DataTable_DataGrid extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	List<String> allCountryValues;
  
	  @Parameters({"browser", "url"})
	  @BeforeClass
	  public void beforeClass(String browserName, String appUrl) {
		  driver = getBrowserDriver(browserName, appUrl);
		  homePage = PageGenaratorManager.getHomePage(driver);
		  
	    
  }
  //@Test
  public void Table_01_Paging() {
	  homePage.openPagingByPageNumber("10");
	  homePage.sleepInSecond(1);
	  Assert.assertTrue(homePage.isPageNumberActive("10"));
	  
	  homePage.openPagingByPageNumber("20");
	  homePage.sleepInSecond(1);
	  Assert.assertTrue(homePage.isPageNumberActive("20"));
	  
	  homePage.openPagingByPageNumber("7");
	  homePage.sleepInSecond(1);
	  Assert.assertTrue(homePage.isPageNumberActive("7"));
	  
	  homePage.openPagingByPageNumber("18");
	  homePage.sleepInSecond(1);
	  Assert.assertTrue(homePage.isPageNumberActive("18"));
  }
 //@Test
  public void Table_02_Enter_To_Header() {
	  homePage.refreshCurentPage(driver);
	  
	  homePage.enterToHeaderTextboxByLabel("Country", "Argentina");
	  homePage.enterToHeaderTextboxByLabel("Females", "338282");
	  homePage.enterToHeaderTextboxByLabel("Males", "349238");
	  homePage.enterToHeaderTextboxByLabel("Total", "687522");
	  homePage.sleepInSecond(3);
	  
	  homePage.enterToHeaderTextboxByLabel("Country", "Albania");
	  homePage.enterToHeaderTextboxByLabel("Females", "24128");
	  homePage.enterToHeaderTextboxByLabel("Males", "25266");
	  homePage.enterToHeaderTextboxByLabel("Total", "49397");
	  homePage.sleepInSecond(3);
	  
  }
  //@Test
  public void Table_03() {
	  //actual values
	  allCountryValues = homePage.getValueEachRowAtAllPage();
	  
  }
  
  @Test
  public void Table_04_Action_At_Any_Row() {
	  homePage.clickLoadButton();
	  homePage.sleepInSecond(5);
	  //Value để nhập liệu - tham số 1
	  //Row number: tại row nào?
	  //Ex: Nhập vào textbox tại dòng số 3, 5, 2
	  //Column name: Company/Country
	  homePage.enterToTextboxByColumnNameAtRowNumber("Company", "2", "NAL");
	  homePage.enterToTextboxByColumnNameAtRowNumber("Contact Person", "4", "Dungnt");
	  homePage.enterToTextboxByColumnNameAtRowNumber("Order Placed", "8", "Test");
	  
	  homePage.selectDropdownByColumnNameAtRowNumber("Country", "1", "Japan");
	  homePage.sleepInSecond(3);
	  homePage.selectDropdownByColumnNameAtRowNumber("Country", "1", "United States");
	  homePage.sleepInSecond(3);
	  homePage.selectDropdownByColumnNameAtRowNumber("Country", "5", "Germany");
	  homePage.sleepInSecond(3);
	  
	  homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "1");
	  homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "4");
	  homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?", "5");
	  
	  homePage.uncheckToCheckboxByColumnNameAtRowNumber("NPO?", "2");
	  homePage.uncheckToCheckboxByColumnNameAtRowNumber("NPO?", "3");
	  
	  homePage.clickToIconByRowNumber("1", "Remove Current Row");
	  homePage.sleepInSecond(2);
	  homePage.clickToIconByRowNumber("1", "Insert Row Above");
	  homePage.sleepInSecond(2);
	  homePage.clickToIconByRowNumber("3", "Move Up");
	  homePage.sleepInSecond(2);
	  homePage.clickToIconByRowNumber("5", "Remove Current Row");
	  homePage.sleepInSecond(2);
	  homePage.clickToIconByRowNumber("4", "Remove Current Row");
	  homePage.clickToIconByRowNumber("3", "Remove Current Row");
  }
  
  @Test
  public void Table_05() {
	  
	 
	  
  }
  

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  
}
