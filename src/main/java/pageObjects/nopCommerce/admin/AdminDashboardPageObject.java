package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.Admin.AdminDashboardPageUI;
import pageUIs.nopCommerce.Admin.AdminLoginPageUI;

public class AdminDashboardPageObject extends BasePage{
	private WebDriver driver;

	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isDashboardHeaderIsDisplay() {
		waitForElementVisible(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
		return isElementDisplayed(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
	}
}
