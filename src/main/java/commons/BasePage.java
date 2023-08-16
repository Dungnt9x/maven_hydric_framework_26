package commons;

import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageObjects.wordpress.admin.AdminDashboardPO;
import pageObjects.wordpress.admin.PageGeneratorManagerWP;
import pageObjects.wordpress.admin.UserHomepagePO;
import pageUIs.jQuery.uploadFile.BasePageJQueryUI;
import pageUIs.nopCommerce.User.BasePageNopcommerceUI;
import pageUIs.nopCommerce.User.CustomerInfoPageUI;

public class BasePage {

	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	public void refreshCurentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	public void acceptAlert(WebDriver driver) {
		Alert alert = waitForAlertPresence(driver);
		alert.accept();
		}
	public void cancelAlert(WebDriver driver) {
		Alert alert = waitForAlertPresence(driver);
		alert.dismiss();
	}
	public void getAlertText(WebDriver driver) {
		Alert alert = waitForAlertPresence(driver);
		alert.getText();
	}
	public void sendkeyToALert(WebDriver driver, String textValue) {
		Alert alert = waitForAlertPresence(driver);
		alert.sendKeys(textValue);
	}
//	private By getByXpath(String locatorType) {
//		return By.xpath(locatorType);
//	}
	
	//locator type is id=, css=, xpath=, class=, name=
	public By getByLocator(String locatorType) {
		By by = null;
		System.out.println("Locator type = " + locatorType);
		if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
			by = By.id(locatorType.substring(3));
		} else if(locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")){
			by = By.className(locatorType.substring(6));
		}else if(locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
			by = By.cssSelector(locatorType.substring(4));
		}else if(locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
			by = By.name(locatorType.substring(5));
		}else if(locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		}else {
			throw new RuntimeException("Locator type is not suppoter");
		}
		return by;
	} 
	
	//Nếu truyền locator là Xpath type thì đúng
	//Nếu truyền locator khác Xpath type thì sai
	public String getDynamicXpath(String locatorType, String...dynamicValues) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		} 
		return locatorType;
	}
	
	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}
	public void clickToElement(WebDriver driver, String locatorType) {
		//Lời gọi hàm: call a function
		getWebElement(driver, locatorType).click();
	}
	
	public void clickToElement(WebDriver driver, String locatorType, String...dynamicValue) {
		//Lời gọi hàm: call a function
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).click();
	}
	
	public void senkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void clearValueInElementByPressKey(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		element.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
	}
	
	public void senkeyToElement(WebDriver driver, String locatorType, String textValue, String...dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	public String getElementText(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}
	public void selectItemInDefaultDropdow(WebDriver driver, String locatorType, String textItem) {
		 Select select = new Select(getWebElement(driver, locatorType));
		 select.selectByVisibleText(textItem);
	}
	public void selectItemInDefaultDropdow(WebDriver driver, String locatorType, String textItem, String...dynamicValues) {
			 Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
			 select.selectByVisibleText(textItem);
	}
	public String getSelectItemDefaultDropdow(WebDriver driver, String locatorType) {
		 Select select = new Select(getWebElement(driver, locatorType));
		 return select.getFirstSelectedOption().getText();
	}
	public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		 Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}
	public void selectItemInCustomDropdown (WebDriver driver, String parentLocator, String chldLocator, String expectedTextItem) {
		getWebElement(driver, chldLocator).click();
		sleepInSecond(1);
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(chldLocator)));
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(chldLocator));
		
		for (WebElement item : allDropdownItems) {
			String atualTextItem = item.getText();
			if (atualTextItem.equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				sleepInSecond(1);
				break;
			}
		}
}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getElementAttribute(WebDriver driver, String locatorType, String attributrName) {
		return getWebElement(driver, locatorType).getAttribute(attributrName);
	}
	
	public String getElementAttribute(WebDriver driver, String locatorType, String attributrName, String...dynamicValues) {
		return getWebElement(driver, locatorType).getAttribute(attributrName);
	}
	
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}
	public int getElementSize(WebDriver driver, String locatorType, String...dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues ) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}
	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		try {
			//Tìm thấy Element
			//Case 1: Displayed trả về true
			//Case2: UnDisplayed trả về False

			return getWebElement(driver, locatorType).isDisplayed();
		} catch (NoSuchElementException e) {
			//Case 3: Undisplayed trả về False
			return false;
		}
	}
