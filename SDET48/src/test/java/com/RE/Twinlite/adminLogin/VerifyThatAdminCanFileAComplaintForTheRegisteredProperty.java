package com.RE.Twinlite.adminLogin;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyThatAdminCanFileAComplaintForTheRegisteredProperty {

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
		//registering a property into the application
		driver.findElement(By.xpath("//a[.='Register']")).click();
		driver.findElement(By.xpath("//input[@name='fullname']")).sendKeys("RamaChandra");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='mobile']")).sendKeys("8884771323");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='alternat_mobile']")).sendKeys("3321100006");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='email']")).sendKeys("ramuUncle2026@yahoo.com");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='plot_number']")).sendKeys("265");
		driver.findElement(By.xpath("(//input[@name='rooms'])")).sendKeys("3bhk");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='country']")).sendKeys("India");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='state']")).sendKeys("Karnataka");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='city']")).sendKeys("Bangalore");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='rent']")).sendKeys("5000");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='deposit']")).sendKeys("50000");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='accommodation']")).sendKeys("semifurnished"); 
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='description']")).sendKeys("available for renting");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='landmark']")).sendKeys("clock tower");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='address']")).sendKeys("ramnagar 4th street");
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='landmark']")).sendKeys("clock tower");
		WebElement options = driver.findElement(By.xpath("//select[@class='form-control']"));
		Select s=new Select(options);
		s.selectByIndex(0);
		//uploading image
				File f=new File("./src/test/resources/Screenshot (38).png");
				String abspath = f.getAbsolutePath();
				driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='image']")).sendKeys(abspath);
				Thread.sleep(6000);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//button[.='Submit']")).click();
		Thread.sleep(2000);
        String successText = driver.findElement(By.xpath("(//div[.='Registration successfull. Thank you'])[1]")).getText();
		if(successText.contains("successfull"))
			System.out.println("property registered successfully");
		else
			System.out.println("property registration failed");
	
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		
		//first log in as user again
		driver.findElement(By.xpath("//a[.='Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("RamuUser");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("ramu@123");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		
		//rising a complaint
		driver.findElement(By.xpath("//a[.='Details/Update']")).click();
		driver.findElement(By.xpath("//a[.='Complaint']")).click();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("room no.258");
		driver.findElement(By.xpath("//input[@name='cmp']")).sendKeys("NO WATER NO ELECTRICITY");
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		//verifying the complaint
		String check = driver.findElement(By.xpath("//div[.='Sent Successfully']")).getText();
		if(check.contains("Sent Successfully"))
			System.out.println("complaint rised successfully");
		else 
			System.out.println("complaint rising failed");
	
		driver.close();
	}

}
