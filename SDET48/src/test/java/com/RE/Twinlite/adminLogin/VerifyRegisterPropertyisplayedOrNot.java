package com.RE.Twinlite.adminLogin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;


public class VerifyRegisterPropertyisplayedOrNot {

	public static void main(String[] args) throws InterruptedException, IOException {

		
	//opening browser
		WebDriver driver=null;
		
		FileInputStream fis=new FileInputStream("./src/test/resources/homerentalcommondata.property");
		Properties p=new Properties();
		p.load(fis);
		String browser = p.getProperty("browser");//selecting browser according to the property file data
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		String url=p.getProperty("url");
		String username=p.getProperty("username");
		String password=p.getProperty("password");
		
		
		
		//generate random numbers
		Random ran=new Random();
		int ranNo=ran.nextInt(1000);
		
		//fetching data from excel file for user registration 
		FileInputStream fis1=new FileInputStream("./src/test/resources/HomeRental TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		 Sheet sheet1 = wb.getSheet("LoginAdmin");
		
		String fullname = sheet1.getRow(1).getCell(1).getStringCellValue();
		String username1 = sheet1.getRow(2).getCell(1).getStringCellValue();
		String mobile = sheet1.getRow(3).getCell(1).getStringCellValue()+ranNo;
		String email = ranNo+sheet1.getRow(4).getCell(1).getStringCellValue();
		String password1 = sheet1.getRow(5).getCell(1).getStringCellValue();
		String c_password = sheet1.getRow(6).getCell(1).getStringCellValue();

		
		WebDriverManager.chromedriver().setup();
		driver.manage().window().maximize();
		driver.get(url);
		//registering the user
		driver.findElement(By.xpath("//a[.='Register']")).click();
		driver.findElement(By.xpath("//input[@id='fullname']")).sendKeys(fullname);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username1);
		driver.findElement(By.xpath("//input[@name='mobile']")).sendKeys(mobile);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password1);
		driver.findElement(By.xpath("//input[@id='c_password']")).sendKeys(password1);
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		
		
		//log in to the application as a user
		driver.findElement(By.xpath("//a[.='Login']")).click();
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[.='Submit']")).click();
		
		
		//fetching data for registering property using excel file
		String alternat_mobile = sheet1.getRow(19).getCell(1).getStringCellValue()+ranNo;
		String plotNo = ranNo+sheet1.getRow(21).getCell(1).getStringCellValue();
		String rooms = sheet1.getRow(22).getCell(1).getStringCellValue();
		String country = sheet1.getRow(23).getCell(1).getStringCellValue();
		String state = sheet1.getRow(24).getCell(1).getStringCellValue();
		String city =sheet1.getRow(25).getCell(1).getStringCellValue();
		String rent =sheet1.getRow(26).getCell(1).getStringCellValue();
		String deposit =sheet1.getRow(27).getCell(1).getStringCellValue();
		String accommodation =sheet1.getRow(28).getCell(1).getStringCellValue();
		String description =sheet1.getRow(29).getCell(1).getStringCellValue();
		String landmark =sheet1.getRow(30).getCell(1).getStringCellValue();
		String address =sheet1.getRow(31).getCell(1).getStringCellValue();
		
		
		//registering a property into the application
		driver.findElement(By.xpath("//a[.='Register']")).click();
		driver.findElement(By.xpath("//input[@name='fullname']")).sendKeys(fullname);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='mobile']")).sendKeys(mobile);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='alternat_mobile']")).sendKeys(alternat_mobile);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='email']")).sendKeys(email);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='plot_number']")).sendKeys(plotNo);
		driver.findElement(By.xpath("(//input[@name='rooms'])")).sendKeys(rooms);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='country']")).sendKeys(country);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='rent']")).sendKeys(rent);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='deposit']")).sendKeys(deposit);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='accommodation']")).sendKeys(accommodation); 
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='description']")).sendKeys(description);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='landmark']")).sendKeys(landmark);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='address']")).sendKeys(address);
		WebElement options = driver.findElement(By.xpath("//select[@class='form-control']"));
		Select s=new Select(options);
		s.selectByIndex(0);
		//uploading image
				File f=new File("./src/test/resources/Screenshot (38).png");
				String abspath = f.getAbsolutePath();
				driver.findElement(By.xpath("//h2[.='Register Room']/../form//input[@name='image']")).sendKeys(abspath);
				Thread.sleep(2000);
		driver.findElement(By.xpath("//h2[.='Register Room']/../form//button[.='Submit']")).click();
		Thread.sleep(2000);
          String successText = driver.findElement(By.xpath("(//div[.='Registration successfull. Thank you'])[1]")).getText();
		if(successText.contains("successfull"))
			System.out.println("property registered successfully");
		else
			System.out.println("property registration failed");
		//logout as user
		driver.findElement(By.xpath("//a[.='Logout']")).click();

		//searching the property in the welcome page of the application
		
		String SearchKey = sheet1.getRow(44).getCell(1).getStringCellValue();
		String searchCity = sheet1.getRow(45).getCell(1).getStringCellValue();
		
		driver.findElement(By.xpath("//a[.='Search']")).click();
		driver.findElement(By.xpath("//input[@id='keywords']")).sendKeys(SearchKey);
		driver.findElement(By.xpath("//input[@id='location']")).sendKeys(searchCity);
		Thread.sleep(2000);
		driver.findElement(By.name("search")).click();
		
		//verify if the registered property is displayed
		List<WebElement> ownersList = driver.findElements(By.xpath("//p"));
		boolean flag=false;
		for(WebElement owner:ownersList) {
			String ownerName = owner.getText();
			if(ownerName.contains("Ram")) {
		              flag=true;
		              System.out.println("Property was found in the search list successfully");
		              break;
		}}
		if(!flag) 
			System.out.println("Property was not  found in the search list successfully");
		driver.close();
	}

}
