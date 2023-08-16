package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.User.UserHomePageUI;

public class HomePageObject extends BasePageFactory {
   private WebDriver driver;
	
	//Hàm khởi tạo = tên class
	//Không có kiểu trả về
	//Khi khởi tạo 1 đối tượng của class đó thì hàm khởi tạo được gọi đâùu tiên
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	
	//Page UI
	@FindBy(how = How.XPATH, using = "//a[@class='ico-register']")
	private WebElement registerLink;
	
	@FindBy(xpath = "//a[@class='ico-login']")
	private WebElement loginLink;
	
	@FindBy(css = "a[@class='ico-account']")
	private WebElement myAccountLnk;
	
	//BasePage Object
	public void clickToRegisterLink() {
		waitForElementClickTable(driver, registerLink);
		clickToElement(driver, registerLink);
	}
	public void clickToLogin() {
		waitForElementClickTable(driver, loginLink);
		clickToElement(driver, loginLink);
		
	}
	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, myAccountLnk); 
		return isElementDisplayed(driver, myAccountLnk);
	}
}
