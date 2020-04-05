package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import pages.HomePage;
import utilis.ExcelUtils;

public class EnterTheStore {

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

	}

	@Test (priority=1)
	public void enterTheStore() throws InterruptedException {
		driver.navigate().to(this.locators.getProperty("petStoreUrl"));
		HomePage home = new HomePage(driver, locators, waiter);
		home.EnterTheStore();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(home.isEnterTheStore());
		Thread.sleep(2000);

	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
		ExcelUtils.closeExcell();
	}
}
