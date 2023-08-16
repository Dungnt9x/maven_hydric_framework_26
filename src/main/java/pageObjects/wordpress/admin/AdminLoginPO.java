package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.admin.AdminLoginPageUI;

public class AdminLoginPO extends BasePage {
	WebDriver driver;
	
	public AdminLoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUserNameTextbox(String adminUsername) {
		waitForElementVisible(driver, AdminLoginPageUI.USERNAME_TEXTBOX);
		senkeyToElement(driver, AdminLoginPageUI.USERNAME_TEXTBOX, adminUsername);
		
	}

	public void enterToPasswordTextbox(String adminPassword) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, adminPassword);
		
	}

	public AdminDashboardPO clickToLoginButton() {
		waitForElementClickTable(driver, AdminLoginPageUI.SUBMIT_BUTTON);
		clickToElement(driver, AdminLoginPageUI.SUBMIT_BUTTON);
		return PageGeneratorManagerWP.getAdminDashboardPage(driver);
		
	}
}
