package pageObject.saurceLab;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.saurceLab.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUserNameTextbox(String string) {
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		senkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, string);
		
	}

	public void enterToPasswordTextbox(String string) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, string);
		
	}

	public ProductPageObject clickToLoginButton() {
		waitForElementClickTable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManagerSL.getProductPage(driver);
	}
}
