package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.User.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;
	
	//Hàm khởi tạo = tên class
	//Không có kiểu trả về
	//Khi khởi tạo 1 đối tượng của class đó thì hàm khởi tạo được gọi đâùu tiên
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public UserRegisterPageObject clickToRegisterLink() {
		waitForElementClickTable(driver, UserHomePageUI.REGISTER_LINK);
		clickToElement(driver, UserHomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}
	public UserLoginPageObject openLoginPage() {
		waitForElementClickTable(driver, UserHomePageUI.LOGIN_LINK);
		clickToElement(driver, UserHomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
		
	}
	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_LINK); 
		return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
	}
	
	public UserCustomerInfoPageObject openMyAccountPage() {
		waitForElementClickTable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}
	

}
