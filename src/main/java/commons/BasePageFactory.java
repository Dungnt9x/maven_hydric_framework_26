package commons;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	private long longTimeout =30;
	protected static BasePage getBasePageObject() {
		return new BasePage();
	}
	protected void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	protected void refreshCurentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	protected void acceptAlert(WebDriver driver) {
		Alert alert = waitForAlertPresence(driver);
		alert.accept();
		}
	protected void cancelAlert(WebDriver driver) {
		Alert alert = waitForAlertPresence(driver);
		alert.dismiss();
	}
	protected void getAlertText(WebDriver driver) {
		Alert alert = waitForAlertPresence(driver);
		alert.getText();
	}
	protected void sendkeyToALert(WebDriver driver, String textValue) {
		Alert alert = waitForAlertPresence(driver);
		alert.sendKeys(textValue);
	}
	protected By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}
	protected WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}
	protected List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}
	protected void clickToElement(WebDriver driver, WebElement element) {
		//Lời gọi hàm: call a function
		element.click();
	}
	protected void senkeyToElement(WebDriver driver, WebElement element, String textValue) {
		element.clear();
		element.sendKeys(textValue);
	}
	protected String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}
	protected void selectItemInDefaultDropdow(WebDriver driver, String xpathLocator, String textItem) {
		 Select select = new Select(getWebElement(driver, xpathLocator));
		 select.selectByValue(textItem);
	}
	protected String getSelectItemDefaultDropdow(WebDriver driver, String xpathLocator) {
		 Select select = new Select(getWebElement(driver, xpathLocator));
		 return select.getFirstSelectedOption().getText();
	}
	protected boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		 Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}
	protected void selectItemInCustomDropdown (WebDriver driver, String parentLocator, String chldLocator, String expectedTextItem) {
		getWebElement(driver, chldLocator).click();
		sleepInSecond(1);
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(chldLocator)));
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
	protected void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributrName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributrName);
	}
	protected String getElementAttribute(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText();
	}
	protected String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}
	protected String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	protected int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}
	protected void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}
	protected boolean isElementDisplayed(WebDriver driver, WebElement element) {
		return element.isDisplayed();
	}
	protected boolean isElementEnabled(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}
	protected boolean isElementSelected(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).isSelected();
	}
	protected void switchToDefaultContent(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}
	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	protected void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator));
	}
	


	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}


	protected void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	protected void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
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

	protected String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

	protected boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	protected void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}
	protected void waitForAllElementVisible(WebDriver driver, List<WebElement> element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	protected void waitForElementInVisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOf(element));
	}
	protected void waitForAllElementInVisible(WebDriver driver, List<WebElement> element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(element));
	}
	protected void waitForElementClickTable(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	private long shortTimeout= 5;
}
