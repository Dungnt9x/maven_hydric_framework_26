package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.User.UserLoginPageUI;

public class LoginPageObject extends BasePageFactory{
   private WebDriver driver;
	
	//Hàm khởi tạo = tên class
	//Không có kiểu trả về
	//Khi khởi tạo 1 đối tượng của class đó thì hàm khởi tạo được gọi đâùu tiên
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	
	//Page UI
	@FindBy(id = "Email")
	private WebElement emailTextbox;
	@FindBy(id = "Password")
	private WebElement passwordTextbox;
	@FindBy(xpath = "//button[contains(@class,'login-button')]")
	private WebElement loginButton;
	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage;
	@FindBy(xpath = "//div[contains(@class,'validation-summary-errors')]")
	private WebElement unsuccessErrorMessage;
	
	//Page Object
	public void clickToLoginButton() {
		waitForElementVisible(driver, loginButton);
		clickToElement(driver, loginButton);
		
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public void inputToEmailTextbox(String invalidEmail) {
		waitForElementVisible(driver, emailTextbox);
		senkeyToElement(driver, emailTextbox,invalidEmail);
		
	}

	public String getErrorMessageUnsuccessfull() {
		waitForElementVisible(driver, unsuccessErrorMessage);
		return getElementText(driver, unsuccessErrorMessage);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		senkeyToElement(driver, passwordTextbox, password);
	}

}
