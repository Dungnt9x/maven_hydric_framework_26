package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.admin.AdminDashBoardPageUI;
import pageUIs.wordpress.admin.UserHomePageUI;

public class UserHomepagePO extends BasePage {
	WebDriver driver;
	
	public UserHomepagePO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostInfoDisplayedWithPostTitle(String postTitle) {
		waitForElementVisible(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		return isElementDisplayed(driver,UserHomePageUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostInfoDisplayedWithPostBody(String postTitle, String postBodyValues) {
		waitForElementVisible(driver, UserHomePageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBodyValues);
		return isElementDisplayed(driver, UserHomePageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBodyValues);
	}

	public boolean isPostInfoDisplayedWithAutherName(String postTitle, String authorName) {
		waitForElementVisible(driver, UserHomePageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, authorName);
		return isElementDisplayed(driver, UserHomePageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, authorName);
	}

	public boolean isPostInfoDisplayedWithTime(String postTitle, String currentDay) {
		waitForElementVisible(driver, UserHomePageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
		return isElementDisplayed(driver, UserHomePageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
	}

	public UserPostDetailPO clickToPostTitle(String postTitle) {
		waitForElementClickTable(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		clickToElement(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		return PageGeneratorManagerWP.getUserPostDetailPage(driver);
	}

	public boolean isPostInrorUndisplayedWithPostTitle(String editPostTitle) {
		return isElementUndisplayed(driver, UserHomePageUI.POST_TITLE_TEXT, editPostTitle);
	}

	public void enterToSearchTextbox(String editPostTitle) {
		waitForElementVisible(driver, UserHomePageUI.SEARCH_TEXTBOX, editPostTitle);;
		senkeyToElement(driver, UserHomePageUI.SEARCH_TEXTBOX, editPostTitle);
		
	}

	public UserSearchPostPO clickSearchButton() {
		waitForElementClickTable(driver, UserHomePageUI.SEARCH_BUTTON);
		clickToElement(driver, UserHomePageUI.SEARCH_BUTTON);
		return PageGeneratorManagerWP.getUserSearchPostPage(driver);
	}

	

	
}
