package JDBCConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class rmgYantraFetchData {
			public static void main(String[] args) throws SQLException, InterruptedException {
                WebDriverManager.chromedriver().setup();
				WebDriver driver1=new ChromeDriver();
				driver1.get("http://rmgtestingserver:8084/");
				driver1.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("rmgyantra");
				driver1.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("rmgy@9999");
				driver1.findElement(By.xpath("//button[.='Sign in']")).click();
				Thread.sleep(2000);
				driver1.findElement(By.xpath("//a[.='Projects']")).click();
				driver1.findElement(By.xpath("//span[.='Create Project']")).click();
				String ProName="rajus10 project";
				driver1.findElement(By.xpath("//input[@name='projectName']")).sendKeys(ProName);
				driver1.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("projectManager");
				WebElement dropbox = driver1.findElement(By.xpath("//h4[.='Create Project']/../..//select[@name='status']"));
				Select s=new Select(dropbox);
				s.selectByVisibleText("On Gogin");
				driver1.findElement(By.xpath("//input[@value='Add Project']")).click();
				Connection connection=null;
				try {
					String data=null;
					Driver driver=new Driver();
					DriverManager.registerDriver(driver);
		            connection=DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects","root@%","root");
		            Statement statement=connection.createStatement();
		            ResultSet result = statement.executeQuery("select * from project");
		            boolean flag=false;
		            while(result.next()) {
		            	 data=result.getString(4);
		            if(data.contains(ProName)){
		            flag=true;
		            }}
		            if(flag) {
		            	System.out.println(data);
		            	System.out.println("data is present");}
		            else
		            	System.out.println("data is absent");}
				finally {
		            	connection.close();}
				Thread.sleep(2000);
				driver1.close();
			}//main ends
		}//class ends


	 

