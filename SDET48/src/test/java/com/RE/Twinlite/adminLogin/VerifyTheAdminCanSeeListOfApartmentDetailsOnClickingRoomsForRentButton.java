package com.RE.Twinlite.adminLogin;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyTheAdminCanSeeListOfApartmentDetailsOnClickingRoomsForRentButton {

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
		////registering a property into the application
		driver.findElement(By.xpath("//a[.='Register']")).click();
		driver.findElement(By.xpath("//a[.='Apartment Registration']")).click();
		driver.findElement(By.xpath("//h2[.='Apartment Room']/../form//input[@name='apartment_name']")).sendKeys("ramu apartments");
		driver.findElement(By.xpath("//h2[.='Apartment Room']/../form//input[@name='mobile']")).sendKeys("8880777755");
		driver.findElement(By.xpath("//h2[.='Apartment Room']/../form//input[@name='alternat_mobile']")).sendKeys("8888840066");
		driver.findElement(By.xpath("//h2[.='Apartment Room']/../form//input[@name='email']")).sendKeys("ramuUncle2024@yahoo.com");
		driver.findElement(By.xpath("//h2[.='Apartment Room']/../form//input[@name='plot_number']")).sendKeys("000333");
		driver.findElement(By.xpath("//h2[.='Apartment Room']/../form//input[@name='country']")).sendKeys("India");
		driver.findElement(By.xpath("//h2[.='Apartment Room']/../form//input[@name='state']")).sendKeys("Karnataka");
		driver.findElement(By.xpath("//h2[.='Apartment Room']/../form//input[@name='city']")).sendKeys("Bangalore");
		driver.findElement(By.xpath("//h2[.='Apartment Room']/../form//input[@name='landmark']")).sendKeys("clock tower");
		driver.findElement(By.xpath("//h2[.='Apartment Room']/../form//input[@name='address']")).sendKeys("ramnagar 4th street");
		
		//uploading image
				File f=new File("./src/test/resources/Screenshot (38).png");
				String abspath = f.getAbsolutePath();
				driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='image']")).sendKeys(abspath);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[.='Add More(Plat Number/Description)']")).click();
				driver.findElement(By.xpath("//input[@name='fullname[]']")).sendKeys("flat101");
				driver.findElement(By.xpath("//input[@name='ap_number_of_plats[]']")).sendKeys("101");
				driver.findElement(By.xpath("//input[@name='rooms[]']")).sendKeys("3BHK");
				driver.findElement(By.xpath("//input[@name='area[]']")).sendKeys("raju layout");
				driver.findElement(By.xpath("//input[@name='rent[]']")).sendKeys("5000");
				driver.findElement(By.xpath("//input[@name='deposit[]']")).sendKeys("100000");
				driver.findElement(By.xpath("//input[@name='accommodation[]']")).sendKeys("hot water");
				driver.findElement(By.xpath("//input[@name='description[]']")).sendKeys("nice house");
				WebElement options2 = driver.findElement(By.xpath("//select[@id='vacant']"));
				Select s1=new Select(options2);
				s1.selectByValue("1");
				driver.findElement(By.xpath("//h2[.='Apartment Room']/../form//button[.='Submit']")).click();
				
		
		String successText = driver.findElement(By.xpath("(//div[.='Registration successfull. Thank you'])[1]")).getText();
		if(successText.contains("successfull"))
			System.out.println("Apartment registered successfully");
		else
			System.out.println("Apartment registration failed");
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		
		//log in to the application as a admin
				driver.findElement(By.xpath("//a[.='Login']")).click();
				driver.findElement(By.xpath("//input[@name='username']")).sendKeys("admin");
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[.='Submit']")).click();
				
		//click on registered users and check for newly registered user
				driver.findElement(By.xpath("//div[contains(.,'Rooms for Rent: ') and @class='alert alert-warning']")).click();
				List<WebElement> allRooms = driver.findElements(By.xpath("//p[contains(.,'Plot Number: ')]"));
				boolean flag=false;
				for(WebElement room:allRooms) {
					String roomName = room.getText();
					if(roomName.contains("000333")) {
				              flag=true;
				              System.out.println("room was found in the admin's registered room list successfully");
				              break;
				}}
				if(!flag) 
					System.out.println("User was not  found in the admin's registered room list successfully");
				driver.close();
	}

}
