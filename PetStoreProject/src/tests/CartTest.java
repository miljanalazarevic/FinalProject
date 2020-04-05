package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartPage;
import pages.StoreItemPage;
import utilis.ExcelUtils;

public class CartTest {

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

	@Test (priority=12)
	public void areProductsInCart() {
		driver.navigate().to(this.locators.getProperty("cartUrl"));
		StoreItemPage items = new StoreItemPage(driver, locators, waiter);
		CartPage cart = new CartPage(driver, locators, waiter);
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(0);
		SoftAssert sa = new SoftAssert();
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(ExcelUtils.getDataAt(i, 1));
			items.addToCart();
			sa.assertTrue(cart.areItemsAdded(ExcelUtils.getDataAt(i, 0)));
		}

	}

	@Test (priority=13)
	public void emptyCart() {
		StoreItemPage item = new StoreItemPage(driver, locators, waiter);
		CartPage cart = new CartPage(driver, locators, waiter);
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(0);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(ExcelUtils.getDataAt(i, 1));
			item.addToCart();
		}
		this.driver.navigate().to(this.locators.getProperty("cartUrl"));
		driver.navigate().refresh();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(cart.cartIsEmpty());

	}

	@AfterClass
	public void afterClass() {
		driver.close();
		ExcelUtils.closeExcell();
	}
}
