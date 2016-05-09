package SuperKartClient.SuperKartClient;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.regex.Pattern;

public class ProductsBean {

	private String productId;
	private String productName;
	private String productCategory;
	private double price;
	private String specifications;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	public ProductsBean stringToProductsBean(String str){
		String[] s=str.split(Pattern.quote("^"));
		System.out.println("In UsersBean stringToUsersBean "+str+" "+s.length);
		ProductsBean bean=new ProductsBean();
		bean.setProductId(s[0]);
		bean.setProductName(s[1]);
		bean.setProductCategory(s[2]);
		bean.setPrice(Double.parseDouble(s[3]));
		bean.setSpecifications(s[4]);
		
		return bean;
	}
	
	
}
