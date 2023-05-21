package Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.result.Row;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AllAmazonLinksIntoExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
		
		//insert all links into into excel file
		FileInputStream fis=new FileInputStream("./src/test/resources/testdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheet("Sheet1");//only 0ne sheet therefore it is outside the for loop
        int i=0;
        
		for(WebElement link:allLinks) {
			String linkName=link.getAttribute("href");
               org.apache.poi.ss.usermodel.Row rw = sheet.createRow(i);//createRow() to create new rows in excel file
               rw.createCell(0).setCellValue(linkName);//writing data into cells   
               i++;
		}
		
		FileOutputStream fos=new FileOutputStream("./src/test/resources/testdata.xlsx");
		wb.write(fos);
		wb.close();
		
		//fetching all links and printing on console
		int lastRow = wb.getSheet("sheet1").getLastRowNum();
		System.out.println("total no. of links are   "+lastRow);
		for(int k=0;k<lastRow;k++) {
			String links=wb.getSheet("sheet1").getRow(k).getCell(0).getStringCellValue();
			System.out.println(links);
		}
		driver.close();
		
	}

}
