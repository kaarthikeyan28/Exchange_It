package Database;
import java.sql.*;


public class DbConnection{
	
	public static Connection getCon() throws SQLException {
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","Abishek00");
		return con;
	}
}


