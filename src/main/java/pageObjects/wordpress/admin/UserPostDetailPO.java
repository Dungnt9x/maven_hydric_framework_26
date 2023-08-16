package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.admin.AdminDashBoardPageUI;
import pageUIs.wordpress.admin.UserHomePageUI;
import pageUIs.wordpress.admin.UserPostDetailPageUI;

public class UserPostDetailPO extends BasePage {
	WebDriver driver;
	
	public UserPostDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostInfoDisplayedWithPostTitle(String postTitle) {
		waitForElementVisible(driver, UserPostDetailPageUI.POST_TITLE_TEXT, postTitle);
		return isElementDisplayed(driver, UserPostDetailPageUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostInfoDisplayedWithPostBody(String postTitle, String postBodyValues) {
		waitForElementVisible(driver, UserPostDetailPageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBodyValues);
		return isElementDisplayed(driver, UserPostDetailPageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBodyValues);
	}

	public boolean isPostInfoDisplayedWithAutherName(String postTitle, String authorName) {
		waitForElementVisible(driver, UserPostDetailPageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, authorName);
		return isElementDisplayed(driver, UserPostDetailPageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, authorName);
	}

	public boolean isPostInfoDisplayedWithTime(String postTitle, String currentDay) {
		waitForElementVisible(driver, UserPostDetailPageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
		return isElementDisplayed(driver, UserPostDetailPageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
	}


	

	
}
