package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreMenuPage;
import utilis.ExcelUtils;

public class PetStoreMenuTest {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.locators = new Properties();
		locators.load(new FileInputStream("config/locators.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test (priority=2)
	public void petStoreMenuLinksVerify() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("petStoreUrl"));
		PetStoreMenuPage menu = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		menu.getAllLinks();
		for (int i = 0; i < menu.getAllLinks().size(); i++) {
			int status = verifyURLStatus(menu.getAllLinks().get(i).getAttribute("href"));
			sa.assertTrue(status < 400);
		}
	}

	public int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return status;
	}

	@Test(priority=3)
	public void areLinksCorrect() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("petMenuUrl"));
		Thread.sleep(2000);
		PetStoreMenuPage menu = new PetStoreMenuPage(driver, locators, waiter);
		Assert.assertTrue(menu.isLogged());

	}

	@Test (priority=4)
	public void isSignedIn() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("petMenuUrl"));
		Thread.sleep(2000);
		PetStoreMenuPage menu = new PetStoreMenuPage(driver, locators, waiter);
		menu.getSignIn().click();
		Assert.assertTrue(menu.isSignedIn());
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
		ExcelUtils.closeExcell();
	}
}
