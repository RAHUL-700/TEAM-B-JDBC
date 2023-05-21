package JDBCConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class InsertDataIntoMySqlDatabase {

	public static void main(String[] args) throws SQLException {
        Connection connection=null;
		
		try {   
			Driver driver=new Driver();//create a driver object
			DriverManager.registerDriver(driver);//register the driver
		    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tyss","root","root");//form a connection with the DB
			Statement statement=connection.createStatement();//create a statement
			String Query="insert into rahul values('preetam22','gupta56','1998-05-23','male');";//create a query
			int result=statement.executeUpdate(Query);//execute query and get the result 
			if(result==1) 
				System.out.println("data added");
				else
					System.out.println("data not added");
			}//try
		
		finally {
			connection.close();
		}		
		}//main ends
	}



