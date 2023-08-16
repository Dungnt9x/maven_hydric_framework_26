package com.wordpress.admin;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.wordpress.admin.AdminDashboardPO;
import pageObjects.wordpress.admin.AdminLoginPO;
import pageObjects.wordpress.admin.AdminPostAddNewPO;
import pageObjects.wordpress.admin.AdminPostSearchPO;
import pageObjects.wordpress.admin.PageGeneratorManagerWP;
import pageObjects.wordpress.admin.UserHomepagePO;
import pageObjects.wordpress.admin.UserPostDetailPO;
import pageObjects.wordpress.admin.UserSearchPostPO;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Post_01_Creat_Read_Update_Delete_search extends BaseTest {
	private WebDriver driver;
	AdminLoginPO adminLoginPage;
	AdminDashboardPO adminDashboardPage;
	AdminPostSearchPO adminPostSearchPage;
	AdminPostAddNewPO adminPostAddNewPage;
	UserHomepagePO userHomePage;
	UserPostDetailPO userPostDetailPage;
	UserSearchPostPO userSearchPostPage;
	
	String adminUsername = "automationfc";
	String adminPassword = "automationfc";
	String searchPostUrl;
	int randomNumber = genarateFakeNumber();
	String postTitle="Live coding Title" + randomNumber;
	String postBodyValues="Live coding Title" + randomNumber;
	String editPostTitle = "Edit Title" + randomNumber;
	String editPostBody = "Edit Body" + randomNumber;
	String authorName = "automationfc";
	String currentDay = getToday();
	String adminUrl, endUserUrl;
	
  
	  @Parameters({"browser", "urlAdmin", "urlUser"})
	  @BeforeClass
	  public void beforeClass(String browserName, String adminUrl, String endUserUrl) {
		  this.adminUrl = adminUrl;
		  this.endUserUrl = endUserUrl;
		  driver = getBrowserDriver(browserName, this.adminUrl);
		  adminLoginPage = PageGeneratorManagerWP.getAdminLoginPage(driver);
		  
		  log.info("Pre-Condition - Step 01: Enter To UserName Textbox: " + adminUsername);
		  adminLoginPage.enterToUserNameTextbox(adminUsername);
		  
		  log.info("Pre-Condition - Step 02: Enter To Password Textbox: " + adminPassword);
		  adminLoginPage.enterToPasswordTextbox(adminPassword);
		  
		  log.info("Pre-Condition - Step 03: Click To Login Button " );
		  adminDashboardPage = adminLoginPage.clickToLoginButton();
		  
		  //adminDashboardPage = PageGeneratorManagerWP.getAdminDashboardPage(driver);
	    
	    
  }
  @Test
  public void Post_01_Creat_New_Post() {
	  log.info("Creat-post - Step 01: Click To Post menu Link " );
	  adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
	  //adminPostSearchPage = PageGeneratorManagerWP.getAdminSearchPage(driver);
	  
	  log.info("Creat-post - Step 02: get Search Post page Url " );
	  searchPostUrl= adminPostSearchPage.getPageUrl(driver);
	  
	  log.info("Creat-post - Step 03: Click To Add new button " );
	  adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();
	  //adminPostAddNewPage = PageGeneratorManagerWP.getAdminAddNewPage(driver);
	  
	  log.info("Creat-post - Step 04: Enter to Post Title " );
	  adminPostAddNewPage.enterToAddNewPostTitle(postTitle);
	  
	  log.info("Creat-post - Step 05: Enter to body " );
	  adminPostAddNewPage.enterToAddNewPostBody(postBodyValues);
	  
	  log.info("Creat-post - Step 06: Click To Publish button  " );
	  adminPostAddNewPage.clickToPublishOrEditButton();
	  
	  log.info("Creat-post - Step 07: Click To Pre Publish button  " );
	  adminPostAddNewPage.clickToPrePublishOrEditButton();
	  
	  log.info("Creat-post - Step 08: Verify Post Publish message is dislayed " );
	  verifyTrue(adminPostAddNewPage.isPostPublishOrEditMessageDisplayed("Post published.")); 
	  
  }
  @Test
  public void Post_02_Search_And_View_Post() {
	  log.info("Search-post - Step 01: Open Search post page " );
	  adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
	  //adminPostSearchPage = PageGeneratorManagerWP.getAdminSearchPage(driver);
	  
	  log.info("Search-post - Step 02: Enter to Search Post button " );
	  adminPostSearchPage.enterToSearchTextbox(postTitle);
	  
	  log.info("Search-post - Step 03: Click to Search Post button " );
	  adminPostSearchPage.clickSearchPostButton();
	  
	  log.info("Search-post - Step 04: Verify Search Table contains " + postTitle );
	  verifyTrue(adminPostSearchPage.isPostsearchTableDisplayed("title", postTitle));
	  
	  log.info("Search-post - Step 56: Verify Search Table contains " + authorName );
	  verifyTrue(adminPostSearchPage.isPostsearchTableDisplayed("author", authorName));
	  
	  log.info("Search-post - Step 06: Open End user site");
	  userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
	  
	  log.info("Search-post - Step 07: Verify Post infor displayed at Home page ");
	  verifyTrue(userHomePage.isPostInfoDisplayedWithPostTitle(postTitle));
	  verifyTrue(userHomePage.isPostInfoDisplayedWithPostBody(postTitle,postBodyValues));
	  verifyTrue(userHomePage.isPostInfoDisplayedWithAutherName(postTitle,authorName));
	  //verifyTrue(userHomePage.isPostInfoDisplayedWithTime(postTitle,currentDay));
	  
	  
	  log.info("Search-post - Step 08: Click to post title");
	  userPostDetailPage = userHomePage.clickToPostTitle(postTitle);
	  
	  log.info("Search-post - Step 09: Verify Post infor displayed at Post detail page");
	  verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostTitle(postTitle));
	  verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostBody(postTitle,postBodyValues));
	  verifyTrue(userPostDetailPage.isPostInfoDisplayedWithAutherName(postTitle,authorName));
	  //verifyTrue(userPostDetailPage.isPostInfoDisplayedWithTime(postTitle,currentDay));
	  
  }
  
  @Test
  public void Post_03_Edit_Post() {
	  log.info("Edit-post - Step 01: Open Admin site");
	  adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);
	  
	  log.info("Edit-post - Step 02: Click To Post menu Link " );
	  adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
	  
	  log.info("Edit-post - Step 03: Enter to Search Post button " );
	  adminPostSearchPage.enterToSearchTextbox(postTitle);
	  
	  log.info("Edit-post - Step 04: Click to Search Post button " );
	  adminPostSearchPage.clickSearchPostButton();
	  
	  log.info("Edit-post - Step 05: Click to Post title link and navigate to edit post page " );
	  adminPostAddNewPage = adminPostSearchPage.clickToPostTitleLink(postTitle);
	  
	  log.info("Edit-post - Step 06: Enter to Post Title " );
	  adminPostAddNewPage.enterToAddNewPostTitle(editPostTitle);
	  
	  log.info("Edit-post - Step 07: Enter to body " );
	  adminPostAddNewPage.enterToEditPostBody(editPostBody);
	  
	  log.info("Edit-post - Step 08: Click To Update button  " );
	  adminPostAddNewPage.clickToPublishOrEditButton();
	  
	  log.info("Edit-post - Step 08: Verify Post Update message is dislayed " );
	  verifyTrue(adminPostAddNewPage.isPostPublishOrEditMessageDisplayed("Post updated.")); 
	  
	  log.info("Edit-post - Step 09: Open Search post page " );
	  adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
	  //adminPostSearchPage = PageGeneratorManagerWP.getAdminSearchPage(driver);
	  
	  log.info("Edit-post - Step 10: Enter to Search Post button " );
	  adminPostSearchPage.enterToSearchTextbox(editPostTitle);
	  
	  log.info("Edit-post - Step 11: Click to Search Post button " );
	  adminPostSearchPage.clickSearchPostButton();
	  
	  log.info("Edit-post - Step 12: Verify Search Table contains " + editPostTitle );
	  verifyTrue(adminPostSearchPage.isPostsearchTableDisplayed("title", editPostTitle));
	  
	  log.info("Edit-post - Step 13: Verify Search Table contains " + authorName );
	  verifyTrue(adminPostSearchPage.isPostsearchTableDisplayed("author", authorName));
	  
	  log.info("Edit-post - Step 14: Open End user site");
	  userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
	  
	  log.info("Edit-post - Step 15: Verify Post infor displayed at Home page ");
	  verifyTrue(userHomePage.isPostInfoDisplayedWithPostTitle(editPostTitle));
	  verifyTrue(userHomePage.isPostInfoDisplayedWithPostBody(editPostTitle,editPostBody));
	  verifyTrue(userHomePage.isPostInfoDisplayedWithAutherName(editPostTitle,authorName));
	  //verifyTrue(userHomePage.isPostInfoDisplayedWithTime(postTitle,currentDay));
	  
	  
	  log.info("Edit-post - Step 16: Click to post title");
	  userPostDetailPage = userHomePage.clickToPostTitle(editPostTitle);
	  
	  log.info("Edit-post - Step 17: Verify Post infor displayed at Post detail page");
	  verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostTitle(editPostTitle));
	  verifyTrue(userPostDetailPage.isPostInfoDisplayedWithPostBody(editPostTitle,editPostBody));
	  verifyTrue(userPostDetailPage.isPostInfoDisplayedWithAutherName(editPostTitle,authorName));
	  //verifyTrue(userPostDetailPage.isPostInfoDisplayedWithTime(postTitle,currentDay));
	  
  }
  
  @Test
  public void Post_04_Delete_Post() {
	  log.info("Delete-post - Step 01: Open Admin site");
	  adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);
	  
	  log.info("Delete-post - Step 02: Click To Post menu Link " );
	  adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
	  
	  log.info("Delete-post - Step 03: Enter to Search Post button " );
	  adminPostSearchPage.enterToSearchTextbox(editPostTitle);
	  
	  log.info("Delete-post - Step 04: Click to Search Post button " );
	  adminPostSearchPage.clickSearchPostButton();
	  
	  log.info("Delete-post - Step 05: Select Post detail checkbox " );
	  adminPostSearchPage.selectPostCheckboxByTitle(editPostTitle);
	  
	  log.info("Delete-post - Step 06: Select Move to trash item in dropdown " );
	  adminPostSearchPage.selectTextItemInActionDrodown("Move to Trash");
	  
	  log.info("Delete-post - Step 07: Click to Apply button " );
	  adminPostSearchPage.clickApplyToButton();
	  
	  log.info("Delete-post - Step 08: Verify 1 post moves to the trash. Message is Displayed " );
	  verifyTrue(adminPostSearchPage.isMoveToTrashMessageDisplayed("1 post moves to the trash."));
	  
	  log.info("Delete-post - Step 09: Enter to Search Post button " );
	  adminPostSearchPage.enterToSearchTextbox(editPostTitle);
	  
	  log.info("Delete-post - Step 10: Click to Search Post button " );
	  adminPostSearchPage.clickSearchPostButton();
	  
	  log.info("Delete-post - Step 11: Verify No post found message is displayed " );
	  verifyTrue(adminPostSearchPage.isNoPostFoundMessageDisplayed("No posts found."));
	  
	  log.info("Delete-post - Step 12: Open End user site");
	  userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
	  
	  log.info("Delete-post - Step 13: Verify Post title Undisplayed at Home page ");
	  verifyTrue(userHomePage.isPostInrorUndisplayedWithPostTitle(editPostTitle));
	  
	  log.info("Delete-post - Step 14: Enter to Search Textbox " );
	  userHomePage.enterToSearchTextbox(editPostTitle);
	  
	  log.info("Delete-post - Step 15: Click to Search button " );
	  userSearchPostPage = userHomePage.clickSearchButton();
	  
	  log.info("Delete-post - Step 13: Verify Nothing found message is Displayed ");
	  verifyTrue(userSearchPostPage.isNothingFoundMessageDisplayed("Nothing Found"));
	  
  }
  
  

  @AfterClass
	public void afterClass() {
		driver.quit();
	}
  
}
