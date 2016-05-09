package SuperKartWebServices.SuperKartWebServices;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LoginDAO {
 
	final static Logger logger = Logger.getLogger(LoginDAO.class);
	
	public int verifyPassword(String userName, String userPassword){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");

            String query = "SELECT userPassword FROM Suraj.Users where userName = ?";
            logger.info("SELECT userPassword FROM Suraj.Users where userName ="+userName);
            
            stmt = con.prepareStatement(query);
            stmt.setString(1,userName);
            rs = stmt.executeQuery();
            
            if(!rs.isBeforeFirst())
            	return 0;
            else{
            	rs.next();
            	if(rs.getNString(1).equals(userPassword)){
            		return 2;
        	    }else{
        	    	return 1;
        	    }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return 0;
	}
	
	public String updateLastLoginTime(String userName){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");

            String query = "UPDATE Suraj.Users SET LastLogin=? where UserName=?";
            stmt = con.prepareStatement(query);
            String timeStamp = new SimpleDateFormat("dd-MMM-yy hh:mm:ss").format(new Date());
            System.out.println(timeStamp);
            stmt.setString(1,timeStamp);
            stmt.setString(2,userName);
            rs = stmt.executeQuery();
            
            System.out.println("inside LoginDAO "+timeStamp);
            return timeStamp;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return null;
	}
	
	public String fetchProfileDetails(String userName){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
            
            String query = "SELECT * FROM Suraj.Users where userName = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,userName);
            rs = stmt.executeQuery();
            
            //System.out.println();
            while(rs.next()) {
            	str+=rs.getNString(1) + "^";
            	str+=rs.getNString(2) + "^";
            	str+=rs.getNString(3) + "^";
            	str+=rs.getNString(4) + "^";
            	str+=rs.getNString(5) + "^";
            	str+=rs.getBigDecimal(6) + "^";
            	str+=rs.getDate(7) + "^";
            	str+=rs.getNString(8) + "^";
            	str+=rs.getNString(9) + "^";
            	str+=rs.getTimestamp(10);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return str;
	}
	
	public Boolean updateProfileDetails(MultivaluedMap<String, String> formParam){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
            
            String query = "UPDATE Suraj.Users SET FirstName=?, LastName=?, EmailId=?, ContactNo=?, Dob=?, BillingAddress=?, ShippingAddress=? where userName = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,formParam.getFirst("fname"));
            stmt.setString(2,formParam.getFirst("lname"));
            stmt.setString(3,formParam.getFirst("emailid"));
            stmt.setString(4,formParam.getFirst("contact"));
            
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(formParam.getFirst("dob"));
				String dob = new SimpleDateFormat("dd-MMM-yy").format(date);
				stmt.setString(5,dob);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            stmt.setString(6,formParam.getFirst("billaddr"));
            stmt.setString(7,formParam.getFirst("shipaddr"));
            stmt.setString(8,formParam.getFirst("username"));
            
            rs = stmt.executeQuery();
            
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return false;
	}
	
	public Boolean registerUser(MultivaluedMap<String, String> formParam){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
            //stmt = con.createStatement();
            
            //Check if User exists
            String query = "Select * from Suraj.Users where UserName = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,formParam.getFirst("username"));
            rs = stmt.executeQuery();
            
            if(!rs.isBeforeFirst()){  //check if result is empty
            	query = "INSERT INTO Suraj.Users(UserName,EmailId,UserPassword) VALUES (?,?,?)";
                stmt = con.prepareStatement(query);
                stmt.setString(1,formParam.getFirst("username"));
                stmt.setString(2,formParam.getFirst("emailid"));
                stmt.setString(3,formParam.getFirst("password"));
                
                rs = stmt.executeQuery();
                //con.commit();
                return true;
            }
            else{
            	return false; //user already exists
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
	}
}
