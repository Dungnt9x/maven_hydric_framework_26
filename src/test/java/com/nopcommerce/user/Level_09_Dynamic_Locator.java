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

public class Level_09_Dynamic_Locator extends BaseTest {
	private WebDriver driver;
	  private String emailAddress, invalidEmail, firstName, lastName, correctPassword;
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
	    
  }
  @Test
  public void Register_01_Register_Login() {

	    registerPage = homePage.clickToRegisterLink();
	    
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputTolastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(correctPassword);
		registerPage.inputToConfirmPasswordTextbox(correctPassword); 
		registerPage.clickToRegisterButton(); 
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	  
  
	  loginPage = homePage.openLoginPage();
	  
	  loginPage.inputToEmailTextbox(emailAddress);
	  loginPage.inputToPasswordTextbox(correctPassword);
	  
	  homePage = loginPage.clickToLoginButton();
	  Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
  
      
	  customerInfoPage = homePage.openMyAccountPage();
	  Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplay());
  }
  @Test
  public void Register_02_Switch_Page() {
	  //Knowledge của Page Object
	  //1 page A khi chuyển sang page B thì phải viết 1 hàm (action: open/click/...: link/buttin/image/...) để mở page B đó lên 
	  
	  //Customer info -> Address
	  addressPage = customerInfoPage.openAddressPage(driver);
	  
	  //Address -> My product review
	  myProductReviewPage = addressPage.openMyProductReviewPage(driver);
	  
	  //My product review -> Reward point
	  rewardPointPage = myProductReviewPage.openRewardPointPage(driver);
	  
	  //Reward point -> Address
	  addressPage = rewardPointPage.openAddressPage(driver);
	  
	  //Address -> Reward point
	  rewardPointPage = addressPage.openRewardPointPage(driver); 
	  
	  //Reward point -> My product review
	  myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
	  
	  //My product review -> Address
	  addressPage = myProductReviewPage.openAddressPage(driver);
	  
	  //Address -> CustomerInfo
	 customerInfoPage = addressPage.openCustomerInfoPage(driver);
	  
  }
  @Test
  public void Register_03_Dynamic_Page() {
	  //Knowledge của Page Object
	  //1 page A khi chuyển sang page B thì phải viết 1 hàm (action: open/click/...: link/buttin/image/...) để mở page B đó lên 
	  
//	  //Customer info -> Address
//	  addressPage = customerInfoPage.openAddressPage(driver);
//	  
//	  //Address -> My product review
//	  myProductReviewPage = addressPage.openMyProductReviewPage(driver);
	  
	  //My product review -> Reward point
	  rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPageAtMyAccountByName(driver, "Reward points");
	  
	  //Reward point -> Address
	  addressPage = (UserAddressPageObject) rewardPointPage.openPageAtMyAccountByName(driver, "Addresses");
	  
	  //Address -> Reward point
	  rewardPointPage = (UserRewardPointPageObject) addressPage.openPageAtMyAccountByName(driver, "Reward points"); 
	  
	  //Reward point -> My product review
	  myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPageAtMyAccountByName(driver, "My product reviews");
	  
	  //My product review -> Address
	  //addressPage = myProductReviewPage.openAddressPage(driver);
	  
	  //Address -> CustomerInfo
	 //customerInfoPage = addressPage.openCustomerInfoPage(driver);
	  
	  //My product review -> Customer Info
	  customerInfoPage = (UserCustomerInfoPageObject) myProductReviewPage.openPageAtMyAccountByName(driver, "Customer info");
	  
  }
  
  @Test
  public void Register_04_Dynamic_Page_02() {
	  //Customer Info -> My product review
	  customerInfoPage.openPageAtMyAccountByPageName(driver, "My product reviews");
	  myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
	  
	  
	  //My product review -> Reward point
	  myProductReviewPage.openPageAtMyAccountByPageName(driver, "Reward points");
	  rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
	  
	  
	  //Reward point -> Address
	  rewardPointPage.openPageAtMyAccountByPageName(driver, "Addresses");
	  addressPage = PageGeneratorManager.getUserAddressPage(driver);
	  
	  //Address -> Reward point
	  addressPage.openPageAtMyAccountByPageName(driver, "Reward points"); 
	  rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
	  
	  //Reward point -> customerinfo
	  rewardPointPage.openPageAtMyAccountByPageName(driver, "Customer info");
	  customerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);
	  
	  
	  
  }
  

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  
}
