package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.User.CustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
		private WebDriver driver;

		public UserCustomerInfoPageObject(WebDriver driver) {
			this.driver = driver;
		}

		public boolean isCustomerInfoPageDisplay() {
			waitForAllElementVisible(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
			return isElementDisplayed(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
		}

		

//		public AddressPageObject openAddressPage() {
//			waitForAllElementVisible(driver, CustomerInfoPageUI.ADDRESS_LINK);
//			clickToElement(driver, CustomerInfoPageUI.ADDRESS_LINK);
//			return PageGeneratorManager.getAddressPage(driver);
//		}
		
}
