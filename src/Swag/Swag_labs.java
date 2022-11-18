package Swag;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Swag_labs {

	public WebDriver driver;

	@BeforeTest
	public void Browzer()  {
		String baseUrl = "https://www.saucedemo.com/";
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void logi() throws InterruptedException
	{
		String vaildUsername = "standard_user";
		 String vaildPass = "secret_sauce";
		 driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(vaildUsername);
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(vaildPass);
		 driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
		 String acctuiaUrl="https://www.saucedemo.com/inventory.html";
		 String expectedUrl=driver.getCurrentUrl();
		 Assert.assertEquals(acctuiaUrl, expectedUrl);
	}

	

	@Test
	public void price_high_To_low() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[4]")).click();

		List<WebElement> thepricelist = driver.findElements(By.className("inventory_item_price"));
		List<Double> neweditelist = new ArrayList<>();
		for (int i = 0; i < thepricelist.size()-1; i++) {

			String price = thepricelist.get(i).getText();
			String editeprice = price.replace("$", "");
			Double val = Double.parseDouble(editeprice);
			neweditelist.add(val);
		}

		for (int k = 0; k < neweditelist.size(); k++) {
			boolean checkprocess = neweditelist.get(0) > neweditelist.get(neweditelist.size() - 1);
			Assert.assertEquals(checkprocess, true);
			

		}

	}

	

	}

