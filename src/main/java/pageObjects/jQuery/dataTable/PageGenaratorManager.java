package pageObjects.jQuery.dataTable;

import org.openqa.selenium.WebDriver;

public class PageGenaratorManager {
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
}
