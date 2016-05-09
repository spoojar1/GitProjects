package SuperKartWebServices.SuperKartWebServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class CartDAO {
	
	public boolean addToCart(String quantity, String productid, String username){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
                                  
            String query = "INSERT INTO Suraj.Cart VALUES(?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1,username);
            stmt.setString(2,productid);
            stmt.setString(3,quantity);
            
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
	
	public String fetchCart(String username){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
                                  
            String query = "select p.productid, productcategory, productname, quantity, price, quantity*price from Suraj.Products p join Suraj.Cart c on p.productid=c.productid where username=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,username);
            
            rs = stmt.executeQuery();
            
            str="<XMLRoot>";
            while(rs.next()) {
            	str+="<Cart>";
            	str+="<ProductId>" + rs.getNString(1) + "</ProductId>";
            	str+="<ProductCategory>" + rs.getNString(2) + "</ProductCategory>";
            	str+="<ProductName>" + rs.getNString(3) + "</ProductName>";
            	str+="<Quantity>" + rs.getNString(4) + "</Quantity>";
            	str+="<Price>" + rs.getNString(5) + "</Price>";
            	str+="<SubTotal>" + rs.getNString(6) + "</SubTotal>";
            	str+="</Cart>";
            }
            str+="</XMLRoot>";
            
            return str;
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
	
	public Boolean updateCart(String username, String productid, String quantity){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
                                  
            String query = "Update Suraj.Cart set quantity=? where username=? and productid=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,quantity);
            stmt.setString(2,username);
            stmt.setString(3,productid);
            
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
	
	public Boolean removeItemCart(String username, String productid){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
                                  
            String query = "Delete from Suraj.Cart where username=? and productid=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,username);
            stmt.setString(2,productid);
            
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
}