//	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
//		boolean status = true;
//				if (getWebElement(driver, locatorType).isDisplayed()) {
//					status = false;
//				}
//		return status;
//	}
	public boolean isElementDisplayed(WebDriver driver, String locatorType, String...dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}
	public void overrideGlobalTimeout(WebDriver driver, int Timeout) {
		driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
	}
	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		System.out.println("Start time=" + new Date().toString());
		overrideGlobalTimeout(driver, 5);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overrideGlobalTimeout(driver, 30);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("Start time=" + new Date().toString());
			return true;
			//Có kích thước bằng 1 là nó có trong DOM
			//Nhưng lại không hiển thị
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()){
			System.out.println("Element in DOM but visible/displayed");
			System.out.println("Start time=" + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}
	public boolean isElementUndisplayed(WebDriver driver, String locatorType, String...dynamicValues) {
		System.out.println("Start time=" + new Date().toString());
		overrideGlobalTimeout(driver, 5);
		List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		overrideGlobalTimeout(driver, 30);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("Start time=" + new Date().toString());
			return true;
			//Có kích thước bằng 1 là nó có trong DOM
			//Nhưng lại không hiển thị
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()){
			System.out.println("Element in DOM but visible/displayed");
			System.out.println("Start time=" + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}
	public boolean isElementEnabled(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).isEnabled();
	}
	public boolean isElementSelected(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).isSelected();
	}
	public void switchToDefaultContent(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String...dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}
	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();;
	}
	


	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}


	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isImageLoaded(WebDriver driver, String locatorType, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getDynamicXpath(locatorType, dynamicValues));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	public void waitForElementVisible(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	public void waitForAllElementVisible(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	public void waitForElementInVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
//	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
//		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
//		overrideGlobalTimeout(driver, shortTimeout);
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
//		overrideGlobalTimeout(driver, longTimeout);
//		
//	}
	public void waitForElementInVisible(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	public void waitForAllElementInVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}
	public void waitForAllElementInVisible(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues))));
	}
	public void waitForElementClickTable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	public void waitForElementClickTable(WebDriver driver, String locatorType, String...dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void uploadMutipleFiles (WebDriver driver, String...fileNames) {
		//Đường dẫn của thư mục uploadFile: chạy được trên win/os/linus
		String filePath = GlobalContants.UPLOAD_FILE;
		//ĐƯờng dẫn của tất cả các file
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageJQueryUI.UPLOAD_FILE).sendKeys(fullFileName);
	}
	//Tối ưu ở bài học switch_page
	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickTable(driver, BasePageNopcommerceUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, BasePageNopcommerceUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}
	
	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickTable(driver, BasePageNopcommerceUI.ADDRESS_LINK);;
		clickToElement(driver, BasePageNopcommerceUI.ADDRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}
	
	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementClickTable(driver, BasePageNopcommerceUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageNopcommerceUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver); 
	}
	
	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickTable(driver, BasePageNopcommerceUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageNopcommerceUI.REWARD_POINT_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}
	//Tối ưu bài học ở dynamicXpath
	public BasePage openPageAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickTable(driver, BasePageNopcommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageNopcommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);

		default:
			throw new RuntimeException("Invalid page name at my Account area");
		}
	}
	public void openPageAtMyAccountByPageName(WebDriver driver, String pageName) {
		waitForElementClickTable(driver, BasePageNopcommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageNopcommerceUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		
	}
	
	//Switch_role
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickTable(driver, BasePageNopcommerceUI.LOG_OUT_LINK_AT_USER);
		clickToElement(driver, BasePageNopcommerceUI.LOG_OUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickTable(driver, BasePageNopcommerceUI.LOG_OUT_LINK_AT_ADMIN);
		clickToElement(driver, BasePageNopcommerceUI.LOG_OUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
	
	/**
	 * Click to dynamic Textbox By Id
	 * @param driver
	 * @param textboxId
	 * @param values
	 */
	public void inputToTextboxByID(WebDriver driver, String textboxId, String values) {
		waitForElementVisible(driver,BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_ID,textboxId);
		senkeyToElement(driver, BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_ID, values, textboxId);
		
	}
	
	/**
	 * Click to dynamic Button by Text
	 * @param driver
	 * @param buttonText
	 */
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementVisible(driver, BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_TEXT, buttonText);
		clickToElement(driver, BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_TEXT, buttonText);
		
	}
	/**
	 * Select Item In Dropdown By Name
	 * @param driver
	 * @param dropdownAttributeName
	 * @param itemValue
	 */
	public void inputToDropdownByName(WebDriver driver, String dropdownAttributeName, String itemValue) {
		waitForElementClickTable(driver, BasePageNopcommerceUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownAttributeName);
		selectItemInDefaultDropdow(driver, BasePageNopcommerceUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownAttributeName);
	}
	
	/**
	 * Select Item In Radio By Label
	 * @param driver
	 * @param radioLabelName
	 */
	public void clickToRadioButtonByLabel(WebDriver driver, String radioLabelName) {
		waitForElementClickTable(driver, BasePageNopcommerceUI.DYNAMIC_RADIO_BY_LABEL, radioLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopcommerceUI.DYNAMIC_RADIO_BY_LABEL, radioLabelName);
		
	}
	
	/**
	 * Select Item In Checkbox By Label
	 * @param driver
	 * @param checkboxLabelName
	 */
	public void clickToCheckboxButtonByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickTable(driver, BasePageNopcommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageNopcommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
		
	}
	/**
	 * Get value in textbox by TextboxID
	 * @param driver
	 * @param textboxID
	 * @return
	 */
	public Object getTextValueByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageNopcommerceUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
	}
	
	public int genarateFakeNumber() {
		  Random rand = new Random();
		  return rand.nextInt(9999);
	  }
	
	public UserHomepagePO openEndUserSite(WebDriver driver, String endUserUrl) {
		openPageUrl(driver, endUserUrl);
		return PageGeneratorManagerWP.getUserHomePage(driver);
	}
	
	public AdminDashboardPO openAdminSite(WebDriver driver, String adminUrl) {
		openPageUrl(driver, adminUrl);
		return PageGeneratorManagerWP.getAdminDashboardPage(driver);
	}
	
	private long longTimeout = GlobalContants.LONG_TIMEOUT;
	private long shortTimeout= 5;
}
