package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManagerWP {
	public static AdminLoginPO getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPO(driver);
	}
	
	public static AdminDashboardPO getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPO(driver);
	}
	
	public static AdminPostSearchPO getAdminSearchPage(WebDriver driver) {
		return new AdminPostSearchPO(driver);
	}
	
	public static AdminPostAddNewPO getAdminAddNewPage(WebDriver driver) {
		return new AdminPostAddNewPO(driver);
	}
	
	public static UserHomepagePO getUserHomePage(WebDriver driver) {
		return new UserHomepagePO(driver);
	}
	
	public static UserPostDetailPO getUserPostDetailPage(WebDriver driver) {
		return new UserPostDetailPO(driver);
	}
	
	public static UserSearchPostPO getUserSearchPostPage(WebDriver driver) {
		return new UserSearchPostPO(driver);
	}
}
