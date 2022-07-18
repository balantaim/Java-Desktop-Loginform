package loginf;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	public static Connection connect() {
		try {
			Connection con = null;
			String jbUrl = "jdbc:sqlite:LoginDB.db";
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(jbUrl);
			System.out.println("DB connection: Success!");
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error: No connection to DB " + e);
			return null;
		}
	}
	
	public static synchronized void CreateDb() {
		 File file = new File("LoginDB.db");
         if(!file.exists()) {
        	System.out.println("DB doesn't exist");
        	try {
    			Connection con = null;
    			String jbUrl = "jdbc:sqlite:LoginDB.db";
    			Class.forName("org.sqlite.JDBC");
    			con = DriverManager.getConnection(jbUrl);
    			
    			Statement stat = con.createStatement();
    			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Acounts("
    					+ "User VARCHAR(20) Primary key NOT NULL,"
    					+ "Pass VARCHAR(40),"
    					+ "Sudo INT);");

    			PreparedStatement prep = con.prepareStatement("INSERT INTO Acounts (User,Pass,Sudo) VALUES (?,?,?);");
    			prep.setString(1, "admin");
    			String pAdmin = "admin";
//    			hide password with base64 encryption
    			pAdmin = SecurePassword.Encoder(pAdmin);
    			prep.setString(2, pAdmin);
    			prep.setString(3, "1");
    			prep.addBatch();
    			
    			prep.setString(1, "user");
    			String pUser = "123";
//    			hide password with base64 encryption
    			pUser = SecurePassword.Encoder(pUser);
    			prep.setString(2, pUser);
    			prep.setString(3, "0");
    			prep.addBatch();
    			
    			con.setAutoCommit(false);
    			prep.executeBatch();
    			con.setAutoCommit(true);
    			con.close();
    			System.out.println("New DB is created");
        	}catch(ClassNotFoundException | SQLException e) {
    			System.out.println("Error: DB cannot be created " + e);
        	}
         }
	}

}
