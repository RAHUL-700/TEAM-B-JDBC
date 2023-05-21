package JDBCConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class FetchDataFromDB {

	public static void main(String[] args) throws SQLException {
		String firstname="sagar2";
		Connection connection=null;
		try
		{
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tyss","root","root");
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery("select * from rahul");
            boolean flag=false;
           
            while(result.next()) 
            {
            	String data=result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+" "+result.getString(4);
            	System.out.println(data);
            	/*if(data.contains(firstname)) 
            	{
                System.out.println("data is present");
            	flag=true;
            	break;
               }//if ends*/
            	}//while ends
	    	
		/*if(!flag) 
			System.out.println("data is not present");*/
		}//try ends
		
		finally {
            	connection.close();
            	}//finally ends
	}//main ends
}//class ends
