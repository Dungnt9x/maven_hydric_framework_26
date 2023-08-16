package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.admin.UserSearchPostPageUI;

public class UserSearchPostPO extends BasePage{
WebDriver driver;
	
	public UserSearchPostPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isNothingFoundMessageDisplayed(String message) {
		waitForElementVisible(driver, UserSearchPostPageUI.NOTHING_FOUND_MESSAGE, message);
		return isElementDisplayed(driver, UserSearchPostPageUI.NOTHING_FOUND_MESSAGE, message);
	}
}
