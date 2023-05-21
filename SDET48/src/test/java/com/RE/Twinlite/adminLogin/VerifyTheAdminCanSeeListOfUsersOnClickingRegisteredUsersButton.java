package com.RE.Twinlite.adminLogin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyTheAdminCanSeeListOfUsersOnClickingRegisteredUsersButton {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://rmgtestingserver/domain/House_Rental_Application/");
		//registering the user
		driver.findElement(By.xpath("//a[.='Register']")).click();
		driver.findElement(By.xpath("//input[@id='fullname']")).sendKeys("ramachandra");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("RamuUser");
		driver.findElement(By.xpath("//input[@name='mobile']")).sendKeys("8884447755");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("ramuUncle@yahoo.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("ramu@123");
		driver.findElement(By.xpath("//input[@id='c_password']")).sendKeys("ramu@123");
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		//log in to the application as a user
		driver.findElement(By.xpath("//a[.='Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("RamuUser");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("ramu@123");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		//log in to the application as a admin
				driver.findElement(By.xpath("//a[.='Login']")).click();
				driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[.='Submit']")).click();
		//click on registered users and check for newly registered user
				driver.findElement(By.xpath("//div[contains(.,'Registered Users: ') and @class='alert alert-warning']")).click();
				List<WebElement> allNames = driver.findElements(By.xpath("//td"));
				boolean flag=false;
				for(WebElement owner:allNames) {
					String ownerName = owner.getText();
					if(ownerName.contains("Ram")) {
				              flag=true;
				              System.out.println("User was found in the admin's User list successfully");
				              break;
				}}
				if(!flag) 
					System.out.println("User was not  found in the admin's User list successfully");
				driver.close();
	}

}
