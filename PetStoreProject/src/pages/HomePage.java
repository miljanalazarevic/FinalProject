package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public HomePage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getEnterStoreBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("enterStoreBtn")));

	}

	public void EnterTheStore() {
		this.getEnterStoreBtn().click();

	}

	public boolean isEnterTheStore() {
		boolean EnterTheStore = true;

		try {
			this.driver.findElement(By.xpath(this.locators.getProperty("signIn")));
		} catch (Exception e) {
			EnterTheStore = false;
		}
		return EnterTheStore;

	}
}
