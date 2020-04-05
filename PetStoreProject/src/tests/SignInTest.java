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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.SignInPage;
import utilis.ExcelUtils;

public class SignInTest {
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
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test(priority=6)
	public void logIn() {
		driver.navigate().to(this.locators.getProperty("signInUrl"));
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		
		ExcelUtils.setWorkSheet(1);
		SignInPage sign = new SignInPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(this.locators.getProperty("signInUrl"));
			String username = ExcelUtils.getDataAt(i, 0);
			String password = ExcelUtils.getDataAt(i, 1);
			sign.LogIn(username, password);
			sa.assertTrue(sign.isLogged());

		}
		sa.assertAll();
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
		ExcelUtils.closeExcell();
	}
}
