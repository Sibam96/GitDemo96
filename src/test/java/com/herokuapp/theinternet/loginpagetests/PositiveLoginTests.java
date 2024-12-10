package com.herokuapp.theinternet.loginpagetests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.base.TestUtilities;

public class PositiveLoginTests extends TestUtilities {
	
	
	@Test
	public void logInTest() {
		System.out.println("Starting logIn test");

		// Create driver
		//WebDriver driver = new ChromeDriver();
		//driver.manage().window().maximize();

		// open main page
		String url = "http://the-internet.herokuapp.com/";
		driver.get(url);
		log.info("Main page is opened.");

		// Click on Form Authentication link
		driver.findElement(By.linkText("Form Authentication")).click();

		// enter username and password
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		sleep(3000);

		// push log in button
		WebElement logInButton = driver.findElement(By.className("radius"));
		wait.until(ExpectedConditions.elementToBeClickable(logInButton));
		logInButton.click();

		// verifications
		// new url
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

		// log out button is visible
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='button secondary radius']")).isDisplayed(),
				"logOutButton is not visible.");

		// Successful log in message
		String expectedSuccessMessage = "You logged into a secure area!";
		String actualSuccessMessage = driver.findElement(By.id("flash")).getText();
		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
				"actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: "
						+ expectedSuccessMessage + "\nactualSuccessMessage: " + actualSuccessMessage);

		
	}
}