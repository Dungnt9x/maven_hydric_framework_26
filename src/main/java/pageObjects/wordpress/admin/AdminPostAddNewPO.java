package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.admin.AdminPostAddNewPageUI;

public class AdminPostAddNewPO extends BasePage{
	WebDriver driver;
	
	public AdminPostAddNewPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToAddNewPostTitle(String postTitle) {
		waitForElementVisible(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX);
		senkeyToElement(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX, postTitle);
		
	}

	public void enterToAddNewPostBody(String postBodyValues) {
		//Click
		waitForElementClickTable(driver, AdminPostAddNewPageUI.BODY_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.BODY_BUTTON);
		
		//Sendkey
		waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		senkeyToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX, postBodyValues);
		
	}

	public void clickToPublishOrEditButton() {
		waitForElementClickTable(driver, AdminPostAddNewPageUI.PUBLISH_OR_EDIT_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.PUBLISH_OR_EDIT_BUTTON);
		
	}
	
	public void clickToPrePublishOrEditButton() {
		waitForElementClickTable(driver, AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
		
	}

	public boolean isPostPublishOrEditMessageDisplayed(String postPublishMesage) {
		waitForAllElementVisible(driver, AdminPostAddNewPageUI.PUBLISH_OR_EDIT_MESSAGE, postPublishMesage);
		return isElementDisplayed(driver, AdminPostAddNewPageUI.PUBLISH_OR_EDIT_MESSAGE, postPublishMesage);
		
	}

	public AdminPostSearchPO openSearchPostPageUrl(String searchPostUrl) {
		openPageUrl(driver, searchPostUrl);
		return PageGeneratorManagerWP.getAdminSearchPage(driver);
		
	}

	public void enterToEditPostBody(String editPostBody) {
		//Click
		waitForElementClickTable(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		clickToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		
		//Sendkey
		waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		clearValueInElementByPressKey(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
		sleepInSecond(5);
		senkeyToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX, editPostBody);
		
	}

	

	
}
