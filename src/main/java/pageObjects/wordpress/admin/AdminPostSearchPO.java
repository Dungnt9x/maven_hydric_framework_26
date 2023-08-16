package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.admin.AdminPostSearchPageUI;

public class AdminPostSearchPO extends BasePage {
	WebDriver driver;
	
	public AdminPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostAddNewPO clickToAddNewButton() {
		waitForElementClickTable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
		return PageGeneratorManagerWP.getAdminAddNewPage(driver);
	}

	public void enterToSearchTextbox(String postTitle) {
		waitForElementVisible(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX);
		senkeyToElement(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX, postTitle);
		
	}

	public void clickSearchPostButton() {
		waitForElementClickTable(driver, AdminPostSearchPageUI.SEARCH_POST_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.SEARCH_POST_BUTTON);
		
	}

	public boolean isPostsearchTableDisplayed(String headerID, String cellValue) {
		int headerIndex = getElementSize(driver, AdminPostSearchPageUI.TABLE_HEADER_INDEX_BY_HEADER_NAME, headerID) +1;
		waitForElementVisible(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
		return isElementDisplayed(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
	}

	public AdminPostAddNewPO clickToPostTitleLink(String postTitle) {
		waitForElementClickTable(driver, AdminPostSearchPageUI.ROW_TITLE_DETAIL_BY_TITLE_NAME, postTitle);
		clickToElement(driver, AdminPostSearchPageUI.ROW_TITLE_DETAIL_BY_TITLE_NAME, postTitle);
		return PageGeneratorManagerWP.getAdminAddNewPage(driver);
	}

	public void selectPostCheckboxByTitle(String editPostTitle) {
		waitForElementVisible(driver, AdminPostSearchPageUI.ROW_CHECKBOX_BY_TITLE_NAME, editPostTitle);
		checkToDefaultCheckboxOrRadio(driver, AdminPostSearchPageUI.ROW_CHECKBOX_BY_TITLE_NAME, editPostTitle);
		
	}

	public void selectTextItemInActionDrodown(String dropDownItem) {
		waitForAllElementVisible(driver, AdminPostSearchPageUI.ACTION_DROPDOWN);
		selectItemInDefaultDropdow(driver, AdminPostSearchPageUI.ACTION_DROPDOWN, dropDownItem);
		
	}

	public void clickApplyToButton() {
		waitForElementClickTable(driver, AdminPostSearchPageUI.APPLY_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.APPLY_BUTTON);
		
	}

	public boolean isMoveToTrashMessageDisplayed(String message) {
		waitForElementVisible(driver, AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE, message);
		return isElementDisplayed(driver, AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE, message);
	}

	public boolean isNoPostFoundMessageDisplayed(String message) {
		waitForElementVisible(driver, AdminPostSearchPageUI.NO_POST_FOUND_MESSAGE, message);
		return isElementDisplayed(driver, AdminPostSearchPageUI.NO_POST_FOUND_MESSAGE, message);
	}


	
}
