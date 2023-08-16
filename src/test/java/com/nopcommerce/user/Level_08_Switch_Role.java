package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalContants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
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

public class Level_08_Switch_Role extends BaseTest {
	private WebDriver driver;
	  private String userEmailAddress, userCorrectPassword, adminEmailAddress, adminCorrectPassword;
	  private String projectPath = System.getProperty("user.dir");
	  private UserHomePageObject userHomePage;
	  private UserRegisterPageObject userRegisterPage;
	  private UserLoginPageObject userLoginPage;
	  private UserCustomerInfoPageObject userCustomerInfoPage;
	  private AdminLoginPageObject adminLoginPage;
	  private AdminDashboardPageObject adminDashboardPage;
  
	  @Parameters("browser")
	  @BeforeClass
	  public void beforeClass(String browserName) {
		  driver = getBrowserDriver(browserName);
		  userHomePage = PageGeneratorManager.getUserHomePage(driver);
	    
	     
	    userCorrectPassword = "123456";
	    userEmailAddress = "atmfc@aff.com";
	    adminEmailAddress = "admin@yourstore.com";
	    adminCorrectPassword = "admin";
	    
  }
  @Test
  public void Register_01_User_To_Admin() {
	  //HomePage -> LoginPage (User)
	  userLoginPage = userHomePage.openLoginPage();
	  
	  //Login usser
	  userHomePage = userLoginPage.loginAsUser(userEmailAddress,userCorrectPassword);
	  Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
	  
	  //HomePage chuyển sang customerInfo
	  userCustomerInfoPage = userHomePage.openMyAccountPage();
	  
	  //CustomerInfo click to lpgout -> homepage
	  userHomePage = userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);
	  
	  //User HomePage -> open Admin Page -> login Page (admin)
	  userHomePage.openPageUrl(driver, GlobalContants.ADMIN_PAGE_URL);
	  adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
	  
	  //Login As Admin role
	  adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminCorrectPassword);
	  Assert.assertTrue(adminDashboardPage.isDashboardHeaderIsDisplay());
	  
	  //Dashboard Page -> click logout -> Login Page (Admin)
	  adminLoginPage = adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);
  }
  @Test
  public void Register_02_Admin_To_User() {
	   //Login Page (admin) -> open user URL -> Login Page (user)
	  adminLoginPage.openPageUrl(driver, GlobalContants.PORTAL_PAGE_URL);
	  userHomePage = PageGeneratorManager.getUserHomePage(driver);
	  
	  //HomePage -> LoginPage (User)
	  userLoginPage = userHomePage.openLoginPage();
	  
	  //Login usser
	  userHomePage = userLoginPage.loginAsUser(userEmailAddress,userCorrectPassword);
	  Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
  }
  
  

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  
}
