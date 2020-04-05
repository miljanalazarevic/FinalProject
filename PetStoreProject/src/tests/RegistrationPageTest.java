package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.RegistrationPage;
import utilis.ExcelUtils;

public class RegistrationPageTest {
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

	@Test(priority = 5)
	public void RegistrationTesting() {

		driver.navigate().to(this.locators.getProperty("registerUrl"));
		RegistrationPage newUser = new RegistrationPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(this.locators.getProperty("registerUrl"));
			ExcelUtils.setRandomAt(i, 0);
			RegistrationPage.setUserID(ExcelUtils.getDataAt(i, 0));
			RegistrationPage.setNewPass(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setRepeatPass(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setFirstName(ExcelUtils.getDataAt(i, 2));
			RegistrationPage.setLastName(ExcelUtils.getDataAt(i, 3));
			RegistrationPage.setEmail(ExcelUtils.getDataAt(i, 4));
			RegistrationPage.setPhone(ExcelUtils.getDataAt(i, 5));
			RegistrationPage.setAddress1(ExcelUtils.getDataAt(i, 6));
			RegistrationPage.setAddress2(ExcelUtils.getDataAt(i, 7));
			RegistrationPage.setCity(ExcelUtils.getDataAt(i, 8));
			RegistrationPage.setState(ExcelUtils.getDataAt(i, 9));
			RegistrationPage.setZip(ExcelUtils.getDataAt(i, 10));
			RegistrationPage.setCountry(ExcelUtils.getDataAt(i, 11));
			ExcelUtils.setLangRandomAt(i, 12);
			RegistrationPage.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			RegistrationPage.selectFavoriteCategory(ExcelUtils.getDataAt(i, 13));
			RegistrationPage.getEnableMyList().click();
			RegistrationPage.getEnableMyBanner().click();
			RegistrationPage.getSaveAccauntInformation().click();
		}

		sa.assertTrue(RegistrationPage.checkRegistration());
		sa.assertAll();

	}
	// IncorrectRepeatPasswordInputTesting, expected result: Impossible
	// registration.
	// Password and repeat passworsd are not matching

	@Test(priority = 7)
	public void IncorrectRepeatPasswordInputTesting() {

		driver.navigate().to(this.locators.getProperty("registerUrl"));
		RegistrationPage newUser = new RegistrationPage(driver, locators, waiter);
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);
		ExcelUtils.setDataAt(0, 14, "Repeat password");
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(this.locators.getProperty("registerUrl"));
			ExcelUtils.setRandomAt(i, 0);
			ExcelUtils.setRandomAt(i, 14);
			RegistrationPage.setUserID(ExcelUtils.getDataAt(i, 0));
			RegistrationPage.setNewPass(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setRepeatPass(ExcelUtils.getDataAt(i, 14));
			RegistrationPage.setFirstName(ExcelUtils.getDataAt(i, 2));
			RegistrationPage.setLastName(ExcelUtils.getDataAt(i, 3));
			RegistrationPage.setEmail(ExcelUtils.getDataAt(i, 4));
			RegistrationPage.setPhone(ExcelUtils.getDataAt(i, 5));
			RegistrationPage.setAddress1(ExcelUtils.getDataAt(i, 6));
			RegistrationPage.setAddress2(ExcelUtils.getDataAt(i, 7));
			RegistrationPage.setCity(ExcelUtils.getDataAt(i, 8));
			RegistrationPage.setState(ExcelUtils.getDataAt(i, 9));
			RegistrationPage.setZip(ExcelUtils.getDataAt(i, 10));
			RegistrationPage.setCountry(ExcelUtils.getDataAt(i, 11));
			ExcelUtils.setLangRandomAt(i, 12);
			RegistrationPage.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			RegistrationPage.selectFavoriteCategory(ExcelUtils.getDataAt(i, 13));
			RegistrationPage.getEnableMyList().click();
			RegistrationPage.getEnableMyBanner().click();
			RegistrationPage.getSaveAccauntInformation().click();
		}

		assertTrue(RegistrationPage.ImpossibleRegistration());
	}
	// IncorrectEmailInputTesting , expected result: Impossible registration.
	// Email doesn't have @ and .com

	@Test(priority = 8)
	public void IncorrectEmailInputTesting() {

		driver.navigate().to(this.locators.getProperty("registerUrl"));
		RegistrationPage newUser = new RegistrationPage(driver, locators, waiter);
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);
		ExcelUtils.setDataAt(0, 15, "Incorect email");
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(this.locators.getProperty("registerUrl"));
			ExcelUtils.setRandomAt(i, 0);
			ExcelUtils.setDataAt(i, 15, "robust");
			RegistrationPage.setUserID(ExcelUtils.getDataAt(i, 0));
			RegistrationPage.setNewPass(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setRepeatPass(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setFirstName(ExcelUtils.getDataAt(i, 2));
			RegistrationPage.setLastName(ExcelUtils.getDataAt(i, 3));
			RegistrationPage.setEmail(ExcelUtils.getDataAt(i, 15));
			RegistrationPage.setPhone(ExcelUtils.getDataAt(i, 5));
			RegistrationPage.setAddress1(ExcelUtils.getDataAt(i, 6));
			RegistrationPage.setAddress2(ExcelUtils.getDataAt(i, 7));
			RegistrationPage.setCity(ExcelUtils.getDataAt(i, 8));
			RegistrationPage.setState(ExcelUtils.getDataAt(i, 9));
			RegistrationPage.setZip(ExcelUtils.getDataAt(i, 10));
			RegistrationPage.setCountry(ExcelUtils.getDataAt(i, 11));
			ExcelUtils.setLangRandomAt(i, 12);
			RegistrationPage.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			RegistrationPage.selectFavoriteCategory(ExcelUtils.getDataAt(i, 13));
			RegistrationPage.getEnableMyList().click();
			RegistrationPage.getEnableMyBanner().click();
			RegistrationPage.getSaveAccauntInformation().click();
		}

		assertTrue(RegistrationPage.ImpossibleRegistration());
	}
	// IncorrectPhoneInputTesting, expected result: Impossible registration.
	// field provided only for numbers containing letters

