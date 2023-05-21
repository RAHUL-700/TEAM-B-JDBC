package JDBCConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class InsertDataIntoRmgYantraDatabase {
		public static void main(String[] args) throws SQLException, InterruptedException {
        Connection connection=null;String ProjName=null;
Driver driver=new Driver();//create a driver object
DriverManager.registerDriver(driver);//register the driver
connection = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects","root@%","root");//form a connection with the DB
Statement statement=connection.createStatement();//create a statement
String projId="TY_PROJ_02";String ProjMangr="raju02";String date="2023-05-11";ProjName="Infrasys01";String status="On Gogin";int Team=4;
String Query="insert into project values('"+projId+"','"+ProjMangr+"','"+date+"','"+ProjName+"','"+status+"','"+Team+"');";//create a query
int result=statement.executeUpdate(Query);//execute query and get the result 
if(result==1) 
	System.out.println("data added");
	else
		System.out.println("data not added");
          connection.close();
	        WebDriverManager.chromedriver().setup();
			WebDriver driver1=new ChromeDriver();
			driver1.get("http://rmgtestingserver:8084/");
			driver1.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("rmgyantra");
			driver1.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("rmgy@9999");
			driver1.findElement(By.xpath("//button[.='Sign in']")).click();
			Thread.sleep(2000);
			driver1.findElement(By.xpath("//a[.='Projects']")).click();
		    List<WebElement> projectnames = driver1.findElements(By.xpath("//table[@class='table table-striped table-hover']//td[2]"));
			ArrayList<String>al1=new ArrayList<String>();
			for(WebElement name:projectnames) {
				al1.add(name.getText()); }
			boolean flag=false;
			for(String search:al1) //iterating arraylist
			{
				if(search.contains(ProjName))
					flag=true;}
			if(flag)
			{
				System.out.println("project is present in project list");
				System.out.println(ProjName);}
			else
				System.out.println(ProjName+"Project is not in the list");
		}
		//main ends
		}





