package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetStoreMenuPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public PetStoreMenuPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public List<WebElement> getLeftMenu() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("leftMenu")));

	}

	public List<WebElement> getCentralMenu() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("centralMenu")));

	}

	public List<WebElement> getPictureMenu() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("pictureMenu")));
	}

	public WebElement getSignIn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signIn")));

	}

	public List<WebElement> getAllLinks() {
		return this.driver.findElements(By.tagName("a"));

	}

	public boolean isLogged() {
		List<WebElement> links = this.getAllLinks();
		boolean logged = false;
		for (int i = 0; i < links.size(); i++) {
			if (links.get(i).getAttribute("href").contains("FISH")) {
				logged = true;
				System.out.println("Url sadrzi fish");

			} else if (links.get(i).getAttribute("href").contains("DOGS")) {
				logged = true;
				System.out.println("Url sadrzi dogs");

			} else if (links.get(i).getAttribute("href").contains("CATS")) {
				logged = true;
				System.out.println("Url sadrzi cats");

			} else if (links.get(i).getAttribute("href").contains("BIRDS")) {
				logged = true;
				System.out.println("Url sadrzi birds");

			} else if (links.get(i).getAttribute("href").contains("REPTILES")) {
				logged = true;
				System.out.println("Url sadrzi reptiles");

			}
		}
		return logged;
	}

	public boolean isSignedIn() {
		boolean isSuccessful = true;
		try {
			driver.findElement(By.xpath(locators.getProperty("logInBtn")));

		} catch (Exception e) {
			isSuccessful = false;
		}
		return isSuccessful;

	}

}
