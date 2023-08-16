package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.User.UserRegisterPageUI;

public class RegisterPageObject extends BasePageFactory{
   private WebDriver driver;
	
	//Hàm khởi tạo = tên class
	//Không có kiểu trả về
	//Khi khởi tạo 1 đối tượng của class đó thì hàm khởi tạo được gọi đâùu tiên
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	
	//Page UI
	@FindBy(id = "FirstName")
	private WebElement firstNameTextbox;
	@FindBy(id = "LastName")
	private WebElement lastNameTextbox;
	@FindBy(id = "Email")
	private WebElement emailTextBox;
	@FindBy(id = "Password")
	private WebElement passwordTextbox;
	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPasswordTextbox;
	@FindBy(id = "register-button")
	private WebElement registerButton;
	@FindBy(id = "FirstName-error")
	private WebElement firstNameErrorMessage;
	@FindBy(id = "LastName-error")
	private WebElement lastNameErrorMessage;
	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage;
	@FindBy(id = "Password-error")
	private WebElement passwordErrorMessage;
	@FindBy(id = "ConfirmPassword-error")
	private WebElement confirmPasswordErrorMessage;
	@FindBy(css = "div.result")
	private WebElement registerSucessMessage;
	@FindBy(xpath = "//div[contains(@class,'message-error')]//li")
	private WebElement existingEmailErrorMessage;
	
	//PageBase
	public void clickToRegisterButton() {
		waitForElementClickTable(driver, registerButton);
		clickToElement(driver, registerButton);
	}

	public String getErrorMessageAtFirstTextbox() {
		waitForElementVisible(driver, firstNameErrorMessage);
		return getElementText(driver, firstNameErrorMessage);
	}

	public String getErrorMessageAtLastTextbox() {
		waitForElementVisible(driver, lastNameErrorMessage);
		return getElementText(driver, lastNameErrorMessage);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, lastNameErrorMessage);
		return getElementText(driver, lastNameErrorMessage);
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}

	public String getErrorMessageAtConffirmPasswordTextbox() {
		waitForElementVisible(driver, confirmPasswordErrorMessage);
		return getElementText(driver, confirmPasswordErrorMessage);
	}

	public void inputToFirstnameTextbox(String firstName) {
		waitForElementVisible(driver,firstNameTextbox);
		senkeyToElement(driver, firstNameTextbox, firstName);
		
	}

	public void inputTolastnameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		senkeyToElement(driver,lastNameTextbox, lastName);
		
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailTextBox);
		senkeyToElement(driver, emailTextBox, emailAddress);
		
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		senkeyToElement(driver, passwordTextbox, password);
		
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		senkeyToElement(driver, confirmPasswordTextbox, confirmPassword);
		
	}


	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, registerSucessMessage);
		return getElementText(driver, registerSucessMessage);
	}

	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, existingEmailErrorMessage);
		return getElementText(driver, existingEmailErrorMessage);
	}

	public String getErrorMessAtPasswordTextbox() {
		waitForElementVisible(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}

	public String getErrorMessAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}
}
