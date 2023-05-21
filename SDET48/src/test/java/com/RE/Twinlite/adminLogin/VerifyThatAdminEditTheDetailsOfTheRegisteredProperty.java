package com.RE.Twinlite.adminLogin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyThatAdminEditTheDetailsOfTheRegisteredProperty {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://rmgtestingserver/domain/House_Rental_Application/");
		
		
		
		//log in to the application as a admin
		driver.findElement(By.xpath("//a[.='Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		
		//goto details and edit the details of a property registered by the user
		driver.findElement(By.xpath("//a[.='Details/Update']")).click();
		driver.findElement(By.xpath("//p[contains(.,'8884411155')]/../../..//a")).click();
	
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='alternat_mobile']")).clear();
		driver.findElement(By.xpath("//input[@id='alternat_mobile']")).sendKeys("2222222222");
		driver.findElement(By.xpath("//input[@name='other']")).sendKeys("hi");
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		Thread.sleep(2000);
		//verify if the mobile number is changed
		driver.findElement(By.xpath("//a[.='Details/Update']")).click();
		List<WebElement> allPhones = driver.findElements(By.xpath("//p[contains(.,'Mobile Number')]"));
		boolean flag=false;
		for(WebElement phone:allPhones) {
			String phoneNo =phone.getText();
			if(phoneNo.contains("2222222222")) {
		              flag=true;
		              System.out.println("phone number was edited successfully by the admin");
		              break;
		}}
		if(!flag) 
			System.out.println("admin could not edit the phone number successfully");
		driver.close();
		
		
	}

}
