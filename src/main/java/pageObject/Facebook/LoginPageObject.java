package pageObject.Facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.facebook.LoginPageUi;

public class LoginPageObject extends BasePage {
	WebDriver driver;
	
	public LoginPageObject (WebDriver driver) {
		this.driver = driver;
	}

	public void clickToCreateNewAccountButotn() {
		waitForElementClickTable(driver, LoginPageUi.NEW_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUi.NEW_ACCOUNT_BUTTON);
		
	}

	public boolean isEmailAddressTextboxDisplayed() {
		waitForElementVisible(driver, LoginPageUi.EMAIL_ADDRESS_TEXTBOX);
		return isElementDisplayed(driver, LoginPageUi.EMAIL_ADDRESS_TEXTBOX);
	}

	public void enterToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, LoginPageUi.EMAIL_ADDRESS_TEXTBOX);
		senkeyToElement(driver, LoginPageUi.EMAIL_ADDRESS_TEXTBOX, emailAddress);
		
	}

	public boolean isConfirmEmailAddressTextboxDisplayed() {
		return isElementDisplayed(driver, LoginPageUi.CONFFIRM_EMAIL_ADDRESS_TEXTBOX);
	}

	public void clickCloseIconAtRegisterForm() {
		waitForElementClickTable(driver, LoginPageUi.CLOSE_ICON);
		clickToElement(driver, LoginPageUi.CLOSE_ICON);
		
	}

	public boolean isConfirmEmailAddressTextboxUndisplaed() {
		return isElementUndisplayed(driver, LoginPageUi.CONFFIRM_EMAIL_ADDRESS_TEXTBOX);
	}
}
