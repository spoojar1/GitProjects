package SuperKartWebServices.SuperKartWebServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class ProductDAO {
	
	public String fetchProductsOnCat(String productcat, String limit, String sortorder){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
            String query ="";
            
            if(limit.equals("")){
            	if(sortorder.equals("1") || sortorder.equals("")){
            		query = "SELECT * FROM Suraj.Products where ProductCategory=?";
            		stmt = con.prepareStatement(query);
            		stmt.setString(1,productcat);
            	}
            	else{
            		if(sortorder.equals("2"))
            			query = "SELECT * FROM Suraj.Products where ProductCategory=? order by Price asc";
            		if(sortorder.equals("3"))
            			query = "SELECT * FROM Suraj.Products where ProductCategory=? order by Price desc";
            		stmt = con.prepareStatement(query);
            		stmt.setString(1,productcat);
            	}
            }else{
            	query = "SELECT * FROM Suraj.Products where ProductCategory=? and rownum<=?";
                stmt = con.prepareStatement(query);
                stmt.setString(1,productcat);
                stmt.setString(2,limit);
            }
            
            rs = stmt.executeQuery();
            
            str="<XMLRoot>";
            while(rs.next()) {
            	str+="<Products>";
            	str+="<ProductId>" + rs.getNString(1) + "</ProductId>";
            	str+="<ProductName>" + rs.getNString(2) + "</ProductName>";
            	str+="<Price>" + rs.getNString(4) + "</Price>";
            	str+="</Products>";
            }
            str+="</XMLRoot>";
            
            System.out.println("Hello sortorder "+sortorder);
            if(sortorder!=null && !sortorder.equals(""))
            	str+="^"+sortorder;
            
            System.out.println("inside fetchProductsOnCat"+str);
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
	
	public String fetchSearchResults(String search, String sortorder){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
            String query ="";
            
            query = "SELECT * FROM Suraj.Products where LOWER(ProductName) LIKE ? or LOWER(Specifications) LIKE ?";
            stmt = con.prepareStatement(query);
            
            if(sortorder.equals("1") || sortorder.equals("")){
        		query = "SELECT * FROM Suraj.Products where LOWER(ProductName) LIKE ? or LOWER(Specifications) LIKE ?";
        		stmt = con.prepareStatement(query);
        	}
        	else{
        		if(sortorder.equals("2"))
        			query = "SELECT * FROM Suraj.Products where LOWER(ProductName) LIKE ? or LOWER(Specifications) LIKE ? order by Price asc";
        		if(sortorder.equals("3"))
        			query = "SELECT * FROM Suraj.Products where LOWER(ProductName) LIKE ? or LOWER(Specifications) LIKE ? order by Price desc";
        		stmt = con.prepareStatement(query);
        	}
            
            String searchstring='%'+search.toLowerCase()+'%';
            System.out.println("searchstring "+searchstring);
            stmt.setString(1,searchstring);
            stmt.setString(2,searchstring);
            
            rs = stmt.executeQuery();
            
            str="<XMLRoot>";
            while(rs.next()) {
            	str+="<Products>";
            	str+="<ProductId>" + rs.getNString(1) + "</ProductId>";
            	str+="<ProductName>" + rs.getNString(2) + "</ProductName>";
            	str+="<ProductCategory>" + rs.getNString(3) + "</ProductCategory>";
            	str+="<Price>" + rs.getNString(4) + "</Price>";
            	str+="</Products>";
            }
            str+="</XMLRoot>";

            System.out.println("within fetchSearchResults"+str);
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
	
	public String fetchProductBean(String productid){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
            
            String query = "SELECT * FROM Suraj.Products where ProductId=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,productid);
            
            rs = stmt.executeQuery();
            
            str="";
            while(rs.next()) {
            	str+=rs.getNString(1)+"^";
            	str+=rs.getNString(2)+"^";
            	str+=rs.getNString(3)+"^";
            	str+=rs.getNString(4)+"^";
            	
            	String tmp=rs.getNString(5);
            	String[] s=tmp.split(Pattern.quote("\n"));
            	tmp="<xmlDocBean>";
            	for(int i=0;i<s.length;i++){
            		tmp+="<Tag"+i+">"+s[i].trim()+"</Tag"+i+">";
            	}
            	tmp+="</xmlDocBean>";
            	str+=tmp;
            }

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
	
	public String fetchProductPurchaseStatus(String productid, String username){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
            
            String query = "SELECT count(*) FROM Suraj.Orders o JOIN Suraj.OrderSummary os ON o.OrderId=os.OrderId where UserName=? AND ProductId=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,username);
            stmt.setString(2,productid);
            
            rs = stmt.executeQuery();
            
            rs.next();
            str=rs.getNString(1);
            
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
	
	public String fetchNumberOfReviews(String productid){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
            
            String query = "SELECT count(*) FROM Suraj.Reviews where ProductId=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,productid);
            
            rs = stmt.executeQuery();
            
            rs.next();
            str=rs.getNString(1);
            
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
	
	public String fetchAverageRating(String productid){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
            
            String query = "select ROUND(AVG(RATING)) from Suraj.Reviews where ProductId=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,productid);
            
            rs = stmt.executeQuery();
            
            rs.next();
            str=rs.getNString(1);
            
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
	
	public String fetchProductReviews(String productid){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
            
            String query = "SELECT UserName, ReviewText FROM Suraj.Reviews where ProductId=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,productid);
            
            rs = stmt.executeQuery();
            
            str="<XMLRoot>";
            while(rs.next()) {
            	str+="<Reviews>";
            	str+="<UserName>" + rs.getNString(1) + "</UserName>";
            	str+="<ReviewText>" + rs.getNString(2) + "</ReviewText>";
            	str+="</Reviews>";
            }
            str+="</XMLRoot>";
            
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
}
