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

public class ReviewDAO {
	
	public boolean insertReview(String username, String productid, String starrating, String reviewtext){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
            
            String query = "SELECT max(ReviewId) FROM Suraj.Reviews";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            
            rs.next();
            str=rs.getNString(1);
            int reviewid=Integer.parseInt(str.substring(1));
            reviewid++;
            str=String.valueOf(str.charAt(0));
            String review=String.valueOf(reviewid);
            for(int i=1;i<=7-review.length();i++)
            	str+="0";
            str+=review;
            
            query = "INSERT INTO Suraj.Reviews values(?,?,?,?,?,?)";
            stmt = con.prepareStatement(query);
            
            stmt.setString(1,str);
            stmt.setString(2,reviewtext);
            stmt.setString(3,username);
            stmt.setString(4,productid);
            stmt.setString(5,starrating);
            //'04-17-2016'
            String sysdate = new SimpleDateFormat("dd-MMM-yy").format(new Date());
            stmt.setString(6,sysdate);
            
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