	@Test(priority = 9)
	public void IncorrectPhoneInputTesting() {

		driver.navigate().to(this.locators.getProperty("registerUrl"));
		RegistrationPage newUser = new RegistrationPage(driver, locators, waiter);
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(this.locators.getProperty("registerUrl"));
			ExcelUtils.setRandomAt(i, 0);
			RegistrationPage.setUserID(ExcelUtils.getDataAt(i, 0));
			RegistrationPage.setNewPass(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setRepeatPass(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setFirstName(ExcelUtils.getDataAt(i, 2));
			RegistrationPage.setLastName(ExcelUtils.getDataAt(i, 3));
			RegistrationPage.setEmail(ExcelUtils.getDataAt(i, 4));
			RegistrationPage.setPhone(ExcelUtils.getDataAt(i, 6));
			RegistrationPage.setAddress1(ExcelUtils.getDataAt(i, 6));
			RegistrationPage.setAddress2(ExcelUtils.getDataAt(i, 7));
			RegistrationPage.setCity(ExcelUtils.getDataAt(i, 8));
			RegistrationPage.setState(ExcelUtils.getDataAt(i, 9));
			RegistrationPage.setZip(ExcelUtils.getDataAt(i, 10));
			RegistrationPage.setCountry(ExcelUtils.getDataAt(i, 11));
			ExcelUtils.setLangRandomAt(i, 12);
			RegistrationPage.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			RegistrationPage.selectFavoriteCategory(ExcelUtils.getDataAt(i, 13));
			RegistrationPage.getEnableMyList().click();
			RegistrationPage.getEnableMyBanner().click();
			RegistrationPage.getSaveAccauntInformation().click();
		}

		assertTrue(RegistrationPage.ImpossibleRegistration());
	}

	// EmptyPasswordInput, Expected results:Impossible registration
	// Password is a required field
	// A warning should come out
	@Test(priority = 10)
	public void EmptyPasswordInputTesting() {
		driver.navigate().to(this.locators.getProperty("registerUrl"));
		RegistrationPage newUser = new RegistrationPage(driver, locators, waiter);
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(this.locators.getProperty("registerUrl"));
			ExcelUtils.setRandomAt(i, 0);
			RegistrationPage.setUserID(ExcelUtils.getDataAt(i, 0));

			RegistrationPage.setFirstName(ExcelUtils.getDataAt(i, 2));
			RegistrationPage.setLastName(ExcelUtils.getDataAt(i, 3));
			RegistrationPage.setEmail(ExcelUtils.getDataAt(i, 4));
			RegistrationPage.setPhone(ExcelUtils.getDataAt(i, 5));
			RegistrationPage.setAddress1(ExcelUtils.getDataAt(i, 6));
			RegistrationPage.setAddress2(ExcelUtils.getDataAt(i, 7));
			RegistrationPage.setCity(ExcelUtils.getDataAt(i, 8));
			RegistrationPage.setState(ExcelUtils.getDataAt(i, 9));
			RegistrationPage.setZip(ExcelUtils.getDataAt(i, 10));
			RegistrationPage.setCountry(ExcelUtils.getDataAt(i, 11));
			ExcelUtils.setLangRandomAt(i, 12);
			RegistrationPage.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			RegistrationPage.selectFavoriteCategory(ExcelUtils.getDataAt(i, 13));
			RegistrationPage.getEnableMyList().click();
			RegistrationPage.getEnableMyBanner().click();
			RegistrationPage.getSaveAccauntInformation().click();
		}
		assertTrue(RegistrationPage.ImpossibleRegistration());

	}

	// EmptyInput, Expected results:Impossible registration
	// the fields should be filled
	@Test(priority = 11)
	public void EmptyInputTesting() {
		driver.navigate().to(this.locators.getProperty("registerUrl"));
		RegistrationPage newUser = new RegistrationPage(driver, locators, waiter);
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(this.locators.getProperty("registerUrl"));
			ExcelUtils.setRandomAt(i, 0);
			RegistrationPage.setUserID(ExcelUtils.getDataAt(i, 0));
			RegistrationPage.setNewPass(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setRepeatPass(ExcelUtils.getDataAt(i, 1));
			RegistrationPage.setFirstName(ExcelUtils.getDataAt(i, 2));
			RegistrationPage.setEmail(ExcelUtils.getDataAt(i, 4));
			RegistrationPage.setAddress2(ExcelUtils.getDataAt(i, 7));
			RegistrationPage.setCity(ExcelUtils.getDataAt(i, 8));
			RegistrationPage.setZip(ExcelUtils.getDataAt(i, 10));
			RegistrationPage.setCountry(ExcelUtils.getDataAt(i, 11));
			ExcelUtils.setLangRandomAt(i, 12);
			RegistrationPage.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			RegistrationPage.selectFavoriteCategory(ExcelUtils.getDataAt(i, 13));
			RegistrationPage.getEnableMyList().click();
			RegistrationPage.getEnableMyBanner().click();
			RegistrationPage.getSaveAccauntInformation().click();
		}

		assertTrue(RegistrationPage.ImpossibleRegistration());
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
		ExcelUtils.closeExcell();
	}

}
