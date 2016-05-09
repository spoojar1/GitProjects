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

import oracle.net.aso.r;

public class OrderDAO {
	
	public String submitOrder(String username){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
                                  
            String query = "select max(orderid) from Suraj.orders";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            rs.next();
            str=rs.getNString(1);
            
            int orderid=Integer.parseInt(str.substring(1));
            orderid++;
            str=String.valueOf(str.charAt(0));
            String order=String.valueOf(orderid);
            for(int i=1;i<=7-order.length();i++)
            	str+="0";
            str+=order;
            
            System.out.println("checking new order id"+str);
            
            query = "insert into Suraj.Orders select ?, p.ProductId, Price, Quantity from Suraj.Products p join Suraj.Cart c on p.productid=c.productid where username=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,str);
            stmt.setString(2,username);
            rs = stmt.executeQuery();

            query = "select sum(Price*Quantity) from Suraj.Orders where orderid=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,str);
            rs = stmt.executeQuery();
            rs.next();
            
            String totalamount=rs.getNString(1);
            System.out.println("printing totlamount in orderDAO "+totalamount);
            
            query = "insert into Suraj.OrderSummary select ?, ?, ?, ShippingAddress, ?, ?, ?, ? from Suraj.Users where username=?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1,str);
            stmt.setString(2,username);
            stmt.setString(3,totalamount);
            stmt.setString(4,"CreditCard");
            String sysdate = new SimpleDateFormat("dd-MMM-yy").format(new Date());
            stmt.setString(5,sysdate);
            stmt.setString(6,sysdate);
            stmt.setString(7,"Completed");
            stmt.setString(8,username);
            
            rs = stmt.executeQuery();
            
            query = "Delete from Suraj.Cart where username=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,username);
            rs = stmt.executeQuery();
            
            query = "select EmailId from Suraj.Users where username=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,username);
            rs = stmt.executeQuery();
            rs.next();
            
            String email=rs.getNString(1);
            System.out.println("Email id is "+email);
            return email;
            
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
        return "false";
	}
	
	public String fetchPrevOrders(String username){
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String str="";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Oracle@123");
            
            String query = "select * from Suraj.OrderSummary where username=?";
            stmt = con.prepareStatement(query);
            stmt.setString(1,username);
            rs = stmt.executeQuery();

            str="<XMLRoot>";
            while(rs.next()) {
            	str+="<Orders>";
            	str+="<OrderId>" + rs.getNString(1) + "</OrderId>";
            	str+="<TotalBill>" + rs.getNString(3) + "</TotalBill>";
            	str+="<PaymentMethod>" + rs.getNString(5) + "</PaymentMethod>";
            	str+="<OrderDate>" + rs.getNString(6) + "</OrderDate>";
            	str+="<OrderStatus>" + rs.getNString(8) + "</OrderStatus>";
            	str+="</Orders>";
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
        return "false";
	}
}
