package commons;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	protected final Log log;
	
	@BeforeSuite
	public void initBeforSuite() {
		deleteAllureReport();
	}
		protected BaseTest() {
			log = LogFactory.getLog(getClass());
		}
	
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		
		if (browserList == browserList.FIREFOX){
			  //System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
			  WebDriverManager.firefoxdriver().setup();
			  driver = new FirefoxDriver();
			  }
		else if (browserList == browserList.H_FIREFOX){
			  //System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
			  WebDriverManager.firefoxdriver().setup();
			  FirefoxOptions options = new FirefoxOptions();
			  options.addArguments("--headless");
			  options.addArguments("windown-size=1900x1080");
			  driver = new FirefoxDriver(options);
			  }
		else if (browserList == browserList.CHROME) {
				 // System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
			  WebDriverManager.chromedriver().setup();
			  driver = new ChromeDriver();
			  }
		else if (browserList == browserList.H_CHROME) {
			  //System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
			  WebDriverManager.chromedriver().setup();
			  ChromeOptions options = new ChromeOptions();
			  options.addArguments("--headless");
			  options.addArguments("windown-size=1900x1080");
			  driver = new ChromeDriver(options);
		  }
		else {
				  throw new RuntimeException("Browser Name Invalid");
			  }
	
	driver.manage().timeouts().implicitlyWait(GlobalContants.LONG_TIMEOUT, TimeUnit.SECONDS);
	driver.get(GlobalContants.PORTAL_PAGE_URL);
	return driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		
		if (browserList == browserList.FIREFOX){
			  //System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
			  WebDriverManager.firefoxdriver().setup();
			  driver = new FirefoxDriver();
			  }
		else if (browserList == browserList.H_FIREFOX){
			  //System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
			  WebDriverManager.firefoxdriver().setup();
			  FirefoxOptions options = new FirefoxOptions();
			  options.addArguments("--headless");
			  options.addArguments("windown-size=1900x1080");
			  driver = new FirefoxDriver(options);
			  }
		else if (browserList == browserList.CHROME) {
				 // System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
			  WebDriverManager.chromedriver().setup();
			  driver = new ChromeDriver();
			  }
		else if (browserList == browserList.H_CHROME) {
			  //System.setProperty("webdriver.chrome.driver", projectPath + "/browserdriver/chromedriver");
			  WebDriverManager.chromedriver().setup();
			  ChromeOptions options = new ChromeOptions();
			  options.addArguments("--headless");
			  options.addArguments("windown-size=1900x1080");
			  driver = new ChromeDriver(options);
		  }
		else {
				  throw new RuntimeException("Browser Name Invalid");
			  }
	
	driver.manage().timeouts().implicitlyWait(GlobalContants.LONG_TIMEOUT, TimeUnit.SECONDS);
	driver.get(appUrl);
	return driver;
	}
	
	protected String getEnviromentValue(String serverName) {
		String envUrl = null;
		EnviromentList enviroment = EnviromentList.valueOf(serverName.toUpperCase());
		if (enviroment == enviroment.DEV) {
			envUrl = "https://demo.nopcommerce.com/";
		} else if (enviroment == enviroment.TESTING) {
			envUrl = "https://admin-demo.nopcommerce.com/";
		} else if (enviroment == enviroment.STAGING) {
			envUrl = "https://staging.orangehrmlive.com/";
		} else if (enviroment == enviroment.PRODUCTION) {
			envUrl = "https://production.orangehrmlive.com/";
		}
		return envUrl;
	}

	public WebDriver getDriverInstance() {
		return this.driver;
	}
	protected int genarateFakeNumber() {
		  Random rand = new Random();
		  return rand.nextInt(9999);
	  }
	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}


	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	
	public void deleteAllureReport() {
		try {
			String pathFolderDownload = GlobalContants.PROJECT_PATH + "/allure-results";
			File file = new File(pathFolderDownload);
			File[] listOfFile = file.listFiles();
			for (int i = 0; i < listOfFile.length; i++) {
				if (listOfFile[i].isFile()) {
					new File(listOfFile.toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	protected String getCurrentDay() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return String.valueOf(day);
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime();
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return String.valueOf(month);
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime();
		return String.valueOf(now.getYear());
	}

	protected String getToday() {
		return getCurrentMonth() + "-" + getCurrentDay() + "," + getCurrentYear();
	}
	
	
	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("internetexplorer")) {
				browserDriverName = "IEDriverServer";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
